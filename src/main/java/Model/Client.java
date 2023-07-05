package Model;

import Other.*;
import Other.Pet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class Client {
    ObservableList<Appointment> appointments;
    public Client() {
        appointments = FXCollections.observableArrayList();
    }
    public void addAppointment(Appointment appointment) {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String statement = "insert into appointments (pet_id, person_id, doctor_id, date) value (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setInt(1, appointment.getPet().getId());
            preparedStatement.setInt(2, appointment.getPerson().getId());
            preparedStatement.setInt(3, appointment.getDoctor().getId());
            preparedStatement.setDate(4, appointment.getDate());
            preparedStatement.executeUpdate();
            appointments.add(appointment);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Other.Appointment> getPersonAppointments(Person person) {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String statement = "select * from appointments where person_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setInt(1, person.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Appointment appointment = Appointment.fromResultSet(resultSet, person);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointments;
    }
}

