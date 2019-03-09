package com.momenalhendawy.myway;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class edit_profile extends AppCompatActivity {
    private static Button saveprofileedit;
    private EditText Firstname;
    private EditText Secondname;
    private EditText Email;
    private EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Firstname = (EditText)findViewById(R.id.EditFirstName);
        final String fr_n = Firstname.getText().toString();

        Secondname = (EditText)findViewById(R.id.EditSecondName);
        String se_n = Secondname.getText().toString();

        Email = (EditText)findViewById(R.id.EditEmail);
        String mail = Email.getText().toString();

        Password = (EditText)findViewById(R.id.EditPassword);
        String pass = Password.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference();
        ref.child("Client").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( com.google.firebase.database.DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Map<String, String> map = (Map) postSnapshot.getValue();
                    if (map != null) {
                        String firstname =map.get("firstName");
                        Firstname.setText(firstname);
                        String secondname =map.get("lastName");
                        Secondname.setText(secondname);
                        String email =map.get("email");
                        Email.setText(email);
                        String password =map.get("password");
                        Password.setText(password);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveprofileedit = findViewById(R.id.saveiditprofile);
        saveprofileedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(edit_profile.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(edit_profile.this, "تم العملية !", Toast.LENGTH_SHORT).show();
            }
        });

    }
}