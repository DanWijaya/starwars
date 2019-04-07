package starwars.coding.com.ParkLah.Database;

import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfoRecord;
import starwars.coding.com.ParkLah.Entity.User;

public interface DataBase {

    boolean checkUser(String email, String password);

    boolean checkUser(String email);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    void addCarparkInfo(CarparkInfoRecord record);

}
