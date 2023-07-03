package org.example.Controller;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.example.App;

import java.net.URL;

import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Client implements Initializable {

    public Label welcomeLabel;
    private String labelText;
    private static final org.example.Model.Client model = new org.example.Model.Client();

    public void passUsername(String username) {
        HashMap<String, String> fullName = model.getFullNameByUsername(username);
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