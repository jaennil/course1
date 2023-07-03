package org.example.Model;

import org.example.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Client {
    public HashMap<String, String> getFullNameByUsername(String username) {
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
