package starwars.coding.com.ParkLah.Entity.Carpark;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarparkInfoResult {
    @SerializedName("resource_id")
    @Expose
    private String resourceId;
    @SerializedName("fields")
    @Expose
    private List<CarparkInfoField> fields = null;
    @SerializedName("records")
    @Expose
    private List<CarparkInfoRecord> records = null;
    @SerializedName("_links")
    @Expose
    private CarparkInfoLinks links;
    @SerializedName("total")
    @Expose
    private int total;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public List<CarparkInfoField> getFields() {
        return fields;
    }

    public void setFields(List<CarparkInfoField> fields) {
        this.fields = fields;
    }

    public List<CarparkInfoRecord> getRecords() {
        return records;
    }

    public void setRecords(List<CarparkInfoRecord> records) {
        this.records = records;
    }

    public CarparkInfoLinks getLinks() {
        return links;
    }

    public void setLinks(CarparkInfoLinks links) {
        this.links = links;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
