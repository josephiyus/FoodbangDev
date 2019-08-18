package id.foodbang.orderstep;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import id.foodbang.adapter.IStepAdapter;

public interface IOrderStep
{
    @NonNull
    String getTitle();

    @Nullable
    String getSummary();

    @NonNull
    Boolean isEditable();

    void setAdapter(IStepAdapter adapter);

    void clean();
}
