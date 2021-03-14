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

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class order_adapter extends ArrayAdapter<order_M> {
    public order_adapter(Context context, List<order_M> objects) {
        super(context, 0, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemview = convertView;
        if (listitemview == null) {
            listitemview = LayoutInflater.from(getContext()).inflate(R.layout.client_reuests_design, parent, false);
        }

        TextView captainname = listitemview.findViewById(R.id.name);
        TextView ordername = listitemview.findViewById(R.id.order_name);
        ImageView captainimage = listitemview.findViewById(R.id.profile_image);




        order_M oder = getItem(position);



        captainname.setText(oder.getCaptainname());
        ordername.setText((oder.getOrdername()));


        Glide.with(listitemview)
                .load(oder.getIdpicture())
                .thumbnail(Glide.with(listitemview)
                        .load(oder.getIdpicture()))
                .into(captainimage);

        //captainimage.setImageResource(oder.getIdpicture());


        return listitemview;
    }
}
