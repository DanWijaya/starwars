package starwars.coding.com.ParkLah.Review;

public class ReviewPresenter implements ReviewContract.Presenter {

    private ReviewContract.View view;

    public ReviewPresenter(ReviewContract.View  view){
        this.view = view;
    }

    @Override
    public void onSubmit() {
        view.showNextUI();

    }

    @Override
    public void onCancel() {
        view.showCancelWarning();
    }
}
