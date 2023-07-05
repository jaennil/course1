package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Other.Database;
import Other.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pet {
    ObservableList<Other.Pet> pets;

    public Pet() {
        pets = FXCollections.observableArrayList();
    }
    public ObservableList<Other.Pet> getPersonPets(Person person) {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String statement = "select * from pets where owner_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setInt(1, person.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Other.Pet pet = Other.Pet.fromResultSet(resultSet, person);
                pets.add(pet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pets;
    }

    public void addPet(Other.Pet pet) {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String statement = "insert into pets (name, breed_id, owner_id) value (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setString(1, pet.getName());
            preparedStatement.setInt(2, pet.getBreed().getId());
            preparedStatement.setInt(3, pet.getOwner().getId());
            preparedStatement.executeUpdate();
            pets.add(pet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}