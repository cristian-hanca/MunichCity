package ro.hanca.cristian.munichcity.models;

import ro.hanca.cristian.munichcity.models.base.LatLon;
import ro.hanca.cristian.munichcity.models.base.POIType;

/**
 * A POI with contact Phone number and Website.
 */
public class ContactPOI extends NormalPOI {

    public static class Builder extends NormalPOI.Builder {
        protected String phone;
        protected String website;

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setWebsite(String website) {
            this.website = website;
            return this;
        }

        @Override
        public ContactPOI build() {
            return new ContactPOI(name, position, parent, phone, website);
        }
    }

    private final String phone;
    private final String website;

    protected ContactPOI(String name, LatLon position, POIType parent,
                         String phone, String website) {
        super(name, position, parent);
        this.phone = phone;
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

}
