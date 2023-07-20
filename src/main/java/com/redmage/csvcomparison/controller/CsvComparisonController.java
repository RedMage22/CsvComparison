package com.redmage.csvcomparison.controller;

import com.redmage.csvcomparison.csv_processor.CsvProcessor;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
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

    private File control;
    private File sample;
    private File outputDir;
    private boolean isOutputDirSelected = false;

    public CsvComparisonController() {
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter csvExtensionFilter =
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(csvExtensionFilter);

        directoryChooser = new DirectoryChooser();
    }

    @FXML
    private void handleControlButtonAction() {
        control = updateTextField(controlButton, controlTextField);
    }

    @FXML
    private void handleSampleButtonAction() {
        sample = updateTextField(sampleButton, sampleTextField);
    }

    @FXML
    private void handleOutputButtonAction() {
        outputDir = directoryChooser.showDialog(outputButton.getScene().getWindow());
        if (outputDir != null) {
            updateOutputDir(outputDir);
            isOutputDirSelected = true;
        }
    }

    @FXML
    private void handleProcessButtonAction() {
        try {
            CsvProcessor.process(control, sample, outputDir);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Process completed!", ButtonType.OK);
            alert.showAndWait();
            clearFiles();
            clearTextFields();
            isOutputDirSelected = false;
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Missing csv file(s)!", ButtonType.OK);
            alert.showAndWait();
        }

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
        if (!isOutputDirSelected) {
            if (file.isFile()) {
                outputDir = new File(file.getParent());
            } else {
                outputDir = file;
            }
            outputTextField.setText(outputDir.toString());
            updateInitialFileDirectories(outputDir);
        }
    }

    private void updateInitialFileDirectories(File dir) {
        fileChooser.setInitialDirectory(dir);
        directoryChooser.setInitialDirectory(dir);
    }


    private void clearFiles() {
        control = null;
        sample = null;
        outputDir = null;
    }

    private void clearTextFields() {
        controlTextField.setText("");
        sampleTextField.setText("");
        outputTextField.setText("");
    }
}