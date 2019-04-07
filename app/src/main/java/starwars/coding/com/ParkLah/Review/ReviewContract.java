package starwars.coding.com.ParkLah.Review;

public interface ReviewContract {

    interface View{

        void showCancelWarning();

        void showNextUI();

    }

    interface Presenter{

        void onSubmit();

        void onCancel();

    }
}
