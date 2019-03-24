package com.momenalhendawy.myway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class login extends AppCompatActivity {
    private static Button login_button;
    private static TextView forgetpass;
    private static TextView createacount;
    private static EditText PhoneNumber ;
    private static EditText Password ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        PhoneNumber = (EditText) findViewById(R.id.login_phone_number);



        login_button = findViewById(R.id.home_login);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ph =PhoneNumber.getText().toString().trim();

                if (ph.isEmpty() || ph.length() < 11  || ph.length() >=10 )
                {
                    PhoneNumber.setError("يجب ادخال رقم الهاتف !");
                    PhoneNumber.requestFocus();
                    return;
                }

                Intent intent = new Intent(login.this, verify_create_acount.class);
                intent.putExtra("PhoneNumber",ph);
                startActivity(intent);
            }
        });

    }



    public void forgetPass(View view) {
        Intent forgetPass = new Intent(login.this, ForgetPassword.class);
        startActivity(forgetPass);
    }

    public void to_create_account(View view) {
        Intent acount = new Intent(login.this, CreateAccount.class);
        startActivity(acount);
    }
}
