package com.momenalhendawy.myway;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.StateSet.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class supermarket_details extends Fragment {
    private static ListView oderlist;


    public supermarket_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_supermarket_details, container, false);

        final ArrayList<supermarket_order> orders = new ArrayList<supermarket_order>();
        orders.add(new supermarket_order("ارز و مكرونة", R.drawable.roswmakrona));
        orders.add(new supermarket_order("زيت وسمن", R.drawable.zet));
        orders.add(new supermarket_order("مشروبات و شيبسى ", R.drawable.m4areb));
        orders.add(new supermarket_order("منظفات منزل ", R.drawable.mnzfat));


        supermarket_adapter adapter = new supermarket_adapter(getContext(), orders);

        oderlist = v.findViewById(R.id.supermarket_category);
        oderlist.setAdapter(adapter);
        oderlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle extras = new Bundle();
                switch (position) {
                    case 0: {
                        Intent intent = new Intent(getActivity(), supermarket_subcategory.class);
                        extras.putString("cate1", "ros");
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                    break;
                    case 1: {
                        Intent intent = new Intent(getActivity(), supermarket_subcategory.class);
                        extras.putString("cate1", "zet");
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                    break;
                    case 2: {
                        Intent intent = new Intent(getActivity(), supermarket_subcategory.class);
                        extras.putString("cate1", "m4robat");
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                    break;
                    case 3: {
                        Intent intent = new Intent(getActivity(), supermarket_subcategory.class);
                        extras.putString("cate1", "monzfat");
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                    break;
                }
            }

        });
        return v;
    }
}
