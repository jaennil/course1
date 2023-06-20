package org.example;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizationController {
    public TextField usernameField;
    public TextField passwordField;

    public void onClickSignInButton(ActionEvent actionEvent) {
        String inputUsername = usernameField.getText();
        String inputPassword = passwordField.getText();
        if (Database.checkCredentials(inputUsername, inputPassword)) {
            try {
                App.setRoot("mainWindow");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("you dont exist in the database. please sign up first");
        }
    }

    public void onClickSignUpButton(ActionEvent actionEvent) {
        String inputUsername = usernameField.getText();
        String inputPassword = passwordField.getText();
        Database.addUser(inputUsername, inputPassword);
        System.out.println("please sign in now");
    }

}
