package org.example.Model;

import org.example.Database;
import org.example.Hash;
import org.example.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authorization {
    public Person signInUser(String username, String password) {
        Database db = Database.getInstance();
        String hash = Hash.toString(Hash.hash(password));
        String statement = "SELECT * FROM Person WHERE username = ? AND password_hash = ?";
        Connection connection = db.getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hash);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                return null;
            return Person.fromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
