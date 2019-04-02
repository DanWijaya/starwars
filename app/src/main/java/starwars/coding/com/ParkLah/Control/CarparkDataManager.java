package starwars.coding.com.ParkLah.Control;

import java.util.List;

import starwars.coding.com.ParkLah.Database.AccountDB;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfoRecord;

public class CarparkDataManager {

    private AccountDB db;

    private List<CarparkInfoRecord> carparks;


    public CarparkDataManager(AccountDB db){
        this.db = db;
    }







}
