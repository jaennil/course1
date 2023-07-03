package org.example.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.App;
import org.example.Database;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Admin implements Initializable {
    private final org.example.Model.Registration model = new org.example.Model.Registration();
    public Label firstnameEmptyLabel;
    public Label surnameEmptyLabel;
    public TextField firstnameField;
    public TextField surnameField;
    public Label lastnameEmptyLabel;
    public TextField lastnameField;
    public Label usernameEmptyLabel;
    public TextField usernameField;
    public Label passwordEmptyLabel;
    public PasswordField passwordField;
    public Label welcomeLabel;
    private String labelText;

    public void addEmployee(ActionEvent actionEvent) {
        String firstname = firstnameField.getText();
        String surname = surnameField.getText();
        String lastname = lastnameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean firstnameIsBlank = firstname.isBlank();
        boolean surnameIsBlank = surname.isBlank();
        boolean lastnameIsBlank = lastname.isBlank();
        boolean usernameIsBlank = username.isBlank();
        boolean passwordIsBlank = password.isBlank();
        firstnameField.setStyle(firstnameIsBlank ? "-fx-border-color: red;" : "");
        surnameField.setStyle(surnameIsBlank ? "-fx-border-color: red;" : "");
        lastnameField.setStyle(lastnameIsBlank ? "-fx-border-color: red;" : "");
        usernameField.setStyle(usernameIsBlank ? "-fx-border-color: red;" : "");
        passwordField.setStyle(passwordIsBlank ? "-fx-border-color: red;" : "");
        firstnameEmptyLabel.setVisible(firstnameIsBlank);
        surnameEmptyLabel.setVisible(surnameIsBlank);
        lastnameEmptyLabel.setVisible(lastnameIsBlank);
        usernameEmptyLabel.setVisible(usernameIsBlank);
        passwordEmptyLabel.setVisible(passwordIsBlank);
        if (firstnameIsBlank || surnameIsBlank || lastnameIsBlank || usernameIsBlank || passwordIsBlank) {
            return;
        }
        model.addUser(firstname, surname, lastname, username, password, "employee");
    }

    public void passUsername(String username) {
        HashMap<String, String> fullName = Database.getFullNameByUsername(username);
        labelText = "Welcome, " + fullName.get("firstname") + " " + fullName.get("lastname");
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
            welcomeLabel.setText(labelText);
        });
    }
}