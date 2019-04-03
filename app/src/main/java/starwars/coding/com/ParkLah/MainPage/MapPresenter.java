package starwars.coding.com.ParkLah.MainPage;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapPresenter implements MapContract.Presenter {

    private String TAG = "MapPresnter";
    private MapContract.View mapView;


    public MapPresenter(MapContract.View mapView) {
        this.mapView = mapView;
    }

    // The Latitude and Longitude range for Singapore
//    private static final double LOWER_LEFT_LATITUDE = 1.1200;
//    private static final double LOWER_LEFT_LONGITUDE = 103.6200;
//    private static final double UPPER_RIGHT_LATITUDE = 1.4600;
//    private static final double UPPER_RIGHT_LONGITUDE = 104.1600;
//    private static final float DEFAULT_ZOOM = 15f;

    @Override
    public void onSearch(String searchString) {
        mapView.showSearchUI(searchString);
//        Log.d(TAG, "geoLocate: geolocating");
//        List<Address> list = new ArrayList<>();
//        try {
//            list = geocoder.getFromLocationName(searchString, 1, LOWER_LEFT_LATITUDE, LOWER_LEFT_LONGITUDE,
//                    UPPER_RIGHT_LATITUDE, UPPER_RIGHT_LONGITUDE);
//        } catch (IOException e) {
//            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
//        }
//
//        if (list.size() > 0) {
//            Address address = list.get(0);
//
//            Log.d(TAG, "geoLocate: found a location: " + address.toString());
//            //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();
//
//            mapView.moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,
//                    address.getAddressLine(0));
//        }
    }
}

//    private void geoLocate(){
//            Log.d(TAG, "geoLocate: geolocating");
//
//            String searchString = mSearchText.getText().toString();
//
//            Geocoder geocoder = new Geocoder(MapActivity.this);
//            List<Address> list = new ArrayList<>();
//            try{geocoder.getFromLocationName(searchString, 1, LOWER_LEFT_LATITUDE, LOWER_LEFT_LONGITUDE,
//                    UPPER_RIGHT_LATITUDE, UPPER_RIGHT_LONGITUDE);
////            list = geocoder.getFromLocationName(searchString, 1);
//            }catch (IOException e){
//                Log.e(TAG, "geoLocate: IOException: " + e.getMessage() );
//            }
//
//            if(list.size() > 0){
//                Address address = list.get(0);
//
//                Log.d(TAG, "geoLocate: found a location: " + address.toString());
//                //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();
//
//                moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,
//                        address.getAddressLine(0));
//            }
//        }