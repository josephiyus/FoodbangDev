package id.foodbang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import id.foodbang.orderstep.IOrderStep;
import id.foodbang.orderstep.IOrderStepFactory;

public abstract class StepAdapter extends CustomVerticalStepperAdapter implements IStepAdapter
{
    @NonNull
    private final IOrderStepFactory factory;
    @Nullable
    private View currentView;

    StepAdapter(@NonNull Context context, @NonNull IOrderStepFactory factory)
    {
        super(context);
        this.factory = factory;
        super.initialize(context);
    }

    @NonNull
    protected IOrderStepFactory getFactory()
    {
        return this.factory;
    }

    @NonNull
    @Override
    public CharSequence getTitle(int position)
    {
        final IOrderStep orderStep = (IOrderStep) this.getContentViews().get(position);
        if(orderStep == null) return "";
        else return orderStep.getTitle();
    }

    @Nullable
    @Override
    public CharSequence getSummary(int position)
    {
        final IOrderStep orderStep = (IOrderStep) this.getContentViews().get(position);
        if(orderStep == null) return null;
        else return orderStep.getSummary();
    }

    @Override
    public boolean isEditable(int position)
    {
        return super.getFocus() == position;
    }

    @NonNull
    @Override
    public View onCreateContentView(Context context, int position)
    {
        this.currentView = this.getFactory().create(position, context);

        ((IOrderStep) this.currentView).setAdapter(this);

        return this.currentView;
    }

    @Override
    public void next()
    {
        if(this.currentView != null) ((IOrderStep) this.currentView).clean();
        super.next();
        ((IOrderStep) this.currentView).setAdapter(this);
    }

    @Override
    @NonNull
    public Boolean allowNext()
    {
        return this.getFocus() < this.getCount() - 1;
    }

    @Override
    public void previous()
    {
        if(this.currentView != null) ((IOrderStep) this.currentView).clean();
        super.previous();
        ((IOrderStep) this.currentView).setAdapter(this);
    }

    @Override
    @NonNull
    public Boolean allowPrevious()
    {
        return this.getFocus() > 0;
    }

    @Override
    public int getCount()
    {
        try
        {
            return this.getFactory().getCount();
        }
        catch (NullPointerException e)
        {
            return 0;
        }
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

}