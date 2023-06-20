package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainModel {
    private final ObservableList<Post> names;

    public MainModel() {
        this.names = FXCollections.observableArrayList();
    }

    public ObservableList<Post> getNames() {
        return names;
    }

    public void loadFromDB() {
        Database db = new Database();
        this.names.clear();
        this.names.addAll(db.getAllPosts());
    }
}
