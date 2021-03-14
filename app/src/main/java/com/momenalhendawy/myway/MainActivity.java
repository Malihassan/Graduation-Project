package com.momenalhendawy.myway;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import com.google.android.material.navigation.NavigationView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.annotation.NonNull;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static Button toMakeOrder;
    private FragmentManager fm;
    private FirebaseUser mAuth;





    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final int PERMISSIONS_REQUEST_ENABLE_GPS = 9002;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 9003;

    Fragment f;

    private boolean mLocationPermissionGranted = false;

    private FusedLocationProviderClient mFusedLocationClient;
    public Criteria criteria;
    public String bestProvider;

    private UserLocation mUserLocation;
    private User_singleton userSingleton;

    private FirebaseFirestore mDb;

    //Global newClint object
    //private newClient mNewClint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fm = getSupportFragmentManager();
        f = fm.findFragmentById(R.id.frame_container);


        //Instantiate firestore instance
        mDb = FirebaseFirestore.getInstance();


        //userSingleton = new User_singleton();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


/*
        if (f == null && isServicesOK()) {
            f = new home_fragment();
            fm.beginTransaction()
                    .add(R.id.frame_container, f)
                    .commit();
        }
*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    // Step 1
    private void getUserDetails()
    {
        if(mUserLocation == null)
        {
            mUserLocation = new UserLocation();

            DocumentReference userRef = mDb.collection(getString(R.string.collection_users))
                    .document(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());

            userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task)
                {
                    if(task.isSuccessful())
                    {

                        Log.d(TAG, "onComplete: Succefully get the user details");

                        //User_singleton userSingleton = new User_singleton();

                        User_singleton user = task.getResult().toObject(User_singleton.class);
                        Log.d(TAG, "onComplete: HIII" + userSingleton);
                        mUserLocation.setUser(userSingleton);

                        //((UserClient)getApplicationContext()).setUser(user_singleton);
                        getLastKnownLocation();

                    }
                }
            });

        }
        else
        {
            getLastKnownLocation();
        }
    }






    //Step 2
    //Getting gps coordinates
    private void getLastKnownLocation() {
        Log.d(TAG, "getLastKnownLocation: called.");


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<android.location.Location>()
                                                                     {
                                                                         @Override
                                                                         public void onComplete(@NonNull Task<android.location.Location> task)
                                                                         {
                                                                             if (task.isSuccessful()) {
                                                                                 mUserLocation = new UserLocation();
                                                                                 Location location = task.getResult();
                                                                                 GeoPoint geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
                                                                                 Log.d(TAG, "onComplete: Zyaaaaaaaaad"+ geoPoint);
                                                                                 //mUserLocation=new UserLocation(geoPoint,null);
                                                                                 mUserLocation.setGeo_point(geoPoint);
                                                                                 mUserLocation.setTimestamp(null);
                                                                                 //UserLocation mUserLocation = new UserLocation(geoPoint,null, userSingleton);
                                                                                 saveUserLocation();
                                                                             }
                                                                         }
                                                                     }
        );

    }

    //Step 3
    //inserting the user location into firestore
    private void saveUserLocation()
    {

        if(mUserLocation != null)
        {
            //Log.d(TAG, "here"+FirebaseAuth.getInstance().getUid());


            String userId = User_singleton.getInstance().getNumber();
            Log.d(TAG, "saveUserLocation: "+userId);

            DocumentReference locationRef=mDb.collection(getString(R.string.collection_user_locations))
                    .document(userId);

            locationRef.set(mUserLocation).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {

                        // if the location saved successfuly in the database
                        Log.d(TAG, "saveUserLocation: \ninserted user location into database." +
                                "\n latitude: " + mUserLocation.getGeo_point().getLatitude() +
                                "\n longitude: " + mUserLocation.getGeo_point().getLongitude());
                    }
                }
            });

        }
    }

    /*



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

*/


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        f = fm.findFragmentById(R.id.frame_container);

        if (id == R.id.nav_profile) {
            // Handle the camera action
            f = new fregmant_profile();
            fm.beginTransaction()
                    .replace(R.id.frame_container, f)
                    .commit();

        } else if (id == R.id.nav_order) {
            f = new orderDetails();
            fm.beginTransaction()
                    .replace(R.id.frame_container, f)
                    .commit();
        }
        if (id == R.id.nav_neworder) {
            // Handle the camera action
            f = new home_fragment();
            fm.beginTransaction()
                    .replace(R.id.frame_container, f)
                    .commit();

        }
        if (id == R.id.nav_discount) {
            f = new reciveorder();
            fm.beginTransaction()
                    .replace(R.id.frame_container, f)
                    .commit();
        }
        if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();

            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean checkMapServices(){
        if(isServicesOK()){
            if(isMapsEnabled()){
                return true;
            }
        }
        return false;
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This application requires GPS to work properly, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        Intent enableGpsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(enableGpsIntent, PERMISSIONS_REQUEST_ENABLE_GPS);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    //Step 2
    //GPS Enapled
    public boolean isMapsEnabled(){
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
            return false;
        }
        return true;
    }

    //Step 3
    //Get location permission
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            getHome();
            getUserDetails();
            //saveUserLocation();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    //Step 1
    //Google service installed
    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    //step 4
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: called.");
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ENABLE_GPS: {
                if(mLocationPermissionGranted){
                    getHome();
                    getUserDetails();
                    saveUserLocation();
                }
                else{
                    getLocationPermission();
                }
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(checkMapServices()){
            if(mLocationPermissionGranted){
                getHome();
                getUserDetails();
                saveUserLocation();
            }
            else{
                getLocationPermission();
            }
        }
    }


    private void getHome()
    {
        setContentView(R.layout.activity_main);


        fm = getSupportFragmentManager();
        f = fm.findFragmentById(R.id.frame_container);

        f = new home_fragment();
        fm.beginTransaction()
                .add(R.id.frame_container, f)
                .commit();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }





}

