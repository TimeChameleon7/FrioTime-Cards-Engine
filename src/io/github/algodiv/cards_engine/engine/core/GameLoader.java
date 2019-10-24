package io.github.algodiv.cards_engine.engine.core;

import io.github.algodiv.cards_engine.commons.tools.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class GameLoader {
    /**
     * Used to load all Game implementing classes from a given folder.
     *
     * @param gamesDir Path to a folder containing jar files with classes that implement Game.
     * @return Returns an array of classes that implement Game.
     */
    static public Game[] loadAllGames(String gamesDir) throws IOException {
        ArrayList<Game> games = new ArrayList<>();
        File gamesFolder = new File(gamesDir);
        if (gamesFolder.exists() && gamesFolder.isDirectory()) {
            File[] files = gamesFolder.listFiles();
            if (files != null) {
                for (File jar : files) {
                    Game game = loadGame(jar);
                    if (game != null) {
                        games.add(game);
                    } else {
                        System.out.println("No class implementing Game found in " + jar);
                    }
                }
                return games.toArray(Game[]::new);
            } else {
                throw new IOException("Unable to read directory " + gamesDir);
            }
        } else if (!gamesFolder.exists()){
            throw new FileNotFoundException(gamesDir);
        } else {
            throw new NotDirectoryException(gamesDir + " is not a directory");
        }
    }

    /**
     * Used to return a class that implements Game from a jar file.
     *
     * @param jar jar file that contains a class implementing Game.
     * @return a Game that is found in the jar file.
     */
    private static Game loadGame(File jar) {
        try {
            ClassLoader loader = URLClassLoader.newInstance(new URL[]{jar.toURI().toURL()}, GameLoader.class.getClassLoader());
            JarFile jarFile = new JarFile(jar);

            Iterator<JarEntry> entryIterator = jarFile.entries().asIterator();
            while (entryIterator.hasNext()) {
                String entryString = entryIterator.next().toString();

                if (entryString.contains(".class")) {
                    String classToLoad = entryString.substring(0, entryString.length() - 6);
                    Class<?> importedClass = Class.forName(classToLoad, true, loader);
                    if (Game.class.isAssignableFrom(importedClass)) {
                        return importedClass.asSubclass(Game.class).getConstructor().newInstance();
                    }
                }
            }
        } catch (Exception e) {
            System.out.printf("Problem with loading <Game.class extends class> from %s, Stack Trace: %n", jar);
            e.printStackTrace();
        }
        return null;
    }
}
