package org.example.Controller;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.App;
import java.util.ArrayList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Client {

    public void logOut(MouseEvent mouseEvent) {
        Stage stage = (Stage) App.scene.getWindow();
        ArrayList<String> fio = (ArrayList<String>) stage.getUserData();
        System.out.println(fio.get(0));
        System.out.println(fio.get(1));
        try {
            App.setRoot("authorization");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}