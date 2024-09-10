//has map method

package edu.au.cpsc.module3;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import org.w3c.dom.Text;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TextField urlTextField;
    /*@FXML
    protected void identSearchAction() {
        String url = urlTextField.getText();
        webView.getEngine().load(url);
    }*/
    @FXML
    private WebView webView;
    private AirportService airportService;
    @FXML
    private TextField typeTextField;
    @FXML
    private TextField iataCodeTextField;
    @FXML
    private TextField identTextField;
    @FXML
    private TextField localCodeTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField elevationTextField;
    @FXML
    private TextField countryTextField;
    @FXML
    private TextField regionTextField;
    @FXML
    private TextField municipalityTextField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Initialize the AirportService to load airport data from the CSV
            airportService = new AirportService();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error if the CSV file cannot be read
        }

        // Add "Enter" key listeners for all search fields (iataCode, ident, localCode)
        addEnterKeyListener(iataCodeTextField, "iataCode");
        addEnterKeyListener(identTextField, "ident");
        addEnterKeyListener(localCodeTextField, "localCode");
    }

    // Method to add an "Enter" key listener to the TextField
    private void addEnterKeyListener(TextField textField, String searchType) {
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String searchValue = textField.getText();
                if (!searchValue.isEmpty()) {
                    Airport airport = null;

                    // Perform search based on the type of field
                    switch (searchType) {
                        case "iataCode":
                            airport = airportService.findAirportByIataCode(searchValue);
                            break;
                        case "ident":
                            airport = airportService.findAirportByIdent(searchValue);
                            break;
                        case "localCode":
                            airport = airportService.findAirportByLocalCode(searchValue);
                            break;
                    }

                    if (airport != null) {
                        // Fill all fields with the airport data
                        typeTextField.setText(airport.getType());
                        nameTextField.setText(airport.getAirportName());
                        elevationTextField.setText(airport.getElevationFt() != null ? airport.getElevationFt().toString() : "");
                        countryTextField.setText(airport.getIsoCountry());
                        regionTextField.setText(airport.getIsoRegion());
                        municipalityTextField.setText(airport.getMunicipality());

                        // Call method to update the map with the airport's location
                        updateMap(airport.getLatitude(), airport.getLongitude());
                    } else {
                        //clearFields();
                    }
                }
            }
        });


        // Add a listener to the IATA code field to search when the user types an IATA code
        iataCodeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                Airport airport = airportService.findAirportByIataCode(newValue);
                if (airport != null) {
                    typeTextField.setText(airport.getType());
                    nameTextField.setText(airport.getAirportName());
                    elevationTextField.setText(String.valueOf(airport.getElevationFt()));
                    countryTextField.setText(airport.getIsoCountry());
                    regionTextField.setText(airport.getIsoRegion());
                    municipalityTextField.setText(airport.getMunicipality());
                } else {
                    typeTextField.setText("Not found");
                }
            } else {
                typeTextField.clear();
            }
        });

        // Add a listener to the IDENT code field to search when the user types an IDENT code
        identTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                Airport airport = airportService.findAirportByIdent(newValue);
                if (airport != null) {
                    typeTextField.setText(airport.getType());
                    nameTextField.setText(airport.getAirportName());
                    elevationTextField.setText(String.valueOf(airport.getElevationFt()));
                    countryTextField.setText(airport.getIsoCountry());
                    regionTextField.setText(airport.getIsoRegion());
                    municipalityTextField.setText(airport.getMunicipality());
                } else {
                    typeTextField.setText("Not found");
                }
            } else {
                typeTextField.clear();
            }
        });

        // Add a listener to the LOCAL code field to search when the user types an LOCAL code
        localCodeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                Airport airport = airportService.findAirportByLocalCode(newValue);
                if (airport != null) {
                    typeTextField.setText(airport.getType());
                    nameTextField.setText(airport.getAirportName());
                    elevationTextField.setText(String.valueOf(airport.getElevationFt()));
                    countryTextField.setText(airport.getIsoCountry());
                    regionTextField.setText(airport.getIsoRegion());
                    municipalityTextField.setText(airport.getMunicipality());
                } else {
                    typeTextField.setText("Not found");
                }
            } else {
                typeTextField.clear();
            }
        });

    }
    // Method to update the map (implementation will depend on how the map is set up)
    private void updateMap(Double latitude, Double longitude) {
        if (latitude != null && longitude != null) {
            // Logic to update map goes here (you can use WebView or a mapping API)
            System.out.println("Updating map to coordinates: " + longitude + ", " + latitude);
            String mapUrl = "https://www.windy.com/?" + longitude + "," + latitude + ",12";
            System.out.println(mapUrl);
            webView.getEngine().load(mapUrl);
        } else {
            System.out.println("No valid coordinates for this airport.");
        }
    }
}


