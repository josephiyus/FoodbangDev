package id.foodbang.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

import com.liefery.android.vertical_stepper_view.VerticalStepperAdapter;

public abstract class CustomVerticalStepperAdapter extends VerticalStepperAdapter
{
    private SparseArray<View> contentViews;

    public CustomVerticalStepperAdapter(Context context)
    {
        super(context);
    }

    public void initialize(Context context)
    {
        this.contentViews = new SparseArray<>(this.getCount());
        for ( int i = 0; i < getCount(); i++ )
        {
            getContentView(context, i);
        }
    }

    @Override
    public SparseArray<View> getContentViews()
    {
        return contentViews;
    }

    private View getContentView(Context context, int position)
    {
        int id = (int) getItemId(position);
        if(contentViews != null)
        {
            View contentView = contentViews.get(id);

            if (contentView == null) {
                contentView = onCreateContentView(context, position);
                contentViews.put(id, contentView);
            }

            return contentView;
        }
        return null;
    }

    public void invalidateContentView(int position)
    {
        if(contentViews != null)
        {
            int id = (int) getItemId(position);
            contentViews.remove(id);
            notifyDataSetChanged();
        }
    }

}