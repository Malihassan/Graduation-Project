package com.momenalhendawy.myway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class NewPassword extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

    }

    public void to_home_page(View view) {
        Toast.makeText(NewPassword.this, "تم تغير كلمة السر بنجاح", Toast.LENGTH_SHORT).show();
        Intent Signup = new Intent(NewPassword.this, MainActivity.class);
        startActivity(Signup);
    }
}
