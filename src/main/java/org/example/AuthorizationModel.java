package org.example;

public class AuthorizationModel {
    public boolean signInUser(String username, String password) {
        String hash = Hash.toString(Hash.hash(password));
        return true;
//        return Database.checkCredentials(username, hash);
    }
}
