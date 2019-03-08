package com.momenalhendawy.myway;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class categorydetails extends Fragment {
    private static ListView oderlist;
    private static Button send ;


    public categorydetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_categorydetails, container, false);

        send =(Button) v.findViewById(R.id.sendorder);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() ,MainActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), " قم باختيار الكابتن المناسب لك ..لكى نقوم بارسال الطلب.. ", Toast.LENGTH_SHORT).show();
            }
        });
        final ArrayList<category_order> orders = new ArrayList<category_order>();
        orders.add(new category_order("سوبر ماركت", R.drawable.supermarket));
        orders.add(new category_order("عطارة", R.drawable.aetara));
        orders.add(new category_order("خضرواتى", R.drawable.vegetabl));
        orders.add(new category_order("صيدلية", R.drawable.phermac));

        category_adapter adapter = new category_adapter(getContext(), orders);
        oderlist = v.findViewById(R.id.categoryorder);
        oderlist.setAdapter(adapter);
        oderlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(),subcategorey.class);
                    intent.putExtra("cate1","q1");
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(getActivity(), Captain_Detials.class);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(getActivity(), Captain_Detials.class);
                    startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent(getActivity(), Captain_Detials.class);
                    startActivity(intent);
                }

            }
        });

        return v;
    }

}
