package org.example.Model;

import org.example.Database;
import org.example.Hash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authorization {
    public String signInUser(String username, String password) {
        Database db = Database.getInstance();
        String hash = Hash.toString(Hash.hash(password));
        String statement = "SELECT * FROM Person WHERE username = ? AND password_hash = ?";
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hash);
            ResultSet result = preparedStatement.executeQuery();
            if (!result.next())
                return "wrong credentials";
            return result.getString("role");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
