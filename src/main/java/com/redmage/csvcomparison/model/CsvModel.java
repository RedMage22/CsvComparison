package com.redmage.csvcomparison.model;

import java.io.File;

public class CsvModel {

    private File file1;
    private File file2;
    private File outputDir;
    private boolean isOutputDirSelected;

    public CsvModel() {
        isOutputDirSelected = false;
    }

    public File getFile1() {
        return file1;
    }

    public void setFile1(File file1) {
        this.file1 = file1;
    }

    public File getFile2() {
        return file2;
    }

    public void setFile2(File file2) {
        this.file2 = file2;
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
        file1 = null;
        file2 = null;
        outputDir = null;
        isOutputDirSelected = false;
    }
}
