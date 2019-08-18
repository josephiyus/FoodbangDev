package id.foodbang.adapter;

import android.support.annotation.NonNull;

public interface IStepAdapter
{
    void next();
    void previous();

    @NonNull
    Boolean allowNext();

    @NonNull
    Boolean allowPrevious();
}
