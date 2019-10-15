package com.ft.ce.core;

import com.ft.ce.tools.AGame;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.Objects;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;

public class GameLoader {
    /**
     * Used to return a class that implements Game from a jar file.
     * @param game File to a jar file that contains a class implementing Game.
     * @return Returns a Game that is found in the jar file.
     */
    private AGame loadGame(File game) {
        try {
            ClassLoader loader = URLClassLoader.newInstance(new URL[] {game.toURI().toURL()}, getClass().getClassLoader());
            JarFile jarFile = new JarFile(game);
            for (Iterator<JarEntry> it = jarFile.entries().asIterator(); it.hasNext(); ) {
                JarEntry entry = it.next();
                if (entry.toString().contains(".class")) {
                    if(!entry.toString().endsWith("/Game.class")) {
                        String gameToLoad = entry.toString().replace('/', '.').substring(0, entry.toString().length() - 6);
                        Class<?> importedGame = Class.forName(gameToLoad, true, loader);
                        if(AGame.class.isAssignableFrom(importedGame)) {
                            Class<? extends AGame> extendedGame = importedGame.asSubclass(AGame.class);

                            Constructor<? extends AGame> gameConstructor = extendedGame.getConstructor();
                            return gameConstructor.newInstance();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error [loadGame/com.ft.ce.core.GameLoader]");
        }
        return null;
    }

    /**
     * Used to load all Game implementing classes from a given folder.
     * @param gamesDir Path to a folder containing jar files with classes that implement Game.
     * @return Returns an array of classes that implement Game.
     */
    static public AGame[] loadAllGames(String gamesDir) {
        GameLoader gl = new GameLoader();
        File gamesFolder = new File(gamesDir);
        if(gamesFolder.exists()) {
            AGame[] games = new AGame[Objects.requireNonNull(gamesFolder.listFiles()).length];
            int i = 0;
            for (File jar : Objects.requireNonNull(gamesFolder.listFiles())) {
                AGame game = gl.loadGame(jar);
                if(game != null) {
                    game.gameState = new CoreGameState();
                    games[i] = game;
                    i++;
                } else {
                    System.out.println("No class implementing Game found in " + jar);
                }
            }
            return games;
        } else {
            System.out.println(gamesDir + " does not exist!");
            return null;
        }
    }
}
