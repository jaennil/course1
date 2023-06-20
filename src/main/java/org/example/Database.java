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
        getInstance();
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

    public static ResultSet query(String query) {
        getInstance();
        try {
            return statement.executeQuery(query);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static int update(String query) {
        getInstance();
        try {
            return statement.executeUpdate(query);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static boolean checkCredentials(String username, String password) {
        getInstance();
        String hash = Hash.toString(Hash.hash(password));
        try (ResultSet result = query("SELECT * FROM accounts WHERE username = '" + username + "' AND passwordHash = '" + hash + "'")) {
            if (result.next()) {
                return true;
            }
            return false;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void addUser(String username, String password) {
        getInstance();
        String hash = Hash.toString(Hash.hash(password));
        update("INSERT INTO accounts (username, passwordHash) value ('" + username + "', '" + hash + "')");
    }
}
