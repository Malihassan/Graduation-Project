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
public class requestzeet_details extends Fragment {

    private static ListView oderlist;

    public requestzeet_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_requestzeet_details, container, false);

        final ArrayList<requestorders> orders = new ArrayList<requestorders>();
        orders.add(new requestorders("زيت كرستال  , 2.4 لتر", R.drawable.zet1 ));
        orders.add(new requestorders("سمن روابى , 1.5 كم", R.drawable.smn1));
        orders.add(new requestorders("كرستال سمن نباتى اصفر , 700 جم", R.drawable.smn2));

        requestorder_adapter adapter = new requestorder_adapter(getContext(), orders);
        oderlist = v.findViewById(R.id.zet_supermarket_category);
        oderlist.setAdapter(adapter);

        return v;
        }

}
