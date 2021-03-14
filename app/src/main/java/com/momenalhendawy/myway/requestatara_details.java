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
public class requestatara_details extends Fragment {
    private static ListView oderlist;

    public String Do="";
    public String IDo="";
    public String Io="";
    String[] z;
    public ArrayList<String> SpiceOrderDescribe = new ArrayList<String>(10);
    public ArrayList<String> SpiceOrderID = new ArrayList<String>(10);
    public ArrayList<String> SpiceOrderImage = new ArrayList<String>(10);

    public requestatara_details() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_requestatara_details, container, false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db
                .collection("Products")
                .document("attar");
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
                            SpiceOrderDescribe.add(Do);
                            SpiceOrderID.add(IDo);
                            SpiceOrderImage.add(Io);
                            Log.d(TAG, "======>>>: " + Do + "--------->" + IDo + "*******>" + Io);

                        }
                    }else
                        Log.d(TAG, "======>>>what is here ::not exist");
                    for (int x=0 ;x<SpiceOrderID.size();x++)
                    {
                        orders.add(new requestorders(SpiceOrderDescribe.get(x), SpiceOrderImage.get(x)));
                    }

                    Log.d(TAG, "======>>>: " +  SpiceOrderDescribe+ "--------->" + SpiceOrderID );

                    User_singleton.getInstance().setSpiceOrderDescribe(SpiceOrderDescribe);
                    User_singleton.getInstance().setSpiceOrderID(SpiceOrderID);
                    User_singleton.getInstance().setPage("Spice");
                    User_singleton.getInstance().setSizeofcategoryspice(SpiceOrderDescribe.size());


                    oderlist = v.findViewById(R.id.Atera_supermarket_category);
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
