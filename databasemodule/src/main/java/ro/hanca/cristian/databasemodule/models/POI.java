package ro.hanca.cristian.databasemodule.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Database Model of a POI.
 */
@DatabaseTable(tableName = "POI")
public class POI {

    @DatabaseField(id = true)
    public long id;

    @DatabaseField(foreign = true, columnName = "SubTypeId", foreignColumnName = "Id")
    public SubType subType;

    @DatabaseField()
    public String OSMId;

    @DatabaseField()
    public double lat;

    @DatabaseField()
    public double lon;

    @DatabaseField()
    public String name;

    public POI() {}

}
