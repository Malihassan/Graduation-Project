package com.momenalhendawy.myway;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

public class Order extends AppCompatActivity {
    private FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.category_fragment);
         if (f == null) {
            f = new categorydetails();
            fm.beginTransaction()
                    .add(R.id.category_fragment, f)
                    .commit();
        }
    }
}
