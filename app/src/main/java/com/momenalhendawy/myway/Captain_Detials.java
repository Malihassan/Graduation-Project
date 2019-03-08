package com.momenalhendawy.myway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Captain_Detials extends AppCompatActivity {
    private static Button cancelorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captain__detials);
        cancelorder = findViewById(R.id.Cancel_oder);
        cancelorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Captain_Detials.this, Cancel_Order_Question.class);
                startActivity(intent);
            }
        });
    }
    }