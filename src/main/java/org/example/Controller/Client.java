package org.example.Controller;

import javafx.scene.input.MouseEvent;
import org.example.App;

import java.io.IOException;

public class Client {
    public void logOut(MouseEvent mouseEvent) {
        try {
            App.setRoot("authorization");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
