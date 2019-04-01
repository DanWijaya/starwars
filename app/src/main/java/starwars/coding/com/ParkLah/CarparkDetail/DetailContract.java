package starwars.coding.com.ParkLah.CarparkDetail;

public interface DetailContract {

    interface View{

        void showNavigation();

        void showReview();

    }

    interface Presenter{

        void onNavigate();

        void onWriteReview();




    }
}
