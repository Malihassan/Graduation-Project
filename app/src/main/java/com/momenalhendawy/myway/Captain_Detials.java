package com.momenalhendawy.myway;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import static androidx.constraintlayout.widget.StateSet.TAG;


import java.util.ArrayList;
import java.util.List;

public class Captain_Detials extends AppCompatActivity {
    private static Button cancelorder;
    private ImageView captainimage;
    private TextView phonenumber;
    private TextView show_order_details;
    private RatingBar ratingBar1;
    private RatingBar service_rating;
    public FirebaseFirestore db;

    public ArrayList<String> arrcaptainpicture = new ArrayList<String>();
    public ArrayList<String> arrcaptainphone= new ArrayList<String>();
    public ArrayList<String> arrcaptainrateing= new ArrayList<>();

    List<String> list = new ArrayList<>();
    float rating ;
    boolean state =true ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captain__detials);

        arrcaptainpicture = User_singleton.getInstance().getArrcaptainpicture();
        arrcaptainphone = User_singleton.getInstance().getArrcaptainphone();
        arrcaptainrateing = User_singleton.getInstance().getArrcaptainrate();

        String sessionId= User_singleton.getInstance().getPosition_waiting_order();
        final int pos = Integer.parseInt(sessionId);

        //Log.d(TAG, "*/*/*/*/*/.>>>"+pos+"====>"+arrcaptainpicture.get(pos)+"---->"+arrcaptainphone.get(pos));

        captainimage = (ImageView)findViewById(R.id.userImage);
        phonenumber = (TextView)findViewById(R.id.phoneacptain);
        show_order_details = (TextView)findViewById(R.id.order_items);


        Glide.with(this)
                .load(arrcaptainpicture.get(pos))
                .thumbnail(Glide.with(this)
                        .load(arrcaptainpicture.get(pos)))
                .into(captainimage);

        phonenumber.setText("رقم الكابتن: "+arrcaptainphone.get(pos));

         list = User_singleton.getInstance().getBasket();
         Log.d(TAG, "onCreate: "+list);

        show_order_details.setText(list.get(pos));

        double x= Double.parseDouble(arrcaptainrateing.get(pos));
        float d= (float) ((x*5));
        ratingBar1 = (RatingBar) findViewById(R.id.ratingBar5);
        ratingBar1.setRating(d);

        service_rating = (RatingBar) findViewById(R.id.service_rating);

            service_rating.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    rating = service_rating.getRating();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    DocumentReference Update = db.collection("Users").document(User_singleton.getInstance().getNumber());
                    Update.update("rate", rating)
                            .addOnSuccessListener(new OnSuccessListener< Void >() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Captain_Detials.this, "شكرا لتعاونكم معنا ", Toast.LENGTH_SHORT).show();
                                }
                            });
                    return service_rating.onTouchEvent(event);

                }
            });



        cancelorder = findViewById(R.id.Cancel_oder);
        cancelorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Captain_Detials.this, Cancel_Order_Question.class);
                startActivity(intent);
            }
        });
    }
    }