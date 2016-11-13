package ro.hanca.cristian.daogenerator;

import java.io.IOException;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class DaoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "ro.hanca.cristian.munichcity.models");
        schema.enableKeepSectionsByDefault();
        schema.enableActiveEntitiesByDefault();

        Entity typeEntry = createTypes(schema);
        Entity subTypeEntry = createSubTypes(typeEntry, schema);
        Entity POIEntry = createPOI(subTypeEntry, schema);

        new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema,
                "app/src/main/java/");
    }

    private static Entity createTypes(Schema schema) {
        Entity entity = schema.addEntity("Type");

        entity.setTableName("Type");
        entity.addIdProperty().columnName("Id");
        entity.addStringProperty("Name").columnName("Name");

        return entity;
    }

    private static Entity createSubTypes(Entity type, Schema schema) {
        Entity entity = schema.addEntity("SubType");

        entity.setTableName("SubType");
        entity.addIdProperty().columnName("Id");
        Property typeId = entity.addLongProperty("TypeId").columnName("TypeId").getProperty();
        entity.addStringProperty("Name").columnName("Name");
        entity.addToOne(type, typeId, "Type");
        type.addToMany(entity, typeId, "Entries");

        return entity;
    }

    private static Entity createPOI(Entity subType, Schema schema) {
        Entity entity = schema.addEntity("POI");

        entity.setTableName("POI");
        entity.addIdProperty().columnName("Id");
        Property subTypeId = entity.addLongProperty("SubTypeId").columnName("SubTypeId").getProperty();
        entity.addDoubleProperty("Lat").columnName("Lat");
        entity.addDoubleProperty("Lon").columnName("Lon");
        entity.addStringProperty("Name").columnName("Name");
        entity.addToOne(subType, subTypeId, "SubType");
        subType.addToMany(entity, subTypeId, "Entries");

        return entity;
    }

}
