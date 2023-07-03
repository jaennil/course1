package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class AuthorizationController {
    private final AuthorizationModel model = new AuthorizationModel();
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
        boolean signedIn = model.signInUser(username, password);
        if (!signedIn) {
            emptyPasswordLabel.setText("wrong credentials");
            emptyPasswordLabel.setVisible(true);
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
