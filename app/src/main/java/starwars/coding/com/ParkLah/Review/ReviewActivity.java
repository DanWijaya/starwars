package starwars.coding.com.ParkLah.Review;

import android.content.Intent;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import starwars.coding.com.ParkLah.CarparkDetail.CarparkDetailActivity;
import starwars.coding.com.ParkLah.R;

public class ReviewActivity extends AppCompatActivity {

    private Button btn;
    private RatingBar ratebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        btn = (Button) findViewById(R.id.btnSubmit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ReviewActivity.this, "Review submitted", Toast.LENGTH_SHORT).show();
//                Intent backToCarpark = new Intent(ReviewActivity.this, CarparkDetailActivity.class);
//                startActivity(backToCarpark);
                finish();
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
    }
}
