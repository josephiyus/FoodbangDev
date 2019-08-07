package id.foodbang.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package {

    @SerializedName("api_message")
    @Expose
    private String apiMessage;
    @SerializedName("api_http")
    @Expose
    private Integer apiHttp;
    @SerializedName("data")
    @Expose
    private List<PackageData> data = null;

    public String getApiMessage() {
        return apiMessage;
    }

    public void setApiMessage(String apiMessage) {
        this.apiMessage = apiMessage;
    }

    public Integer getApiHttp() {
        return apiHttp;
    }

    public void setApiHttp(Integer apiHttp) {
        this.apiHttp = apiHttp;
    }

    public List<PackageData> getData() {
        return data;
    }

    public void setData(List<PackageData> data) {
        this.data = data;
    }

}