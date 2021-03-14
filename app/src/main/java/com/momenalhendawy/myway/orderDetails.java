package com.momenalhendawy.myway;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static androidx.constraintlayout.widget.StateSet.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class orderDetails extends Fragment {
    private  ListView oderlist;

    private TextView CaptainName;
    private TextView OrderName;
    private ImageView CaptainImage;

    String mobile;
    public String cn="";
    public String on="";
    public String cp="";
    public String cphone="";
    public static int m  ;

    String[] z;
    public  ArrayList<String> arrcaptainname = new ArrayList<>();
    public  ArrayList<String> arrcaptainpicture = new ArrayList<>();
    public ArrayList<String> arrcaptainphone = new ArrayList<>();
    public ArrayList<String> rataingcaptain = new ArrayList<>();

    public Task<DocumentSnapshot> docRef ;


    public orderDetails() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_order_details, container, false);

        mobile = User_singleton.getInstance().getNumber();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final FirebaseFirestore db_details = FirebaseFirestore.getInstance();
        db.collection("Order").document(mobile)
                .collection("Order User").whereEqualTo("accept",true)
                .whereEqualTo("canceling",false)
                .whereEqualTo("finished",false)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull final Task<QuerySnapshot> task) {
                        final ArrayList<order_M> orders = new ArrayList<order_M>();
                         final order_adapter adapter = new order_adapter(getContext(), orders);

                        if (task.isSuccessful()) {
                            final List<String> phonenumberlist = new ArrayList<>();
                            final List<String> list_Basket = new ArrayList<>();
                            final List<String> document_id = new ArrayList<>();
                            final List<String> Basket = new ArrayList<>();


                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                document_id.add(document.getId());
                                Log.d(TAG, "onComplete: " + document.get("Basket"));

                                Map<String, Object> map_basket = (Map<String, Object>) document.get("Basket");
                                if (map_basket != null) {
                                    for (Map.Entry<String, Object> entry : map_basket.entrySet()) {
                                        list_Basket.add(entry.getValue().toString());
                                    }

                                }

                                        phonenumberlist.add((String) document.get("captainPhone"));

                            }

                            for (int x= 0 ;x < list_Basket.size() ;x++) {
                                Log.d(TAG, "list : " + list_Basket.get(x));
                            }
                            int x,y ;
                            for ( x = 1 , y = 3; x < list_Basket.size() && y < list_Basket.size() ; x = x + 5 , y=y+5)
                            {
                                String i = list_Basket.get(x);
                                String v = list_Basket.get(y);
                                Log.d(TAG, "output:  x "+x+"==>"+i+" y "+v+"==>"+y);
                                Basket.add("items order "+i+" value "+v);
                            }

                            Collections.reverse(Basket);
                            Log.d(TAG, "onComplete: "+Basket);
                            Collections.reverse(phonenumberlist);
                            Log.d(TAG, "onComplete: "+phonenumberlist);
                            Log.d(TAG, "onComplete: "+document_id);
                            User_singleton.getInstance().setBasket(Basket);
                            User_singleton.getInstance().setDocument_id(document_id);

                            for( m=0;m < phonenumberlist.size();m++) {
                                docRef = db_details.collection("Captains").document("+2" + phonenumberlist.get(m))
                                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {

                                            DocumentSnapshot document = task.getResult();
                                            arrcaptainname.add((String) document.get("name"));
                                            arrcaptainpicture.add((String) document.get("imageURl"));
                                            arrcaptainphone.add((String) document.get("phone"));

//                                            Log.d(TAG, "onComplete: gggggggg"+(Integer) document.get("rate"));

                                            rataingcaptain.add((String) document.get("rate").toString());

                                            orders.add(new order_M((String) document.get("name")
                                                    ,(String) document.get("phone")
                                                    , (String) document.get("imageURl")));

                                            User_singleton.getInstance().setArrcaptainame(arrcaptainname);
                                            User_singleton.getInstance().setArrcaptainpicture(arrcaptainpicture);
                                            User_singleton.getInstance().setArrcaptainphone(arrcaptainphone);
                                            User_singleton.getInstance().setArrcaptainrate(rataingcaptain);

                                            oderlist = v.findViewById(R.id.listorder);
                                            oderlist.setAdapter(adapter);
                                            oderlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                    Log.d(TAG, "onItemClick: first " + position);
                                                    Intent intent = new Intent(getActivity(), Captain_Detials.class);
                                                    String pos =String.valueOf(position);
                                                    User_singleton.getInstance().setPosition_waiting_order(pos);
                                                    startActivity(intent);
                                                }
                                            });
                                        } else {
                                            task.getException();
                                        }
                                    }
                                });

                            }
                        }
                        else {
                           Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                    }
                });
        return v;
    }
}