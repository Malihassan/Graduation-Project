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
public class requestzeet_details extends Fragment {

    private static ListView oderlist;
    public String Do="";
    public String IDo="";
    public String Io="";
    String[] z;
    public ArrayList<String> OilOrderDescribe = new ArrayList<String>();
    public ArrayList<String> OilOrderID = new ArrayList<String>();
    public ArrayList<String> OilOrderImage = new ArrayList<String>();



    public requestzeet_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_requestzeet_details, container, false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db
                .collection("Products")
                .document("grocery")
                .collection("Oil")
                .document("Fat and Oil");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                final ArrayList<requestorders> orders = new ArrayList<requestorders>();
                requestorder_adapter adapter = new requestorder_adapter(getContext(), orders);
                // Log.d(TAG, "======>>>what is here ::cola");
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

                            Do = z[1];
                            IDo = z[0];
                            Io = z[2];
                            OilOrderDescribe.add(Do);
                            OilOrderID.add(IDo);
                            OilOrderImage.add(Io);
                           Log.d(TAG, "======>>>: " + Do + "--------->" + IDo + "*******>" + Io);

                        }
                    }
                    //         Log.d(TAG, "======>>>what is here ::not exist");
                    for (int x=0 ;x<OilOrderID.size();x++)
                    {
                        orders.add(new requestorders(OilOrderDescribe.get(x), OilOrderImage.get(x)));
                    }

                    User_singleton.getInstance().setOilOrderDescribe(OilOrderDescribe);
                    User_singleton.getInstance().setOilOrderID(OilOrderID);
                    User_singleton.getInstance().setPage("Oil");
                    User_singleton.getInstance().setSizeofcategoryoil(OilOrderID.size());

                    oderlist = v.findViewById(R.id.zet_supermarket_category);
                    oderlist.setAdapter(adapter);
                    oderlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            //int value =User_singleton.getInstance().getValue_of_products();
                            //Log.d(TAG, "======>>>: " + position + "--------->" + arrOrderDescribe.get(position) + "*******>" + value);

                        }
                    });
                }

            }
        });
        return v;
        }

}
