package com.theagilemonkeys.ellmental.core;

import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;


public class ActionManager<TState, TMessage> {
    private AtomicReference<TState> state;
    private final Supplier<UpdateResult<TState>> init;
    private final Function<String, TMessage> sub;
    private final BiFunction<TState, TMessage, UpdateResult<TState>> update;
    PublishSubject<Command> commandStream;
    BehaviorSubject<String> subscriptionStream;

    private static final Logger logger
            = LoggerFactory.getLogger(ActionManager.class);

    private ActionManager(Supplier<UpdateResult<TState>> init,
                          Function<String, TMessage> sub,
                          BiFunction<TState, TMessage, UpdateResult<TState>> update) {
        this.state = new AtomicReference<>();
        this.init = init;
        this.sub = sub;
        this.update = update;
        this.commandStream = PublishSubject.create();
        this.subscriptionStream = BehaviorSubject.create();
    }

    public static <TState, TMessage> Builder<TState, TMessage> withUpdate(
            BiFunction<TState, TMessage, UpdateResult<TState>> update) {
        return new Builder<>(update);
    }

    public void run() {
        var initResult = init.get();
        var initState = initResult.newState();
        logger.debug("Initial state: " + initState.toString());
        this.state.set(initState);

        // handling commands
        commandStream.subscribe((command) -> {
            ////////////////////////////
            // TODO: Implement command handler loading
            ////////////////////////////
            logger.debug("Command: " + command.toString());
            if (command.info().name().equals("test")) {
                subscriptionStream.onNext("result");
                return;
            }
            subscriptionStream.onNext("increment");
            ////////////////////////////
        });

        // handling subscriptions
        subscriptionStream.subscribe((result) -> {
            logger.debug("Subscription result: " + result);
            var message = this.sub.apply(result);
            var updateResult = this.update.apply(state.get(), message);
            this.state.set(updateResult.newState());
            logger.debug("New state: " + state.get());
            if (updateResult.command() != null) {
                commandStream.onNext(updateResult.command());
            }
        });

        commandStream.onNext(initResult.command());
    }

    public void executeCommand(Command command) {
        commandStream.onNext(command);
    }

    public String waitResult() {
        return subscriptionStream.blockingFirst();
    }

    public static class Builder<TState, TMessage> {
        private Supplier<UpdateResult<TState>> init;
        private Function<String, TMessage> sub;
        private final BiFunction<TState, TMessage, UpdateResult<TState>> update;

        public Builder(
                BiFunction<TState, TMessage, UpdateResult<TState>> update) {
            this.update = update;
        }

        public Builder<TState, TMessage> withSub(Function<String, TMessage> sub) {
            this.sub = sub;
            return this;
        }

        public Builder<TState, TMessage> withInit(Supplier<UpdateResult<TState>> init) {
            this.init = init;
            return this;
        }

        public ActionManager<TState, TMessage> build() {
            return new ActionManager<>(init, sub, update);
        }
    }
}
