package id.foodbang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.liefery.android.vertical_stepper_view.VerticalStepperAdapter;

import id.foodbang.R;
import id.foodbang.orderstep.BookRequestStep;

public class MainStepperAdapter extends VerticalStepperAdapter {


    public MainStepperAdapter(Context context) {
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
        return false;
    }

    @NonNull
    @Override
    public View onCreateContentView(Context context, int position) {
        View content = new BookRequestStep( context );

        Button actionContinue = content.findViewById( R.id.btn_catering_finish );
        actionContinue.setEnabled( position < getCount() - 1 );
        actionContinue.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                next();
            }
        } );

        Button actionBack = content.findViewById( R.id.btn_paid );
        actionBack.setEnabled( position > 0 );
        actionBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick( View v ) {
                        previous();
                    }
                } );

        return content;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public void jumpTo(int position) {
        super.jumpTo(position);
    }

    @Override
    public boolean hasPrevious() {
        return super.hasPrevious();
    }

    @Nullable
    @Override
    public void next() {
        super.next();
    }

    @Nullable
    @Override
    public void previous() {
        super.previous();
    }
}
