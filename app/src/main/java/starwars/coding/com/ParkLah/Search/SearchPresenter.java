package starwars.coding.com.ParkLah.Search;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import starwars.coding.com.ParkLah.Control.CarparkDataManager;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View searchView;
    private Geocoder geocoder;
    private CarparkDataManager carparkDataManager;



//  The Latitude and Longitude range for Singapore
    private static final double LOWER_LEFT_LATITUDE = 1.1200;
    private static final double LOWER_LEFT_LONGITUDE = 103.6200;
    private static final double UPPER_RIGHT_LATITUDE = 1.4600;
    private static final double UPPER_RIGHT_LONGITUDE = 104.1600;
    private static final float DEFAULT_ZOOM = 15f;

    SearchPresenter(SearchContract.View searchView, Geocoder geocoder){
        this.searchView = searchView;
        this.geocoder = geocoder;
        this.carparkDataManager = CarparkDataManager.getaInstance((Context)searchView);
    }

    @Override
    public void onSearch(String searchString) {
        Address address = geoLocate(searchString);
        Log.e("addresstest", address.getLongitude() + " " + address.getLatitude());
        searchView.showSearchResult(carparkDataManager.getNearByCarparks(address));
    }

    public void onSortbySlots(){
        searchView.showSearchResult(carparkDataManager.sortByAvailable());
    }

    public void onSortbyDistance(){
        searchView.showSearchResult(carparkDataManager.sortByDistance());
    }

    private Address geoLocate(String searchString){
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchString, 1, LOWER_LEFT_LATITUDE, LOWER_LEFT_LONGITUDE,
                    UPPER_RIGHT_LATITUDE, UPPER_RIGHT_LONGITUDE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (list.size() > 0) {
            Address address = list.get(0);
            return address;
        }
        return null;
    }


}
