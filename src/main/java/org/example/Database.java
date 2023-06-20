package org.example;

import java.util.ArrayList;
import java.sql.*;

public class Database {
    public ArrayList<Post> readPostsFromDB() {
        ArrayList<Post> posts = new ArrayList<>();
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
                String shortName = result.getString("short_name");
                posts.add(new Post(id, name, shortName));
            }
            connection.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        return posts;
    }
}
