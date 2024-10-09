package edu.au.cpsc.module7.model;

import edu.au.cpsc.module7.model.Airport;

import java.io.IOException;
import java.util.List;

public class AirportService {
    private List<Airport> airports;

    // Initialize the service by loading the CSV file
    public AirportService() throws IOException {
        airports = Airport.readAll();  // Read all airports from the CSV file
    }


    // Method to find airport by IDENT
    public Airport findAirportByIdent(String ident) {
        for (Airport airport : airports) {
            if (airport.getIdent() != null && airport.getIdent().equalsIgnoreCase(ident)) {
                return airport;
            }
        }
        return null;
    }
    // Method to find airport by its IATA code
    public Airport findAirportByIataCode(String iataCode) {
        for (Airport airport : airports) {
            if (airport.getIataCode() != null && airport.getIataCode().equalsIgnoreCase(iataCode)) {
                return airport;
            }
        }
        return null;
    }
    // Method to find airport by LOCAL code
    public Airport findAirportByLocalCode(String localCode) {
        for (Airport airport : airports) {
            if (airport.getLocalCode() != null && airport.getLocalCode().equalsIgnoreCase(localCode)) {
                return airport;
            }
        }
        return null;
    }

}
