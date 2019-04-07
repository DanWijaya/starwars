package starwars.coding.com.ParkLah.Search;

import java.util.List;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfoRecord;

public interface SearchContract {

    interface View{
        void showSearchResult(List<CarparkInfoRecord> records);

        void showNoAddressError();

        void showNoCarparksError();
    }

    interface Presenter{

        void onSearch(String searchString);

        void onSortbySlots();

        void onSortbyDistance();



    }
}
