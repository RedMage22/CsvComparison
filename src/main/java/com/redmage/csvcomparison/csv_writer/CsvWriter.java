package com.redmage.csvcomparison.csv_writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class CsvWriter {


    private CsvWriter() {}

    public static void write(String filePath, Map<String, String> resultsMap) {
        if (filePath == null || resultsMap == null) {
            return;
        }
        try {
            File csvFile = new File(filePath + generateFileName());
            csvFile.setWritable(true);

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(csvFile));

            resultsMap.forEach((k,v) -> {
                String resultStr;
                if (!k.contains("Records summary")) {
                    resultStr = k.concat(",").concat(v).concat("\n");
                } else {
                    resultStr = "\n".concat(k.concat("\n").concat(v).concat("\n"));
                }
                try {
                    bufferedWriter.write(resultStr);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String generateFileName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
        return "/comparison-" + dateFormat.format(new Date()) + ".csv";
    }
}
