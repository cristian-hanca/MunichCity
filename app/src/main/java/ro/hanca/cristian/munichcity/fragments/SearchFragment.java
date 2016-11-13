package ro.hanca.cristian.munichcity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ro.hanca.cristian.managedrecyclerview.ManagedRecyclerView;
import ro.hanca.cristian.munichcity.AppContext;
import ro.hanca.cristian.munichcity.R;
import ro.hanca.cristian.munichcity.adapters.TypeAdapter;
import ro.hanca.cristian.munichcity.models.Type;

/**
 * Nearby Fragment.
 */
public class SearchFragment extends Fragment {

    private ManagedRecyclerView list;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        AppContext.activity.setTitle(R.string.title_search);

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        list = (ManagedRecyclerView) view.findViewById(R.id.list);
        list.setAdapter(new TypeAdapter(AppContext.db.getTypeDao().queryBuilder().list()));
        return view;
    }

    private class Content {

    }

}
