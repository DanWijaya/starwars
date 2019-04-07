package starwars.coding.com.ParkLah.Control;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import starwars.coding.com.ParkLah.Database.DataBaseManager;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkAPIInterface;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkAvailability;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkAvailabilityDatum;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfo;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfoRecord;


public class APIManager {

    private DataBaseManager db;
    private static APIManager aInstance;
    private List<CarparkAvailabilityDatum> carparkAvailability;

    public static synchronized APIManager getaInstance(Context context){
        if(aInstance == null){
            aInstance = new APIManager(context.getApplicationContext());
        }
        return aInstance;
    }

    private APIManager(Context context){
        this.db = DataBaseManager.getInstance(context);
    }

    private Call<CarparkAvailability> fetchCarparkAvailability() {
        String BASE_URL = "https://api.data.gov.sg/";
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CarparkAPIInterface apiService = retrofit.create(CarparkAPIInterface.class);
        Call<CarparkAvailability> call = apiService.getAvailableSlot();
        return call;
    }

    private Call<CarparkInfo> fetchAllCarparkInfo() {
        String BASE_URL = "https://data.gov.sg/";
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        CarparkAPIInterface apiService = retrofit.create(CarparkAPIInterface.class);
        Call<CarparkInfo> call = apiService.getAllLots();
        return call;
    }

    public class fetchCarparkInformation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings){
            List<CarparkInfoRecord> results = new ArrayList<>();
            Call<CarparkInfo> fetchAllCarparkInfo = fetchAllCarparkInfo();
            try{
                Response<CarparkInfo> carparkLotsResults = fetchAllCarparkInfo.execute();
                List<CarparkInfoRecord> total = carparkLotsResults.body().getResult().getRecords();
                Log.e("debug", "before we add the data into our table"+total.size());
                db.deleteAllEntries();
                for (CarparkInfoRecord i: total
                        ) {
                    db.addCarparkInfo(i);
                }
                Log.e("test", "Ok I think fetching info is done.");
            }catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

    }

    public class fectchCarparkAvailability extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            Call<CarparkAvailability> fetchCarparkAvailability = fetchCarparkAvailability();
            try{
                Response<CarparkAvailability> carparkAvailabilityResults = fetchCarparkAvailability.execute();
                carparkAvailability = carparkAvailabilityResults.body().getItems().get(0).getCarparkData();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

    public List<CarparkAvailabilityDatum> getCarparkAvailability() {
        return carparkAvailability;
    }
}