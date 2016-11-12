package ro.hanca.cristian.databasemodule;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        (new DatabaseExporter()).parseCSV(
                new File("C:\\[Projects]\\MunichCity\\Data\\Types.csv"),
                new File("C:\\[Projects]\\MunichCity\\Data\\SubTypes.csv"),
                new File("C:\\[Projects]\\MunichCity\\Data\\POI.csv"),
                "C:\\[Projects]\\MunichCity\\Data\\poi.db");
    }

}