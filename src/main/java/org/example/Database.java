package org.example;

import java.util.ArrayList;
import java.sql.*;

public class Database {
    private static Database instance;
    private static Statement statement;

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
            connect();
        }
        return instance;
    }

    public ArrayList<Post> readPostsFromDB() {
        ArrayList<Post> posts = new ArrayList<>();
        try (ResultSet queryResult = query("SELECT * FROM posts")) {
            while (queryResult.next()) {
                int id = queryResult.getInt("id");
                String name = queryResult.getString("name");
                String shortName = queryResult.getString("short_name");
                posts.add(new Post(id, name, shortName));
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return posts;
    }

    private static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql:3306/std_2276_johnil",
                    "std_2276_johnil", "naeNN6457");
            statement = connection.createStatement();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private ResultSet query(String query) {
        try {
            return statement.executeQuery(query);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
