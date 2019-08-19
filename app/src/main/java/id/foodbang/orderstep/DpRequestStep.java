package id.foodbang.orderstep;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import id.foodbang.R;
import id.foodbang.adapter.IStepAdapter;

public class DpRequestStep extends OrderStep
{
    public DpRequestStep(Context context)
    {
        super(context);
    }

    @Override
    @NonNull
    public String getTitle()
    {
        return "DP Requested";
    }

    @Override
    @Nullable
    public String getSummary()
    {
        return null;
    }

    @Override
    @NonNull
    public Boolean isEditable()
    {
        return true;
    }

    @Override
    protected void onCreate(View view)
    {
        final Button actionContinue = view.findViewById(R.id.btn_next_step);
        final Button actionBack = view.findViewById(R.id.btn_prev_step);

        actionContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final IStepAdapter IStepAdapter = getIStepAdapter();
                if(IStepAdapter != null) IStepAdapter.next();
            }
        });

        actionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final IStepAdapter IStepAdapter = getIStepAdapter();
                if(IStepAdapter != null) IStepAdapter.previous();
            }
        });
    }
}
