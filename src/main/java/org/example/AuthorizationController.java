package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AuthorizationController {
    private final AuthorizationModel model = new AuthorizationModel();
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label resultLabel;

    public void onClickSignInButton(ActionEvent actionEvent) {
        resultLabel.setText("attempting...");
        String inputUsername = usernameField.getText();
        String inputPassword = passwordField.getText();
        boolean signedIn = model.signInUser(inputUsername, inputPassword);

        if (!signedIn) {
            resultLabel.setText("wrong login/password");
            return;
        }

        try {
            App.setRoot("mainWindow");
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
