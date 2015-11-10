package tyson.example.com.greendaosample.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tyson.example.com.greendaosample.R;

/**
 * Created by ag on 11/10/2015.
 */
public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.ViewHolder>{
    private List<Lease> mData;

    public MyCustomAdapter(List<Lease> data) {
        this.mData = data;
    }

    @Override
    public MyCustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_item, null);

        // create ViewHolder

        ViewHolder viewHolder = new MyCustomAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.textViewItem.setText(mData
                .get(position)
                .getItem());
        viewHolder.textViewPersonName.setText(mData
                .get(position)
                .getPerson()
                .getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewItem;
        public TextView textViewPersonName;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            textViewItem = (TextView) itemLayoutView.findViewById(R.id.activity_main_item_item);
            textViewPersonName = (TextView) itemLayoutView.findViewById(R.id.activity_main_item_person_name);
        }
    }
}
