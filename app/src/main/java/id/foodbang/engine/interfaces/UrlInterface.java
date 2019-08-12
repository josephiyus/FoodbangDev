package id.foodbang.engine.interfaces;

import id.foodbang.engine.config.UrlService;
import id.foodbang.model.DetailPackage;
import id.foodbang.model.LoginBody;
import id.foodbang.model.LoginResponse;
import id.foodbang.model.MerchantResponse;
import id.foodbang.model.OrderListRequest;
import id.foodbang.model.OrderRequest;
import id.foodbang.model.OrderResponse;
import id.foodbang.model.Package;
import id.foodbang.model.PackageListRequest;
import id.foodbang.model.RegisterRequest;
import id.foodbang.model.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UrlInterface {
    @Headers("Content-Type:application/json")
    @POST(UrlService.login)
    Call<LoginResponse> checkLogin(@Body LoginBody loginBody);

    @Headers("Content-Type:application/json")
    @GET(UrlService.packages)
    Call<Package> packages();

    @Headers("Content-Type:application/json")
    @POST(UrlService.packages)
    Call<Package> packagesByParam(@Body PackageListRequest searchKey);

    @Headers("Content-Type:application/json")
    @GET(UrlService._package)
    Call<DetailPackage> _package(@Path("id") int id);

    @Headers("Content-Type:application/json")
    @POST(UrlService.orders)
    Call<OrderResponse> orders(@Body OrderRequest leads, @Header("Authorization") String auth);

    @Headers("Content-Type:application/json")
    @GET(UrlService.orders)
    Call<OrderListRequest> listOrder(@Header("Authorization") String auth);

    @Headers("Content-Type:application/json")
    @GET(UrlService.merchant)
    Call<MerchantResponse> merchant(@Path("id") int id);

    @Headers("Content-Type:application/json")
    @POST(UrlService.signup)
    Call<RegisterResponse> signup(@Body RegisterRequest reg);

    /*@Headers("Content-Type:application/json")
    @GET(UrlService.products)
    Call<ProductResponse> products(@Query("brand_id") int id, @Header("Authorization") String auth);

    @Headers("Content-Type:application/json")
    @GET(UrlService.specifications)
    Call<SpecificationResponse> specifications(@Query("product_id") int id, @Header("Authorization") String auth);

    @Headers("Content-Type:application/json")
    @GET(UrlService.branches)
    Call<BranchResponse> branches(@Header("Authorization") String auth);

    @Headers("Content-Type:application/json")
    @POST(UrlService.create_leads)
    Call<CreateLeadsResponse> createLeads(@Body Leads leads, @Header("Authorization") String auth);

    @Headers("Content-Type:application/json")
    @PUT(UrlService.update_leads)
    Call<CreateLeadsResponse> updateLeads(@Body Leads leads, @Path("code") String code, @Header("Authorization") String auth);*/
}
