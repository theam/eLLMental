package com.theagilemonkeys.ellmental.core;

import com.theagilemonkeys.ellmental.core.actions.Action;
import com.theagilemonkeys.ellmental.core.actions.NoOp;

public record UpdateResult<TState>(
        TState newState,
        Action action
) {
    public static <TState> UpdateResult<TState> of(TState newState) {
        return new UpdateResult<>(newState, NoOp.action());
    }
}