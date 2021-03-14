package com.momenalhendawy.myway;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class supermarket_adapter extends ArrayAdapter<supermarket_order> {
    public supermarket_adapter(Context context, List<supermarket_order> objects) {
        super(context, 0, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemview = convertView;
        if (listitemview == null) {
            listitemview = LayoutInflater.from(getContext()).inflate(R.layout.activity_category_design, parent, false);
        }
        TextView ordername = listitemview.findViewById(R.id.categoryname);
        ImageView captainimage = listitemview.findViewById(R.id.category_image);

        supermarket_order oder = getItem(position);

        ordername.setText((oder.getOrdername()));
        captainimage.setImageResource(oder.getId());


        return listitemview;
    }
}
