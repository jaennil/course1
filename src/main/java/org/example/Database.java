package org.example;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Database {
    public List<Post> constructPosts() {
        ArrayList<String> postsNames = readPostsNamesFromDB();
        List<Post> posts = new ArrayList<>();
        for (String postName: postsNames) {
            posts.add(new Post(postName));
        }
        return posts;
    }
    public ArrayList<String> readPostsNamesFromDB() {
        ArrayList<String> names = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql:3306/std_2276_johnil",
                    "std_2276_johnil", "naeNN6457");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM posts";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String short_name = result.getString("short_name");
                names.add(name);
            }
            connection.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        return names;
    }
}
