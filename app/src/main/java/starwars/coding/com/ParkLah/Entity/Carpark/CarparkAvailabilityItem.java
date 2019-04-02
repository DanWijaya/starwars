package starwars.coding.com.ParkLah.Entity.Carpark;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarparkAvailabilityItem {
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("carpark_data")
    @Expose
    private List<CarparkAvailabilityDatum> carparkData = null;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<CarparkAvailabilityDatum> getCarparkData() {
        return carparkData;
    }

    public void setCarparkData(List<CarparkAvailabilityDatum> carparkData) {
        this.carparkData = carparkData;
    }
}
