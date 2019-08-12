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

import com.liefery.android.vertical_stepper_view.VerticalStepperAdapter;

import java.util.List;

import id.foodbang.model.OrderStepItem;
import id.foodbang.orderstep.BookRequestStep;

public class OrderStepAdapter extends VerticalStepperAdapter {
    private List<OrderStepItem> list_items;
    private Integer current_position;

    public OrderStepAdapter(Context context, List<OrderStepItem> list_items) {
        super(context);
        this.list_items = list_items;
        this.current_position = 0;
    }

    @NonNull
    @Override
    public CharSequence getTitle(int position) {
        if (this.list_items != null)
            return this.list_items.get(position).getTitle();
        else
            return "";
    }

    @Nullable
    @Override
    public CharSequence getSummary(int position) {
        if (this.list_items != null)
            return this.list_items.get(position).getSummary();
        else
            return "";
    }

    @Override
    public boolean isEditable(int position) {
        if (this.list_items != null)
            return this.list_items.get(position).getIs_editable();
        else
            return false;
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

        return content;
    }

    @Override
    public int getCount() {
        if (this.list_items != null) return this.list_items.size();
        else return 0;
    }

    @Override
    public Object getItem(int i) {
        if (this.list_items != null) return this.list_items.get(i);
        else return null;
    }

}