package com.theagilemonkeys.ellmental.core;

public record UpdateResult<TState>(
        TState newState,
        Command command
) {
    public static <TState> UpdateResult<TState> noCommand(TState newState) {
        // FIXME: Change null to a NoOp command
        return new UpdateResult<>(newState, null);
    }
}