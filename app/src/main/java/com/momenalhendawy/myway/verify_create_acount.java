package com.momenalhendawy.myway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class verify_create_acount extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_create_acount);

    }

    public void to_home_page(View view) {
        Toast.makeText(verify_create_acount.this, "تم إنشاء الحساب بنجاح", Toast.LENGTH_SHORT).show();
        Intent Signup = new Intent(verify_create_acount.this, MainActivity.class);
        startActivity(Signup);
    }
}
