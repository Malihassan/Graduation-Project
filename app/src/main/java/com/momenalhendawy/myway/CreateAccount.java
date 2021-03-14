package com.momenalhendawy.myway;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.Date;
//import com.google.firebase.firestore.FirebaseFirestore;

public class CreateAccount extends AppCompatActivity {
        private static Button registaccount ;
        private static TextView firstname ;
        private static TextView secondname ;
        private static TextView phonenumber ;
        private static TextView password ;
    private RadioGroup radioSexGroup;
    private static final String TAG = "MainActivity";

    String fn,ln,ph ,phone ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        registaccount = findViewById(R.id.createaccount_submit);
        registaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname =(TextView) findViewById(R.id.createaccount_firstname);
                 fn =  firstname.getText().toString();
                secondname = (TextView) findViewById(R.id.createaccount_lastname);
                 ln = secondname.getText().toString();
                phonenumber = (TextView) findViewById(R.id.createaccount_phonenumber);
               ph = phonenumber.getText().toString().trim();

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
                else {
                    DocumentReference docIdRef = db.collection("Users").document(ph);
                    docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot documentph = task.getResult();
                                if (documentph.exists()) {
                                    Toast.makeText(CreateAccount.this, "هذا الحساب مووجود من قبل يرجى التاكد من من رقم الهاتف ", Toast.LENGTH_SHORT).show();

                                }else {
                                    Client_firebase newaccount = new Client_firebase();
                                    newaccount.setFirstName(fn);
                                    newaccount.setLastName(ln);
                                    newaccount.setPhoneNumber(ph);
                                    newaccount.setEmail("");
                                    radioSexGroup = (RadioGroup)findViewById(R.id.gender);
                                    int selectedId = radioSexGroup.getCheckedRadioButtonId();
                                    if (selectedId == R.id.Male)
                                    {
                                        newaccount.setGender("ذكر");
                                    }
                                    else if (selectedId == R.id.Female)
                                    {
                                        newaccount.setGender("انثى");
                                    }
                                    newaccount.setCountry("Assuit");
                                    newaccount.setName(fn+" "+ln);
                                    db.collection("Users").document(ph).set(newaccount).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Sendtouserlocation mUserLocation = new Sendtouserlocation();
                                                GeoPoint geoPoint = new GeoPoint(0,0);
                                                mUserLocation.setGeo_point(geoPoint);
                                                mUserLocation.setSrart_Time(new com.google.firebase.Timestamp(new Date()));
                                                db.collection("User Locations").document(ph).set(mUserLocation).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Log.d(TAG, "create in user location success");
                                                        }
                                                    }
                                                });
                                                Toast.makeText(CreateAccount.this, "تم تسجيل بنجاح.", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(CreateAccount.this, login.class);
                                                startActivity(intent);
                                            }else {
                                                Toast.makeText(CreateAccount.this, "حدث خطأ اثناء العملية 🙁 ", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                }
                            }
                        }
                    });
                }
            }
        });
    }

}
