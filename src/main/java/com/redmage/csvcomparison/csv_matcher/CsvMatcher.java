package com.redmage.csvcomparison.csv_matcher;

import com.redmage.csvcomparison.formatting.LogColors;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

public class CsvMatcher {

    private static Logger logger = Logger.getLogger(CsvMatcher.class.getName());

    private CsvMatcher() {}

    public static Map<String, String> match(Map<String, String> mapA, Map<String, String> mapB, String fileName1, String fileName2) throws IllegalArgumentException {
        if (mapA == null || mapA.isEmpty() || mapB == null || mapB.isEmpty()) {
            return null;
        }

        Map<String, String> resultsMap = new LinkedHashMap<>();

        Map.Entry<String, String> firstEntry = mapA.entrySet().iterator().next();

        String firstKey = firstEntry.getKey();
        String firstValue = firstEntry.getValue();

        String[] firstValueArray = null;

        if (firstValue.contains(",")) {
            firstValueArray = firstValue.split(",");
        } else {
            firstValueArray = new String[] { firstValue };
        }

        String abColumns = "";

        for (String column : firstValueArray) {
            if (abColumns.isEmpty()) {
                abColumns = abColumns.concat(column).concat("_a,").concat(column).concat("_b");
            } else {
                abColumns = abColumns.concat(",").concat(column).concat("_a,").concat(column).concat("_b");

            }
        }

        abColumns = abColumns.concat(",Is equal?");
        resultsMap.put(firstKey, abColumns);

        Map<String, String> sortedMap = new TreeMap<>();

        logger.info(LogColors.ANSI_GREEN + "Matching records in " + fileName1 + " to " + fileName2 + "." + LogColors.ANSI_RESET);
        String noData = "no data";
        Map<String, String> noMatchMapA = new LinkedHashMap<>();
        mapA.forEach((k, v) -> {
            String newColumns = "";
            boolean isEqual = false;
            String[] mapAValues = v.split(",");

            if (mapB.containsKey(k) && !resultsMap.containsKey(k)) {
                String[] mapBValues = mapB.get(k).split(",");

                for(int i = 0; i < mapAValues.length; i++) {
                    if (i == 0) {
                        isEqual = mapAValues[i].equals(mapBValues[i]);
                    } else {
                        isEqual = isEqual && (mapAValues[i].equals(mapBValues[i]));
                    }
                    newColumns = newColumns.concat(mapAValues[i]).concat(",").concat(mapBValues[i]).concat(",");
                }
                newColumns = newColumns.concat(Boolean.toString(isEqual));
            } else {
                for (String mapAValue : mapAValues) {
                    newColumns = newColumns.concat(mapAValue).concat(",").concat(noData).concat(",");
                }
                newColumns = newColumns.concat(Boolean.toString(isEqual));
                noMatchMapA.put(k, newColumns);
            }

            if (!resultsMap.containsKey(k)) {
                sortedMap.put(k, newColumns);
            }

        });

        logger.info(LogColors.ANSI_GREEN + "Scanning for unmatched records in " + fileName2 + "." + LogColors.ANSI_RESET);
        Map<String, String> noMatchMapB = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : mapB.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            String newColumns = "";
            String[] mapBValues = v.split(",");

            if (!mapA.containsKey(k) && !resultsMap.containsKey(k)) {
                for (String mapBValue : mapBValues) {
                    newColumns = newColumns.concat(noData).concat(",").concat(mapBValue).concat(",");
                }
                newColumns = newColumns.concat(Boolean.toString(false));
                sortedMap.put(k, newColumns);
                noMatchMapB.put(k, newColumns);
            }
        }

        resultsMap.putAll(sortedMap);

        addResultSummary(resultsMap, mapA.size(), mapB.size(), noMatchMapA.size(), noMatchMapB.size(), fileName1, fileName2);

        return resultsMap;
    }

    private static void addResultSummary(Map<String, String> resultsMap, long recordTotalA, long recordTotalB,
                                        long noMatchA, long noMatchB, String fileName1, String fileName2) {
        long falseTotal = resultsMap.entrySet().stream().filter(k -> k.getValue().contains("false")).count();

        String summaryKey = "----------------------Records summary-------------------------";
        String summaryValue = "Total rows in " + fileName1 + " = " + (recordTotalA - 1) + "\n" +
                "Total rows in " + fileName2 + " = " + (recordTotalB - 1) + "\n" +
                "Total unequal (FALSE) data rows = " + falseTotal + "\n" +
                "Total rows not found in " + fileName1 + " = " + noMatchB + "\n" +
                "Total rows not found in " + fileName2 + " = " + noMatchA + "\n" +
                "Column suffix _a = " + fileName1 + "\n" +
                "Column suffix _b = " + fileName2;

        resultsMap.put(summaryKey, summaryValue);
    }
}
