package com.theagilemonkeys.ellmental.core;

import com.theagilemonkeys.ellmental.core.actions.ActionResult;

import java.util.Optional;

/**
 * Interface for a module.
 * <p>
 * All implementations of this interface must define an inner class `State` and
 * an interface `Message`.
 *
 * @param <TState>
 * @param <TMessage>
 */
public interface Worker<TState, TMessage> {
    String getWorkerName();

    TState initialState();

    Optional<TMessage> parseMessage(ActionResult actionResult);

    UpdateResult<TState> update(TState state, TMessage message);
}
