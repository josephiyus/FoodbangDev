package id.foodbang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderRequest
{
    @SerializedName("order")
    @Expose
    private OrderItem order_item;

    public OrderItem getOrder_item()
    {
        return order_item;
    }

    public void setOrder_item(OrderItem order_item)
    {
        this.order_item = order_item;
    }
}