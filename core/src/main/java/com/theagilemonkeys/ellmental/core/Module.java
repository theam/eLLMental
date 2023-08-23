package com.theagilemonkeys.ellmental.core;

import com.theagilemonkeys.ellmental.core.actions.ActionResult;

/**
 * Interface for a module.
 * <p>
 * All implementations of this interface must define an inner class `State` and
 * an interface `Message`.
 *
 * @param <TState>
 * @param <TMessage>
 */
public interface Module<TState, TMessage> {
    public TState initialState();

    public TMessage parseMessage(ActionResult actionResult);

    public UpdateResult<TState> update(TState state, TMessage message);
}
