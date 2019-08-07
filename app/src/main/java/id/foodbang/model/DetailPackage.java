package id.foodbang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailPackage {

    @SerializedName("api_message")
    @Expose
    private String apiMessage;
    @SerializedName("api_http")
    @Expose
    private Integer apiHttp;
    @SerializedName("package")
    @Expose
    private PackageData _package;

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

    public PackageData get_package() {
        return _package;
    }

    public void set_package(PackageData _package) {
        this._package = _package;
    }
}
