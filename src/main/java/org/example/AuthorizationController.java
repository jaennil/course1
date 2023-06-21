package org.example;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class AuthorizationController {
    private AuthorizationModel model = new AuthorizationModel();
    public TextField usernameField;
    public TextField passwordField;

    public void onClickSignInButton(ActionEvent actionEvent) {
        String inputUsername = usernameField.getText();
        String inputPassword = passwordField.getText();
        model.signInUser(inputUsername, inputPassword);
    }

    public void onClickSignUpButton(ActionEvent actionEvent) {
        String inputUsername = usernameField.getText();
        String inputPassword = passwordField.getText();
        model.signUpUser(inputUsername, inputPassword);
    }
}
