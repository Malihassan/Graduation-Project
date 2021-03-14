package com.momenalhendawy.myway;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class menuorder_design extends AppCompatActivity {
    private static Button increase ;
    private static Button decrease ;
    private TextView result ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuorder_design);
    }
    public void incearseresult(View view) {
        result = (TextView) findViewById(R.id.showvalue);
        String x = (String) result.getText();
        int re = Integer.parseInt(x);
        if (re >= 0)
        {
            int y = re+1;

            result.setText(String.valueOf(y));
        }
    }

    public void decraeseresult(View view) {
        result = (TextView) findViewById(R.id.showvalue);
        String x = (String) result.getText();
        int re = Integer.parseInt(x);
        if (re > 0)
        {
            int y = re-1;
            result.setText(String.valueOf(y));

        }
        else
            result.setText("0");

    }

    public void next(View view) {
    }
}
