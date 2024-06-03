package com.redmage.csvcomparison.controller;

import com.redmage.csvcomparison.csv_processor.CsvProcessor;
import com.redmage.csvcomparison.model.CsvModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;

public class CsvComparisonController {

    @FXML
    private Button controlButton;

    @FXML
    private TextField controlTextField;

    @FXML
    private Button sampleButton;

    @FXML
    private TextField sampleTextField;

    @FXML
    private Button outputButton;

    @FXML
    private TextField outputTextField;

    private FileChooser fileChooser;
    private DirectoryChooser directoryChooser;

    private CsvModel csvModel;


    public CsvComparisonController() {
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter csvExtensionFilter =
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(csvExtensionFilter);

        directoryChooser = new DirectoryChooser();
        csvModel = new CsvModel();
    }

    @FXML
    private void handleControlButtonAction() {
        csvModel.setFile1(updateTextField(controlButton, controlTextField));
    }

    @FXML
    private void handleSampleButtonAction() {
        csvModel.setFile2(updateTextField(sampleButton, sampleTextField));
    }

    @FXML
    private void handleOutputButtonAction() {
        File outputDir = directoryChooser.showDialog(outputButton.getScene().getWindow());
        if (outputDir != null) {
            csvModel.setOutputDir(outputDir);
            updateOutputDir(outputDir);
            csvModel.setOutputDirSelected(true);
        }
    }

    @FXML
    private void handleProcessButtonAction() {
        Alert alert;
        try {
            CsvProcessor.process(csvModel);
            alert = new Alert(Alert.AlertType.INFORMATION, "Process completed!", ButtonType.OK);
            alert.showAndWait();
            csvModel.resetToDefaults();
            clearTextFields();
        } catch (FileNotFoundException e) {
            alert = new Alert(Alert.AlertType.ERROR, "Missing csv file(s)!\n" + buildErrorMessage(e), ButtonType.OK);
            alert.showAndWait();
            e.printStackTrace();
        } catch (Exception e) {
            if (e instanceof ArrayIndexOutOfBoundsException) {
                alert = new Alert(Alert.AlertType.ERROR, "Check that all columns are present in both files!\n" +
                        buildErrorMessage(e), ButtonType.OK);
            } else {
                alert = new Alert(Alert.AlertType.ERROR, buildErrorMessage(e), ButtonType.OK);
            }
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    private String buildErrorMessage(Exception e) {
        return (e.getMessage() != null) ? e.getClass().getName() + " occurred!\n" + e.getMessage() : e.getClass().getName() + " occurred!";
    }

    private File updateTextField(Button button, TextField textField) {
        File file = fileChooser.showOpenDialog(button.getScene().getWindow());
        if (file != null) {
            textField.setText(file.getName());
            updateOutputDir(file);
            return file;
        }
        return null;
    }

    private void updateOutputDir(File file) {
        File outputDir = csvModel.updateOutputDir(file);
        if (outputDir != null) {
            outputTextField.setText(outputDir.toString());
            updateInitialFileDirectories(outputDir);
        }
    }

    private void updateInitialFileDirectories(File dir) {
        fileChooser.setInitialDirectory(dir);
        directoryChooser.setInitialDirectory(dir);
    }

    private void clearTextFields() {
        controlTextField.setText("");
        sampleTextField.setText("");
        outputTextField.setText("");
    }
}