package Controller;

import Other.Person;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.example.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Admin implements Initializable {
    private final Model.Registration model = new Model.Registration();
    public Label firstnameEmptyLabel;
    public Label surnameEmptyLabel;
    public TextField firstnameField;
    public TextField surnameField;
    public Label lastnameEmptyLabel;
    public TextField lastnameField;
    public Label usernameEmptyLabel;
    public TextField usernameField;
    public Label passwordEmptyLabel;
    public PasswordField passwordField;
    public Label welcomeLabel;
    public ComboBox<String> roleComboBox;
    private Person person;

    public void passPerson(Person person) {
        this.person = person;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> roles = FXCollections.observableArrayList("Employee", "Doctor");
        roleComboBox.setItems(roles);
        Platform.runLater(() -> welcomeLabel.setText("Welcome, " + person.getWelcomeName()));
    }

    public void addEmployee(ActionEvent actionEvent) {
        String firstname = firstnameField.getText();
        String surname = surnameField.getText();
        String lastname = lastnameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();
        spawnWarningLabels();
        highlightEmptyFields();
        if (firstname.isBlank() || surname.isBlank() || lastname.isBlank() || username.isBlank() || password.isBlank() || role == null)
            return;
        model.addUser(firstname, surname, lastname, username, password, role);
        showInformationDialog(firstname, surname, lastname);
        clearFields();
    }

    private void highlightEmptyFields() {
        firstnameField.setStyle(firstnameField.getText().isBlank() ? "-fx-border-color: red;" : "");
        surnameField.setStyle(surnameField.getText().isBlank() ? "-fx-border-color: red;" : "");
        lastnameField.setStyle(lastnameField.getText().isBlank() ? "-fx-border-color: red;" : "");
        usernameField.setStyle(usernameField.getText().isBlank() ? "-fx-border-color: red;" : "");
        passwordField.setStyle(passwordField.getText().isBlank() ? "-fx-border-color: red;" : "");
        roleComboBox.setStyle(roleComboBox.getValue() == null ? "-fx-border-color: red;" : "");
    }

    private void spawnWarningLabels() {
        firstnameEmptyLabel.setVisible(firstnameField.getText().isBlank());
        surnameEmptyLabel.setVisible(surnameField.getText().isBlank());
        lastnameEmptyLabel.setVisible(lastnameField.getText().isBlank());
        usernameEmptyLabel.setVisible(usernameField.getText().isBlank());
        passwordEmptyLabel.setVisible(passwordField.getText().isBlank());
    }

    private void showInformationDialog(String firstname, String surname, String lastname) {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Adding employee");
        dialog.setHeaderText("Successfully added employee");
        dialog.setContentText(surname + " " + firstname + " " + lastname);
        dialog.showAndWait();
    }
    private void clearFields() {
        firstnameField.clear();
        surnameField.clear();
        lastnameField.clear();
        usernameField.clear();
        passwordField.clear();
        roleComboBox.setValue(null);
    }

    public void logOut(MouseEvent mouseEvent) {
        try {
            App.setRoot("authorization");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}