package com.steam.libraryviewer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.collections.*;

import java.io.File;

public class Main extends javafx.application.Application {

    @Override
    public void start(Stage stage) {
        TextField steamIdField = new TextField();
        Button fetchButton = new Button("Fetch Library");
        Button exportToTextButton = new Button("Export to Plain Text");


        TableView<Game> table = new TableView<>();
        TableColumn<Game, String> nameCol = new TableColumn<>("Game Name");
        nameCol.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<Game, Number> appIdCol = new TableColumn<>("App ID");
        appIdCol.setCellValueFactory(data -> data.getValue().appIdProperty());

        TableColumn<Game, Number> playtimeCol = new TableColumn<>("Playtime (min)");
        playtimeCol.setCellValueFactory(data -> data.getValue().playtimeProperty());

        table.getColumns().addAll(nameCol, appIdCol, playtimeCol);

        fetchButton.setOnAction(e -> {
            String steamId = steamIdField.getText();
            ObservableList<Game> games = LibraryFetcher.fetchGames(steamId);
            table.setItems(games);
        });

        exportToTextButton.setOnAction(e -> {
            String steamId = steamIdField.getText();
            ObservableList<Game> games = LibraryFetcher.fetchGames(steamId);

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Game List");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                boolean success = Export.exportToTextFile(games, file);
                if (success) {
                    System.out.println("Exporting " + games.size() + " success");
                } else {
                    System.out.println("Export failed");
                }
            }
        });

        VBox root = new VBox(10,
                new Label("Steam ID:"), steamIdField,
                fetchButton, exportToTextButton ,table);
        root.setPadding(new javafx.geometry.Insets(10));

        stage.setTitle("Steam Library Viewer");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
