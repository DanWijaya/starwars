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
import java.util.Map;
import java.util.concurrent.TimeUnit;

import starwars.coding.com.ParkLah.Database.AccSqlManager;
import starwars.coding.com.ParkLah.Database.AccountDB;
import starwars.coding.com.ParkLah.Database.CarparkDB;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkAPIInterface;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkAvailability;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkAvailabilityDatum;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkAvailabilityInfo;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfo;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfoRecord;


public class APIManager {

    private AccSqlManager db;
    private static APIManager aInstance;
    public List<CarparkAvailabilityDatum> carparkAvailability;

    public static synchronized APIManager getaInstance(Context context){
        if(aInstance == null){
            aInstance = new APIManager(context.getApplicationContext());
        }
        return aInstance;
    }

    private APIManager(Context context){
        this.db = AccSqlManager.getInstance(context);
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

//        @Override
//        protected String doInBackground(String... strings) {
//            List<CarparkInfoRecord> results = new ArrayList<>();
//            Call<CarparkAvailability> fetchCarparkAvailability = fetchCarparkAvailability();
//            Call<CarparkInfo> fetchAllCarparkInfo = fetchAllCarparkInfo();
//            try {
//                Response<CarparkAvailability> carparkAvailabilityResults = fetchCarparkAvailability.execute();
//                Response<CarparkInfo> carparkLotsResults = fetchAllCarparkInfo.execute();
//                List<CarparkAvailabilityDatum> available = carparkAvailabilityResults.body().getItems().get(0).getCarparkData();
//                List<CarparkInfoRecord> total = carparkLotsResults.body().getResult().getRecords();
//
//                for (int i = 0; i < available.size(); i++) { // optimize this please
//                    for (int p = 0; p < total.size(); p++) {
//                        if (available.get(i).getCarparkNumber().equals(total.get(p).getCarParkNo())) {
//                            Log.e("Got 1", available.get(i).getCarparkNumber());
//                            CarparkInfoRecord record = total.get(p);
//                            record.setTotalLots(Integer.parseInt(available.get(i).getCarparkInfo().get(0).getTotalLots()));
//                            record.setLotsAvailable(Integer.parseInt(available.get(i).getCarparkInfo().get(0).getLotsAvailable()));
//                            results.add(record);
//                            total.remove(p);
//                            available.remove(i);
//                            break;
//                        }
//                    }
//                }
//
//                Log.e("Records", results.size() + " total records");
//                db.deleteAllEntries();
//                for (int i = 0; i < results.size(); i++) {
//                    db.addCarparkInfo(results.get(i));
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }

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
                Log.e("test", "Ok I think fetching availability info is done.");
            }catch (Exception e){
                Log.e("------------","There is something wrong");
                e.printStackTrace();
            }

            int j = 0;
            for (CarparkAvailabilityDatum i:carparkAvailability
                 ) {
                Log.e("------------------1>" + "<" + j +">" , i.getCarparkNumber());
                Log.e("------------------2>", i.getCarparkInfo().get(0).getLotsAvailable());
                j++;

            }

            return null;
        }
    }
}
