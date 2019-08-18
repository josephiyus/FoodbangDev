package id.foodbang.orderstep;

import android.content.Context;
import android.view.View;

public interface IOrderStepFactory
{
    Integer getCount();
    View create(Integer position, Context context);
}
