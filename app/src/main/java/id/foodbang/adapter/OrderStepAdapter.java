/**
 * Adapter untuk Stepper Order
 *
 * @author MufidJamaluddin
 */
package id.foodbang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.liefery.android.vertical_stepper_view.VerticalStepperAdapter;

import java.util.ArrayList;
import java.util.List;

import id.foodbang.R;
import id.foodbang.model.OrderStepItem;
import id.foodbang.orderstep.BookRequestStep;

public class OrderStepAdapter extends VerticalStepperAdapter {
    private List<OrderStepItem> list_items;
    private Integer current_position;

    public OrderStepAdapter(Context context) {
        super(context);
        this.list_items = new ArrayList<>();
        this.current_position = 0;

        this.list_items.add(new OrderStepItem("Booking Requested", "", false));
        this.list_items.add(new OrderStepItem("Booking Approved", "", false));
        this.list_items.add(new OrderStepItem("DP Requested", "", false));
        this.list_items.add(new OrderStepItem("DP Approved", "", false));
        this.list_items.add(new OrderStepItem("Full Payment Requested", "", false));
        this.list_items.add(new OrderStepItem("Full Payment Approved", "", false));
        this.list_items.add(new OrderStepItem("Catering in Progress", "", false));
        this.list_items.add(new OrderStepItem("Catering Execute Today!", "", false));
        this.list_items.add(new OrderStepItem("Catering Done", "", false));
    }

    @NonNull
    @Override
    public CharSequence getTitle(int position) {
        return this.list_items.get(position).getTitle();
    }

    @Nullable
    @Override
    public CharSequence getSummary(int position) {
        return this.list_items.get(position).getSummary();
    }

    @Override
    public boolean isEditable(int position) {
        return this.list_items.get(position).getIs_editable();
    }

    public void setStep(@NonNull final Integer step) {
        if (step < this.getCount() && step >= 0) {
            if (step < this.current_position) {
                for (int i = step; i < this.current_position; i++) {
                    this.previous();
                }
            } else {
                for (int i = this.current_position; i < step; i++) {
                    this.next();
                }
            }
        }
    }

    @NonNull
    @Override
    public View onCreateContentView(Context context, int position) {
        View content = new BookRequestStep(context);

        this.current_position = position;

        final Button actionContinue = content.findViewById(R.id.btn_next_step);

        actionContinue.setEnabled(position < this.getCount() - 1);
        actionContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderStepAdapter.this.next();
            }
        });

        final Button actionBack = content.findViewById(R.id.btn_prev_step);

        actionBack.setEnabled(position > 0);
        actionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderStepAdapter.this.previous();
            }
        });

        return content;
    }

    @Override
    public int getCount() {
        return this.list_items.size();
    }

    @Override
    public Object getItem(int i) {
        return this.list_items.get(i);
    }

}