module edu.au.cpsc.module6 {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.au.cpsc.module6.model to javafx.base;
    opens edu.au.cpsc.module6 to javafx.fxml;
    //opens edu.au.cpsc.part1 to javafx.graphics;
    opens edu.au.cpsc.part1 to javafx.fxml;


    exports edu.au.cpsc.module6;
    exports edu.au.cpsc.module6.controller;
    exports  edu.au.cpsc.part1;
    opens edu.au.cpsc.module6.controller to javafx.fxml;
}