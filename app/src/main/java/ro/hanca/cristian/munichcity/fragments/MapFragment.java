package ro.hanca.cristian.munichcity.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.util.Map;

import ro.hanca.cristian.munichcity.AppContext;
import ro.hanca.cristian.munichcity.R;
import ro.hanca.cristian.munichcity.models.POI;

/**
 * POI Details Fragment.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap map;
    private Map.Entry<Float, POI> poi = null;
    private DecimalFormat df = new DecimalFormat("#.##");

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        AppContext.activity.setTitle(R.string.title_map);
        poi = AppContext.selected_poi;

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(poi.getValue().getName());

        TextView type = (TextView) view.findViewById(R.id.type);
        type.setText(poi.getValue().getSubType().getType().getName());

        TextView subtype = (TextView) view.findViewById(R.id.subtype);
        subtype.setText(poi.getValue().getSubType().getName());

        TextView distance = (TextView) view.findViewById(R.id.distance);
        distance.setText(df.format(poi.getKey()));

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        if (ContextCompat.checkSelfPermission(AppContext.activity,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        }

        LatLng pos = new LatLng(poi.getValue().getLat(), poi.getValue().getLon());
        map.addMarker(new MarkerOptions().position(pos));
        map.moveCamera(CameraUpdateFactory.zoomTo(15));
        map.moveCamera(CameraUpdateFactory.newLatLng(pos));
    }

}
