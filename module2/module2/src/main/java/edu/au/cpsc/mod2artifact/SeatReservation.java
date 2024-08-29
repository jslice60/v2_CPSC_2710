package edu.au.cpsc.mod2artifact;

import java.time.LocalDate;

public class SeatReservation {

    // private variables
    private String flightDesignator;
    private LocalDate flightDate;
    private String firstName;
    private String lastName;
    private int numberOfBags = 0;
    private boolean flyingWithInfant;

    // Constructor
    public SeatReservation(String flightDesignator, LocalDate flightDate, String firstName,
                           String lastName, int numberOfBags, boolean flyingWithInfant) {

        this.flightDesignator = flightDesignator;
        this.flightDate = flightDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfBags = 0;
        this.flyingWithInfant = false;
    }
//GETTERS
    public String getFlightDesignator() {
        return flightDesignator;
    }
    public LocalDate getFlightDate() {
        return flightDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public int getNumberOfBags() {
        return numberOfBags;
    }
    public boolean isFlyingWithInfant() {
        return flyingWithInfant;
    }

    // Setters
    public void setFlightDesignator(String flightDesignator) {
        if (flightDesignator == null || flightDesignator.length() < 4 || flightDesignator.length() > 6) {
            throw new IllegalArgumentException("Flight Designator not in range of acceptable char count.");
        }
        this.flightDesignator = flightDesignator;
    }
    public void setFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setNumberOfBags(int numberOfBags) {
        if (numberOfBags < 0) {
            throw new IllegalArgumentException("Bags cannot be negative value");
        }
        this.numberOfBags = numberOfBags;
    }
    public void setFlyingWithInfant() {
        this.flyingWithInfant = true;
    }
    public void setNotFlyingWithInfant() {
        this.flyingWithInfant = false;
    }


    // toString method
    @Override
    public String toString() {
        return "SeatReservation{" +
                "flightDesignator='" + (flightDesignator != null ? flightDesignator : "null") + '\'' +
                ", flightDate=" + (flightDate != null ? flightDate : "null") +
                ", firstName='" + (firstName != null ? firstName : "null") + '\'' +
                ", lastName='" + (lastName != null ? lastName : "null") + '\'' +
                ", numberOfBags=" + numberOfBags +
                ", flyingWithInfant=" + flyingWithInfant +
                '}';
    }
}

