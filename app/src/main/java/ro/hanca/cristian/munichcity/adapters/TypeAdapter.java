package ro.hanca.cristian.munichcity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ro.hanca.cristian.managedrecyclerview.adapter.FilterableRecyclerViewAdapter;
import ro.hanca.cristian.munichcity.AppContext;
import ro.hanca.cristian.munichcity.R;
import ro.hanca.cristian.munichcity.models.Type;

/**
 * RecyclerView Adapter for the Types.
 */
public class TypeAdapter extends FilterableRecyclerViewAdapter<Type, TypeAdapter.ViewHolder>{

    public TypeAdapter(List<Type> dataSet) {
        super(dataSet);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_type, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Type item = dataSet.get(position);

        holder.icon.setImageResource(AppContext.activity.getResources().getIdentifier(
                "t" + item.getId(), "drawable", AppContext.activity.getPackageName()));
        holder.name.setText(item.getName());
        holder.count.setText("(" + item.getEntries().size() + ")");
        holder.separator.setVisibility(position == dataSet.size()
                ? View.INVISIBLE : View.VISIBLE);
    }

    class ViewHolder extends FilterableRecyclerViewAdapter.ClickableViewHolder {

        ImageView icon;
        TextView name;
        TextView count;
        ImageView next;
        View separator;

        public ViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.icon);
            name = (TextView) itemView.findViewById(R.id.name);
            count = (TextView) itemView.findViewById(R.id.count);
            next = (ImageView) itemView.findViewById(R.id.next);
            separator = itemView.findViewById(R.id.separator);
        }
    }
}
