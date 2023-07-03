package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainModel {
    private final ObservableList<Post> posts;

    public MainModel() {
        this.posts = FXCollections.observableArrayList();
    }

    public ObservableList<Post> getPosts() {
        return posts;
    }

    public void loadPostsFromDB() {
        Database database = Database.getInstance();
        this.posts.clear();
//        this.posts.addAll(database.readPosts());
    }
}
