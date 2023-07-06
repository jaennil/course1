package Controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.example.App;
import Other.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Loading implements Initializable {
    @FXML
    private Label connectionProgress;
    private final Model.Loading model = new Model.Loading();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectionProgress.textProperty().bindBidirectional(model.connectionResultProperty());
        delay(100, model::connectToDatabase);
    }

    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }
}
