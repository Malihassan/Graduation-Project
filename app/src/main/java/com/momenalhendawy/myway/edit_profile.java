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
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class edit_profile extends AppCompatActivity {
    private static Button saveprofileedit;
    private EditText Firstname;
    private EditText Secondname;
    private EditText Email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String,String> map =dataSnapshot.getValue(Map.class);
                String firstname =map.get("firstName");
                String secondname =map.get("lastName");
                String email =map.get("email");
                String password =map.get("password");

                Log.v("E_VALUE","FirstName");
                Log.v("E_VALUE","LastName");
                Log.v("E_VALUE","Email");
                Log.v("E_VALUE","Password");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Firstname =(EditText)findViewById(R.id.EditFirstName);
        Secondname =(EditText)findViewById(R.id.EditSecondName);
        Email =(EditText)findViewById(R.id.EditEmail);
        password =(EditText)findViewById(R.id.EditPassword);

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