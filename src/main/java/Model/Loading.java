package Model;

import Other.Database;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.example.App;

import javax.xml.crypto.Data;
import java.io.IOException;

public class Loading {
    private final StringProperty connectionResult = new SimpleStringProperty("Connecting to database...");
    public Property<String> connectionResultProperty() {
        return connectionResult;
    }

    public void setConnected(boolean isConnected) {
        if (isConnected)
            connectionResult.setValue("Successfully connected. Please wait");
        else
            connectionResult.setValue("Failed to connect. Please check your VPN and restart the app.");
    }

    public void connectToDatabase() {
        Database.getInstance();
        setConnected(Database.connect());
        authenticateView();
    }

    private void authenticateView() {
        Platform.runLater(() -> {
            try {
                App.setRoot("authorization");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
