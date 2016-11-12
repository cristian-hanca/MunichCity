package ro.hanca.cristian.munichcity.models;

import java.util.List;

import ro.hanca.cristian.munichcity.models.base.LatLon;
import ro.hanca.cristian.munichcity.models.base.POIType;

/**
 * POI with an associated Operational Time list.
 */
public class TimetablePOI extends ContactPOI {

    public static class Buider extends ContactPOI.Builder {
        protected List<OperationalTime> times;

        public Builder setTimes(List<OperationalTime> times) {
            this.times = times;
            return this;
        }

        @Override
        public TimetablePOI build() {
            return new TimetablePOI(name, position, parent, phone,
                    website, times);
        }
    }

    private final List<OperationalTime> times;

    private TimetablePOI(String name, LatLon position, POIType parent,
                         String phone, String website, List<OperationalTime> times) {
        super(name, position, parent, phone, website);
        this.times = times;
    }

    public List<OperationalTime> getTimes() {
        return times;
    }

}
