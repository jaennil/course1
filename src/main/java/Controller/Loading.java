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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        delay(100, () -> {
            Database.getInstance();
            boolean connected = Database.connect();
            if (!connected) {
                connectionProgress.setText("Failed to connect. Please check your VPN and restart the app.");
                return;
            }

            connectionProgress.setText("Successfully connected. Please wait");
            Platform.runLater(() -> {
                try {
                    App.setRoot("authorization");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() throws Exception {
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
