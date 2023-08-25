package com.theagilemonkeys.ellmental.core;

import com.google.gson.JsonElement;
import com.theagilemonkeys.ellmental.core.actionhandlers.ActionHandler;
import com.theagilemonkeys.ellmental.core.actionhandlers.HandlerManager;
import com.theagilemonkeys.ellmental.core.actionhandlers.NoOpHandler;
import com.theagilemonkeys.ellmental.core.actions.Action;
import com.theagilemonkeys.ellmental.core.actions.ActionResult;
import com.theagilemonkeys.ellmental.core.actions.NoOp;
import com.theagilemonkeys.ellmental.core.configuration.Configuration;
import com.theagilemonkeys.ellmental.core.configuration.ConfigurationLoader;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.Map.entry;


// TODO: Make a singleton
public class WorkerManager {

    private void handleActionResult(ActionResult result) {
        workers.forEach((name, workerEntry) -> {
            Object message = parseWorkerMessage(result, name, workerEntry);
            if (message == null) return;
            var updateResult = runUpdate(name, workerEntry, message);
            var newState = updateResult.newState();
            logger.debug("New state: " + newState);
            saveNewState(name, newState);
            executeNextAction(updateResult);
        });
    }

    record WorkerEntry(
            Class<Worker<Object, Object>> workerClass,
            Worker<Object, Object> worker
    ) {
    }

    private static final Map<String, ActionHandler> defaultHandlers = Map.ofEntries(
            entry("NoOp", new NoOpHandler())
    );


    private final StateManager stateManager = StateManager.getInstance();
    private Map<String, WorkerEntry> workers = new HashMap<>();
    PublishSubject<Action> actionRequests;
    BehaviorSubject<ActionResult> actionResults;
    final HandlerManager handlerManager;

    private static final Logger logger
            = LoggerFactory.getLogger(WorkerManager.class);

    public WorkerManager() {
        var conf = ConfigurationLoader.loadConfiguration();
        if (conf.isEmpty()) {
            var path = ConfigurationLoader.getDefaultConfigurationPath();
            logger.error("Could not load configuration from " + path);
            throw new RuntimeException("Could not load configuration from " + path);
        }
        Configuration configuration = conf.get();
        configuration.features().forEach(feature -> {
            registerWorker(feature.getClassName());
        });
        this.actionRequests = PublishSubject.create();
        this.actionResults = BehaviorSubject.create();
        this.handlerManager = HandlerManager.load(defaultHandlers);
    }

    public <TState extends Object, TMsg extends Object, T extends Worker<TState, TMsg>> void registerWorkerClass(
            T worker) {
        // WARNING!
        //
        // This function is not type-safe at all, but it's what's required to have a good UX.
        // If something starts to fail, probably here's the place to look for first.
        var castWorker = (Worker<Object, Object>) worker;
        var entry = new WorkerEntry((Class<Worker<Object, Object>>) worker.getClass(), castWorker);
        workers.put(worker.getWorkerName(), entry);
    }

    public void registerWorker(String fullyQualifiedWorkerClassName) {
        var worker = loadWorkerFromClassString(fullyQualifiedWorkerClassName);
        if (worker.isEmpty()) {
            logger.error("Could not load worker class: " + fullyQualifiedWorkerClassName);
            return;
        }
        registerWorkerClass(worker.get());
    }

    private Optional<Worker<Object, Object>> loadWorkerFromClassString(String fullyQualifiedClassName) {
        try {
            // Load the class
            Class<?> myClass = Class.forName(fullyQualifiedClassName);

            // Create a new instance
            Object myInstance = myClass.getDeclaredConstructor().newInstance();

            // If you know the type, you can cast it
            if (myInstance instanceof Worker<?, ?>) {
                var worker = (Worker<Object, Object>) myInstance;
                return Optional.of(worker);
            }

        } catch (ClassNotFoundException |
                 InstantiationException |
                 IllegalAccessException |
                 NoSuchMethodException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void run() {
        initializeWorkerStates();
        actionRequests.subscribe(this::handleActionRequest);
        actionResults.subscribe(this::handleActionResult);
    }

    private void handleActionRequest(Action action) {
        ////////////////////////////
        // TODO: Implement dynamic action handler loading
        ////////////////////////////
        logger.debug("Trying to handle the action: " + action.toString());
        var handler = handlerManager.getHandler(action.info().name());
        if (handler.isEmpty()) {
            logger.warn("No handler found for action: " + action.info().name() + ". Ignoring.");
            return;
        }
        var handlerResult = handler.get().handle(action.value());
        if (action.nextMessageName().isEmpty()) {
            return;
        }
        var nextMessageName = action.nextMessageName().get();
        var actionResult = new ActionResult(nextMessageName, handlerResult);
        actionResults.onNext(actionResult);
    }

    private void executeNextAction(UpdateResult<Object> updateResult) {
        if (updateResult.action() == null) {
            logger.error("The worker returned `null` in the place of the next action.\nTHIS IS NOT ALLOWED.\nIGNORING.");
            return;
        }
        actionRequests.onNext(updateResult.action());
    }

    private UpdateResult<Object> runUpdate(String name, WorkerEntry workerEntry, Object message) {
        var currentState = stateManager.get(name);
        return workerEntry.worker.update(currentState, message);
    }

    private void saveNewState(String name, Object newState) {
        this.stateManager.put(name, newState);
        var savedState = this.stateManager.get(name);
        logger.debug("Saved state: " + savedState);
    }

    private static Object parseWorkerMessage(ActionResult result, String name, WorkerEntry workerEntry) {
        var maybeMsg = workerEntry.worker.parseMessage(result);
        if (maybeMsg.isEmpty()) {
            logger.debug("Worker " + name + " didn't parse the message " + result.messageName());
            return null;
        }
        var message = maybeMsg.get();
        logger.debug("Worker " + name + " parsed the message " + result.messageName() + " as " + message.toString());
        return message;
    }

    private void initializeWorkerStates() {
        workers.forEach((name, worker) -> {
            logger.debug("Initializing worker: " + name);
            var state = worker.worker().initialState();
            logger.debug("Initial state: " + state.toString());
            stateManager.put(name, state);
        });
    }

    /**
     * Run an action asynchronously.
     *
     * @param action
     */
    public void runAction(Action action) {
        actionRequests.onNext(action);
    }

    /**
     * Wait for an action result based on the name.
     *
     * @param messageName
     * @return
     */
    public JsonElement waitActionResult(String messageName) {
        return actionResults
                .filter(r -> r.messageName().contentEquals(messageName))
                .blockingFirst()
                .value();
    }

    /**
     * Run an action synchronously, getting the result back.
     *
     * @param action
     * @return
     */
    public JsonElement runActionSync(Action action) {
        var uuid = UUID.randomUUID().toString();
        actionRequests.onNext(action.withMessage(uuid));
        return waitActionResult(uuid);
    }

    /**
     * Sends a message to the module.
     *
     * @param messageName
     * @param value
     */
    public void sendMessage(String messageName, JsonElement value) {
        var action = NoOp.action()
                .withMessage(messageName)
                .withValue(value);
        runAction(action);
    }

}
