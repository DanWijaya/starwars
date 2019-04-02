package starwars.coding.com.ParkLah.Database;
import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfoRecord;

public interface CarparkDB {
    void deleteAllEntries();
    void addCarparkInfo(CarparkInfoRecord record);
}
