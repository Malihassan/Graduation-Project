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
public class requestmkrona_details extends Fragment {
    private static ListView oderlist;

    public String Do="";
    public String IDo="";
    public String Io="";

    String[] z;
    public ArrayList<String> RiceOrderDescribe = new ArrayList<String>();
    public ArrayList<String> RiceOrderID = new ArrayList<String>();
    public ArrayList<String> RiceOrderImage = new ArrayList<String>();

    public requestmkrona_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_requestmkrona_details, container, false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db
                .collection("Products")
                .document("grocery")
                .collection("Rice and pasta")
                .document("Rice and Pasta");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                final ArrayList<requestorders> orders = new ArrayList<requestorders>();
                requestorder_adapter adapter = new requestorder_adapter(getContext(), orders);
                 Log.d(TAG, "======>>>what is here ::cola");

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
                            RiceOrderDescribe.add(Do);
                            RiceOrderID.add(IDo);
                            RiceOrderImage.add(Io);
                             Log.d(TAG, "======>>>: " + Do + "--------->" + IDo + "*******>" + Io);

                        }
                    }else
                            Log.d(TAG, "======>>>what is here ::not exist");
                    for (int x=0 ;x<RiceOrderID.size();x++)
                    {
                        orders.add(new requestorders(RiceOrderDescribe.get(x), RiceOrderImage.get(x)));
                    }


                    User_singleton.getInstance().setRiceOrderDescribe(RiceOrderDescribe);
                    User_singleton.getInstance().setRiceOrderID(RiceOrderID);
                    User_singleton.getInstance().setPage("Rice");
                    User_singleton.getInstance().setSizeofcategoryrice(RiceOrderID.size());

                    Log.d(TAG, " all Rice "+RiceOrderDescribe);


                    oderlist = v.findViewById(R.id.mkaronacategoryorder);
                    oderlist.setAdapter(adapter);
                    oderlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {






                        }
                    });
                }

            }
        });

        return v;
    }

}
