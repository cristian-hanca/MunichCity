package ro.hanca.cristian.munichcity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ro.hanca.cristian.munichcity.AppContext;
import ro.hanca.cristian.munichcity.R;

/**
 * Nearby Fragment.
 */
public class NearFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        AppContext.activity.setTitle(R.string.title_near);

        View view = inflater.inflate(R.layout.fragment_near, container, false);

        return view;
    }

}
