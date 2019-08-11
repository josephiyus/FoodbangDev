package id.foodbang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderResponse
{

    @SerializedName("api_message")
    @Expose
    private String apiMessage;

    @SerializedName("api_http")
    @Expose
    private Integer apiHttp;

    @SerializedName("data")
    @Expose
    private OrderData data;

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

    public OrderData getData() {
        return data;
    }

    public void setData(OrderData data) {
        this.data = data;
    }

}