package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import org.example.App;

import java.io.IOException;

//public class Main implements Initializable {
public class Employee {

//    @FXML
//    private TableView<Post> tablePosts;
    @FXML
    public TableColumn idColumn;
    @FXML
    public TableColumn nameColumn;
    @FXML
    public TableColumn shortNameColumn;
    private final Model.Employee model = new Model.Employee();

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        tablePosts.setItems(model.getPosts()); // binds the list with the model
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        shortNameColumn.setCellValueFactory(new PropertyValueFactory<>("shortName"));
//    }

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
}
