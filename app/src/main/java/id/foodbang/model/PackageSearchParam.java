package id.foodbang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PackageSearchParam {

    @SerializedName("package_category_id")
    @Expose
    private List<String> package_category_id;

    @SerializedName("package_location_id")
    @Expose
    private List<String> package_location_id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("lowest_price")
    @Expose
    private String lowest_price;

    @SerializedName("highest_price")
    @Expose
    private String highest_price;

    @SerializedName("lowest_price_minportion")
    @Expose
    private String lowest_price_minportion;

    @SerializedName("highest_price_minportion")
    @Expose
    private String highest_price_minportion;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("rating")
    @Expose
    private String rating;

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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLowest_price_minportion() {
        return lowest_price_minportion;
    }

    public void setLowest_price_minportion(String lowest_price_minportion) {
        this.lowest_price_minportion = lowest_price_minportion;
    }

    public String getHighest_price_minportion() {
        return highest_price_minportion;
    }

    public void setHighest_price_minportion(String highest_price_minportion) {
        this.highest_price_minportion = highest_price_minportion;
    }

    public List<String> getPackage_location_id() {
        return package_location_id;
    }

    public void setPackage_location_id(List<String> package_location_id) {
        this.package_location_id = package_location_id;
    }
}
