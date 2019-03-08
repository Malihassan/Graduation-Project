package com.momenalhendawy.myway;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class category_adapter extends ArrayAdapter<category_order> {
    public category_adapter(Context context, List<category_order> objects) {
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

        category_order oder = getItem(position);

        ordername.setText((oder.getOrdername()));
        captainimage.setImageResource(oder.getId());


        return listitemview;
    }


}
