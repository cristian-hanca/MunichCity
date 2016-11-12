package ro.hanca.cristian.databasemodule.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Database Model of a POI Type.
 */
@DatabaseTable(tableName = "Type")
public class Type {

    @DatabaseField(id = true)
    public int id;

    @DatabaseField()
    public String name;

    public Type() {}

}
