package com.ft.ce.tools;

public class GameState implements IGameState {
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
