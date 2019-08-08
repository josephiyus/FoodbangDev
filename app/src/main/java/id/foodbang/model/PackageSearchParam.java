package id.foodbang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PackageSearchParam {

    @SerializedName("package_category_id")
    @Expose
    private List<String> package_category_id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("lowest_price")
    @Expose
    private String lowest_price;

    @SerializedName("highest_price")
    @Expose
    private String highest_price;

    @SerializedName("status")
    @Expose
    private String status;

    public List<String> getPackage_category_id() {
        return package_category_id;
    }

    public void setPackage_category_id(List<String> package_category_id) {
        this.package_category_id = package_category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLowest_price() {
        return lowest_price;
    }

    public void setLowest_price(String lowest_price) {
        this.lowest_price = lowest_price;
    }

    public String getHighest_price() {
        return highest_price;
    }

    public void setHighest_price(String highest_price) {
        this.highest_price = highest_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}