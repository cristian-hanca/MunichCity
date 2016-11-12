package ro.hanca.cristian.munichcity.models;

import ro.hanca.cristian.munichcity.models.base.LatLon;
import ro.hanca.cristian.munichcity.models.base.POI;
import ro.hanca.cristian.munichcity.models.base.POIType;

/**
 * Representation of a basic POI.
 */
public class NormalPOI implements POI {

    public static class Builder {
        protected String name = null;
        protected LatLon position = null;
        protected POIType parent = null;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPosition(LatLon position) {
            this.position = position;
            return this;
        }

        public Builder setParent(POIType parent) {
            this.parent = parent;
            return this;
        }

        public NormalPOI build() {
            return new NormalPOI(name, position, parent);
        }
    }

    private final String name;
    private final LatLon position;
    private final POIType parent;

    protected NormalPOI(String name, LatLon position, POIType parent) {
        this.name = name;
        this.position = position;
        this.parent = parent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LatLon getPosition() {
        return position;
    }

    @Override
    public POIType getType() {
        return parent;
    }
}
