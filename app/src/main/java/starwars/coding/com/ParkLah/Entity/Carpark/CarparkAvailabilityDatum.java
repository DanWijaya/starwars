package starwars.coding.com.ParkLah.Entity.Carpark;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarparkAvailabilityDatum {
    @SerializedName("carpark_info")
    @Expose
    private List<CarparkAvailabilityInfo> carparkInfo = null;
    @SerializedName("carpark_number")
    @Expose
    private String carparkNumber;
    @SerializedName("update_datetime")
    @Expose
    private String updateDatetime;

    public List<CarparkAvailabilityInfo> getCarparkInfo() {
        return carparkInfo;
    }

    public void setCarparkInfo(List<CarparkAvailabilityInfo> carparkInfo) {
        this.carparkInfo = carparkInfo;
    }

    public String getCarparkNumber() {
        return carparkNumber;
    }

    public void setCarparkNumber(String carparkNumber) {
        this.carparkNumber = carparkNumber;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}
