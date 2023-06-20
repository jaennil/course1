package org.example;

import java.io.IOException;

public class AuthorizationModel {
    public void signInUser(String username, String password) {
        String hash = Hash.toString(Hash.hash(password));
        if (Database.checkCredentials(username, hash)) {
            try {
                App.setRoot("mainWindow");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("you dont exist in the database. please sign up first");
        }
    }
    public void signUpUser(String username, String password) {
        String hash = Hash.toString(Hash.hash(password));
        Database.addUser(username, hash);
        System.out.println("please sign in now");
    }
}
