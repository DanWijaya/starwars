package starwars.coding.com.ParkLah.MainPage;

public class MapPresenter implements MapContract.Presenter{

    private MapContract.View mapView;

    public MapPresenter(MapContract.View view){
        this.mapView = view;
    }
    @Override
    public void onSearch(String searchString) {

    }
}
