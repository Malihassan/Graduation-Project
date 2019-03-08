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
public class requestmkrona_details extends Fragment {
    private static ListView oderlist;


    public requestmkrona_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_requestmkrona_details, container, false);

        final ArrayList<requestorders> orders = new ArrayList<requestorders>();
        orders.add(new requestorders("المطبخ ارز , 1 كم", R.drawable.rosmdb5 ));
        orders.add(new requestorders("الضحى ارز , 1 كم", R.drawable.rosd7a));
        orders.add(new requestorders("الملكة مكرونة, 500 جم", R.drawable.mkronamlk1));
        orders.add(new requestorders("اطليانو مكرونة , 500 جم", R.drawable.mkaronaitia));

        requestorder_adapter adapter = new requestorder_adapter(getContext(), orders);
        oderlist = v.findViewById(R.id.mkaronacategoryorder);
        oderlist.setAdapter(adapter);

        return v;
    }

}
