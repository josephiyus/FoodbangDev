package id.foodbang;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.liefery.android.vertical_stepper_view.VerticalStepperView;

import id.foodbang.adapter.OrderStepAdapter;

public class OrderStepActivity extends AppCompatActivity {
    private VerticalStepperView bookOrderStepper;

    @Override
    protected void onDestroy() {
        if (this.bookOrderStepper != null) this.bookOrderStepper = null;
        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState)
    {
        super.onCreate(savedInstanceState, persistentState);
        super.setContentView(R.layout.activity_order_step);

        this.bookOrderStepper = super.findViewById(R.id.list_orderstep);
        this.bookOrderStepper.setAdapter(new OrderStepAdapter(this));
        this.setTitle("Booking Status");
    }
}
