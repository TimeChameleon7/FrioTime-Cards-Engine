package com.ft.ce.tools;

import com.ft.ce.core.GameState;
// Still need to remove this include in final version if possible

public interface IGame {
    IGameState gameState = new GameState();

    void init();
    void run();
}
