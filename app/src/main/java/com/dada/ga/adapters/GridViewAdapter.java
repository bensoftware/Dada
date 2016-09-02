package com.dada.ga.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.dada.ga.R;
import java.util.ArrayList;

/**
 * Created by steeve on 3/10/16.
 */

public class GridViewAdapter extends ArrayAdapter<CardItem> {

    Context mContext;
    int resourceId;
    ArrayList<CardItem> data = new ArrayList<CardItem>();

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList<CardItem> data) {
        super(context, layoutResourceId, data);
        this.mContext = context;
        this.resourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        ViewHolder holder = null;

        if (itemView == null) {
            final LayoutInflater layoutInflater =
                    (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = layoutInflater.inflate(resourceId, parent, false);

            holder = new ViewHolder();
            holder.category_icon = (ImageView) itemView.findViewById(R.id.category_icon);
            holder.category_name = (TextView) itemView.findViewById(R.id.category_name);
            itemView.setTag(holder);
        } else {
            holder = (ViewHolder) itemView.getTag();
        }

        CardItem item = getItem(position);
        holder.category_icon.setImageDrawable(item.getImage());
        holder.category_name.setText(item.getTitle());

        return itemView;
    }

    static class ViewHolder {
        ImageView category_icon;
        TextView category_name;
    }

}
