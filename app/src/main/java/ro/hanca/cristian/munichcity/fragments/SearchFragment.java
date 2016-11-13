package ro.hanca.cristian.munichcity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.support.v7.widget.SearchView;
import ro.hanca.cristian.managedrecyclerview.ManagedRecyclerView;
import ro.hanca.cristian.managedrecyclerview.adapter.RecyclerViewClickListener;
import ro.hanca.cristian.munichcity.AppContext;
import ro.hanca.cristian.munichcity.R;
import ro.hanca.cristian.munichcity.adapters.POIAdapter;
import ro.hanca.cristian.munichcity.adapters.SubTypeAdapter;
import ro.hanca.cristian.munichcity.adapters.TypeAdapter;
import ro.hanca.cristian.munichcity.filters.POIFilters;
import ro.hanca.cristian.munichcity.models.POI;
import ro.hanca.cristian.munichcity.models.SubType;
import ro.hanca.cristian.munichcity.models.SubTypeDao;
import ro.hanca.cristian.munichcity.models.Type;

/**
 * Nearby Fragment.
 */
public class SearchFragment extends Fragment {

    private TextView typeText;
    private TextView subTypeText;
    private View caret;
    private TextView head;
    private View back;
    private ManagedRecyclerView list;
    private SearchView search;

    private Type selectedType;
    private SubType selectedSubType;
    private POIFilters filter;
    private POIAdapter poiAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        AppContext.activity.setTitle(R.string.title_search);

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        head = (TextView) view.findViewById(R.id.head);
        list = (ManagedRecyclerView) view.findViewById(R.id.list);
        back = view.findViewById(R.id.back);
        typeText = (TextView) view.findViewById(R.id.selected_type);
        caret = view.findViewById(R.id.caret);
        subTypeText = (TextView) view.findViewById(R.id.selected_subtype);
        search = (SearchView) view.findViewById(R.id.search);

        if (selectedSubType == null) {
            if (selectedType == null) {
                setupTypes();
            } else {
                setupSubTypes();
            }
        } else {
            setupPOI();
        }

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                filter.setSearch(newText);
                updatePOI();
                return true;
            }
        });

        updateUi();
        return view;
    }

    private void updateUi() {
        if (selectedSubType == null) {
            if (selectedType == null) {
                back.setVisibility(View.INVISIBLE);
                typeText.setVisibility(View.INVISIBLE);
                caret.setVisibility(View.INVISIBLE);
                subTypeText.setVisibility(View.INVISIBLE);
                search.setVisibility(View.GONE);
            } else {
                back.setVisibility(View.VISIBLE);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setupTypes();
                    }
                });

                typeText.setVisibility(View.VISIBLE);
                caret.setVisibility(View.VISIBLE);
                subTypeText.setVisibility(View.INVISIBLE);
                typeText.setText(selectedType.getName());
                search.setVisibility(View.GONE);
            }
        } else {
            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setupSubTypes();
                }
            });

            typeText.setVisibility(View.VISIBLE);
            caret.setVisibility(View.VISIBLE);
            subTypeText.setVisibility(View.VISIBLE);
            typeText.setText(selectedType.getName());
            subTypeText.setText(selectedSubType.getName());
            search.setVisibility(View.VISIBLE);
            search.setQuery(filter.getSearch(), false);
        }
    }

    private void updatePOI() {
        List<Map.Entry<Float, POI>> filteredModelList = filter.getFiltered();
        poiAdapter.animateTo(filteredModelList);
        list.getListView().scrollToPosition(0);
    }

    private void setupTypes() {
        selectedType = null;
        selectedSubType = null;
        back.setVisibility(View.GONE);

        head.setText(R.string.header_search_types);
        final TypeAdapter adapter = new TypeAdapter(AppContext.db.getTypeDao().queryBuilder().list());
        adapter.setItemClickListener(new RecyclerViewClickListener() {
            @Override
            public void itemClicked(View v, int position) {
                selectedType = adapter.getItem(position);
                AppContext.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setupSubTypes();
                    }
                });
            }
        });
        list.setAdapter(adapter);
        updateUi();
    }

    private void setupSubTypes() {
        if (selectedType == null) {
            return;
        }
        selectedSubType = null;

        head.setText(R.string.header_search_subtypes);
        final SubTypeAdapter adapter = new SubTypeAdapter(selectedType.getEntries());
        adapter.setItemClickListener(new RecyclerViewClickListener() {
            @Override
            public void itemClicked(View v, int position) {
                selectedSubType = adapter.getItem(position);
                AppContext.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setupPOI();
                    }
                });
            }
        });
        list.setAdapter(adapter);
        updateUi();
    }

    private void setupPOI() {
        if (selectedSubType == null) {
            return;
        }

        filter = new POIFilters(new ArrayList<>(AppContext.location
                .computeDistances(selectedSubType.getEntries(),
                        AppContext.location.getLocation()).entrySet()));

        head.setText(R.string.header_search_poi);
        poiAdapter = new POIAdapter(filter.getFiltered());
        poiAdapter.setItemClickListener(new RecyclerViewClickListener() {
            @Override
            public void itemClicked(View v, int position) {
                //adapter.getItem(position);
                AppContext.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
        list.setAdapter(poiAdapter);
        //search.setQuery("", false);

        updateUi();
    }
}