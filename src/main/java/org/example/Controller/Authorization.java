package org.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    public Button signInButton;
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
            FXMLLoader loader = new FXMLLoader(App.class.getResource(result+".fxml"));
            Parent root = loader.load();
            if (result.equals("admin")) {
                Admin controller = loader.getController();
                controller.passUsername(username);
            } else if (result.equals("client")) {
                Client controller = loader.getController();
                controller.passUsername(username);
            }
            Scene scene = signInButton.getScene();
            scene.setRoot(root);
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
