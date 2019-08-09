package id.foodbang;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.liefery.android.vertical_stepper_view.VerticalStepperView;

import id.foodbang.adapter.OrderStepAdapter;

public class OrderTestStepActivity extends AppCompatActivity {
    private VerticalStepperView bookOrderTestStepper;

    @Override
    protected void onDestroy() {
        if (this.bookOrderTestStepper != null) this.bookOrderTestStepper = null;
        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        super.setContentView(R.layout.activity_ordertest_step);

        this.bookOrderTestStepper = super.findViewById(R.id.list_orderteststep);
        this.bookOrderTestStepper.setAdapter(new OrderStepAdapter(this));
    }
}
