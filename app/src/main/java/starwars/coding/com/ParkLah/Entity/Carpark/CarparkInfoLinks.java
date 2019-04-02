package starwars.coding.com.ParkLah.Entity.Carpark;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarparkInfoLinks {
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("next")
    @Expose
    private String next;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
