package com.steam.libraryviewer;

import javafx.beans.property.*;

public class Game {
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty appId = new SimpleIntegerProperty();
    private final IntegerProperty playtime = new SimpleIntegerProperty();

    public Game(String name, int appId, int playtime) {
        this.name.set(name);
        this.appId.set(appId);
        this.playtime.set(playtime);
    }

    public StringProperty nameProperty() { return name; }
    public IntegerProperty appIdProperty() { return appId; }
    public IntegerProperty playtimeProperty() { return playtime; }
}
