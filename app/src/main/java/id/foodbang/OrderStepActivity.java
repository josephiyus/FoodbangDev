package id.foodbang;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.liefery.android.vertical_stepper_view.VerticalStepperView;

import id.foodbang.adapter.OrderStepAdapter;
import id.foodbang.model.OrderData;

public class OrderStepActivity extends AppCompatActivity {
    private VerticalStepperView bookOrderStepper;
    private OrderData orderData;

    @Override
    protected void onDestroy() {
        if (this.orderData != null) this.orderData = null;
        if (this.bookOrderStepper != null) this.bookOrderStepper = null;

        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        super.setContentView(R.layout.activity_order_step);

        final OrderStepAdapter orderStepAdapter = new OrderStepAdapter(this);

        Intent intent = this.getIntent();
        if (intent != null) {

            //this.orderData = (OrderData) intent.getSerializableExtra("order_data");

            if (this.orderData != null) {
                //orderStepAdapter.setStep(2);
                TextView booking_status = this.findViewById(R.id.booking_status);
                booking_status.setText(this.orderData.getBookedStatus());

                TextView order_number = this.findViewById(R.id.order_number);
                order_number.setText(this.orderData.getCode());

                TextView order_packet = this.findViewById(R.id.order_packet);
                order_packet.setText(String.valueOf(this.orderData.getPackageId()));


            }

        }

        this.bookOrderStepper = super.findViewById(R.id.list_orderstep);
        this.bookOrderStepper.setAdapter(orderStepAdapter);

        ActionBar actionBar = this.getActionBar();
        if (actionBar != null) {
            if (orderData != null) actionBar.setTitle("Booking Status");
        }
    }
}
