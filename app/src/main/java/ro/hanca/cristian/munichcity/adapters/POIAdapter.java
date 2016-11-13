package ro.hanca.cristian.munichcity.adapters;

import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ro.hanca.cristian.managedrecyclerview.adapter.FilterableRecyclerViewAdapter;
import ro.hanca.cristian.munichcity.Constants;
import ro.hanca.cristian.munichcity.R;
import ro.hanca.cristian.munichcity.models.POI;
import ro.hanca.cristian.munichcity.models.SubType;

/**
 * RecyclerView Adapter for POIs of a given SubType.
 */
public class POIAdapter extends FilterableRecyclerViewAdapter<Map.Entry<Float, POI>, POIAdapter.ViewHolder>{

    private DecimalFormat df = new DecimalFormat("#.##");

    public POIAdapter(List<Map.Entry<Float, POI>> dataSet) {
        super(dataSet);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_poi, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map.Entry<Float, POI> item = dataSet.get(position);

        holder.name.setText(item.getValue().getName());
        holder.distance.setText(df.format(item.getKey()));
    }

    class ViewHolder extends FilterableRecyclerViewAdapter.ClickableViewHolder {

        TextView name;
        TextView distance;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            distance = (TextView) itemView.findViewById(R.id.distance);
        }
    }
}
