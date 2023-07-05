package Other;

import java.sql.*;

public class Appointment {
    private int id;
    private Pet pet;
    private Person person;
    private Person doctor;
    private Date date;

    public Appointment(int id, Pet pet, Person person, Person doctor, Date date) {
        this.id = id;
        this.pet = pet;
        this.person = person;
        this.doctor = doctor;
        this.date = date;
    }

    public Appointment(Pet pet, Person person, Person doctor, Date date) {
        this.pet = pet;
        this.person = person;
        this.doctor = doctor;
        this.date = date;
    }

    public static Appointment fromResultSet(ResultSet resultSet, Person person) {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        String statement = "select * from pets where owner_id = ?";
        Pet pet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setInt(1, resultSet.getInt("person_id"));
            ResultSet petResultSet = preparedStatement.executeQuery();
            if (!petResultSet.next())
                throw new RuntimeException();
            pet = Pet.fromResultSet(petResultSet, person);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        statement = "select * from people where id = ? and role = 'doctor'";
        Person doctor;
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setInt(1, resultSet.getInt("doctor_id"));
            ResultSet doctorResultSet = preparedStatement.executeQuery();
            if (!doctorResultSet.next())
                throw new RuntimeException();
            doctor = Person.fromResultSet(doctorResultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            return new Appointment(
                    resultSet.getInt("id"),
                    pet,
                    person,
                    doctor,
                    resultSet.getDate("date")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPetName() {
        return pet.getName();
    }
    public String getDoctorName() {
        return doctor.getFullName();
    }
    public String getDateString() {
        return date.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getDoctor() {
        return doctor;
    }

    public void setDoctor(Person doctor) {
        this.doctor = doctor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
