package com.momenalhendawy.myway;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class orderDetails extends Fragment {
    private static ListView oderlist;

    public orderDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order_details, container, false);
        final ArrayList<order_M> orders = new ArrayList<order_M>();
        orders.add(new order_M("momen", "order 1", R.drawable.userprofile));
        orders.add(new order_M("mohamed", "order 2", R.drawable.userprofile));
        orders.add(new order_M("kamal", "order 3", R.drawable.userprofile));
        orders.add(new order_M("zyad", "order 4", R.drawable.userprofile));
        orders.add(new order_M("ahmed", "order 5", R.drawable.userprofile));
        orders.add(new order_M("mina", "order 6", R.drawable.userprofile));
        orders.add(new order_M("ibrahim", "order 7", R.drawable.userprofile));
        orders.add(new order_M("momen", "order 8", R.drawable.userprofile));
        orders.add(new order_M("momen", "order 9", R.drawable.userprofile));
        orders.add(new order_M("mohamed", "order 10", R.drawable.userprofile));
        orders.add(new order_M("kamal", "order 11", R.drawable.userprofile));
        orders.add(new order_M("zyad", "order 12", R.drawable.userprofile));
        orders.add(new order_M("ahmed", "order 13", R.drawable.userprofile));
        orders.add(new order_M("mina", "order 14", R.drawable.userprofile));
        orders.add(new order_M("ibrahim", "order 15", R.drawable.userprofile));
        orders.add(new order_M("momen", "order 16", R.drawable.userprofile));
        order_adapter adapter = new order_adapter(getContext(), orders);
        oderlist = v.findViewById(R.id.listorder);
        oderlist.setAdapter(adapter);
        oderlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Captain_Detials.class);
                startActivity(intent);
            }
        });
        return v;
    }
}