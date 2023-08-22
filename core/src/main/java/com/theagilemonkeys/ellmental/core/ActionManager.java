package com.theagilemonkeys.ellmental.core;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;


public class ActionManager<TState, TMessage> {
    private TState state;
    private final Supplier<TState> init;
    private final Function<String, TMessage> sub;
    private final BiFunction<TState, TMessage, UpdateResult<TState>> update;

    private ActionManager(Supplier<TState> init,
                          Function<String, TMessage> sub,
                          BiFunction<TState, TMessage, UpdateResult<TState>> update) {
        this.init = init;
        this.sub = sub;
        this.update = update;
    }

    public static <TState, TMessage> Builder<TState, TMessage> withUpdate(
            BiFunction<TState, TMessage, UpdateResult<TState>> update) {
        return new Builder<>(update);
    }

    public void run() {
        state = init.get();
        // You would add your stream/queue here and set up subscriptions,
        // process messages, etc. For simplicity, I'm skipping that here.
    }

    public static class Builder<TState, TMessage> {
        private Supplier<TState> init;
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

        public Builder<TState, TMessage> withInit(Supplier<TState> init) {
            this.init = init;
            return this;
        }

        public ActionManager<TState, TMessage> build() {
            return new ActionManager<>(init, sub, update);
        }
    }
}
