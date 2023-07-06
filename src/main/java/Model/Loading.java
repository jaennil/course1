package Model;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
}
