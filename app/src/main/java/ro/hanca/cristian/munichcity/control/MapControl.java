package ro.hanca.cristian.munichcity.control;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ro.hanca.cristian.munichcity.AppContext;
import ro.hanca.cristian.munichcity.R;

/**
 * Custom UI Control to display Points on a map.
 */
public class MapControl extends LinearLayout implements OnMapReadyCallback {

    private GoogleMap map;

    public MapControl(Context context) {
        super(context);
        init();
    }

    public MapControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MapControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater)
                AppContext.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.control_map, this);

        SupportMapFragment mapFragment = (SupportMapFragment) AppContext.activity
                .getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        updateUI();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        if (ContextCompat.checkSelfPermission(AppContext.activity,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        }

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng munich = new LatLng(48.1351, 11.5820);
        map.addMarker(new MarkerOptions().position(munich).title("Marker in Munich"));
        map.moveCamera(CameraUpdateFactory.newLatLng(munich));
    }

    private void updateUI() {

    }
}
