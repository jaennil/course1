package org.example;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList();
        posts.add(new Post("programmer"));
        posts.add(new Post("team leader"));
        return posts;
    }
}
