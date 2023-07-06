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
    public Label nameEmptyLabel;
    public Label breedEmptyLabel;
    Model.Pet model = new Model.Pet();
    public TextField nameTextField;
    public Label welcomeLabel;
    public ComboBox<Breed> breedComboBox;
    public TableView<Other.Pet> tableView;
    public TableColumn<Other.Pet, String> nameColumn;
    public TableColumn<Other.Pet, String> breedColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeLabel.textProperty().bindBidirectional(model.welcomeText);

        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> model.setName(newValue));
        nameTextField.styleProperty().bindBidirectional(model.nameFieldStyle);
        nameEmptyLabel.visibleProperty().bindBidirectional(model.nameEmpty);

        breedComboBox.valueProperty().addListener((observable, oldValue, newValue) -> model.setBreed(newValue));
        breedComboBox.styleProperty().bindBidirectional(model.breedComboBoxStyle);
        breedEmptyLabel.visibleProperty().bindBidirectional(model.breedEmpty);

        breedComboBox.setItems(model.breeds);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        breedColumn.setCellValueFactory(new PropertyValueFactory<>("breedName"));
        tableView.setItems(model.pets);
    }

    public void onClickAppointmentView() {
        model.setAppointmentView();
    }

    public void onClickLogOut() {
        model.logOut();
    }

    public void onClickSubmit() {
        model.setName(nameTextField.getText());
        model.setBreed(breedComboBox.getValue());
        model.addPet();
    }
}
