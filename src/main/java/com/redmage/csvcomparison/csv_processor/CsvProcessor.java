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
        process(model.getFile1(), model.getFile2(), model.getOutputDir());
    }

    public static void process(File file1, File file2, File output) throws FileNotFoundException {
        if (file1 != null && file2 != null && output != null) {
            Map<String, String> mapA = CsvReader.read(file1.getPath());
            Map<String, String> mapB = CsvReader.read(file2.getPath());
            Map<String, String> resultsMap = CsvMatcher.match(mapA, mapB, file1.getName(), file2.getName());
            CsvWriter.write(output.getPath(), resultsMap);
        } else {
            throw new FileNotFoundException();
        }
    }

}
