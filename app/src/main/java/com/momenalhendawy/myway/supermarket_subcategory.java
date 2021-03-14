package com.momenalhendawy.myway;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class supermarket_subcategory extends AppCompatActivity {
    private FragmentManager fm;
    public ArrayList<Integer> pos = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supermarket_subcategory);

        fm = getSupportFragmentManager();

        Fragment f = fm.findFragmentById(R.id.rosWmkarona_subcategory_fragment);

        String sessionId= getIntent().getStringExtra("cate1");
/*
        String position= getIntent().getStringExtra("position");
        int x =Integer.parseInt(position);
        pos.add(x);
        User_singleton.getInstance().setPos(pos);
*/
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
            case "monzfat": {
                f = new monzfat_subcategory();
                fm.beginTransaction()
                        .add(R.id.rosWmkarona_subcategory_fragment, f)
                        .commit();
            }
            break;
        }
    }
}
