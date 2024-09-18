package edu.au.cpsc.module4.controller;

import edu.au.cpsc.module4.model.ScheduledFlight;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FlightTableViewController {

    @FXML
    private TableView<ScheduledFlight> flightTableView;

    @FXML
    private TableColumn<ScheduledFlight, String> flightDesignatorColumn, departureAirportIdentColumn, arrivalAirportIdentColumn; //TODO dayOfWeekColumn

    public void initialize() {
        flightDesignatorColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight, String>("flightDesignator"));
        departureAirportIdentColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight, String>("departureAirportIdent"));
        arrivalAirportIdentColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight, String>("arrivalAirportIdent"));
        //TODO dayofWeekColumn
        // Initialize Flight Table
        flightTableView.getSelectionModel().selectedItemProperty().addListener(c -> tableSelectionChanged());
    }

    public void showFlights(List<ScheduledFlight> flights) {
        SortedList<ScheduledFlight> sortedList= new SortedList<>((FXCollections.observableList(flights)));
        flightTableView.setItems(sortedList);
        sortedList.comparatorProperty().bind(flightTableView.comparatorProperty());
        flightTableView.refresh();
    }

    public void onFlightSelectionChanged(EventHandler<ScheduledFlightTableEvent> handler) {
        flightTableView.addEventHandler(ScheduledFlightTableEvent.FLIGHT_SELECTED, handler);
    }

    private void tableSelectionChanged() {
        ScheduledFlight selectedFlight = flightTableView.getSelectionModel().getSelectedItem();
        ScheduledFlightTableEvent event = new ScheduledFlightTableEvent(ScheduledFlightTableEvent.FLIGHT_SELECTED, selectedFlight);
        flightTableView.fireEvent(event);
    }

    public void select(ScheduledFlight flight) {flightTableView.getSelectionModel().select(flight);}

    public static class ScheduledFlightTableEvent extends Event {

        public static final EventType<ScheduledFlightTableEvent> ANY = new EventType<>(Event.ANY, "ANY");
        public static final EventType<ScheduledFlightTableEvent> FLIGHT_SELECTED = new EventType<>(ANY, "FLIGHT_SELECTED");
        private ScheduledFlight selectedFlight;
        public ScheduledFlightTableEvent(EventType<? extends Event> eventType, ScheduledFlight selectedFlight) {
            super(eventType);
            this.selectedFlight = selectedFlight;
    }

    public ScheduledFlight getSelectedFlight() {
        return selectedFlight;
        }
    }

}
