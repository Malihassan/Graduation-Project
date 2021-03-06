package com.momenalhendawy.myway;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

public class verify_create_acount extends AppCompatActivity {

    //These are the objects needed
    //It is the verification id that will be sent to the user



    private static final String TAG = "FirebasePhoneNumAuth";


    //The edittext to input the code
    private EditText editTextCode;
    private static Button submit ;
    private FirebaseAuth mAuth;
    public String mVerificationId;

    final FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_create_acount);

        editTextCode = findViewById(R.id.verifyacount_code);
        mAuth = FirebaseAuth.getInstance();
        final String mobile = getIntent().getExtras().getString("PhoneNumber");



        sendVerificationCode(mobile);

        submit = (Button)findViewById(R.id.verifyaccount_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCode.getText().toString();
                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.setError("Enter valid code");
                    editTextCode.requestFocus();
                    return;
                }
                verifyVerificationCode(code);
            }
        });

    }

    //the method is sending verification code
    //the country id is concatenated
    //you can take the country id as user input as well
    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                 mobile,
                70,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }
    //the callback to detect the verification status

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                //Getting the code sent by SMS
                 String code = phoneAuthCredential.getSmsCode();

                //sometime the code is not detected automatically
                //in this case the code will be null
                //so user has to manually enter the code
            if (code != null) {
                editTextCode.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(verify_create_acount.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                //storing the verification id that is sent to the user
                mVerificationId = s;
                Log.d(TAG, "code sent " + mVerificationId);

            }
        };

    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);


        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(verify_create_acount.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activity

                            Intent intent = new Intent(verify_create_acount.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {

                            //verification unsuccessful.. display an error message

                           String message = "?????? ?????? ?????????? ?????? ???????????? ...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "?????????? ?????? ???????? ...";
                                editTextCode.setError("Invalid code.");
                            }
                        }
                    }
                });
        }

    }
