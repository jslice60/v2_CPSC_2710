module edu.au.cpsc.mod2artifact {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.au.cpsc.mod2artifact to javafx.fxml;
    exports edu.au.cpsc.mod2artifact;
}