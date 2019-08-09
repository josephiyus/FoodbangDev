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

import id.foodbang.R;
import id.foodbang.orderteststep.BookRequestStep;

public class OrderTestStepAdapter extends VerticalStepperAdapter {
    public OrderTestStepAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public CharSequence getTitle(int position) {
        return "Title " + position;
    }

    @Nullable
    @Override
    public CharSequence getSummary(int position) {
        return "Summary " + position;
    }

    @Override
    public boolean isEditable(int position) {
        return position == 1;
    }

    @NonNull
    @Override
    public View onCreateContentView(Context context, int position) {

        View content = new BookRequestStep(context);

        final Button actionContinue = content.findViewById(R.id.btn_next_step);

        actionContinue.setEnabled(position < this.getCount() - 1);
        actionContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });

        final Button actionBack = content.findViewById(R.id.btn_prev_step);

        actionBack.setEnabled(position > 0);
        actionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous();
            }
        });

        return content;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

}
