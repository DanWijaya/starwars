package starwars.coding.com.ParkLah.Entity.Carpark;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class CarparkInfoRecord{
    @SerializedName("short_term_parking")
    @Expose
    private String shortTermParking;
    @SerializedName("car_park_type")
    @Expose
    private String carParkType;
    @SerializedName("y_coord")
    @Expose
    private String yCoord;
    @SerializedName("x_coord")
    @Expose
    private String xCoord;
    @SerializedName("free_parking")
    @Expose
    private String freeParking;
    @SerializedName("gantry_height")
    @Expose
    private String gantryHeight;
    @SerializedName("car_park_basement")
    @Expose
    private String carParkBasement;
    @SerializedName("night_parking")
    @Expose
    private String nightParking;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("car_park_decks")
    @Expose
    private String carParkDecks;
    @SerializedName("_id")
    @Expose
    private int id;
    @SerializedName("car_park_no")
    @Expose
    private String carParkNo;
    @SerializedName("type_of_parking_system")
    @Expose
    private String typeOfParkingSystem;

    private int totalLots;

    private double distanceToDevice;

    public double getDistanceToDevice() {
        return distanceToDevice;
    }

    public void setDistanceToDevice(double distanceToDevice) {
        this.distanceToDevice = distanceToDevice;
    }

    public String getyCoord() {
        return yCoord;
    }

    public void setyCoord(String yCoord) {
        this.yCoord = yCoord;
    }

    public String getxCoord() {
        return xCoord;
    }

    public void setxCoord(String xCoord) {
        this.xCoord = xCoord;
    }

    public int getTotalLots() {
        return totalLots;
    }

    public void setTotalLots(int totalLots) {
        this.totalLots = totalLots;
    }

    public String getLotType() {
        return lotType;
    }

    public void setLotType(String lotType) {
        this.lotType = lotType;
    }

    public int getLotsAvailable() {
        return lotsAvailable;
    }

    public void setLotsAvailable(int lotsAvailable) {
        this.lotsAvailable = lotsAvailable;
    }

    private String lotType;

    private int lotsAvailable;


//    private int totalLots;

//    public int getTotalLots() {
//        return totalLots;
//    }

//    public void setTotalLots(int totalLots) {
//        this.totalLots = totalLots;
//    }

//    public int getLotsAvailable() {
//        return lotsAvailable;
//    }

//    public void setLotsAvailable(int lotsAvailable) {
//        this.lotsAvailable = lotsAvailable;
//    }

//    private int lotsAvailable;

    public String getShortTermParking() {
        return shortTermParking;
    }

    public void setShortTermParking(String shortTermParking) {
        this.shortTermParking = shortTermParking;
    }

    public String getCarParkType() {
        return carParkType;
    }

    public void setCarParkType(String carParkType) {
        this.carParkType = carParkType;
    }

    public String getYCoord() {
        return yCoord;
    }

    public void setYCoord(String yCoord) {
        this.yCoord = yCoord;
    }

    public String getXCoord() {
        return xCoord;
    }

    public void setXCoord(String xCoord) {
        this.xCoord = xCoord;
    }

    public String getFreeParking() {
        return freeParking;
    }

    public void setFreeParking(String freeParking) {
        this.freeParking = freeParking;
    }

    public String getGantryHeight() {
        return gantryHeight;
    }

    public void setGantryHeight(String gantryHeight) {
        this.gantryHeight = gantryHeight;
    }

    public String getCarParkBasement() {
        return carParkBasement;
    }

    public void setCarParkBasement(String carParkBasement) {
        this.carParkBasement = carParkBasement;
    }

    public String getNightParking() {
        return nightParking;
    }

    public void setNightParking(String nightParking) {
        this.nightParking = nightParking;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarParkDecks() {
        return carParkDecks;
    }

    public void setCarParkDecks(String carParkDecks) {
        this.carParkDecks = carParkDecks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarParkNo() {
        return carParkNo;
    }

    public void setCarParkNo(String carParkNo) {
        this.carParkNo = carParkNo;
    }

    public String getTypeOfParkingSystem() {
        return typeOfParkingSystem;
    }

    public void setTypeOfParkingSystem(String typeOfParkingSystem) {
        this.typeOfParkingSystem = typeOfParkingSystem;
    }

    public static class Comparators {

        public static Comparator<CarparkInfoRecord> AVAILABLESLOTS = new Comparator<CarparkInfoRecord>() {
            @Override
            public int compare(CarparkInfoRecord o1, CarparkInfoRecord o2) {
                return o1.getLotsAvailable() - o2.getLotsAvailable();
            }
        };
        public static Comparator<CarparkInfoRecord> TOTALSLOTS = new Comparator<CarparkInfoRecord>() {
            @Override
            public int compare(CarparkInfoRecord o1, CarparkInfoRecord o2) {
                return o1.getTotalLots() - o2.getTotalLots();
            }
        };
        public static Comparator<CarparkInfoRecord> DISTANCE = new Comparator<CarparkInfoRecord>() {
            @Override
            public int compare(CarparkInfoRecord o1, CarparkInfoRecord o2) {
                if(o1.getDistanceToDevice() > o2.getDistanceToDevice()) return 1;
                if(o1.getDistanceToDevice() < o2.getDistanceToDevice()) return -1;
                return 0;
            }
        };
    }
}
