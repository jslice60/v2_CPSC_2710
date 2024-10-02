package edu.au.cpsc.module6.uimodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.function.BinaryOperator;

public class FlightDetailModel {

    // Variables
    private final StringProperty flightDesignatorProperty;
    private final StringProperty departureAirportIdentProperty;
    private final StringProperty arrivalAirportIdentProperty;
    private final BooleanProperty modifiedProperty;
    private final BooleanProperty newProperty;

    // Constructor
    public FlightDetailModel() {
        flightDesignatorProperty = new SimpleStringProperty();
        departureAirportIdentProperty = new SimpleStringProperty();
        arrivalAirportIdentProperty = new SimpleStringProperty();
        modifiedProperty = new SimpleBooleanProperty();
        newProperty = new SimpleBooleanProperty();
        flightDesignatorProperty.addListener(((observable, oldValue, NewValue) -> setModifiedProperty(true)));
        departureAirportIdentProperty.addListener(((observable, oldValue, NewValue) -> setModifiedProperty(true)));
        arrivalAirportIdentProperty.addListener(((observable, oldValue, NewValue) -> setModifiedProperty(true)));
    }

    // Methods to mantain properties. The name is the same as the variables above (not a getter)
    public StringProperty flightDesignatorProperty() { return flightDesignatorProperty; }
    public StringProperty departureAirportIdentProperty() { return  departureAirportIdentProperty; }
    public StringProperty arrivalAirportIdentProperty() { return  arrivalAirportIdentProperty; }
    public BooleanProperty modifiedProperty() {return modifiedProperty; }
    public BooleanProperty newProperty() {return newProperty; }


    // Getters & Setters for each property. Convenience methods
    public String getFlightDesignatorProperty() {return flightDesignatorProperty.get(); }
    public void setFlightDesignatorProperty(String fd) {flightDesignatorProperty.set(fd);}
    public String getDepartureAirportIdent() {return departureAirportIdentProperty.get(); }
    public void setDepartureAirportIdentProperty(String daip) { departureAirportIdentProperty.set(daip);}
    public String getArrivalAirportIdentProperty() {return arrivalAirportIdentProperty.get(); }
    public void setArrivalAirportIdentProperty(String aaip) {arrivalAirportIdentProperty.set(aaip);}
    public  boolean isModifiedProperty() {return modifiedProperty.get();}
    public void setModifiedProperty(boolean m) {modifiedProperty.set(m);}
    public boolean isNewProperty() {return newProperty.get();}
    public void setNewProperty(boolean n) {newProperty.set(n);}


}
