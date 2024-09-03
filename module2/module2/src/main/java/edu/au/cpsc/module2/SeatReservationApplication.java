package edu.au.cpsc.module2;

import java.time.LocalDate;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;

public class SeatReservationApplication extends Application {
    private SeatReservation seatReservation;
    // UI controls
    private TextField flightDesignator;
    private DatePicker flightDate;
    private TextField firstName;

    private TextField lastName;
    private TextField numberOfBags;
    private CheckBox flyingWithInfant;
    private TextField numberOfPassengers;
    private Button saveButton;
    private Button cancelButton;

    @Override
    public void start(Stage stage) throws IOException {
        seatReservation = new SeatReservation("DL123", LocalDate.of(2024, 10, 31), "Jimmy", "Manuel", 1, false);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Initialize UI controls
        initializeControls();

        // Create GridPane
        createGridPane(gridPane);
        Button cancelBtn = new Button("Cancel");
        Button saveBtn = new Button("Save");
        HBox bottomButtons = new HBox(cancelBtn, saveBtn);
        bottomButtons.setSpacing(10);

        // Use Lamba format to adjust numOfPassengers if flyingWithInfant
        flyingWithInfant.setOnAction(event -> {
            if (flyingWithInfant.isSelected()) {
                numberOfPassengers.setText("2");
            } else {
                numberOfPassengers.setText("1");
            }
        });
        //Save + Cancel Button event handler
        saveButton.setOnAction(event -> handleSave());
        cancelButton.setOnAction(event -> handleCancel());

        BorderPane root = new BorderPane(gridPane, null, null, bottomButtons, null);
        updateUI();
        Scene scene = new Scene(root, 300, 300);
        stage.setTitle("Flight Reservation App");
        stage.setScene(scene);
        stage.show();
    }

    private void initializeControls() {
        flightDesignator = new TextField();
        flightDate = new DatePicker();
        firstName = new TextField();
        lastName = new TextField();
        numberOfBags = new TextField();
        flyingWithInfant = new CheckBox();
        numberOfPassengers = new TextField("1");
        numberOfPassengers.setEditable(false);
        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");

    }

    private void updateUI() {
        flightDesignator.setText(seatReservation.getFlightDesignator());
        flightDate.setValue(seatReservation.getFlightDate());
        firstName.setText(seatReservation.getFirstName());
        lastName.setText(seatReservation.getLastName());
        numberOfBags.setText(Integer.toString(seatReservation.getNumberOfBags()));
        flyingWithInfant.setSelected(seatReservation.isFlyingWithInfant());
    }

    private void createGridPane(GridPane gridPane) {
        // Left Column (Labels)
        gridPane.add(new Label("Flight Designator:"), 0, 0);
        gridPane.add(new Label("First Name:"), 0, 1);
        gridPane.add(new Label("Last Name:"), 0, 2);
        gridPane.add(new Label("Bags:"), 0, 3);
        gridPane.add(new Label("Flight Date:"), 0, 4);
        gridPane.add(new Label("Number of Passengers:"), 0, 5);
        gridPane.add(new Label("Flying with Infant?"), 0, 6);

        // Right Column (Controls)
        gridPane.add(flightDesignator, 1, 0);
        gridPane.add(firstName, 1, 1);
        gridPane.add(lastName, 1, 2);
        gridPane.add(numberOfBags, 1, 3);
        gridPane.add(flightDate, 1, 4);
        gridPane.add(numberOfPassengers, 1, 5);
        gridPane.add(flyingWithInfant, 1, 6);
        gridPane.add(saveButton,1,7);
        gridPane.add(cancelButton,1,8);

    }

    private void handleSave() {
        try {
            seatReservation.setFlightDesignator(flightDesignator.getText());
            seatReservation.setFlightDate(flightDate.getValue());
            seatReservation.setFirstName(firstName.getText());
            seatReservation.setLastName(lastName.getText());
            seatReservation.setNumberOfBags(Integer.parseInt(numberOfBags.getText()));

            if (flyingWithInfant.isSelected()) {
                seatReservation.setFlyingWithInfant();
            } else {
                seatReservation.setNotFlyingWithInfant();
            }

            // Display the seatReservation object
            System.out.println(seatReservation);

            // Exit the application
            Platform.exit();

        } catch (IllegalArgumentException e) {
            // Display the error message
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleCancel() {
        // Display "Cancel clicked" and exit the application
        System.out.println("Cancel clicked");
        Platform.exit();
    }
}