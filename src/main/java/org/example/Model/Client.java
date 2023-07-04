package org.example.Model;

import org.example.Animal;
import org.example.Breed;
import org.example.Database;
import org.example.Person;

import java.sql.Connection;
import java.time.LocalDate;

public class Client {
    public void addAppointment(Animal animal, Breed breed, Person owner, Person doctor, LocalDate date) {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String statement = "insert into Appointment (animal_id, breed_id, animal_owner_id, doctor_id, date) value (?,?,?,?,?)";
    }
}

