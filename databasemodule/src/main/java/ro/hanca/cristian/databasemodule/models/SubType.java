package ro.hanca.cristian.databasemodule.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Database Model of a POI SubType.
 */
@DatabaseTable(tableName = "SubType")
public class SubType {

    @DatabaseField(id = true, columnName = "Id")
    public long id;

    @DatabaseField(foreign = true, columnName = "TypeId", foreignColumnName = "Id")
    public Type type;

    @DatabaseField(columnName = "Name")
    public String name;

    public SubType() {}

}
