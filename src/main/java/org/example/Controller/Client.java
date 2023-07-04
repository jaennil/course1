package org.example.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Client implements Initializable {

    private static final org.example.Model.Client model = new org.example.Model.Client();
    public Label welcomeLabel;
    public ComboBox<Person> doctorComboBox;
    public TextField animalNameTextField;
    public DatePicker datePicker;
    public ComboBox<Breed> breedComboBox;
    private Person person;

    public void passPerson(Person person) {
        this.person = person;
    }

    public void logOut(MouseEvent mouseEvent) {
        try {
            App.setRoot("authorization");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            welcomeLabel.setText("Welcome, " + person.getWelcomeName());
            initBreedComboBox();
            initDoctorComboBox();
        });
    }

    private void initDoctorComboBox() {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String statement = "select * from Person where role = 'doctor'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Person doctor = Person.fromResultSet(resultSet);
                doctorComboBox.getItems().add(doctor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initBreedComboBox() {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String statement = "select * from Breed";
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Breed breed = Breed.fromResultSet(resultSet);
                breedComboBox.getItems().add(breed);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onClickSubmit(ActionEvent actionEvent) {
        LocalDate date = datePicker.getValue();
        Breed breed = breedComboBox.getValue();
        Person doctor = doctorComboBox.getValue();

//        model.addAppointment(animal, breed, person, doctor, date);
    }
}