package org.example;

import java.io.IOException;

public class AuthorizationModel {
    public boolean signInUser(String username, String password) {
        String hash = Hash.toString(Hash.hash(password));
        if (Database.checkCredentials(username, hash)) {
            try {
                App.setRoot("mainWindow");
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return false;
        }
    }
    public void signUpUser(String username, String password) {
        String hash = Hash.toString(Hash.hash(password));
        if (Database.checkCredentials(username, hash)) {
            System.out.println("you already registered. please sign in with your credentials");
        } else {
            Database.addUser(username, hash);
        }
        System.out.println("please sign in now");
    }
}
