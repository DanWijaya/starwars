package starwars.coding.com.ParkLah.Review;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import starwars.coding.com.ParkLah.R;

public class ReviewActivity extends AppCompatActivity implements ReviewContract.View {

    private Button btn;
    private Button cancelbtn;
    private RatingBar ratebar;

    private ConfirmDialogFragment confirmdialog;
    private CancelDialogFragment canceldialog;

    private ReviewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        btn = (Button) findViewById(R.id.btnSubmit);
        confirmdialog = new ConfirmDialogFragment();
        canceldialog = new CancelDialogFragment();

        presenter = new ReviewPresenter(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSubmit();
            }
        });

        ratebar = (RatingBar) findViewById(R.id.ratingBar);
        ratebar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(ReviewActivity.this,
                        "Rating : " + String.valueOf(ratingBar.getRating()),
                        Toast.LENGTH_SHORT).show();
            }
        });

        cancelbtn = (Button) findViewById(R.id.btnCancel);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCancel();
            }
        });
    }

    @Override
    public void showCancelWarning() {
        Toast.makeText(ReviewActivity.this, "Review cancelled", Toast.LENGTH_SHORT).show();
        canceldialog.show(getSupportFragmentManager(), "Notice");
    }

    @Override
    public void showNextUI() {
        confirmdialog.show(getSupportFragmentManager(), "Notice");
    }
}
