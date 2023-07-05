package Controller;

import Other.Appointment;
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
import org.example.*;
import Other.Breed;
import Other.Database;
import Other.Person;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Client implements Initializable {

    private static final Model.Client model = new Model.Client();
    public Label welcomeLabel;
    public ComboBox<Person> doctorComboBox;
    public DatePicker datePicker;
    public ComboBox<Other.Pet> petComboBox;
    public TableView<Appointment> appointmentsTableView;
    public TableColumn<Appointment, String> doctorColumn;
    public TableColumn<Appointment, String> dateColumn;
    public TableColumn<Appointment, String> petColumn;
    private Person person;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            welcomeLabel.setText("Welcome, " + person.getWelcomeName());
            initDoctorComboBox();
            initPetComboBox();
            petColumn.setCellValueFactory(new PropertyValueFactory<>("petName"));
            doctorColumn.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateString"));
            ObservableList<Other.Appointment> observableList = model.getPersonAppointments(person);
            appointmentsTableView.setItems(observableList);
            datePicker.setDayCellFactory(this::disablePastDates);
        });
    }

    private DateCell disablePastDates(DatePicker datePicker) {
        return new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };
    }

    private void initDoctorComboBox() {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String statement = "select * from people where role = 'doctor'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Person doctor = Person.fromResultSet(resultSet);
                doctorComboBox.getItems().add(doctor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initPetComboBox() {
        Model.Pet modelPet = new Model.Pet();
        ObservableList<Other.Pet> pets = modelPet.getPersonPets(person);
        petComboBox.setItems(pets);
    }

    public void onClickSubmit(ActionEvent actionEvent) {
        LocalDate date = datePicker.getValue();
        Person doctor = doctorComboBox.getValue();
        Other.Pet pet = petComboBox.getValue();
        Appointment appointment = new Appointment(pet, person, doctor, Date.valueOf(date));
        model.addAppointment(appointment);
    }


    public void setAddPetView(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("pet.fxml"));
        try {
            Parent root = loader.load();
            Pet controller = loader.getController();
            controller.passPerson(person);
            Scene scene = welcomeLabel.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}