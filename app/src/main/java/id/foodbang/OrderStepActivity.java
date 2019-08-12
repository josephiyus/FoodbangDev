package id.foodbang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.liefery.android.vertical_stepper_view.VerticalStepperView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.adapter.OrderStepAdapter;
import id.foodbang.model.OrderData;
import id.foodbang.model.OrderStepItem;

public class OrderStepActivity extends AppCompatActivity {
    private final List<OrderStepItem> list_items = new ArrayList<>();
    private OrderData orderData;

    @BindView(R.id.list_orderstep)
    protected VerticalStepperView bookOrderStepper;

    @Override
    protected void onDestroy() {
        this.list_items.clear();
        if (this.orderData != null) this.orderData = null;
        if (this.bookOrderStepper != null) this.bookOrderStepper = null;

        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_step);
        ButterKnife.bind(this);

        this.list_items.add(new OrderStepItem("Booking Requested", "", false));
        this.list_items.add(new OrderStepItem("Booking Approved", "", false));
        this.list_items.add(new OrderStepItem("DP Requested", "", false));
        this.list_items.add(new OrderStepItem("DP Approved", "", false));
        this.list_items.add(new OrderStepItem("Full Payment Requested", "", false));
        this.list_items.add(new OrderStepItem("Full Payment Approved", "", false));
        this.list_items.add(new OrderStepItem("Catering in Progress", "", false));
        this.list_items.add(new OrderStepItem("Catering Execute Today!", "", false));
        this.list_items.add(new OrderStepItem("Catering Done", "", false));

        Intent intent = this.getIntent();

        OrderStepAdapter orderStepAdapter = new OrderStepAdapter(this, this.list_items);

        if (intent != null) {
            this.orderData = (OrderData) intent.getSerializableExtra("order_data");

            if (this.orderData != null) {
                TextView booking_status = this.findViewById(R.id.booking_status);
                booking_status.setText(this.orderData.getBookedStatus());

                TextView order_number = this.findViewById(R.id.order_number);
                order_number.setText(this.orderData.getCode());

                TextView order_packet = this.findViewById(R.id.order_packet);
                order_packet.setText(String.valueOf(this.orderData.getPackageId()));


            }

        }

        this.bookOrderStepper.setAdapter(orderStepAdapter);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Booking Status");
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }
}
