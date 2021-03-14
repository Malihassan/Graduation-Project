package com.momenalhendawy.myway;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.app.NotificationCompat;

import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.iid.FirebaseInstanceId;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class home_fragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    public SparseArray<String> DrinkOrderDescribe = new SparseArray<>();
    public SparseArray<String> DrinkOrderID = new SparseArray<>();
    public SparseArray<Integer> DrinkOrderValue = new SparseArray<>();

    public SparseArray<String> RiceOrderDescribe = new SparseArray<>();
    public SparseArray<String> RiceOrderID = new SparseArray<>();
    public SparseArray<Integer> RiceOrderValue = new SparseArray<>();

    public SparseArray<String> OilOrderDescribe = new SparseArray<>();
    public SparseArray<String> OilOrderID = new SparseArray<>();
    public SparseArray<Integer> OilOrderValue = new SparseArray<>();

    public SparseArray<String> CleaningOrderDescribe = new SparseArray<>();
    public SparseArray<String> CleaningOrderID = new SparseArray<>();
    public SparseArray<Integer> CleaningOrderValue = new SparseArray<>();

    public SparseArray<String> VegetableOrderDescribe = new SparseArray<>();
    public SparseArray<String> VegetableOrderID = new SparseArray<>();
    public SparseArray<Integer> VegetableOrderValue = new SparseArray<>();

    public SparseArray<String> SpiceOrderDescribe = new SparseArray<>();
    public SparseArray<String> SpiceOrderID = new SparseArray<>();
    public SparseArray<Integer> SpiceOrderValue = new SparseArray<>();

    public ArrayList<String> BasketOrderDescribe = new ArrayList<String>();
    public ArrayList<String> BasketOrderID = new ArrayList<String>();
    public ArrayList<Integer> BasketOrderValue = new ArrayList<Integer>();
    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    private MapView mMapView;
    //vars
    private static Button sendorder;
    private String accepted;
    public int m;
    final Handler handler = new Handler();
    private GoogleMap mGoogleMap;
    private LatLngBounds mMapBoundary;
    private UserLocation mUserPosition;
    private FirebaseFirestore mDb;
    public FirebaseFirestore db;
    private FirebaseUser mAuth;

    public List<String> captain_IDs = new ArrayList<>();
    public List<GeoPoint> captain_Geopoint = new ArrayList<>();
    public List<String> captain_IDs_do_order = new ArrayList<>();
    public List<GeoPoint> captain_Geopoint_do_order = new ArrayList<>();
    public List<String> ph_list = new ArrayList<>();

    List<String> list = new ArrayList<>();
    public boolean accept;
    public static String x = "";
    boolean surevalue = false;
    public static String idToken ;

    public static String captainiD ;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);
        mMapView = (MapView) view.findViewById(R.id.user_list_map);
        //getLocationPermission();

        mAuth = FirebaseAuth.getInstance().getCurrentUser();
        User_singleton.getInstance().setNumber(mAuth.getPhoneNumber().substring(2));

        db = FirebaseFirestore.getInstance();
        x = db.collection("Order").document(User_singleton.getInstance().getNumber()).collection("Order User").document().getId();
        Log.d(TAG, "onClick: ............." + x);


        sendorder = view.findViewById(R.id.new_order);
        sendorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Order.class);
                startActivity(intent);
                Toast.makeText(getContext(), "طلب جديد ..", Toast.LENGTH_SHORT).show();

                clear_arrays();
                Log.d(TAG, "onClick: all cleare");
            }
        });

        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);
        showcaptains();
        // update_location();
        return view;
    }

    public void getUserPosition() {
        mUserPosition = new UserLocation();

        mAuth = FirebaseAuth.getInstance().getCurrentUser();

        String ph = mAuth.getPhoneNumber().substring(2);
        Log.d(TAG, "getUserPosition: " + ph);

        mDb = FirebaseFirestore.getInstance();
        DocumentReference locationRef = mDb.collection(getString(R.string.collection_user_locations))
                .document(ph);
        locationRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {


                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        //Log.i("LOGGER","geo_point "+document.getGeoPoint("geo_point"));
                        Log.d(TAG, "onComplete: LEEEEEEEH" + document.getGeoPoint("geo_point"));

                        mUserPosition = new UserLocation(document.getGeoPoint("geo_point"), null,null);

                        final GeoPoint geoPoint = mUserPosition.getGeo_point();
                        Log.d(TAG, "setCameraView: " + geoPoint);

                        double bottomBoundry = geoPoint.getLatitude() - .1;
                        double leftBoundry = geoPoint.getLongitude() - .1;
                        double topBoundry = geoPoint.getLatitude() + .1;
                        double rightBoundry = geoPoint.getLongitude() + .1;

                        // Log.d(TAG, "TTTTTTTTTT: "+bottomBoundry);
                        // Log.d(TAG, "TTTTTTTTTT: "+leftBoundry);
                        // Log.d(TAG, "TTTTTTTTTT: "+topBoundry);
                        // Log.d(TAG, "TTTTTTTTTT: "+rightBoundry);

                        mMapBoundary = new LatLngBounds(
                                new LatLng(bottomBoundry, leftBoundry),
                                new LatLng(topBoundry, rightBoundry)
                        );

                        mGoogleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                            @Override
                            public void onMapLoaded() {
                                Log.d(TAG, "onMapLoaded: captain_IDs " + captain_IDs.size() + "====>" + captain_IDs);
                                Log.d(TAG, "onMapLoaded: captain_Geopoint " + captain_Geopoint.size() + "---->" + captain_Geopoint);
                                BitmapDescriptor bitmapDescriptor
                                        = BitmapDescriptorFactory.defaultMarker(
                                        BitmapDescriptorFactory.HUE_AZURE);
                                for (int x = 0; x < captain_Geopoint.size(); x++) {
                                    GeoPoint geo = captain_Geopoint.get(x);
                                    Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                                            .position(new LatLng(geo.getLatitude(), geo.getLongitude()))
                                            .title("Captain")
                                            .snippet("ONLINE"));
                                    Log.d(TAG, "onMapLoaded: (((((((((((((((((:" + marker.getId());
                                }

                                for (int x = 0; x < ph_list.size(); x++) {
                                    if (ph_list.size() != 0) {
                                        Log.d(TAG, "onMapLoaded: captain_IDs_do_order " + captain_IDs_do_order.size() + "====>" + captain_IDs_do_order);
                                        Log.d(TAG, "onMapLoaded: captain_Geopoint_do_order " + captain_Geopoint_do_order.size() + "---->" + captain_Geopoint_do_order);
                                        GeoPoint geo = captain_Geopoint_do_order.get(x);
                                        Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                                                .position(new LatLng(geo.getLatitude(), geo.getLongitude()))
                                                .title("Captain")
                                                .icon(bitmapDescriptor)
                                                .snippet("Deliver Your Order :)"));
                                        Log.d(TAG, "onMapLoaded: (((((((((((((((((:" + marker.getId());

                                    }
                                }

                                //Your code where exception occurs goes here...
                                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(mMapBoundary, -1000);
                                mGoogleMap.moveCamera(cameraUpdate);
                            }
                        });
                    } else {
                        Log.d("LOGGER", "No such document");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
            //Log.d(TAG, "onSaveInstanceState: Called");
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {


        //GeoPoint geo_point =mUserPosition.getGeo_point();


        // mGoogleMap.addMarker(new MarkerOptions()
        //            .position(new LatLng(10, 10))
        //            .title("Hello world"));


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);

        mGoogleMap = map;

        update_location();

        getUserPosition();



        mGoogleMap.setOnInfoWindowClickListener(this);

    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public void showcaptains() {
        Log.d(TAG, "showcaptains: in show captain");
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Captains").whereEqualTo("status", true)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull final Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    final List<String> captain_sta_true = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        captain_sta_true.add(document.getId());
                    }
                    Log.d(TAG, "onComplete: captain_sta_true " + captain_sta_true);
                    for (m = 0; m < captain_sta_true.size(); m++) {
                        DocumentReference docRef = db.collection("Captein Location")
                                .document(captain_sta_true.get(m));
                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    //    Log.d(TAG, "onComplete: captain true"+captain_sta_true.get(m));
                                    DocumentSnapshot document = task.getResult();
                                    GeoPoint geoPoint;
                                    geoPoint = (GeoPoint) document.get("geoPoint");
                                    Log.d(TAG, "geoPoint:=-=-=-=> " + geoPoint + " ID =-=-=> " + document.getId());
                                    captain_IDs.add(document.getId().substring(2));
                                    captain_Geopoint.add(geoPoint);

                                } else {
                                    task.getException();
                                }
                            }
                        });

                    }

                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }

    //The method will excute when the info method click
    public void onInfoWindowClick(final Marker marker) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure to choose this " + marker.getTitle())
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        //hide the window message
                        Log.d(TAG, "Captain ID " + marker.getId());

                        db = FirebaseFirestore.getInstance();
                        LatLng geoPoint1 = marker.getPosition();
                        GeoPoint geoPoint = new GeoPoint(geoPoint1.latitude, geoPoint1.longitude);
                        db.collection("Captein Location")
                                .whereEqualTo("geoPoint", geoPoint)
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        Log.d(TAG, "onComplete: SSSSSSSS");
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                Log.d(TAG, "document " + document.getId());
                                                list.clear();
                                                list.add(document.getId());
                                            }
                                            for (int x = 0; x < list.size(); x++) {
                                                String ph = list.get(x);
                                                if (ph.length() > 11) {
                                                    list.remove(x);
                                                    list.add(x, ph.substring(2));
                                                }
                                            }
                                            Log.d(TAG, "onComplete: " + list);

                                            send_order();

                                        } else {
                                            Log.d(TAG, "Error getting documents: ", task.getException());
                                        }
                                    }
                                });
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        //cancel the window message
                        dialog.cancel();
                    }
                });

        final AlertDialog alert = builder.create();
        alert.show();
    }

    public void send_order() {
        Log.d(TAG, "send_order:  inside");

        BasketOrderID = User_singleton.getInstance().getBasketOrderID();
        BasketOrderDescribe = User_singleton.getInstance().getBasketOrderDescribe();
        BasketOrderValue = User_singleton.getInstance().getBasketOrderValue();
        //  Log.d(TAG, "send_order: "+BasketOrderID.size());
        if (BasketOrderID != null && BasketOrderID.size() != 0) {
            String ph = User_singleton.getInstance().getNumber();
            final DocumentReference docIdRef = db.collection("Order").document(ph);

            Log.d(TAG, "document ===========>>>>>> " + x);

            docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        Basket_Order orders = new Basket_Order();
                        orders.setOrder_Name("order");
                        orders.setOrder_Item(BasketOrderDescribe);
                        orders.setItem_ID(BasketOrderID);
                        orders.setOrder_Values(BasketOrderValue);
                        Map<String, Object> Basket = new HashMap<>();
                        Basket.put("Basket", orders);
                        docIdRef.collection("Order User").document(x).set(Basket).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    final Send_order order = new Send_order();
                                    order.setCaptainPhone(list.get(0));
                                    order.setTokenID(FirebaseInstanceId.getInstance().getToken());
                                    order.setAccept(false);
                                    order.setOrder_ID(x);
                                    order.setSrart_Time(new com.google.firebase.Timestamp(new Date()));
                                    order.setReason_for_canceling_the_request("");
                                    order.setCanceling(false);
                                    order.setFinished(false);
                                    docIdRef.collection("Order User").document(x).set(order, SetOptions.merge())
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {

                                                        //  sendorder.setFocusableInTouchMode(false);
                                                        Context context = getContext();
                                                        CharSequence text = "برجاء الانتظار 30 ثانية حتى يتم اخبارك قبول الطلب من عدمه";
                                                        int duration = Toast.LENGTH_LONG;
                                                        final Toast toast = Toast.makeText(context, text, duration);
                                                        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
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
                                                                                accept = (boolean) document.get("accept");
                                                                                Log.d(TAG, "accept value is " + accept);
                                                                                // User_singleton.getInstance().setAcceptvalue(Boolean.toString(accept));
                                                                                //  String accept = User_singleton.getInstance().getAcceptvalue();
                                                                                Log.d(TAG, "onCreate: accept  " + accept);
                                                                                NotificationCompat.Builder notification_builder;
                                                                                if (Boolean.valueOf(accept).equals(false)) {
                                                                                    Context context = getContext();
                                                                                    CharSequence text = "تم رفض طلبك من قبل الكابتن ... يرجى اختيار كابتن اخر";
                                                                                    int duration = Toast.LENGTH_LONG;
                                                                                    Toast toast = Toast.makeText(context, text, duration);
                                                                                    toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
                                                                                    toast.show();
                                                                                } else {
                                                                                    Context context = getContext();
                                                                                    CharSequence text = "تم قبول طلبك ..يمكنك تتبع طلبك";
                                                                                    int duration = Toast.LENGTH_LONG;
                                                                                    Toast toast = Toast.makeText(context, text, duration);
                                                                                    toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
                                                                                    toast.show();
                                                                                    //      update_location();
                                                                                    clear_arrays(); // for clear array after do order
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
                                                        }, 30000);

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
        } else
            Toast.makeText(getActivity(), "من فضلك ادخل منتج !", Toast.LENGTH_SHORT).show();
    }

    public void clear_arrays() {
        DrinkOrderDescribe.clear();
        User_singleton.getInstance().setDrinkItem(DrinkOrderDescribe);
        DrinkOrderID.clear();
        User_singleton.getInstance().setDrinkItemID(DrinkOrderID);
        DrinkOrderValue.clear();
        User_singleton.getInstance().setDrinkItemValues(DrinkOrderValue);

        RiceOrderDescribe.clear();
        User_singleton.getInstance().setRiceItem(RiceOrderDescribe);
        RiceOrderID.clear();
        User_singleton.getInstance().setRiceItemID(RiceOrderID);
        RiceOrderValue.clear();
        User_singleton.getInstance().setRiceItemValues(RiceOrderValue);

        OilOrderDescribe.clear();
        User_singleton.getInstance().setOilItem(OilOrderDescribe);
        OilOrderID.clear();
        User_singleton.getInstance().setOilItemID(OilOrderID);
        OilOrderValue.clear();
        User_singleton.getInstance().setOilItemValues(OilOrderValue);


        CleaningOrderDescribe.clear();
        User_singleton.getInstance().setCleaningItem(CleaningOrderDescribe);
        CleaningOrderID.clear();
        User_singleton.getInstance().setCleaningItemID(CleaningOrderID);
        CleaningOrderValue.clear();
        User_singleton.getInstance().setCleaningtemValues(CleaningOrderValue);

        VegetableOrderDescribe.clear();
        User_singleton.getInstance().setVegetableItem(VegetableOrderDescribe);
        VegetableOrderID.clear();
        User_singleton.getInstance().setVegetableItemID(VegetableOrderID);
        VegetableOrderValue.clear();
        User_singleton.getInstance().setVegetabletemValues(VegetableOrderValue);

        SpiceOrderDescribe.clear();
        User_singleton.getInstance().setSpiceItem(SpiceOrderDescribe);
        SpiceOrderID.clear();
        User_singleton.getInstance().setSpiceItemID(SpiceOrderID);
        SpiceOrderValue.clear();
        User_singleton.getInstance().setSpicetemValues(SpiceOrderValue);

        BasketOrderDescribe.clear();
        User_singleton.getInstance().setBasketOrderDescribe(BasketOrderDescribe);
        BasketOrderID.clear();
        User_singleton.getInstance().setBasketOrderID(BasketOrderID);
        BasketOrderValue.clear();
        User_singleton.getInstance().setBasketOrderValue(BasketOrderValue);
    }

    public void update_location() {

        Log.d(TAG, "update_location =-=--=-=-=>i`m here");
        db.collection("Order").document(User_singleton.getInstance().getNumber())
                .collection("Order User")
                .whereEqualTo("accept", true)
                .whereEqualTo("canceling", false)
                .whereEqualTo("finished", false)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull final Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " ==============> " + document.getData());
                        ph_list.add(String.valueOf(document.get("captainPhone")));
                        Log.d(TAG, "onComplete: " + ph_list);
                    }
                    for (m = 0; m < ph_list.size(); m++) {
                        DocumentReference docRef = db.collection("Captains")
                                .document("+2" + ph_list.get(m));
                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Log.d(TAG, "onComplete: status captain " + document.getData());
                                        surevalue = document.getBoolean("status");
                                        Log.d(TAG, "onComplete: sss0" + surevalue);
                                        if (Boolean.valueOf(surevalue).equals(true)) {
                                            if (ph_list.size() == 1) {
                                                ph_list.clear();
                                            } else {
                                                ph_list.remove(m);
                                            }
                                        }
                                        Log.d(TAG, "onComplete:ph_list===> " + ph_list.size());
                                    } else Log.d(TAG, "onComplete: not exist");
                                }
                            }
                        });
                    }
                    for (m = 0; m < ph_list.size(); m++) {
                        DocumentReference docRef = db.collection("Captein Location")
                                .document("+2" + ph_list.get(m));
                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    //    Log.d(TAG, "onComplete: captain true"+captain_sta_true.get(m));
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        GeoPoint geoPoint;
                                        geoPoint = (GeoPoint) document.get("geoPoint");
                                        Log.d(TAG, "geoPoint:=-=-=-=> " + geoPoint + " ID =-=-=> " + document.getId());
                                        captain_IDs_do_order.add(document.getId().substring(2));
                                        captain_Geopoint_do_order.add(geoPoint);
                                    }
                                }
                            }
                        });
                    }
                } else
                    Log.d(TAG, "لا يوجد طلبات منتهية");

            }
        });
    }


}