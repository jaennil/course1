package Controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.App;
import Other.Breed;
import Other.Database;
import Other.Person;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Pet implements Initializable {
    public TextField nameTextField;
    Model.Pet model = new Model.Pet();
    public Label welcomeLabel;
    public ComboBox<Breed> breedComboBox;
    public TableView<Other.Pet> tableView;
    public TableColumn<Other.Pet, String> nameColumn;
    public TableColumn<Other.Pet, String> breedColumn;
    private Person person;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            welcomeLabel.setText("Welcome, " + person.getWelcomeName());
            initBreedComboBox();
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            breedColumn.setCellValueFactory(new PropertyValueFactory<>("breed"));
            ObservableList<Other.Pet> observableList = model.getPersonPets(person);
            tableView.setItems(observableList);
        });
    }

    private void initBreedComboBox() {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String statement = "select * from breeds";
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Breed breed = Breed.fromResultSet(resultSet);
                breedComboBox.getItems().add(breed);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAppointmentView(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("client.fxml"));
        try {
            Parent root = loader.load();
            Client controller = loader.getController();
            controller.passPerson(person);
            Scene scene = welcomeLabel.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void passPerson(Person person) {
        this.person = person;
    }

    public void logOut(MouseEvent mouseEvent) {
        try {
            App.setRoot("authorization");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onClickSubmit(ActionEvent actionEvent) {
        Other.Pet pet = new Other.Pet(nameTextField.getText(), breedComboBox.getValue(), person);
        model.addPet(pet);
    }
}
