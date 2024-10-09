package edu.au.cpsc.module7.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Airport {

    private String ident;
    private String type;
    private String airportName;
    private Integer elevationFt;
    private String continent;
    private String isoCountry;
    private String isoRegion;
    private String municipality;
    private String gpsCode;
    private String iataCode;
    private String localCode;
    private Double latitude;
    private Double longitude;


    //constructor for CSV
    public Airport(String ident, String type, String airportName, Integer elevationFt, String continent,
                   String isoCountry, String isoRegion, String municipality, String gpsCode, String iataCode,
                   String localCode, Double latitude, Double longitude) {
        this.ident = ident;
        this.type = type;
        this.airportName = airportName;
        this.elevationFt = elevationFt;
        this.continent = continent;
        this.isoCountry = isoCountry;
        this.isoRegion = isoRegion;
        this.municipality = municipality;
        this.gpsCode = gpsCode;
        this.iataCode = iataCode;
        this.localCode = localCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public Integer getElevationFt() {
        return elevationFt;
    }

    public void setElevationFt(Integer elevationFt) {
        this.elevationFt = elevationFt;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getIsoCountry() {
        return isoCountry;
    }

    public void setIsoCountry(String isoCountry) {
        this.isoCountry = isoCountry;
    }

    public String getIsoRegion() {
        return isoRegion;
    }

    public void setIsoRegion(String isoRegion) {
        this.isoRegion = isoRegion;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getGpsCode() {
        return gpsCode;
    }

    public void setGpsCode(String gpsCode) {
        this.gpsCode = gpsCode;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getLocalCode() {
        return localCode;
    }

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    @Override
    public String toString() {
        return "Airport{" +
                "ident='" + ident + '\'' +
                "type='" + type + '\'' +
                ", airportName='" + airportName + '\'' +
                ", elevationFt=" + elevationFt +
                ", continent=" + continent +
                ", isoCountry='" + isoCountry + '\'' +
                ", isoRegion='" + isoRegion + '\'' +
                ", municipality='" + municipality + '\'' +
                ", gpsCode='" + gpsCode + '\'' +
                ", iataCode='" + iataCode + '\'' +
                ", localCode='" + localCode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }


    public static List<Airport> readAll() throws IOException {
        List<Airport> airports = new ArrayList<>();

        //InputStream inputStream = Airport.class.getClassLoader().getResourceAsStream("edu/au/cpsc/module7/airport_codes.csv");

        InputStream inputStream = Airport.class.getClassLoader().getResourceAsStream("airport-codes.csv");
        if (inputStream == null) {
            throw new IOException("Could not find the file.");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String row;
            reader.readLine();

            while ((row = reader.readLine()) != null) {
                String[] column = row.split(",");

                if (column.length == 13){
                    String ident = column[0];
                    String type = column[1];
                    String airportName = column[2];
                    Integer elevationFt = column[3].isEmpty() ? null : Integer.parseInt(column[3]);
                    String continent = column[4];
                    String isoCountry = column[5];
                    String isoRegion = column[6];
                    String municipality = column[7];
                    String gpsCode = column[8];
                    String iataCode = column[9];
                    String localCode = column[10];
                    Double latitude = column[11].isEmpty() ? null : Double.parseDouble(column[11]);
                    Double longitude = column[12].isEmpty() ? null : Double.parseDouble(column[12]);

                    Airport airport = new Airport(ident, type, airportName, elevationFt, continent,
                            isoCountry, isoRegion, municipality, gpsCode, iataCode,
                            localCode, latitude, longitude);
                    airports.add(airport);

                }

            }

        }

        return airports;
    }


}
