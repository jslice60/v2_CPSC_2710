package edu.au.cpsc.module6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FlightSummaryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FlightSummaryApplication.class.getResource("/edu/au/cpsc/module6/flight-summary-app.fxml"));

        //FXMLLoader fxmlLoader = new FXMLLoader(FlightSummaryApplication.class.getResource("flight-summary-app.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 700);
        stage.setTitle("Flight Schedule App!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}