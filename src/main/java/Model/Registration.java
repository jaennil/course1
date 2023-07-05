package Model;

import Other.Database;
import Other.Hash;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registration {
    public void addUser(String firstname, String surname, String lastname, String username, String password, String role) {
        Database db = Database.getInstance();
        String statement = "insert into people (firstname, surname, lastname, address, phone_number, username, password_hash, role) value (?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(statement)) {
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, lastname);
            preparedStatement.setString(4, null);
            preparedStatement.setString(5, null);
            preparedStatement.setString(6, username);
            preparedStatement.setString(7, Hash.toString(Hash.hash(password)));
            preparedStatement.setString(8, role);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}