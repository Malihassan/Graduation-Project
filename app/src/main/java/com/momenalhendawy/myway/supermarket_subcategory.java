package com.momenalhendawy.myway;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class supermarket_subcategory extends AppCompatActivity {
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supermarket_subcategory);

        fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.rosWmkarona_subcategory_fragment);
        String sessionId= getIntent().getStringExtra("cate1");
        switch (sessionId) {
            case "ros": {
                f = new requestmkrona_details();
                fm.beginTransaction()
                        .add(R.id.rosWmkarona_subcategory_fragment, f)
                        .commit();
            }
            break;
            case "zet": {
                f = new requestzeet_details();
                fm.beginTransaction()
                        .add(R.id.rosWmkarona_subcategory_fragment, f)
                        .commit();
            }
            break;
            case "m4robat": {
                f = new requestm4robat_details();
                fm.beginTransaction()
                        .add(R.id.rosWmkarona_subcategory_fragment, f)
                        .commit();
            }
            break;
        }
    }
}
