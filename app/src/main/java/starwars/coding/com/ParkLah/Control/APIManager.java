package starwars.coding.com.ParkLah.Control;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import android.os.AsyncTask;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import starwars.coding.com.ParkLah.Database.AccountDB;
import starwars.coding.com.ParkLah.Database.CarparkDB;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkAPIInterface;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkAvailability;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkAvailabilityDatum;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfo;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfoRecord;


public class APIManager {

    private AccountDB db;

    public APIManager(AccountDB db){
        this.db = db;
    }

    private Call<CarparkAvailability> fetchCarparkAvailability() {
        String BASE_URL = "https://api.data.gov.sg/";
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
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

    private class fetchCarparkInformation extends AsyncTask<String, Void, String> {

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

                for (CarparkInfoRecord i: total
                     ) {
                    db.addCarparkInfo(i);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

    }

    public List<CarparkAvailabilityDatum> fetchCarpdarkAvailability(){
        Call<CarparkAvailability> fetchCarparkAvailability = fetchCarparkAvailability();
        try{
            Response<CarparkAvailability> carparkAvailabilityResults = fetchCarparkAvailability.execute();
            List<CarparkAvailabilityDatum> available = carparkAvailabilityResults.body().getItems().get(0).getCarparkData();
            return available;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
