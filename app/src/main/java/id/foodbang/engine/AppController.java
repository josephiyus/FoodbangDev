package id.foodbang.engine;

import java.util.Map;

import id.foodbang.engine.config.RetrofitSetting;
import id.foodbang.engine.interfaces.RetrofitCallback;
import id.foodbang.engine.interfaces.UrlInterface;
import id.foodbang.model.DetailPackage;
import id.foodbang.model.LoginBody;
import id.foodbang.model.LoginResponse;
import id.foodbang.model.MerchantResponse;
import id.foodbang.model.OrderListRequest;
import id.foodbang.model.OrderRequest;
import id.foodbang.model.OrderResponse;
import id.foodbang.model.Package;
import id.foodbang.model.PackageSearchParam;
import id.foodbang.model.PackageSortParam;
import id.foodbang.model.RegisterRequest;
import id.foodbang.model.RegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppController {
    private UrlInterface getAPI(){return RetrofitSetting.getAPI();}

    private String TOKEN;

    public AppController() {
    }

    public AppController(String TOKEN) {
        this.TOKEN = TOKEN;
    }

    public void login(LoginBody loginBody, final RetrofitCallback retrofitCallback) {

        final Call<LoginResponse> taskModelCall = getAPI().checkLogin(loginBody);

        taskModelCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                retrofitCallback.onResponse(response);

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                retrofitCallback.onFailure(t.getMessage());
            }
        });
    }

    public void getAllPackage(final RetrofitCallback retrofitCallback) {

        final Call<Package> taskModelCall = getAPI().packages();

        taskModelCall.enqueue(new Callback<Package>() {
            @Override
            public void onResponse(Call<Package> call, Response<Package> response) {
                retrofitCallback.onResponse(response);

            }

            @Override
            public void onFailure(Call<Package> call, Throwable t) {
                retrofitCallback.onFailure(t.getMessage());
            }
        });
    }

    public void getPackageByParameter(final RetrofitCallback retrofitCallback, PackageSearchParam searchKey, PackageSortParam sortKey) {

        final Call<Package> taskModelCall = getAPI().packagesByParam(searchKey, sortKey);

        taskModelCall.enqueue(new Callback<Package>() {
            @Override
            public void onResponse(Call<Package> call, Response<Package> response) {
                retrofitCallback.onResponse(response);

            }

            @Override
            public void onFailure(Call<Package> call, Throwable t) {
                retrofitCallback.onFailure(t.getMessage());
            }
        });
    }

    public void getPackage(int id, final  RetrofitCallback retrofitCallback){
        final Call<DetailPackage> taskModelCall = getAPI()._package(id);

        taskModelCall.enqueue(new Callback<DetailPackage>() {
            @Override
            public void onResponse(Call<DetailPackage> call, Response<DetailPackage> response) {
                retrofitCallback.onResponse(response);
            }

            @Override
            public void onFailure(Call<DetailPackage> call, Throwable t) {
                retrofitCallback.onFailure(t.getMessage());
            }
        });
    }

    public void order(OrderRequest order, final RetrofitCallback retrofitCallback){
        final Call<OrderResponse> taskModelCall = getAPI().orders(order, "Bearer " + TOKEN);

        taskModelCall.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                retrofitCallback.onResponse(response);
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                retrofitCallback.onFailure(t.getMessage());
            }
        });
    }

    public void merchant(int id, final  RetrofitCallback retrofitCallback){
        final Call<MerchantResponse> taskModelCall = getAPI().merchant(id);

        taskModelCall.enqueue(new Callback<MerchantResponse>() {
            @Override
            public void onResponse(Call<MerchantResponse> call, Response<MerchantResponse> response) {
                retrofitCallback.onResponse(response);
            }

            @Override
            public void onFailure(Call<MerchantResponse> call, Throwable t) {
                retrofitCallback.onFailure(t.getMessage());
            }
        });
    }

    public void register(final RegisterRequest request, final RetrofitCallback retrofitCallback){
        final Call<RegisterResponse> taskModelCall = getAPI().signup(request);

        taskModelCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                retrofitCallback.onResponse(response);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                retrofitCallback.onFailure(t.getMessage());
            }
        });
    }

    public void listOrder(final RetrofitCallback retrofitCallback){
        final Call<OrderListRequest> taskModelCall = getAPI().listOrder("Bearer " + TOKEN);

        taskModelCall.enqueue(new Callback<OrderListRequest>() {
            @Override
            public void onResponse(Call<OrderListRequest> call, Response<OrderListRequest> response) {
                retrofitCallback.onResponse(response);
            }

            @Override
            public void onFailure(Call<OrderListRequest> call, Throwable t) {
                retrofitCallback.onFailure(t.getMessage());
            }
        });
    }

    /*public void getCategories(String token, final RetrofitCallback retrofitCallback){
        final Call<CategoryResponse> taskModelCall = getAPI().categories("Bearer " + token);

        taskModelCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                retrofitCallback.onResponse(response);
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                retrofitCallback.onFailure(t.getMessage());
            }
       */
}