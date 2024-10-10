package edu.au.cpsc.module7.controller;

import edu.au.cpsc.module7.model.Airport;
import edu.au.cpsc.module7.model.AirportService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AirportController implements Initializable {

    //TODO - Open Airport window
    public static void show() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AirportController.class.getResource("/edu/au/cpsc/module7/windows/airport-app.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setFullScreen(false);
        stage.setTitle("Airport Search Screen");
        stage.setScene(scene);
        stage.show();
    }


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
    @FXML
    private Button searchButtonControl;


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



    private void clearFields() {
        typeTextField.clear();
        nameTextField.clear();
        elevationTextField.clear();
        countryTextField.clear();
        regionTextField.clear();
        municipalityTextField.clear();
        iataCodeTextField.clear();
        identTextField.clear();
        localCodeTextField.clear();
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
                        clearFields();
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
                    typeTextField.setText("Not Found");
                    nameTextField.setText("Not Found");
                    elevationTextField.setText("Not Found");
                    countryTextField.setText("Not Found");
                    regionTextField.setText("Not Found");
                    municipalityTextField.setText("Not Found");
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
                    nameTextField.setText("Not Found");
                    elevationTextField.setText("Not Found");
                    countryTextField.setText("Not Found");
                    regionTextField.setText("Not Found");
                    municipalityTextField.setText("Not Found");
                }
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


    @FXML
    public void searchButtonAction(ActionEvent actionEvent) {
        String iataCode = iataCodeTextField.getText();
        String identCode = identTextField.getText();
        String localCode = localCodeTextField.getText();

        Airport airport = null;

        // Perform search based on the first non-empty field (priority: IATA > IDENT > LOCAL)
        if (!iataCode.isEmpty()) {
            airport = airportService.findAirportByIataCode(iataCode);
        } else if (!identCode.isEmpty()) {
            airport = airportService.findAirportByIdent(identCode);
        } else if (!localCode.isEmpty()) {
            airport = airportService.findAirportByLocalCode(localCode);
        }

        if (airport != null) {
            // Fill all fields with the airport data
            typeTextField.setText(airport.getType());
            nameTextField.setText(airport.getAirportName());
            elevationTextField.setText(airport.getElevationFt() != null ? airport.getElevationFt().toString() : "");
            countryTextField.setText(airport.getIsoCountry());
            regionTextField.setText(airport.getIsoRegion());
            municipalityTextField.setText(airport.getMunicipality());

            // Update the map with the airport's location
            updateMap(airport.getLatitude(), airport.getLongitude());
        } else {
            // If no airport is found, clear the fields or display a message
            clearFields();
        }
    }

    public void clearButtonAction(ActionEvent actionEvent) {
        clearFields();
    }
}
