package starwars.coding.com.ParkLah.CarparkDetail;

import starwars.coding.com.ParkLah.MainPage.MapPresenter;
public class CarparkDetailPresenter implements DetailContract.Presenter {

    DetailContract.View detailView;

    public CarparkDetailPresenter(DetailContract.View view){
        this.detailView = view;
    }
    public void onNavigate() {
        detailView.showNavigation();
    }

    public void onWriteReview(){
        detailView.showWriteReview();

    }
}
