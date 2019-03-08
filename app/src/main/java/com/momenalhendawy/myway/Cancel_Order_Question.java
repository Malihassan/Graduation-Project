package com.momenalhendawy.myway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Cancel_Order_Question extends AppCompatActivity {
        private static Button save ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel__order__question);

        save = findViewById(R.id.Send_Cancel_Order);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cancel_Order_Question.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(Cancel_Order_Question.this, "تم!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
