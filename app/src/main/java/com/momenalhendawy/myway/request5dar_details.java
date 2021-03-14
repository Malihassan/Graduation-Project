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
public class request5dar_details extends Fragment {
    private static ListView oderlist;


    public String Do="";
    public String IDo="";
    public String Io="";
    String[] z;
    public ArrayList<String> VegetableOrderDescribe = new ArrayList<String>(10);
    public ArrayList<String> VegetableOrderID = new ArrayList<String>(10);
    public ArrayList<String> VegetableOrderImage = new ArrayList<String>(10);


    public request5dar_details() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_request5dar_details, container, false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db
                .collection("Products")
                .document("green_grocery");
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
                            VegetableOrderDescribe.add(Do);
                            VegetableOrderID.add(IDo);
                            VegetableOrderImage.add(Io);
                            Log.d(TAG, "======>>>: " + Do + "--------->" + IDo + "*******>" + Io);

                        }
                    }else
                        Log.d(TAG, "======>>>what is here ::not exist");
                    for (int x=0 ;x<VegetableOrderID.size();x++)
                    {
                        orders.add(new requestorders(VegetableOrderDescribe.get(x), VegetableOrderImage.get(x)));
                    }
                    User_singleton.getInstance().setVegetableOrderDescribe(VegetableOrderDescribe);
                    User_singleton.getInstance().setVegetableOrderID(VegetableOrderID);
                    User_singleton.getInstance().setPage("Vegetable");
                    User_singleton.getInstance().setSizeofcategoryvegetable(VegetableOrderDescribe.size());



                    oderlist = v.findViewById(R.id.r5dar_supermarket_category);
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
