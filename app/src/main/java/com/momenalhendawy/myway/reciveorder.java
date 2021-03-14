package com.momenalhendawy.myway;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class reciveorder extends Fragment {
    private static Button recivebutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reciveorder, container, false);
        recivebutton = v.findViewById(R.id.reciveorder_page);
        recivebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "شكرا لتسجيل تقديرك :) ", Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }
}