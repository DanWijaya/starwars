package starwars.coding.com.ParkLah.Search;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.google.android.gms.maps.SupportMapFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import starwars.coding.com.ParkLah.Control.CarparkDataManager;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfoRecord;

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

    SearchPresenter(SearchContract.View searchView){
        this.searchView = searchView;
        this.geocoder = new Geocoder((Context) searchView);
        this.carparkDataManager = CarparkDataManager.getaInstance((Context)searchView);
    }

    @Override
    public void onSearch(String searchString) {
        Address address = geoLocate(searchString);
        if(address == null){
            Log.e("A", "address is null");
            searchView.showNoAddressError();
//            return;
        }
        Log.e("addresstest", address.getLongitude() + " " + address.getLatitude());

        List<CarparkInfoRecord> records = carparkDataManager.getNearByCarparks(address);

        if(records.size() == 0) {
            searchView.showNoCarparksError();
        }

        searchView.showSearchResult(carparkDataManager.getNearByCarparks(address));
    }

    /**
     * This method is to fetch carparks arranged by available slots
     * from carparkDataManager
     */
    public void onSortbySlots(){
        searchView.showSearchResult(carparkDataManager.sortByAvailable());
    }

    /**
     *
     */
    public void onSortbyDistance(){
        searchView.showSearchResult(carparkDataManager.sortByDistance());
    }


    /**
     * This method return an address according to user input search string
     * through geocoder
     * @param searchString User inputted search string to search for an address
     * @return An address object.
     */
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
