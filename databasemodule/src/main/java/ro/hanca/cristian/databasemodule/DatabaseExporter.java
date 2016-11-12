package ro.hanca.cristian.databasemodule;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import ro.hanca.cristian.databasemodule.helpers.CsvParser;
import ro.hanca.cristian.databasemodule.models.POI;
import ro.hanca.cristian.databasemodule.models.SubType;
import ro.hanca.cristian.databasemodule.models.Type;

public class DatabaseExporter {

    public DatabaseExporter() {
        // this loads the JDBC driver to access a sqlite db with jdbc
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void parseCSV(File types, File subTypes, File poi, String destination) {
        deleteIfExists(destination);

        ConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:" + destination);

            storeType(types, connectionSource);
            storeSubType(subTypes, connectionSource);
            storePOI(poi, connectionSource);

            connectionSource.closeQuietly();
        } catch (Exception e) {
            if (connectionSource != null) {
                connectionSource.closeQuietly();
            }
            e.printStackTrace();
        }
    }

    private void deleteIfExists(String destination) {
        File dbFile = new File(destination);
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }

    private void storeType(File source, ConnectionSource conn) {
        try {
            final List<Type> items = new ArrayList<>();
            for(String[] line : CsvParser.parse(source, true)) {
                Type item = new Type();
                item.id = Integer.valueOf(line[0]);
                item.name = line[1];
                items.add(item);
            }

            final Dao<Type, Integer> dao = DaoManager.createDao(conn, Type.class);

            TableUtils.createTableIfNotExists(conn, Type.class);

            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Type item : items) {
                        dao.createOrUpdate(item);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void storeSubType(File source, ConnectionSource conn) {
        try {
            final Dao<Type, Integer> typeDao = DaoManager.createDao(conn, Type.class);

            final List<SubType> items = new ArrayList<>();
            for(String[] line : CsvParser.parse(source, true)) {
                SubType item = new SubType();
                item.id = Integer.valueOf(line[0]);
                item.type = typeDao.queryForId(Integer.valueOf(line[1]));
                item.name = line[2];
                items.add(item);
            }

            final Dao<SubType, Integer> dao = DaoManager.createDao(conn, SubType.class);

            TableUtils.createTableIfNotExists(conn, SubType.class);

            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (SubType item : items) {
                        dao.createOrUpdate(item);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void storePOI(File source, ConnectionSource conn) {
        try {
            final Dao<SubType, Integer> subTypeDao = DaoManager.createDao(conn, SubType.class);

            int id = 1;
            final List<POI> items = new ArrayList<>();
            for(String[] line : CsvParser.parse(source, true)) {
                POI item = new POI();
                item.id = id++;
                item.subType = subTypeDao.queryForId(Integer.valueOf(line[0]));
                item.OSMId = line[1];
                item.lat = Double.valueOf(line[2]);
                item.lon = Double.valueOf(line[3]);
                item.name = line[4];
                items.add(item);
            }

            final Dao<POI, Long> dao = DaoManager.createDao(conn, POI.class);

            TableUtils.createTableIfNotExists(conn, POI.class);

            dao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (POI item : items) {
                        dao.createOrUpdate(item);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
