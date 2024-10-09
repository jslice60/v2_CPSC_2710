package edu.au.cpsc.module7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class LauncherApplicationController {


   @FXML
   protected void onAirportsClickButton() throws IOException {
    AirportController.show();
}
}
