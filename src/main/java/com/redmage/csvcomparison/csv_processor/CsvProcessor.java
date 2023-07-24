package com.redmage.csvcomparison.csv_processor;

import com.redmage.csvcomparison.csv_matcher.CsvMatcher;
import com.redmage.csvcomparison.csv_reader.CsvReader;
import com.redmage.csvcomparison.csv_writer.CsvWriter;
import com.redmage.csvcomparison.model.CsvModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

public class CsvProcessor {


    private CsvProcessor() {}

    public static void process(CsvModel model) throws FileNotFoundException {
        process(model.getControl(), model.getSample(), model.getOutputDir());
    }

    public static void process(File control, File sample, File output) throws FileNotFoundException {
        if (control != null && sample != null && output != null) {
            Map<String, String> mapA = CsvReader.read(control.getPath());
            Map<String, String> mapB = CsvReader.read(sample.getPath());
            Map<String, String> resultsMap = CsvMatcher.match(mapA, mapB);
            CsvWriter.write(output.getPath(), resultsMap);
        } else {
            throw new FileNotFoundException();
        }

    }

}
