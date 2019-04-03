package starwars.coding.com.ParkLah.MainPage;

import com.google.android.gms.maps.model.LatLng;

public interface MapContract {

    interface View{

        void showCurrentLocation();

        void showSearchUI(String searchString);

    }

    interface Presenter{
        void onSearch(String searchString);
    }
}
