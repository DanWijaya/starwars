package starwars.coding.com.ParkLah.Entity.Carpark;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarparkInfoRecord {
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
}
