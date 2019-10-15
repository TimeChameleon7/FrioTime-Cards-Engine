package com.ft.ce.core;

import com.ft.ce.tools.IGameState;

public class CoreGameState implements IGameState {
    int value = 0;

    @Override
    public void setStateValue(int value) {
        this.value = value;
    }

    @Override
    public int getStateValue() {
        return value;
    }
}
