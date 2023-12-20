module com.redmage.csvcomparison {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens com.redmage.csvcomparison to javafx.fxml;
    exports com.redmage.csvcomparison;
    exports com.redmage.csvcomparison.controller;
    opens com.redmage.csvcomparison.controller to javafx.fxml;
}