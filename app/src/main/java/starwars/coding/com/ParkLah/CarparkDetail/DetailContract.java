package starwars.coding.com.ParkLah.CarparkDetail;

public interface DetailContract {

    interface View{

        void showNavigation();

        void showWriteReview();

    }

    interface Presenter{

        void onNavigate();

        void onWriteReview();
    }
}
