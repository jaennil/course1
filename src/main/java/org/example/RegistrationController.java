package org.example;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class RegistrationController {
    public TextField usernameField;
    public TextField passwordField;
    public Label resultLabel;
    public TextField firstnameField;
    public TextField surnameField;

    public void onClickSignUpButton(ActionEvent actionEvent) {
        String firstname = firstnameField.getText();
        String surname = surnameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
    }

    public void onClickSignInButton(MouseEvent mouseEvent) {
        try {
            App.setRoot("authorization");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
