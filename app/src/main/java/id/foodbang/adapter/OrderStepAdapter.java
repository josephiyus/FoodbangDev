/**
 * Adapter untuk Stepper Order
 *
 * @author MufidJamaluddin
 */
package id.foodbang.adapter;

import android.content.Context;

import id.foodbang.orderstep.OrderStepFactory;

public class OrderStepAdapter extends StepAdapter
{
    public OrderStepAdapter(Context context)
    {
        super(context, new OrderStepFactory());
    }
}