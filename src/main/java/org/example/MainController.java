package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

public class MainController {

    public Button loadDatabaseButton;
    public TableColumn posts;

    @FXML
    private Label clickedAmountLabel;

    private int clickedAmount = 0;

    public void loadDatabase(ActionEvent actionEvent) {
        clickedAmountLabel.setText("clicked " + clickedAmount++ + " times!");
    }
}
