package edu.au.cpsc.module4.controller;

import edu.au.cpsc.module4.model.ScheduledFlight;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;



public class FlightDetailViewController {

    @FXML
    private TextField flightDesignator, departureAirportIdent, arrivalAirportIdent;

    public void showFlight(ScheduledFlight flight) {
        if (flight == null) {
            flightDesignator.clear();
            departureAirportIdent.clear();
            arrivalAirportIdent.clear();
            return;
        }
        flightDesignator.setText(flight.getFlightDesignator());
        departureAirportIdent.setText(flight.getDepartureAirportIdent());
        arrivalAirportIdent.setText(flight.getArrivalAirportIdent());
    }

    public void updateFlight(ScheduledFlight flight) {
        flight.setFlightDesignator(flightDesignator.getText());
        flight.setDepartureAirportIdent(departureAirportIdent.getText());
        flight.setArrivalAirportIdent(arrivalAirportIdent.getText());
    }



}