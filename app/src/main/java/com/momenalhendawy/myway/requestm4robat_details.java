package com.momenalhendawy.myway;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

import static androidx.constraintlayout.widget.StateSet.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class requestm4robat_details extends Fragment {

    private  ListView oderlist;

    public String Do="";
    public String IDo="";
    public String Io="";
    String[] z;
    public ArrayList<String> DrinkOrderDescribe = new ArrayList<String>();
    public ArrayList<String> DrinkOrderID = new ArrayList<String>();
    public ArrayList<String> DrinkOrderImage = new ArrayList<String>();




    public requestm4robat_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_requestm4robat_details, container, false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db
                .collection("Products")
                .document("grocery")
                .collection("Drinks ")
                .document("cola");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
               // Log.d(TAG, "======>>>what is here ::cola");

                final ArrayList<requestorders> orders = new ArrayList<requestorders>();
                requestorder_adapter adapter = new requestorder_adapter(getContext(), orders);

                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> map = document.getData();
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            Map<String, Object> m = (Map<String, Object>) entry.getValue();
                            StringBuilder s = new StringBuilder(100);
                            for (Map.Entry<String, Object> e : m.entrySet()) {
                                s.append(e.getValue() + " ");
                            }
                            z = s.toString().split(" ");

                            Do = z[0];
                            IDo = z[1];
                            Io = z[2];
                            DrinkOrderDescribe.add(Do);
                            DrinkOrderID.add(IDo);
                            DrinkOrderImage.add(Io);
                            Log.d(TAG, "======>>>: " + Do + "--------->" + IDo + "*******>" + Io);
                        }
                    }
           //         Log.d(TAG, "======>>>what is here ::not exist");

                    for (int x=0 ;x<DrinkOrderID.size();x++)
                    {
                        orders.add(new requestorders(DrinkOrderDescribe.get(x), DrinkOrderImage.get(x)));
                    }

                    User_singleton.getInstance().setDrinkOrderDescribe(DrinkOrderDescribe);
                    User_singleton.getInstance().setDrinkOrderID(DrinkOrderID);

                    User_singleton.getInstance().setPage("Drinks");
                    User_singleton.getInstance().setSizeofcategorydrink(DrinkOrderID.size());


                   Log.d(TAG, " all drink "+DrinkOrderDescribe);


                    oderlist = v.findViewById(R.id.m4robat_supermarket_category);

                    oderlist.setAdapter(adapter);
                    oderlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> list, View v, final int pos, long id) {

                        }
                    });
                }
            }
        });
        return v;

    }

}

