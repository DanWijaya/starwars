package starwars.coding.com.ParkLah.Database;

import starwars.coding.com.ParkLah.Entity.User;

public interface AccountDB {

    boolean checkUser(String email, String password);

    boolean checkUser(String email);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

}
