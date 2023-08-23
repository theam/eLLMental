package com.theagilemonkeys.ellmental.core;

import com.google.gson.JsonElement;
import com.theagilemonkeys.ellmental.core.actionhandlers.ActionHandler;
import com.theagilemonkeys.ellmental.core.actionhandlers.HandlerManager;
import com.theagilemonkeys.ellmental.core.actionhandlers.NoOpHandler;
import com.theagilemonkeys.ellmental.core.actions.Action;
import com.theagilemonkeys.ellmental.core.actions.ActionResult;
import com.theagilemonkeys.ellmental.core.actions.NoOp;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Map.entry;


// TODO: Make a singleton
public class ActionManager<TState, TMessage, TModule extends Module<TState, TMessage>> {

    private static final Map<String, ActionHandler> defaultHandlers = Map.ofEntries(
            entry("NoOp", new NoOpHandler())
    );


    private final AtomicReference<TState> state;
    private final TModule module;
    PublishSubject<Action> actionRequests;
    BehaviorSubject<ActionResult> actionResults;
    final HandlerManager handlerManager;

    private static final Logger logger
            = LoggerFactory.getLogger(ActionManager.class);

    public ActionManager(TModule module) {
        this.state = new AtomicReference<>();
        this.module = module;
        this.actionRequests = PublishSubject.create();
        this.actionResults = BehaviorSubject.create();
        this.handlerManager = HandlerManager.load(defaultHandlers);
    }

    public void run() {
        var initState = module.initialState();
        logger.debug("Initial state: " + initState.toString());
        this.state.set(initState);

        // handling commands
        actionRequests.subscribe((action) -> {
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
        });

        // handling action results
        actionResults.subscribe((result) -> {
            logger.debug("Action result: " + result);
            var message = this.module.parseMessage(result);
            var updateResult = this.module.update(state.get(), message);
            this.state.set(updateResult.newState());
            logger.debug("New state: " + state.get());
            if (updateResult.action() != null) {
                actionRequests.onNext(updateResult.action());
            }
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
