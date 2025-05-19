package com.steam.libraryviewer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        TextField apiKeyField = new TextField();
        TextField steamIdField = new TextField();
        Button fetchButton = new Button("Fetch Library");

        TableView<Game> table = new TableView<>();
        TableColumn<Game, String> nameCol = new TableColumn<>("Game Name");
        nameCol.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<Game, Number> appIdCol = new TableColumn<>("App ID");
        appIdCol.setCellValueFactory(data -> data.getValue().appIdProperty());

        TableColumn<Game, Number> playtimeCol = new TableColumn<>("Playtime (min)");
        playtimeCol.setCellValueFactory(data -> data.getValue().playtimeProperty());

        table.getColumns().addAll(nameCol, appIdCol, playtimeCol);

        fetchButton.setOnAction(e -> {
            String apiKey = apiKeyField.getText();
            String steamId = steamIdField.getText();
            ObservableList<Game> games = LibraryFetcher.fetchGames(apiKey, steamId);
            table.setItems(games);
        });

        VBox root = new VBox(10,
                new Label("Steam Web API Key:"), apiKeyField,
                new Label("SteamID64:"), steamIdField,
                fetchButton, table);
        root.setPadding(new javafx.geometry.Insets(10));

        stage.setTitle("Steam Library Viewer");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
