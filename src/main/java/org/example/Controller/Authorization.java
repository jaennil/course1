package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.App;

import java.io.IOException;
import java.util.Objects;

public class Authorization {
    private final org.example.Model.Authorization model = new org.example.Model.Authorization();
    public Label emptyUsernameLabel;
    public Label emptyPasswordLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    public void onClickSignInButton(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean usernameIsBlank = username.isBlank();
        boolean passwordIsBlank = password.isBlank();
        usernameField.setStyle(usernameIsBlank ? "-fx-border-color: red;" : "");
        passwordField.setStyle(passwordIsBlank ? "-fx-border-color: red;" : "");
        emptyUsernameLabel.setVisible(usernameIsBlank);
        emptyPasswordLabel.setVisible(passwordIsBlank);
        if (usernameIsBlank || passwordIsBlank)
            return;

        emptyPasswordLabel.setVisible(true);
        emptyPasswordLabel.setText("attempting...");
        String result = model.signInUser(username, password);
        if (Objects.equals(result, "wrong credentials")) {
            emptyPasswordLabel.setText("wrong credentials");
            emptyPasswordLabel.setVisible(true);
            return;
        }
        try {
            App.setRoot(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onClickSignUpButton(MouseEvent mouseEvent) {
        try {
            App.setRoot("registration");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
