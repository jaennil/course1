package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AuthorizationController {
    private final AuthorizationModel model = new AuthorizationModel();
    public TextField usernameField;
    public TextField passwordField;
    @FXML
    private Label resultLabel;

    public void onClickSignInButton(ActionEvent actionEvent) {
        resultLabel.setText("attempting...");
        String inputUsername = usernameField.getText();
        String inputPassword = passwordField.getText();
        boolean signedIn = model.signInUser(inputUsername, inputPassword);
        if (!signedIn)
            resultLabel.setText("wrong login/password");
    }

    public void onClickSignUpButton(ActionEvent actionEvent) {
        String inputUsername = usernameField.getText();
        String inputPassword = passwordField.getText();
        model.signUpUser(inputUsername, inputPassword);
    }
}
