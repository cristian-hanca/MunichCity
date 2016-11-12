package ro.hanca.cristian.munichcity.models.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a POV Type that supports SubTypes and Items at the same time.
 */
public class POIType {
    private final int nameRes;
    private final int iconRes;
    private final List<POIType> subTypes;
    private final List<POI> items;

    public POIType(int nameRes, int iconRes) {
        this.nameRes = nameRes;
        this.iconRes = iconRes;
        this.subTypes = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public int getNameRes() {
        return nameRes;
    }

    public int getIconRes() {
        return iconRes;
    }

    public List<POIType> getSubTypes() {
        return subTypes;
    }

    public List<POI> getItems() {
        return items;
    }
}
