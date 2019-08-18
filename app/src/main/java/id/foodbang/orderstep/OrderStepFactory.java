package id.foodbang.orderstep;

import android.content.Context;
import android.view.View;

public class OrderStepFactory implements IOrderStepFactory
{
    @Override
    public Integer getCount()
    {
        return 9;
    }

    @Override
    public View create(Integer position, Context context)
    {
        switch (position)
        {
            case 0:
                return new BookingRequestStep(context);
            case 1:
                return new BookingApproveStep(context);
            case 2:
                return new DpRequestStep(context);
            case 3:
                return new DpApproveStep(context);
            case 4:
                return new FpRequestStep(context);
            case 5:
                return new FpApprovedStep(context);
            case 6:
                return new OnProgressStep(context);
            case 7:
                return new ExecuteTodayStep(context);
            case 8:
                return new DoneStep(context);
            default:
                return null;
        }
    }
}
