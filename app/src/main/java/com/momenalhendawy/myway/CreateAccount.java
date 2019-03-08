package com.momenalhendawy.myway;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount extends AppCompatActivity {
        private static Button registaccount ;
        private static TextView firstname ;
        private static TextView secondname ;
        private static TextView phonenumber ;
        private static TextView password ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference();

        registaccount = findViewById(R.id.createaccount_submit);
        registaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname =(TextView) findViewById(R.id.createaccount_firstname);
                String fn =  firstname.getText().toString();
                secondname = (TextView) findViewById(R.id.createaccount_lastname);
                String ln = secondname.getText().toString();
                phonenumber = (TextView) findViewById(R.id.createaccount_phonenumber);
                String ph = phonenumber.getText().toString();
                password = (TextView) findViewById(R.id.createaccount_password);
                String pa = password.getText().toString();

                if (TextUtils.isEmpty(firstname.getText())  )
                {
                    firstname.setError("يجب ادخال الاسم الاول !");
                }
                else if( TextUtils.isEmpty(secondname.getText()) )
                {
                    secondname.setError("يجب ادخال الاسم الثانى !");
                }
                else if( TextUtils.isEmpty(phonenumber.getText()) )
                {
                    phonenumber.setError("يجب ادخال رقم الهاتف !");
                }
                else if( TextUtils.isEmpty(password.getText()) )
                {
                    password.setError("يجب ادخال كلمة السر !");
                }
                else {
                    Client_firebase newaccount = new Client_firebase();

                    newaccount.setFirstName(fn);
                    newaccount.setLastName(ln);
                    newaccount.setPhoneNumber(ph);
                    newaccount.setPassword(pa);
                    newaccount.setEmail("....@gmail.com");
                    newaccount.setCountry("Assuit");
                    ref.child("Client").child(ph).setValue(newaccount).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(CreateAccount.this, "تم تسجيل بنجاح.", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(CreateAccount.this, "حدث خطأ اثناء العملية 🙁 ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                startActivity(intent);
                }
            }
        });

    }

    /*
    public void to_verify_account(View view) {
        Intent submit_button = new Intent(CreateAccount.this, verify_create_acount.class);
        startActivity(submit_button);
    }
    */
}
