package com.redmage.csvcomparison.csv_reader;

import com.redmage.csvcomparison.formatting.LogColors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CsvReader {

    private static Logger logger = Logger.getLogger(CsvReader.class.getName());

    private CsvReader() {}

    public static Map<String, String> read(String file) {
        String row;
        Map<String, String> map = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            map = new LinkedHashMap<>();
            while ((row = reader.readLine()) != null) {
                map.put(getKey(row), getValue(row));
            }
            reader.close();
            logger.info(LogColors.ANSI_GREEN + file + " read successfully! " + (map.size() - 1) + " records loaded." + LogColors.ANSI_RESET);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    private static String getKey(String line) {
        if (line == null || !line.contains(",")) {
            return null;
        }
        return line.substring(0, line.indexOf(","));
    }

    private static String getValue(String line) {
        if (line == null || !line.contains(",")) {
            return null;
        }

        return line.substring(line.indexOf(",") + 1);
    }

}
