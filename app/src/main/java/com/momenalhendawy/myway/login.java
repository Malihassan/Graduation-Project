package com.momenalhendawy.myway;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class login extends AppCompatActivity {
    private static Button login_button;
    private static TextView forgetpass;
    private static TextView createacount;
    private static EditText PhoneNumber ;
    private static EditText Password ;
    private FirebaseUser mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        PhoneNumber = (EditText) findViewById(R.id.login_phone_number);



        login_button = findViewById(R.id.home_login);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String ph =PhoneNumber.getText().toString().trim();
                if (ph.isEmpty() || ph.length() < 11  )
                {
                    PhoneNumber.setError("يجب ادخال رقم الهاتف !");
                    PhoneNumber.requestFocus();
                    return;
                }else {
                    // to makesure that login user are exist in firebase
                    //else must create account
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    DocumentReference docIdRef = db.collection("Users").document(ph);
                    docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot documentph = task.getResult();
                                if (documentph.exists()) {

                                    User_singleton.getInstance().setNumber(PhoneNumber.getText().toString());


                                    Intent intent = new Intent(login.this, verify_create_acount.class);
                                    intent.putExtra("PhoneNumber", "+2"+ph);
                                    startActivity(intent);

                                }else {
                                    Intent intent = new Intent(login.this, CreateAccount.class);
                                    Toast.makeText(login.this, "يجب انشاء حساب", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                            }
                        }
                    });


                }
            }
        });

    }

    protected void onStart() {
        super.onStart();
        //Auto Login
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        if (mAuth!=null){
            Intent intent =  new Intent(login.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }




    public void to_create_account(View view) {
        Intent acount = new Intent(login.this, CreateAccount.class);
        startActivity(acount);
    }

}
