package ro.hanca.cristian.munichcity.helpers;

import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import ro.hanca.cristian.munichcity.AppContext;
import ro.hanca.cristian.munichcity.Constants;
import ro.hanca.cristian.munichcity.models.DaoMaster;
import ro.hanca.cristian.munichcity.models.DaoSession;

/**
 * Methods that help with opening the Database.
 */
public class DataBaseHelpers {

    public static DaoSession openDb() {
        if (dbExists()) {
            deleteDb();
        }
        copyDb();
        return new DaoMaster(SQLiteDatabase
                .openDatabase(getFile().getAbsolutePath(), null,
                        SQLiteDatabase.OPEN_READONLY)).newSession();
    }

    private static File getFile() {
        return AppContext.activity.getFileStreamPath(Constants.dbName);
    }

    private static boolean dbExists() {
        return getFile().exists();
    }

    private static boolean deleteDb() {
        return getFile().delete();
    }

    private static void copyDb() {
        try {
            InputStream myInput = AppContext.activity.getAssets().open(Constants.dbName);
            OutputStream myOutput = new FileOutputStream(getFile().getAbsolutePath());
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
