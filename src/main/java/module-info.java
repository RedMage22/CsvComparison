module com.redmage.csvcomparison {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.redmage.csvcomparison to javafx.fxml;
    exports com.redmage.csvcomparison;
    exports com.redmage.csvcomparison.controller;
    opens com.redmage.csvcomparison.controller to javafx.fxml;
}