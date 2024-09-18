package edu.au.cpsc.module4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FlightSummaryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FlightSummaryApplication.class.getResource("flight-detail-view.fxml")); //JIMMY fix this!!
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Flight Schedule App!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}