package ro.hanca.cristian.munichcity.models.base;

/**
 * Point of Interest Interface.
 */
public interface POI {
    String getName();
    LatLon getPosition();
    POIType getType();
}
