package Other;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pet {
    private int id;
    private String name;
    private Breed breed;
    private Person owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Pet(int id, String name, Breed breed, Person owner) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.owner = owner;
    }

    public Pet(String name, Breed breed, Person owner) {
        this.name = name;
        this.breed = breed;
        this.owner = owner;
    }

    public static Pet fromResultSet(ResultSet resultSet, Person owner) {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String statement = "select * from breeds where id = ?";
        Breed breed;
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setInt(1, resultSet.getInt("breed_id"));
            ResultSet resultSetBreed = preparedStatement.executeQuery();
            if (!resultSetBreed.next())
                return null;
            breed = Breed.fromResultSet(resultSetBreed);
            return new Pet(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    breed,
                    owner
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
