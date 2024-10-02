package edu.au.cpsc.part1;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Part1Controller {

  @FXML
  private TextField messageTextField, echoTextField, firstBidirectionalTextField, secondBidirectionalTextField;

  @FXML
  private ImageView secretOverlayImageView;

  @FXML
  private Slider secretSlider;

  @FXML
  private CheckBox selectMeCheckBox;

  @FXML
  private Label selectMeLabel;

  @FXML
  private TextField tweetTextField;

  @FXML
  private Label numberOfCharactersLabel, validityLabel;

  public void initialize() {
    // Bind messageTextField's text to echoTextField
    echoTextField.textProperty().bind(messageTextField.textProperty());

    // Create a bi-directional binding between firstBidirectionalTextField and secondBidirectionalTextField
    firstBidirectionalTextField.textProperty().bindBidirectional(secondBidirectionalTextField.textProperty());

    // Bind the opacity of secretOverlayImageView to the value of the secretSlider
    secretOverlayImageView.opacityProperty().bind(secretSlider.valueProperty());

    // Bind selectMeLabel to display the state of selectMeCheckBox as true/false
    selectMeLabel.textProperty().bind(selectMeCheckBox.selectedProperty().asString());

    // Bind numberOfCharactersLabel to display the number of characters in tweetTextField
    numberOfCharactersLabel.textProperty().bind(tweetTextField.textProperty().length().asString());

    // Bind validityLabel to display "Valid" if tweetTextField has 10 or fewer characters, "Invalid" otherwise
    validityLabel.textProperty().bind(
            Bindings.when(tweetTextField.textProperty().length().lessThanOrEqualTo(10))
                    .then("Valid")
                    .otherwise("Invalid")
    );
  }



}