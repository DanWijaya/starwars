package starwars.coding.com.ParkLah.MainPage;

public interface MapContract {

    interface View{

        void showCurrentLocation();

        void showSearchUI();
    }

    interface Presenter{

        void onSearch();
    }
}
