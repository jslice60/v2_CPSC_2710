package edu.au.cpsc.module6.controller;

import edu.au.cpsc.module6.model.ScheduledFlight;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

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
    private TableColumn<ScheduledFlight, String> flightDesignatorColumn, departureAirportIdentColumn,
            arrivalAirportIdentColumn, dayOfWeekColumn; //TODO dayOfWeekColumn

    public void initialize() {
        flightDesignatorColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight, String>("flightDesignator"));
        departureAirportIdentColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight, String>("departureAirportIdent"));
        arrivalAirportIdentColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight, String>("arrivalAirportIdent"));
        //dayOfWeekColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight, Set>("daysOfWeek"));
        dayOfWeekColumn.setCellValueFactory(cellData -> {
            Set<DayOfWeek> daysOfWeek = cellData.getValue().getDaysOfWeek();
            String daysOfWeekString = daysOfWeek.stream()
                    .map(DayOfWeek::toString)
                    .reduce((day1, day2) -> day1 + ", " + day2)
                    .orElse("");
            return new javafx.beans.property.SimpleStringProperty(daysOfWeekString);
        });
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
