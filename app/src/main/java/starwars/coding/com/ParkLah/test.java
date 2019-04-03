package starwars.coding.com.ParkLah;

import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.Locale;

import starwars.coding.com.ParkLah.Control.APIManager;
import starwars.coding.com.ParkLah.Control.CarparkDataManager;
import starwars.coding.com.ParkLah.Database.AccSqlManager;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkAvailabilityInfo;

public class test extends AppCompatActivity {

    private AccSqlManager accSqlManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        accSqlManager = AccSqlManager.getInstance(this);
        Address address = new Address(new Locale(Locale.ENGLISH.getLanguage()));
        address.setLongitude(1.3131027);
        address.setLatitude(103.76958 );
        APIManager apiManager = new APIManager(accSqlManager);
        apiManager.new fectchCarparkAvailability().execute();

    }
}
