package edu.au.cpsc.module6.controller;

import edu.au.cpsc.module6.data.Db;
import edu.au.cpsc.module6.model.ScheduledFlight;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;


public class FlightSummaryApplicationController {

    @FXML
    private FlightTableViewController flightTableViewController;

    @FXML
    private  FlightDetailViewController flightDetailViewController;

    @FXML
    private Button updateButton;

    @FXML
    private Button newButton;

    @FXML
    private MenuItem updateMenuItem;

    // Current flight being edited
    private ScheduledFlight flightBeingEdited;

    // Indicates whether the flight being edited is new or not
    private boolean flightBeingEditedIsNew;

   /*
    1. Enable/disable buttons based on state of the model object (new/modified).
    2. Place validation logic in your UI model and use it to highlight invalid input values (one simple way to do this is to have a boolean property
         for each input that indicates if the input is valid or not and use that property to change some visual feature of the UI). */

    public void initialize() {
        flightTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        flightTableViewController.onFlightSelectionChanged(
                event -> showFlight(event.getSelectedFlight()));
        // #1 -disable update button
        updateButton.disableProperty().bind(flightDetailViewController.getModel().modifiedProperty().not());
        // #2 -new update button
        //newButton.disableProperty().bind(flightDetailViewController.getModel().modifiedProperty().or(flightDetailViewController.getModel().newProperty()));
        showFlight(null);
    }

    public void showFlight(ScheduledFlight flight) {
        flightDetailViewController.showFlight(flight);
        HashSet<DayOfWeek> defaultDaysOfWeek = new HashSet<>();
        flightBeingEdited = flight == null ? new ScheduledFlight("", "", LocalTime.of(0, 0),
                "", LocalTime.of(0, 0), defaultDaysOfWeek) : flight;
        flightBeingEditedIsNew = flight == null;
        String buttonLabel = flightBeingEditedIsNew ? "Add" : "Update";
        updateButton.setText(buttonLabel);
        updateMenuItem.setText(buttonLabel);

    }

    @FXML
    protected void updateButtonAction() {
        flightDetailViewController.updateFlight(flightBeingEdited);
        if (flightBeingEditedIsNew) {
            Db.getDatabase().addScheduledFlight(flightBeingEdited);
        } else {
            Db.getDatabase().updateScheduledFlight(flightBeingEdited);
        }
        Db.saveDatabase();
        flightTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        flightTableViewController.select(flightBeingEdited);
    }

    @FXML
    protected void newButtonAction() {flightTableViewController.select(null);}

    @FXML
    protected void deleteButtonAction() {
        if (flightBeingEditedIsNew) {
            flightTableViewController.select(null);
        } else {
            Db.getDatabase().removeScheduledFlight(flightBeingEdited);
            Db.saveDatabase();
            flightTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        }
    }

    @FXML
    protected void updateMenuItem() {
        updateButtonAction();
    }

    @FXML
    protected void newMenuAction() {
        newButtonAction();
    }

    @FXML
    protected void deleteMenuAction() {
        deleteButtonAction();
    }

    @FXML
    protected void closeMenuAction() {
        Platform.exit();
    }


}
