package id.foodbang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liefery.android.vertical_stepper_view.VerticalStepperView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.adapter.OrderStepAdapter;

public class DeliveryActivity extends AppCompatActivity {

    @BindView(R.id.stepper_order)
    VerticalStepperView stepperOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Progress Status");

        stepperOrder.setStepperAdapter(new OrderStepAdapter(this));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
