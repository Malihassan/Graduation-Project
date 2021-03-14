package com.momenalhendawy.myway;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class subcategorey extends AppCompatActivity {
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategorey);

        fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.category_fragment);


        String sessionId = getIntent().getStringExtra("cate2");
        switch (sessionId) {
            case "q1": {
                f = new supermarket_details();
                fm.beginTransaction()
                        .add(R.id.subcategory_fragment, f)
                        .commit();
            }
            break;

            case "q2": {
                f = new requestatara_details();
                fm.beginTransaction()
                        .add(R.id.subcategory_fragment, f)
                        .commit();
            }
            break;
            case "q3": {
                f = new request5dar_details();
                fm.beginTransaction()
                        .add(R.id.subcategory_fragment, f)
                        .commit();
            }
            break;
            /*
            case "q4": {
                f = new supermarket_details();
                fm.beginTransaction()
                        .add(R.id.subcategory_fragment, f)
                        .commit();
            }
            break;
            */
        }
    }
}