package ro.hanca.cristian.munichcity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ro.hanca.cristian.managedrecyclerview.adapter.FilterableRecyclerViewAdapter;
import ro.hanca.cristian.munichcity.R;
import ro.hanca.cristian.munichcity.models.SubType;
/**
 * RecyclerView Adapter for the SubTypes of a given Type.
 */
public class SubTypeAdapter extends FilterableRecyclerViewAdapter<SubType, SubTypeAdapter.ViewHolder>{

    public SubTypeAdapter(List<SubType> dataSet) {
        super(dataSet);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subtype, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SubType item = dataSet.get(position);

        holder.name.setText(item.getName());
        holder.count.setText("(" + item.getEntries().size() + ")");
        holder.separator.setVisibility(position == dataSet.size()
                ? View.INVISIBLE : View.VISIBLE);
    }

    class ViewHolder extends  FilterableRecyclerViewAdapter.ClickableViewHolder {

        TextView name;
        TextView count;
        ImageView next;
        View separator;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            count = (TextView) itemView.findViewById(R.id.count);
            next = (ImageView) itemView.findViewById(R.id.next);
            separator = itemView.findViewById(R.id.separator);
        }
    }
}
