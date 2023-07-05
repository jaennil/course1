package Controller;

import Other.Person;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import org.example.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Employee implements Initializable {

    private final Model.Employee model = new Model.Employee();
    public Label welcomeLabel;
    public Label welcomeLabel1;
    public Label welcomeLabel2;
    private Person person;

    public void passPerson(Person person) {
        this.person = person;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            welcomeLabel.setText("Welcome, " + person.getWelcomeName());
            welcomeLabel1.setText("Welcome, " + person.getWelcomeName());
            welcomeLabel2.setText("Welcome, " + person.getWelcomeName());
        });
    }

    @FXML
    private void clickBtn(ActionEvent actionEvent) {
//        model.loadPostsFromDB();
    }

    public void logOut(MouseEvent mouseEvent) {
        try {
            App.setRoot("authorization");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPerson(ActionEvent actionEvent) {
    }

    public void onClickSubmit(ActionEvent actionEvent) {
    }
}
