package com.momenalhendawy.myway;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class requestm4robat_details extends Fragment {
    private static ListView oderlist;


    public requestm4robat_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_requestm4robat_details, container, false);

        final ArrayList<requestorders> orders = new ArrayList<requestorders>();
        orders.add(new requestorders("كوكولا  , 320 مل", R.drawable.cola ));
        orders.add(new requestorders("مانجو من جهينة , 235 مل", R.drawable.ghena));
        orders.add(new requestorders("مياه معدنية  , 1.5 لتر", R.drawable.myya));
        orders.add(new requestorders("كرنشى شيبسى , طماطم", R.drawable.kran4e));
        requestorder_adapter adapter = new requestorder_adapter(getContext(), orders);
        oderlist = v.findViewById(R.id.m4robat_supermarket_category);
        oderlist.setAdapter(adapter);
        return v;
    }
}
