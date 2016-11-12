package ro.hanca.cristian.databasemodule.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility Class that parses a CSV file.
 */
public class CsvParser {

    /**
     * Static Class.
     */
    private CsvParser() { }

    /**
     * Parses a CSV (UTF-8 Encoded) file into a List of String Arrays.
     * Note, comment lines start with '#' and ignores empty lines.
     * @param source Path to the Source file.
     * @param ignoreFirst If true, the first Line (headings) are Ignored.
     * @return List of String Arrays representing the CSV File.
     */
    public static List<String[]> parse(File source, boolean ignoreFirst) {
        List<String[]> results = new ArrayList<String[]>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(source), "UTF-8"));
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.equals("") || line.charAt(0) == '#') {
                    continue;
                }

                if (first && ignoreFirst) {
                    first = false;
                } else {
                    String[] result = line.split(",");
                    if (result.length != 0) {
                        results.add(result);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

}
