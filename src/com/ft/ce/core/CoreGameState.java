package com.ft.ce.core;

import com.ft.ce.tools.GameState;

// This is the gameState class that will actually be used.
// If any part of a function should not exist in user space then it will be implemented here so that the user has no knowing of it.
public class CoreGameState extends GameState {
    @Override
    public void setStateValue(int value) {
        super.setStateValue(value);
        System.out.println("The user has no way of knowing about this line of code from their scope.");
    }
}
