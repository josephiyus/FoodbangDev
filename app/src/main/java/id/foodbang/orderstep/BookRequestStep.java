package id.foodbang.orderstep;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import id.foodbang.R;

public class BookRequestStep extends LinearLayout {

    public BookRequestStep(Context context) {
        super(context);
        initialize(context);
    }

    public BookRequestStep(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public BookRequestStep(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context ) {
        setClipChildren( true );
        setOrientation( VERTICAL );

        LayoutInflater.from( context ).inflate( R.layout.item_step_order, this, true );
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Log.wtf( "FOODBANG", "Saving state" );
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Log.wtf( "FOODBANG", "Restoring state" );
        super.onRestoreInstanceState(state);
    }
}