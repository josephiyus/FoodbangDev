package id.foodbang.orderstep;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import id.foodbang.R;
import id.foodbang.adapter.IStepAdapter;

public abstract class OrderStep extends LinearLayout implements IOrderStep
{
    private IStepAdapter stepAdapter;

    public OrderStep(Context context)
    {
        super(context);
        initialize(context);
    }

    public OrderStep(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        initialize(context);
    }

    public OrderStep(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @Override
    public void setAdapter(IStepAdapter stepAdapter)
    {
        this.stepAdapter = stepAdapter;
    }

    @Nullable
    public IStepAdapter getIStepAdapter()
    {
        return this.stepAdapter;
    }

    @Override
    public void clean()
    {
        // UnBind Ref
        this.stepAdapter = null;
    }

    protected void initialize(Context context)
    {
        setClipChildren(true);
        setOrientation(VERTICAL);

        final View view = LayoutInflater.from(context).inflate(R.layout.step_order_main, this, true);

        this.onCreate(view);
    }

    protected abstract void onCreate(final View view);

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState()
    {
        Log.wtf( "FOODBANG", "Saving state" );
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state)
    {
        Log.wtf( "FOODBANG", "Restoring state" );
        super.onRestoreInstanceState(state);
    }
}
