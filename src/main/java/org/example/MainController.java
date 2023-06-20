package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button loadDatabaseButton;
    @FXML
    private TableView<Post> tablePosts;
    @FXML
    public TableColumn columnPost;
    private MainModel model = new MainModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tablePosts.setItems(model.getNames()); // binds the list with the model
        columnPost.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @FXML
    private void clickBtn(ActionEvent actionEvent) {
        loadDatabaseButton.setText("loaded from database");
        model.loadFromDB();
    }
}
