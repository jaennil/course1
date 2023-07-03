package org.example;

import java.sql.*;

public class Database {
    private static Database instance;
    private Connection connection;
    private final String url = "jdbc:mysql://std-mysql:3306/std_2276_course1";
    private final String user = "std_2276_course1";
    private final String password = "naeNN6457";

    private Database() {
        System.out.println("connecting to database...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("successfully connected to database");
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println("Database Connection Creation Failed : " + exception.getMessage());
        }
    }

    public static Database getInstance() {
        try {
            if (instance == null)
                instance = new Database();
            else if (instance.connection.isClosed())
                instance = new Database();
            return instance;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
//    public ResultSet query(String query) {
//        try {
//            return statement.executeQuery(query);
//        } catch (Exception exception) {
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public int update(String query) {
//        try {
//            return statement.executeUpdate(query);
//        } catch (Exception exception) {
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public static boolean checkCredentials(String username, String passwordHash) {
//        try (ResultSet result = query("SELECT * FROM accounts WHERE username = '" + username + "' AND passwordHash = '" + passwordHash + "'")) {
//            if (result.next()) {
//                return true;
//            }
//            return false;
//        } catch (Exception exception) {
//            throw new RuntimeException(exception);
//        }
//    }
//
//    public static void addUser(String username, String passwordHash) {
//        update("INSERT INTO accounts (username, passwordHash) value ('" + username + "', '" + passwordHash + "')");
//    }