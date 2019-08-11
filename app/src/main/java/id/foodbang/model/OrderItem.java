package id.foodbang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderItem
{
    @SerializedName("package_id")
    @Expose
    private Integer packageId;

    @SerializedName("request_order_date")
    @Expose
    private String requestOrderDate;

    @SerializedName("request_order_location")
    @Expose
    private String requestOrderLocation;

    @SerializedName("request_order_portion")
    @Expose
    private Integer requestOrderPortion;

    @SerializedName("request_order_note")
    @Expose
    private String requestOrderNote;

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getRequestOrderDate() {
        return requestOrderDate;
    }

    public void setRequestOrderDate(String requestOrderDate) {
        this.requestOrderDate = requestOrderDate;
    }

    public String getRequestOrderLocation() {
        return requestOrderLocation;
    }

    public void setRequestOrderLocation(String requestOrderLocation) {
        this.requestOrderLocation = requestOrderLocation;
    }

    public Integer getRequestOrderPortion() {
        return requestOrderPortion;
    }

    public void setRequestOrderPortion(Integer requestOrderPortion) {
        this.requestOrderPortion = requestOrderPortion;
    }

    public String getRequestOrderNote() {
        return requestOrderNote;
    }

    public void setRequestOrderNote(String requestOrderNote) {
        this.requestOrderNote = requestOrderNote;
    }
}