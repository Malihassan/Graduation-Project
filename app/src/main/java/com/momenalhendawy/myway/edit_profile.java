package com.momenalhendawy.myway;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class edit_profile extends AppCompatActivity {
    private static Button saveprofileedit;
    private EditText Firstname;
    private EditText Secondname;
    private EditText Email;

    String fn,ln,setmail,mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Firstname = (EditText)findViewById(R.id.EditFirstName);

        Secondname = (EditText)findViewById(R.id.EditSecondName);

        Email = (EditText)findViewById(R.id.EditEmail);


        final FirebaseFirestore db = FirebaseFirestore.getInstance();


         mobile = User_singleton.getInstance().getNumber();

        DocumentReference docRef = db.collection("Users").document(mobile);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        fn =(String)document.get("firstName");
                        ln =(String) document.get("lastName");
                        setmail =(String) document.get("email");

                        Firstname.setText(fn);
                        Secondname.setText(ln);
                        Email.setText(setmail);
                    } else {
                        Toast.makeText(edit_profile.this, "حدث خطأ ونأسف على ذلك", Toast.LENGTH_SHORT).show();

                    }
                } else {
                     task.getException();
                }
            }
        });
        saveprofileedit = findViewById(R.id.saveiditprofile);
        saveprofileedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                Client_firebase newaccount = new Client_firebase();

                 String fr_n = Firstname.getText().toString();
                 String se_n = Secondname.getText().toString();
                 String mail = Email.getText().toString();

                newaccount.setFirstName(fr_n);
                newaccount.setLastName(se_n);
                newaccount.setEmail(mail);
                newaccount.setPhoneNumber(mobile);
                newaccount.setCountry("Assuit");

                db.collection("Users").document(mobile).set(newaccount).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(edit_profile.this, "تم تعديل البيانات بنجاح.", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(edit_profile.this, "حدث خطأ اثناء العملية 🙁 ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Intent intent = new Intent(edit_profile.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}