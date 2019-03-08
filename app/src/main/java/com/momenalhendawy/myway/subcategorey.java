package com.momenalhendawy.myway;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class subcategorey extends AppCompatActivity {
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategorey);

        fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.category_fragment);


        String sessionId= getIntent().getStringExtra("cate1");
        if (sessionId.equals("q1")) {
            f = new supermarket_details();
            fm.beginTransaction()
                    .add(R.id.subcategory_fragment, f)
                    .commit();
        }
    }
}