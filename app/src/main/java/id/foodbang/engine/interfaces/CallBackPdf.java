package id.foodbang.engine.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Response;

public interface CallBackPdf {
    void onResponse(Response<ResponseBody> response);

    void onFailure(Throwable t);
}
