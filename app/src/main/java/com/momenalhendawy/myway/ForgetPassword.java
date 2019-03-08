package com.momenalhendawy.myway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ForgetPassword extends AppCompatActivity {
    private static Button verify_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

    }

    public void to_new_password(View view) {
        Intent virify_pass = new Intent(ForgetPassword.this, NewPassword.class);
        startActivity(virify_pass);
    }
}
