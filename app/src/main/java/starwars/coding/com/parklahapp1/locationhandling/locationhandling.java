//package starwars.coding.com.parklahapp1.locationhandling;
//
//import android.location.Address;
//import android.location.Geocoder;
//import android.util.Log;
//
//import com.google.android.gms.maps.model.LatLng;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//public class locationhandling {
//
//    private static final String TAG = "LocationHandler";
//
//    private void geoLocate(){
//        Log.d(TAG, "geoLocate: geolocating");
//
//        String searchString = getText().toString();
//
//
//        List<Address> list = new ArrayList<>();
//        try{
//            list = geocoder.getFromLocationName(searchString, 1);
//        }catch (IOException e){
//            Log.e(TAG, "geoLocate: IOException: " + e.getMessage() );
//        }
//
//        if(list.size() > 0){
//            Address address = list.get(0);
//        }
//    }
//}
