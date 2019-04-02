package starwars.coding.com.ParkLah.Entity.Carpark;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarparkAvailability {
    @SerializedName("items")
    @Expose
    private List<CarparkAvailabilityItem> items = null;

    public List<CarparkAvailabilityItem> getItems() {
        return items;
    }

    public void setItems(List<CarparkAvailabilityItem> items) {
        this.items = items;
    }
}
