package ro.hanca.cristian.databasemodule.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Database Model of a POI.
 */
@DatabaseTable(tableName = "POI")
public class POI {

    @DatabaseField(id = true, columnName = "Id")
    public long id;

    @DatabaseField(foreign = true, columnName = "SubTypeId", foreignColumnName = "Id")
    public SubType subType;

    @DatabaseField(columnName = "OSMId")
    public String OSMId;

    @DatabaseField(columnName = "Lat")
    public double lat;

    @DatabaseField(columnName = "Lon")
    public double lon;

    @DatabaseField(columnName = "Name")
    public String name;

    public POI() {}

}
