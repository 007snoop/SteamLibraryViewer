package com.steam.libraryviewer;

import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LibraryFetcher {
    private static final String BACKEND_URL = "https://steam-backend-divn.onrender.com/games";

    public static ObservableList<Game> fetchGames(String username) {
        ObservableList<Game> games = FXCollections.observableArrayList();

        try {
            String apiUrl = BACKEND_URL + "?username=" + username;

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            JsonElement response = JsonParser.parseReader(new InputStreamReader(conn.getInputStream()));

            JsonArray gameList = response.getAsJsonArray();

            for (JsonElement el : gameList) {
                JsonObject g = el.getAsJsonObject();
                String name = g.get("name").getAsString();
                int appid = g.get("appid").getAsInt();
                int playtime = g.get("playtime").getAsInt();
                games.add(new Game(name, appid, playtime));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return games;
    }
}
