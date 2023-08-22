package com.theagilemonkeys.ellmental.core;

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
    private static final Logger logger
            = LoggerFactory.getLogger(ActionManager.class);

    private ActionManager(Supplier<UpdateResult<TState>> init,
                          Function<String, TMessage> sub,
                          BiFunction<TState, TMessage, UpdateResult<TState>> update) {
        this.state = new AtomicReference<>();
        this.init = init;
        this.sub = sub;
        this.update = update;
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

        PublishSubject<Command> commandStream = PublishSubject.create();
        PublishSubject<String> subscriptionStream = PublishSubject.create();

        // handling commands
        commandStream.subscribe((command) -> {
            logger.debug("Command: " + command.toString());
            Thread.sleep(1000);
            subscriptionStream.onNext("increment");
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
