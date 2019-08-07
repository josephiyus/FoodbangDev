package id.foodbang.engine.interfaces;

import retrofit2.Response;

public interface RetrofitCallback {
    void onResponse(Response<?> response);

    void onFailure(String result);
}
