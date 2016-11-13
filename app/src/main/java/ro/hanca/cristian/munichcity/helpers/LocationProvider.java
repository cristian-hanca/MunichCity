package ro.hanca.cristian.munichcity.helpers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import ro.hanca.cristian.munichcity.AppContext;
import ro.hanca.cristian.munichcity.models.POI;

/**
 * Class designed to keep an eye out for Location.
 */
public class LocationProvider implements LocationListener {

    private Location location;
    private LocationManager locationManager;
    private String provider;

    public LocationProvider() {
        // Get the location manager
        locationManager = (LocationManager) AppContext.activity
                .getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);

        // Stupid permission Test to make the IDE happy.
        if (ActivityCompat.checkSelfPermission(AppContext.activity,
                Manifest.permission.ACCESS_FINE_LOCATION) == 0) {}

        location = locationManager.getLastKnownLocation(provider);
    }

    public Location getLocation() {
        return location;
    }

    public void enable(boolean value) {
        if (value) {
            // Stupid permission Test to make the IDE happy.
            if (ActivityCompat.checkSelfPermission(AppContext.activity,
                    Manifest.permission.ACCESS_FINE_LOCATION) == 0) {}

            locationManager.requestLocationUpdates(provider, 400, 1, this);
        } else {
            locationManager.removeUpdates(this);
        }
    }

    public TreeMap<Float, POI> computeDistances(List<POI> source, Location location) {
        TreeMap<Float, POI> res = new TreeMap<>();
        for (POI poi : source) {
            Location poiloc = new Location("");
            poiloc.setLatitude(poi.getLat());
            poiloc.setLongitude(poi.getLon());
            res.put(location.distanceTo(poiloc) / 1000, poi);
        }
        return res;
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

}
