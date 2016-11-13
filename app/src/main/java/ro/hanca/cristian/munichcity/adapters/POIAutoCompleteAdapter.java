package ro.hanca.cristian.munichcity.adapters;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Predicate;

import java.text.DecimalFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ro.hanca.cristian.munichcity.AppContext;
import ro.hanca.cristian.munichcity.R;
import ro.hanca.cristian.munichcity.models.POI;

public class POIAutoCompleteAdapter extends BaseAdapter implements Filterable {

    private List<Map.Entry<Float, POI>> dataSet = new ArrayList<>();

    private List<Map.Entry<Float, POI>> filterSet = new ArrayList<>();

    private POIFilter filter;

    private DecimalFormat df = new DecimalFormat("#.##");

    public POIAutoCompleteAdapter(List<Map.Entry<Float, POI>> dataSet) {
        this.dataSet = dataSet;
        this.filterSet = this.dataSet;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Map.Entry<Float, POI> getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dataSet.get(position).getValue().getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = AppContext.activity.getLayoutInflater();
            int layoutId = R.layout.item_poi;
            convertView = inflater.inflate(layoutId, parent, false);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.distance = (TextView) convertView.findViewById(R.id.distance);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Map.Entry<Float, POI> item = dataSet.get(position);
        holder.name.setText(item.getValue().getName());
        holder.distance.setText(df.format(item.getKey()));

        return convertView;
    }

    public class ViewHolder {
        TextView name;
        TextView distance;
    }

    @Override
    public Filter getFilter() {
        if(filter == null) {
            filter = new POIFilter();
        }
        return filter;
    }

    private class POIFilter extends Filter {

        //Invoked in a worker thread to filter the data according to the constraint.
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0){
                String con = constraint.toString().toLowerCase();
                ArrayList<Map.Entry<Float, POI>> filterList = new ArrayList<>();
                for (int i=0; i < filterSet.size(); i++){
                    Map.Entry<Float, POI> fEntry = filterSet.get(i);
                    if (fEntry.getValue().getName().toLowerCase().contains(con)) {
                        filterList.add(new AbstractMap.SimpleEntry<>(fEntry.getKey(), fEntry.getValue()));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = filterSet.size();
                results.values = filterSet;
            }
            return results;
        }

        //Invoked in the UI thread to publish the filtering results in the user interface.
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            dataSet = (ArrayList<Map.Entry<Float, POI>>) results.values;
            notifyDataSetChanged();
        }
    }
}
