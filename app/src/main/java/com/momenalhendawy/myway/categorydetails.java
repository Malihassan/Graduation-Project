package com.momenalhendawy.myway;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.iid.FirebaseInstanceId;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import androidx.fragment.app.Fragment;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class categorydetails extends Fragment {
    private static ListView oderlist;
    private static Button send ;

    String page;
    boolean accept ;

    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();



    public ArrayList<String> BasketOrderDescribe = new ArrayList<>();
    public ArrayList<String> BasketOrderID = new ArrayList<>();
    public ArrayList<Integer> BasketOrderValue = new ArrayList<>();



    public SparseArray<String> DrinkOrderDescribe = new SparseArray<>();
    public SparseArray<String> DrinkOrderID = new SparseArray<>();
    public SparseArray<Integer> DrinkOrderValue = new SparseArray<>();

    public SparseArray<String> OilOrderDescribe = new SparseArray<>();
    public SparseArray<String> OilOrderID = new SparseArray<>();
    public SparseArray<Integer> OilOrderValue = new SparseArray<>();

    public SparseArray<String> RiceOrderDescribe = new SparseArray<>();
    public SparseArray<String> RiceOrderID = new SparseArray<>();
    public SparseArray<Integer> RiceOrderValue = new SparseArray<>();

    public SparseArray<String> CleaningOrderDescribe = new SparseArray<>();
    public SparseArray<String> CleaningOrderID = new SparseArray<>();
    public SparseArray<Integer> CleaningOrderValue = new SparseArray<>();

    public SparseArray<String> VegetableOrderDescribe = new SparseArray<>();
    public SparseArray<String> VegetableOrderID = new SparseArray<>();
    public SparseArray<Integer> VegetableOrderValue = new SparseArray<>();

    public SparseArray<String> SpiceOrderDescribe = new SparseArray<>();
    public SparseArray<String> SpiceOrderID = new SparseArray<>();
    public SparseArray<Integer> SpiceOrderValue = new SparseArray<>();

    public categorydetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_categorydetails, container, false);

        send =(Button) v.findViewById(R.id.sendorder);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                DrinkOrderDescribe = User_singleton.getInstance().getDrinkItem();
                DrinkOrderID = User_singleton.getInstance().getDrinkItemID();
                DrinkOrderValue = User_singleton.getInstance().getDrinkItemValues();

                OilOrderDescribe = User_singleton.getInstance().getOilItem();
                OilOrderID = User_singleton.getInstance().getOilItemID();
                OilOrderValue = User_singleton.getInstance().getOilItemValues();

                RiceOrderDescribe = User_singleton.getInstance().getRiceItem();
                RiceOrderID = User_singleton.getInstance().getRiceItemID();
                RiceOrderValue = User_singleton.getInstance().getRiceItemValues();

                CleaningOrderDescribe = User_singleton.getInstance().getCleaningItem();
                CleaningOrderID = User_singleton.getInstance().getCleaningItemID();
                CleaningOrderValue = User_singleton.getInstance().getCleaningtemValues();

                VegetableOrderDescribe = User_singleton.getInstance().getVegetableItem();
                VegetableOrderID = User_singleton.getInstance().getVegetableItemID();
                VegetableOrderValue = User_singleton.getInstance().getVegetabletemValues();

                SpiceOrderDescribe = User_singleton.getInstance().getSpiceItem();
                SpiceOrderID = User_singleton.getInstance().getSpiceItemID();
                SpiceOrderValue = User_singleton.getInstance().getSpicetemValues();

                final Intent intent = new Intent(getActivity(), MainActivity.class);

                Log.d(TAG, "des Dri"+DrinkOrderDescribe);
                Log.d(TAG, "des rice"+RiceOrderDescribe);



                if (DrinkOrderID != null && DrinkOrderID.size() != 0 ) {
                    Log.d(TAG, "add drink      ,drink id"+DrinkOrderID.size() );
                    // for (int i = 0; i < DrinkOrderID.size(); i++) {
                    int count =0;
                    while (count < User_singleton.getInstance().getSizeofcategorydrink()){

                        BasketOrderDescribe.add(DrinkOrderDescribe.get(count));
                        BasketOrderID.add(DrinkOrderID.get(count));
                        BasketOrderValue.add(DrinkOrderValue.get(count));

                        count++ ;
                    }

                    //}
                    Log.d(TAG, "======>>>: " + BasketOrderDescribe );
                    Log.d(TAG, "--------->" + BasketOrderID );
                    Log.d(TAG,   "*******>" + BasketOrderValue);
                }
                if (RiceOrderID != null && RiceOrderID.size() != 0 ) {
                    Log.d(TAG, "add rice" );
                    Log.d(TAG, "add drink      ,drink id"+RiceOrderID.size() );
                    int count =0;
                    while (count < User_singleton.getInstance().getSizeofcategoryrice()){
                        BasketOrderDescribe.add(RiceOrderDescribe.get(count));
                        BasketOrderID.add(RiceOrderID.get(count));
                        BasketOrderValue.add(RiceOrderValue.get(count));
                        count++;
                    }
                    Log.d(TAG, "======>>>: " + BasketOrderDescribe );
                    Log.d(TAG, "--------->" + BasketOrderID );
                    Log.d(TAG,   "*******>" + BasketOrderValue);
                }
                if (OilOrderID != null && OilOrderID.size() != 0) {
                    int count =0;
                    while (count < User_singleton.getInstance().getSizeofcategoryoil()){
                        BasketOrderDescribe.add(OilOrderDescribe.get(count));
                        BasketOrderID.add(OilOrderID.get(count));
                        BasketOrderValue.add(OilOrderValue.get(count));

                        count++;
                    }
                    Log.d(TAG, "======>>>: " + BasketOrderDescribe );
                    Log.d(TAG, "--------->" + BasketOrderID );
                    Log.d(TAG,   "*******>" + BasketOrderValue);
                }

                if (CleaningOrderID != null && CleaningOrderID.size() != 0) {
                    int count =0;
                    while (count < User_singleton.getInstance().getSizeofcategoryclean()){
                        BasketOrderDescribe.add(CleaningOrderDescribe.get(count));
                        BasketOrderID.add(CleaningOrderID.get(count));
                        BasketOrderValue.add(CleaningOrderValue.get(count));

                        count++;
                    }
                    Log.d(TAG, "======>>>: " + BasketOrderDescribe );
                    Log.d(TAG, "--------->" + BasketOrderID );
                    Log.d(TAG,   "*******>" + BasketOrderValue);
                }
                if (VegetableOrderID != null && VegetableOrderID.size() != 0) {
                    Log.d(TAG, "onClick:  basket Vegetable "+User_singleton.getInstance().getVegetableItem()+"indexes "
                            +User_singleton.getInstance().getSizeofcategoryvegetable());
                    int count =0;
                    while (count < User_singleton.getInstance().getSizeofcategoryvegetable() ){

                        BasketOrderDescribe.add(VegetableOrderDescribe.get(count));
                        BasketOrderID.add(VegetableOrderID.get(count));
                        BasketOrderValue.add(VegetableOrderValue.get(count));
                        Log.d(TAG, "onClick: " +BasketOrderDescribe);
                        count++ ;
                    }
                    Log.d(TAG, "======>>>: " + BasketOrderDescribe );
                    Log.d(TAG, "--------->" + BasketOrderID );
                    Log.d(TAG,   "*******>" + BasketOrderValue);
                }
                if (SpiceOrderID != null && SpiceOrderID.size() != 0) {
                    Log.d(TAG, "add Spice" );
                    User_singleton.getInstance().getSizeofcategoryspice() ;
                    int count =0;
                    while (count < User_singleton.getInstance().getSizeofcategoryspice() ){
                        BasketOrderDescribe.add(SpiceOrderDescribe.get(count));
                        BasketOrderID.add(SpiceOrderID.get(count));
                        BasketOrderValue.add(SpiceOrderValue.get(count));

                        count++;
                    }
                    Log.d(TAG, "======>>>: " + BasketOrderDescribe );
                    Log.d(TAG, "--------->" + BasketOrderID );
                    Log.d(TAG,   "*******>" + BasketOrderValue);
                }
                Log.d(TAG, "onClick: "+BasketOrderDescribe.size());
                int nullcount=0;
                while (nullcount <= BasketOrderDescribe.size()+1) {
                    BasketOrderDescribe.remove(null);
                    BasketOrderID.remove(null);
                    BasketOrderValue.remove(null);
                    Log.d(TAG,   "*******>" + BasketOrderValue);
                    nullcount ++ ;
                }
                Log.d(TAG, "onClick: "+BasketOrderID.size());

                User_singleton.getInstance().setBasketOrderDescribe(BasketOrderDescribe);
                User_singleton.getInstance().setBasketOrderID(BasketOrderID);
                User_singleton.getInstance().setBasketOrderValue(BasketOrderValue);


                Log.d(TAG, "=====>"+BasketOrderDescribe);
                Log.d(TAG, "onClick: "+"====>>"+BasketOrderID);
                Log.d(TAG, "onClick: "+"=-=->"+BasketOrderValue);



/*
                if (BasketOrderID.size() != 0) {
                    String ph = User_singleton.getInstance().getNumber();
                    final FirebaseFirestore db = FirebaseFirestore.getInstance();
                    final DocumentReference docIdRef = db.collection("Order").document(ph);
                    final DocumentReference doccreate = db.collection("Order").document(ph);
                    final String x = doccreate.collection("Order User").document().getId();

                        Log.d(TAG, "==== >"+BasketOrderDescribe+"====>>"+BasketOrderID+"=-=->"+BasketOrderValue);

                    Log.d(TAG, "document ===========>>>>>> " + x);
                    docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                Basket_Order orders = new Basket_Order();
                                orders.setTokenID(FirebaseInstanceId.getInstance().getToken());
                                orders.setOrder_Name("order_2");
                                orders.setOrder_Item(BasketOrderDescribe);
                                orders.setItem_ID(BasketOrderID);
                                orders.setOrder_Values(BasketOrderValue);
                                Map<String, Object> Basket = new HashMap<>();
                                Basket.put("Basket", orders);
                                docIdRef.collection("Order User").document(x).set(Basket).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d(TAG, " successfully written with new user Basket !");
                                            Captain_order captain_order = new Captain_order();
                                            captain_order.setPhone_Number("01026");
                                            Map<String, Object> Captain = new HashMap<>();
                                            Captain.put("Captain Details", captain_order);
                                            docIdRef.collection("Order User").document(x).set(Captain, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Send_order order = new Send_order();
                                                        order.setAccept(false);
                                                        order.setOrder_ID(x);
                                                        order.setSrart_Time(new com.google.firebase.Timestamp(new Date()));
                                                        order.setReason_for_canceling_the_request("");
                                                        order.setCanceling(false);
                                                        docIdRef.collection("Order User").document(x).set(order, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    Log.d(TAG, " successfully written with new user Basket !");
                                                                    User_singleton.getInstance().setBasketOrderDescribe(BasketOrderDescribe);
                                                                    User_singleton.getInstance().setBasketOrderID(BasketOrderID);
                                                                    User_singleton.getInstance().setBasketOrderValue(BasketOrderValue);

                                                                    Context context =getContext();
                                                                    CharSequence text = "برجاء الانتظار 60 ثانية حتى يتم اخبارك قبول الطلب من عدمه";
                                                                    int duration = Toast.LENGTH_LONG;
                                                                    final Toast toast = Toast.makeText(context, text, duration);
                                                                    toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                                                                  //  toast.setView(getView());
                                                                    toast.show();

                                                                    handler.postDelayed(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            //Do something after 100ms
                                                                            //**************************************************
                                                                            DocumentReference docRef = db.collection("Order").document(User_singleton.getInstance().getNumber())
                                                                                    .collection("Order User").document(x);
                                                                            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                    if (task.isSuccessful()) {
                                                                                        DocumentSnapshot document = task.getResult();
                                                                                        if (document.exists()) {
                                                                                            accept =(boolean)document.get("accept");
                                                                                            Log.d(TAG, "accept value is "+accept);
                                                                                           // User_singleton.getInstance().setAcceptvalue(Boolean.toString(accept));
                                                                                          //  String accept = User_singleton.getInstance().getAcceptvalue();
                                                                                            Log.d(TAG, "onCreate: accept  "+accept);
                                                                                            NotificationCompat.Builder notification_builder ;
                                                                                                if (Boolean.valueOf(accept).equals(false)){
                                                                                                    Context context =getContext();
                                                                                                    CharSequence text = "تم رفض طلبك من قبل الكابتن ... يرجى اختيار كابتن اخر";
                                                                                                    int duration = Toast.LENGTH_LONG;
                                                                                                    Toast toast = Toast.makeText(context, text, duration);
                                                                                                    toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                                                                                                    toast.show();
                                                                                                }
                                                                                                else {
                                                                                                    Context context = getContext();
                                                                                                    CharSequence text ="تم قبول طلبك ..يمكنك تتبع طلبك";
                                                                                                    int duration = Toast.LENGTH_LONG;
                                                                                                    Toast toast = Toast.makeText(context, text, duration);
                                                                                                    toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
                                                                                                    toast.show();
                                                                                                }

                                                                                        } else {
                                                                                            Toast.makeText(getContext(), "حدث خطأ  ونأسف على ذلك", Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                        //**************************************************
                                                                                    } else {
                                                                                        task.getException();
                                                                                    }
                                                                                }
                                                                            });
                                                                        }
                                                                    }, 60000);

                                                                } else {
                                                                    Log.w(TAG, "Error ");
                                                                }
                                                            }
                                                        });
                                                    } else {
                                                        Log.w(TAG, "Error ");
                                                    }
                                                }
                                            });
                                        } else {
                                            Log.w(TAG, "Error ");
                                        }

                                    }
                                });
                            }
                        }
                    });
                    startActivity(intent);
                    Context context = getContext();
                    CharSequence text =" قم باختيار الكابتن المناسب لك ..لكى نقوم بارسال الطلب..";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else
                    Toast.makeText(getActivity(), "من فضلك ادخل منتج !", Toast.LENGTH_SHORT).show();
              */
                startActivity(intent);
                Context context = getContext();
                CharSequence text =" قم باختيار الكابتن المناسب لك ..لكى نقوم بارسال الطلب..";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        final ArrayList<category_order> orders = new ArrayList<category_order>();
        orders.add(new category_order("سوبر ماركت", R.drawable.supermarket));
        orders.add(new category_order("عطارة", R.drawable.aetara));
        orders.add(new category_order("خضرواتى", R.drawable.vegetabl));
        //  orders.add(new category_order("صيدلية", R.drawable.phermac));

        category_adapter adapter = new category_adapter(getContext(), orders);
        oderlist = v.findViewById(R.id.categoryorder);
        oderlist.setAdapter(adapter);
        oderlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(),subcategorey.class);
                    intent.putExtra("cate2","q1");
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(getActivity(), subcategorey.class);
                    intent.putExtra("cate2","q2");
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(getActivity(), subcategorey.class);
                    intent.putExtra("cate2","q3");
                    startActivity(intent);
                }

            }
        });

        return v;
    }

}
