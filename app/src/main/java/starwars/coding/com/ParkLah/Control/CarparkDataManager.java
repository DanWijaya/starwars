package starwars.coding.com.ParkLah.Control;

import android.content.Context;
import android.location.Address;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import starwars.coding.com.ParkLah.Control.CoordManager.SVY21;
import starwars.coding.com.ParkLah.Control.CoordManager.SVY21Coordinate;
import starwars.coding.com.ParkLah.Database.AccSqlManager;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkAvailabilityDatum;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfoRecord;

public class CarparkDataManager {

    private AccSqlManager db;
    private APIManager apiManager;
    private static CarparkDataManager aInstance;

    private List<CarparkInfoRecord> carparks;
    private int range = 1000;

    public static CarparkDataManager getaInstance(Context context){
        if(aInstance == null){
            aInstance = new CarparkDataManager(context); }
        return aInstance;
    }
    private CarparkDataManager(Context context){
        this.db = AccSqlManager.getInstance(context);
        this.apiManager = APIManager.getaInstance(context);
    }

    private SVY21Coordinate getBottomLeft(Address address){

        double lat = address.getLatitude();
        double lon = address.getLongitude();

        SVY21Coordinate coord = SVY21.computeSVY21(lat, lon);
        SVY21Coordinate result = new SVY21Coordinate(coord.getNorthing()-range, coord.getEasting()-range);

        return result;
    }

    private SVY21Coordinate getUpperRight(Address address){
        double lat = address.getLatitude();
        double lon = address.getLongitude();

        SVY21Coordinate coord = SVY21.computeSVY21(lat, lon);
        SVY21Coordinate result = new SVY21Coordinate(coord.getNorthing()+range, coord.getEasting()+range);

        return result;
    }

    public List<CarparkInfoRecord> getNearByCarparks(Address address){

        List<CarparkInfoRecord> result = new ArrayList<>();
        List<CarparkAvailabilityDatum> availability = new ArrayList<>();

        SVY21Coordinate bottomLeft = getBottomLeft(address);
        SVY21Coordinate upperRight = getUpperRight(address);

        result = db.getCarparkByCoord(bottomLeft, upperRight);
        availability = apiManager.carparkAvailability;

        for ( CarparkInfoRecord record: result) {
            for (int i = 0; i < availability.size(); i++) {
                if (record.getCarParkNo().contentEquals(availability.get(i).getCarparkNumber())){
                    record.setTotalLots(Integer.parseInt(availability.get(i).getCarparkInfo().get(0).getTotalLots()));
                    record.setLotsAvailable(Integer.parseInt(availability.get(i).getCarparkInfo().get(0).getLotsAvailable()));
                    record.setLotType(availability.get(i).getCarparkInfo().get(0).getLotType());
                    break;
                }
            }
        }
        Log.e("debug", "Successfully finished creating carpark objects");
        this.carparks = result;

        double distance;
        double x = SVY21.computeSVY21(address.getLatitude(), address.getLongitude()).getEasting();
        double y = SVY21.computeSVY21(address.getLatitude(), address.getLongitude()).getNorthing();

        for (CarparkInfoRecord record: carparks) {

            double xcord=Double.parseDouble(record.getXCoord());
            double ycord=Double.parseDouble(record.getYCoord());
            distance= Math.sqrt(Math.pow((xcord-x),2)+(Math.pow((ycord-y),2)));
            record.setDistance(Math.round(distance));
        }

        Collections.sort(carparks, CarparkInfoRecord.Comparators.DISTANCE);
        return carparks;
    }


    public List<CarparkInfoRecord> sortByDistance()
    {
        Collections.sort(carparks, CarparkInfoRecord.Comparators.DISTANCE);
        return carparks;
    }

    public List<CarparkInfoRecord> sortByAvailable()
    {
        Collections.sort(carparks, CarparkInfoRecord.Comparators.AVAILABLESLOTS);
        return carparks;
    }

    public List<CarparkInfoRecord> sortByTotal(){
        Collections.sort(carparks, CarparkInfoRecord.Comparators.TOTALSLOTS);
        return carparks;
    }












}
