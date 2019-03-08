package com.momenalhendawy.myway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login extends AppCompatActivity {
    private static Button login_button;
    private static TextView forgetpass;
    private static TextView createacount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_button = findViewById(R.id.home_login);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    /*  public void login_direction ()
      {
          login_button = (Button)findViewById(R.id.home_login);
          login_button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(MainActivity.this,Home.class);
                  startActivity(intent);

              }
          });

      }*/
    public void forgetPass(View view) {
        Intent forgetPass = new Intent(login.this, ForgetPassword.class);
        startActivity(forgetPass);
    }

    public void to_create_account(View view) {
        Intent acount = new Intent(login.this, CreateAccount.class);
        startActivity(acount);
    }
}
