package com.redmage.csvcomparison.model;

import java.io.File;

public class CsvModel {

    private File control;
    private File sample;
    private File outputDir;
    private boolean isOutputDirSelected;

    public CsvModel() {
        isOutputDirSelected = false;
    }

    public File getControl() {
        return control;
    }

    public void setControl(File control) {
        this.control = control;
    }

    public File getSample() {
        return sample;
    }

    public void setSample(File sample) {
        this.sample = sample;
    }

    public File getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(File outputDir) {
        this.outputDir = outputDir;
    }

    public File updateOutputDir(File file) {
        if (!isOutputDirSelected()) {
            if (file.isFile()) {
                outputDir = new File(file.getParent());
            } else {
                outputDir = file;
            }
            return outputDir;
        }
        return null;
    }

    public boolean isOutputDirSelected() {
        return isOutputDirSelected;
    }

    public void setOutputDirSelected(boolean outputDirSelected) {
        isOutputDirSelected = outputDirSelected;
    }

    public void resetToDefaults() {
        control = null;
        sample = null;
        outputDir = null;
        isOutputDirSelected = false;
    }
}
