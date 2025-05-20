package com.steam.libraryviewer;

import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Export {
    public static boolean exportToTextFile(ObservableList<Game> games, File outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (Game game : games) {
                writer.write(game.nameProperty().get());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
