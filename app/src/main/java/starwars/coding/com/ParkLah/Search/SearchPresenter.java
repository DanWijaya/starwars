package starwars.coding.com.ParkLah.Search;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View searchView;

    public SearchPresenter(SearchContract.View searchView){
        this.searchView = searchView;
    }

    @Override
    public void doMySearch(String searchString) {

    }
}
