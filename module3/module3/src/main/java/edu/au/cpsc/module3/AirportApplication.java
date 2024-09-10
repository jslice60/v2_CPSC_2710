package edu.au.cpsc.module3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AirportApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AirportApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Welcome to the Airport Service Search App!");
        stage.setScene(scene);
        stage.setWidth(900);  // Initial window width
        stage.setHeight(700);  // Initial window height
        stage.show();
        try {
            // Call the readAll() method to get the list of airports
            List<Airport> airports = Airport.readAll();

            // Iterate through the list and print each Airport object
            for (Airport airport : airports) {
                System.out.println(airport);  // This will call the toString() method of the Airport class
            }
        } catch (IOException e) {
            e.printStackTrace();  // Handle any potential IOExceptions
        }

    }

    public static void main(String[] args) {
        launch();
}
}