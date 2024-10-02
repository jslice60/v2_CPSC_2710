package edu.au.cpsc.module6.controller;

import edu.au.cpsc.module6.model.ScheduledFlight;
import edu.au.cpsc.module6.uimodel.FlightDetailModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;



public class FlightDetailViewController {

    @FXML
    private TextField flightDesignator, departureAirportIdent, arrivalAirportIdent;

    private FlightDetailModel model;

    public void initialize() {
        model = new FlightDetailModel();
        flightDesignator.textProperty().bindBidirectional(model.flightDesignatorProperty());
        departureAirportIdent.textProperty().bindBidirectional(model.departureAirportIdentProperty());
        arrivalAirportIdent.textProperty().bindBidirectional(model.arrivalAirportIdentProperty());
        // TODO #video3 - 12:20
    }

    public FlightDetailModel getModel() {return model; }

    // TODO #video3 - 13:20
    public void showFlight(ScheduledFlight flight) {
        if (flight == null) {
            model.setFlightDesignatorProperty("");
            model.setDepartureAirportIdentProperty("");
            model.setArrivalAirportIdentProperty("");
            model.setModifiedProperty(false);
            model.setNewProperty(true);
            return;
        }
        model.setFlightDesignatorProperty(flight.getFlightDesignator());
        model.setDepartureAirportIdentProperty(flight.getDepartureAirportIdent());
        model.setArrivalAirportIdentProperty(flight.getArrivalAirportIdent());
        model.setModifiedProperty(false);
        model.setNewProperty(false);
    }

    // TODO #video3 - 14:20
    public void updateFlight(ScheduledFlight flight) {
        flight.setFlightDesignator(model.getFlightDesignatorProperty());
        flight.setDepartureAirportIdent(model.getDepartureAirportIdent());
        flight.setArrivalAirportIdent(model.getArrivalAirportIdentProperty());
    }



}