package io.github.algodiv.cards_engine.commons.tools;

public abstract class AGame {
    public IGameState gameState = new GameState();

    public abstract void init();

    public abstract void run();
}
