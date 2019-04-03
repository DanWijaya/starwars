package starwars.coding.com.ParkLah.CarparkDetail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

//import starwars.coding.com.ParkLah.MainPage.MapActivity;
import starwars.coding.com.ParkLah.Control.CoordManager.SVY21;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfoRecord;
import starwars.coding.com.ParkLah.Entity.Review;
import starwars.coding.com.ParkLah.R;

public class CarparkDetailActivity extends AppCompatActivity implements DetailContract.View, OnMapReadyCallback {

    private static final String TAG = "CarparkdetailActivtiy";

    private FloatingActionButton goToMapButton;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;

    //vars
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private Button reviewBtn;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private double latitude;
    private double longitude;

    // Create a Uri from an intent string. Use the result to create an Intent
    // geo:latitude, longitude?z=zoom is to display a map at a specified location and zoom level.
    private Uri gmnIntentUri;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    //Set the carpark details view

    AppCompatTextView carparkId;
    AppCompatTextView address;
    AppCompatTextView distance;
    AppCompatTextView slots;
    AppCompatTextView carparkType;
    AppCompatTextView typeOfCarpark;
    AppCompatTextView typeOfCarparkSystem;
    AppCompatTextView nightParking;
    AppCompatTextView freeParking;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "Activity starts");
        setContentView(R.layout.activity_carpark_detail);

        Intent intent = getIntent();
        CarparkInfoRecord record = (CarparkInfoRecord) intent.getSerializableExtra("carpark");

        carparkId = (AppCompatTextView) findViewById(R.id.carparkid);
        address = (AppCompatTextView) findViewById(R.id.address);
        distance = (AppCompatTextView) findViewById(R.id.distance);
        slots = (AppCompatTextView) findViewById(R.id.slots);
        carparkType = (AppCompatTextView) findViewById(R.id.carparktype);
        typeOfCarpark = (AppCompatTextView) findViewById(R.id.carparktype);
        typeOfCarparkSystem = (AppCompatTextView) findViewById(R.id.type_of_carpark);
        nightParking = (AppCompatTextView) findViewById(R.id.night_parking);
        freeParking = (AppCompatTextView) findViewById(R.id.free_parking);

        carparkId.setText(record.getCarParkNo());
        address.setText(record.getAddress());
        distance.setText(String.valueOf(record.getDistance()));
        slots.setText(String.valueOf(record.getLotsAvailable()));
        carparkType.setText(record.getCarParkType());
        typeOfCarparkSystem.setText(record.getTypeOfParkingSystem());
        nightParking.setText(record.getNightParking());
        freeParking.setText(record.getFreeParking());


        // get the latitude and set to latitude.
//        Log.e("debug", "" + record.getxCoord());
        latitude = SVY21.computeLatLon(Double.parseDouble(record.getyCoord()), Double.parseDouble(record.getxCoord())).getLatitude();
        longitude = SVY21.computeLatLon(Double.parseDouble(record.getyCoord()), Double.parseDouble(record.getxCoord())).getLongitude();

        // get the longitude and set to longitude.

        gmnIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination=" + latitude + ","+ longitude + "&travelmode=driving");

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ReviewAdapter();
        recyclerView.setAdapter(adapter);
        Log.e(TAG, "Activity starts");

        goToMapButton = (FloatingActionButton) findViewById(R.id.go_fab);
        reviewBtn = (Button) findViewById(R.id.reviewButton);

        getLocationPermission();
    }

    public void init() {
        goToMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent from gmnIntentUri. Set the Action to ACTION_VIEW.
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmnIntentUri);


                mapIntent.setPackage("com.google.android.apps.maps");

                // Check if there is an available app that can receive the intent
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    // Attempt to start an activity that can handle the Intent.
                    startActivity(mapIntent);
                }

            }
        });

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reviewIntent = new Intent (getApplicationContext(), Review.class);
                startActivity(reviewIntent);

            }
        });


    }

    private void initMap() {
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.carparkmap);

        mapFragment.getMapAsync(CarparkDetailActivity.this);
    }

    public void showNavigation() {

    }

    public void showReview() {

    }

    public void showCurrentLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try{
            if(mLocationPermissionsGranted){

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "onComplete: found location!");
//                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(latitude, longitude),
                                    DEFAULT_ZOOM,
                                    "My Location");

                        }else{
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(CarparkDetailActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: map is Ready");
        mMap = googleMap;

        if (mLocationPermissionsGranted) {
            showCurrentLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            init();
        }
    }

        private void getLocationPermission(){
            Log.d(TAG, "getLocationPermission: getting location permissions");
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION};

            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                        COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionsGranted = true;
                    initMap();
                } else {
                    ActivityCompat.requestPermissions(this,
                            permissions,
                            LOCATION_PERMISSION_REQUEST_CODE);
                }
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }

    public void moveCamera(LatLng latLng, float zoom, String title){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);
    }
}
