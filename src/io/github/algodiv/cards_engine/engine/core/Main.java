package io.github.algodiv.cards_engine.engine.core;

import io.github.algodiv.cards_engine.commons.tools.Game;

import java.io.IOException;

public class Main {
    //todo game devs should not use explicit class constructors, call order being: getName -> init
    public static void main(String[] args) throws IOException {
        Game[] games = GameLoader.loadAllGames(System.getProperty("user.dir") + "/games");
        for (Game game : games) {
            game.init();
            System.out.println("Name: " + game.getName());
        }
    }
}
