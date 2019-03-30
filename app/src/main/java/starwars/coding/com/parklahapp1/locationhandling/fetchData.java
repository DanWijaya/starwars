package starwars.coding.com.parklahapp1.locationhandling;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class fetchData extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "MapActivity";

    String data = "";
    String dataParsed = "";
    String singleParsed = "";

    ArrayList<String> carparklocations = new ArrayList<String>();
    @Override
    public Void doInBackground(Void... voids) {
        try{
            URL url = new URL("https://api.myjson.com/bins/uglle");

            HttpsURLConnection httpsURLConnection = (HttpsURLConnection)url.openConnection();
            // this will let us to read the data, with stream.
            InputStream inputStream = httpsURLConnection.getInputStream();

            // going to read the data from the url.
            BufferedReader bufferedReader =  new BufferedReader (new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line=bufferedReader.readLine();
                data = data + line;
            }


            JSONArray JA = new JSONArray(data);
            for(int i = 0; i<JA.length();i++){

                JSONObject jo = (JSONObject) JA.get(i);
                singleParsed = "Carpark: " + jo.get("carpark") + "\n"+
                        "Category: " + jo.get("category") + "\n"+
                        "Weekdays Rate 1: " + jo.get("weekdays_rate_1") + "\n"+
                        "Weekdays Rate 2: " + jo.get("weekdays_rate_2") + "\n"+
                        "Saturday Rate: " + jo.get("saturday_rate") + "\n"+
                        "Sunday/Public Holiday Rate: " + jo.get("sunday_publicholiday_rate") + "\n";

                dataParsed = dataParsed + singleParsed + "\n";
                carparklocations.add((String) jo.get("carpark"));
                Log.d(TAG, carparklocations.get(i));

            }

        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }






//    @Override
//    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
//
//        MainActivity.data.setText(this.dataParsed);
//    }
}
