package com.steam.libraryviewer;

import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LibraryFetcher {
    public static ObservableList<Game> fetchGames(String apiKey, String steamId) {
        ObservableList<Game> games = FXCollections.observableArrayList();
        try {
            String apiUrl = String.format(
                    "https://api.steampowered.com/IPlayerService/GetOwnedGames/v1/?key=%s&steamid=%s&include_appinfo=true",
                    apiKey, steamId);

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            JsonObject response = JsonParser.parseReader(new InputStreamReader(conn.getInputStream()))
                    .getAsJsonObject()
                    .getAsJsonObject("response");

            JsonArray gameList = response.getAsJsonArray("games");

            for (JsonElement el : gameList) {
                JsonObject g = el.getAsJsonObject();
                String name = g.get("name").getAsString();
                int appid = g.get("appid").getAsInt();
                int playtime = g.get("playtime_forever").getAsInt();
                games.add(new Game(name, appid, playtime));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return games;
    }
}
