package starwars.coding.com.ParkLah.MainPage;

import com.google.android.gms.maps.model.LatLng;

public interface MapContract {

    interface View{

        void showCurrentLocation();

        void showSearchUI();

        void moveCamera(LatLng latLng, float zoom, String title);
    }

    interface Presenter{

        void onSearch(String searchString);
    }
}
