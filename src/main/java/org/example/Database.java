package org.example;

import java.sql.*;
import java.util.HashMap;

public class Database {
    private static Database instance;
    private static Connection connection;
    private static final String url = "jdbc:mysql://std-mysql:3306/std_2276_course1";
    private static final String user = "std_2276_course1";
    private static final String password = "naeNN6457";

    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            System.out.println("Database Connection Creation Failed : " + exception.getMessage());
        }
    }

    public static boolean connect() {
        System.out.println("connecting to database...");
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("can't connect to database");
            return false;
        }
        System.out.println("successfully connected to database");
        return true;
    }

    public static Database getInstance() {
        try {
            if (instance == null)
                instance = new Database();
            else if (connection.isClosed())
                instance = new Database();
            return instance;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static HashMap<String, String> getFullNameByUsername(String username) {
        HashMap<String, String> fullName = new HashMap<>();
        Database db = Database.getInstance();
        String statement = "SELECT firstname, surname, lastname FROM Person WHERE username = ?";
        Connection connection = db.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("result is empty");
                return null;
            }
            fullName.put("firstname", resultSet.getString("firstname"));
            fullName.put("surname", resultSet.getString("surname"));
            fullName.put("lastname", resultSet.getString("lastname"));
            return fullName;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}