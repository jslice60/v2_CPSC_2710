package edu.au.cpsc.module7.controller;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;

public class LauncherApplicationController {

    @FXML
    private ImageView loadingImage;

   @FXML
   protected void onAirportsClickButton() throws IOException {
       if (loadingImage != null) {
           loadingImage.setVisible(true);
           RotateTransition rotateTransition = new RotateTransition(Duration.millis(4000), loadingImage);
           rotateTransition.setFromAngle(0);
           rotateTransition.setToAngle(360);
           rotateTransition.setInterpolator(Interpolator.LINEAR);
           /* FadeTransition fade = new FadeTransition(Duration.millis(3000), loadingImage);
           fade.setFromValue(1.0);
           fade.setToValue(0.0);
           fade.setCycleCount(4);
           fade.setAutoReverse(true);
           fade.playFromStart();  */
           rotateTransition.setOnFinished(event -> loadingImage.setVisible(false));
           rotateTransition.playFromStart();
       } else {
           System.out.println("Debug - Image loading error");
       }

       PauseTransition pause = new PauseTransition(Duration.millis(4000));

       pause.setOnFinished(event -> {
           try {
               AirportController.show();
           } catch (IOException e) {
               e.printStackTrace();
           }
       });

       pause.play();
}
}
