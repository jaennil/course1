package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.App;

import java.io.IOException;
import java.util.ArrayList;

public class Registration {
    private final org.example.Model.Registration model = new org.example.Model.Registration();
    public TextField usernameField;
    public TextField passwordField;
    public TextField firstnameField;
    public TextField surnameField;
    public Label surnameEmptyLabel;
    public Label passwordEmptyLabel;
    public TextField lastnameField;
    public Label firstnameEmptyLabel;
    public Label lastnameEmptyLabel;
    public Label usernameEmptyLabel;

    @FXML
    protected void onClickSignUpButton(ActionEvent actionEvent) {
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
        model.addUser(firstname, surname, lastname, username, password, "client");
        try {
            App.setRoot("authorization");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onClickSignInButton(MouseEvent mouseEvent) {
        try {
            App.setRoot("authorization");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
