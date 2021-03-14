package com.momenalhendawy.myway;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.StateSet.TAG;



public class Cancel_Order_Question extends AppCompatActivity {
        private static Button save ;
        private CheckBox Ques1,Ques2,Ques3 ;
        String q1="";
        String ph ;
        List <String> documents_id =new ArrayList<>();
        FirebaseFirestore db =  FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel__order__question);

        Ques1 = findViewById(R.id.Ques1);
        Ques2 = findViewById(R.id.Ques2);
        Ques3 = findViewById(R.id.Ques3);

        Ques1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                  q1=q1+" , "+ Ques1.getText();
                    Log.d(TAG, "onClick: "+q1);
                }
            }
        });
        Ques2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    q1=q1+" , "+ Ques2.getText();
                    Log.d(TAG , "onClick: "+q1);
                }
            }
        });
        Ques3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    q1=q1+" , "+ Ques3.getText();
                    Log.d(TAG, "onClick: "+q1);
                }
            }
        });
        save = findViewById(R.id.Send_Cancel_Order);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cancel_Order_Question.this, MainActivity.class);
                ph= User_singleton.getInstance().getNumber();
                String sessionId = User_singleton.getInstance().getPosition_waiting_order();
                final int pos = Integer.parseInt(sessionId);
                documents_id = User_singleton.getInstance().getDocument_id();
                DocumentReference washingtonRef =
                        db.collection("Order")
                        .document(ph).collection("Order User")
                        .document(documents_id.get(pos));
                washingtonRef
                        .update("canceling",true,"reason_for_canceling_the_request",q1)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error updating document", e);
                            }
                        });
                startActivity(intent);
                Toast.makeText(Cancel_Order_Question.this, "تم!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
