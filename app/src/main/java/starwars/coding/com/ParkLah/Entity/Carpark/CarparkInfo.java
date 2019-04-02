package starwars.coding.com.ParkLah.Entity.Carpark;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarparkInfo {
    @SerializedName("help")
    @Expose
    private String help;
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("result")
    @Expose
    private CarparkInfoResult result;

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public CarparkInfoResult getResult() {
        return result;
    }

    public void setResult(CarparkInfoResult result) {
        this.result = result;
    }
}
