package com.momenalhendawy.myway;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * A simple {@link Fragment} subclass.
 */
public class fregmant_profile extends Fragment {
    private static Button saveprofileedit;
    private EditText Firstname;
    private EditText Secondname;
    private EditText Email;

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;

    String fn,ln,setmail,mobile,gend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v   = inflater.inflate(R.layout.fregmant_profile, container, false);

        Firstname = (EditText)v.findViewById(R.id.EditFirstName);

        Secondname = (EditText)v.findViewById(R.id.EditSecondName);

        Email = (EditText)v.findViewById(R.id.EditEmail);


        final FirebaseFirestore db = FirebaseFirestore.getInstance();


        mobile = User_singleton.getInstance().getNumber();

        radioSexGroup = (RadioGroup) v.findViewById(R.id.gender);


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
                        gend = (String) document.get("gender");

                        Firstname.setText(fn);
                        Secondname.setText(ln);
                        Email.setText(setmail);

                        if (gend.equals("ذكر")){radioSexGroup.check(R.id.Male);}
                        if (gend.equals("انثى")){radioSexGroup.check(R.id.Female);}

                    } else {
                        Toast.makeText(getContext(), "حدث خطأ ونأسف على ذلك", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    task.getException();
                }
            }
        });
        saveprofileedit = v.findViewById(R.id.saveiditprofile);
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
                newaccount.setName(fr_n+" "+se_n);

                // get selected radio button from radioGroup
                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                if (selectedId == R.id.Male)
                {
                   newaccount.setGender("ذكر");
                }
                else if (selectedId == R.id.Female)
                {
                    newaccount.setGender("انثى");
                }
                db.collection("Users").document(mobile).set(newaccount).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getContext(), "تم تعديل البيانات بنجاح.", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "حدث خطأ اثناء العملية 🙁 ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
