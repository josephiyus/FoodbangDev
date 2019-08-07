package id.foodbang;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.adapter.MenuAdapter;
import id.foodbang.engine.AppController;
import id.foodbang.engine.interfaces.RetrofitCallback;
import id.foodbang.model.DetailPackage;
import id.foodbang.model.Menu;
import id.foodbang.model.Merchant;
import id.foodbang.model.MerchantResponse;
import id.foodbang.model.OrderData;
import id.foodbang.utils.RoundedTransformation;
import id.foodbang.utils.ThousandSeparator;
import retrofit2.Response;

public class ReviewOrderActivity extends AppCompatActivity {

    @BindView(R.id.iv_package)
    ImageView ivPackage;
    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;
    @BindView(R.id.btn_home)
    Button btnHome;

    @BindView(R.id.tv_subtotal)
    TextView tvSubtotal;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_tax)
    TextView tvTax;
    @BindView(R.id.tv_package_name)
    TextView tvPackageName;
    @BindView(R.id.tv_merchant_name)
    TextView tvMerchantName;
    @BindView(R.id.tv_merchant_address)
    TextView tvMerchantAddress;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_note)
    TextView tvNote;


    int real_price;
    OrderData orderData;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_order);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Review Pesanan");

        AppController app = new AppController();

        real_price = getIntent().getIntExtra("real_price", 0);
        orderData = (OrderData) getIntent().getSerializableExtra("order_data");

        tvPackageName.setText(getIntent().getStringExtra("package_name"));

        int tax = (int) (0.1 * real_price);
        int total = real_price + tax;

        tvSubtotal.setText(ThousandSeparator.createCurrency(String.valueOf(real_price)));
        tvTax.setText(ThousandSeparator.createCurrency(String.valueOf(tax)));
        tvTotal.setText("Rp. " + ThousandSeparator.createCurrency(String.valueOf(total)));

        String[] dateTime = getDateTime(orderData.getRequestOrderDate());

        tvDate.setText(dateTime[0]);
        tvTime.setText(dateTime[1]);
        tvLocation.setText(orderData.getRequestOrderLocation());

        tvNote.setText(orderData.getRequestOrderNote());

        if (getIntent().getStringExtra("package_image") == null)
            Picasso.with(this).load(getUriDrawable(R.drawable.no_image))
                    .transform(new RoundedTransformation(8, 0))
                    .into(ivPackage);
        else
            Picasso.with(this).load(getIntent().getStringExtra("package_image"))
                    .into(ivPackage);


        final List<Menu> menus = new ArrayList<>();

        rvMenu.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvMenu.setLayoutManager(layoutManager);
        final MenuAdapter menuAdapter = new MenuAdapter(this, menus, true);
        rvMenu.setAdapter(menuAdapter);

        app.getPackage(orderData.getPackageId(), new RetrofitCallback() {
            @Override
            public void onResponse(Response<?> response) {
                if(response.isSuccessful()){
                    DetailPackage detailPackage = (DetailPackage) response.body();
                    menus.addAll(detailPackage.get_package().getMenus());
                    menuAdapter.notifyDataSetChanged();
                } else{
                    Toast.makeText(getApplicationContext(), "Ups! Something Wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String result) {
                Toast.makeText(getApplicationContext(), "Ups! Something Wrong!", Toast.LENGTH_SHORT).show();
            }
        });

        app.merchant(orderData.getMerchantId(), new RetrofitCallback() {
            @Override
            public void onResponse(Response<?> response) {
                if(response.isSuccessful()){
                    MerchantResponse merchantResponse = (MerchantResponse) response.body();
                    assert merchantResponse != null;
                    Merchant merchant = merchantResponse.getData();
                    tvMerchantName.setText(merchant.getName());
                    tvMerchantAddress.setText(merchant.getLocation());
                } else{
                    Toast.makeText(getApplicationContext(), "Ups! Something Wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String result) {
                Toast.makeText(getApplicationContext(), "Ups! Something Wrong!", Toast.LENGTH_SHORT).show();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        backToHome();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        backToHome();
        super.onBackPressed();
    }

    private Uri getUriDrawable(int resId){
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(resId)
                + '/' + getResources().getResourceTypeName(resId) + '/' + getResources().getResourceEntryName(resId));
    }

    @SuppressLint("SimpleDateFormat")
    private String[] getDateTime(String dateTime){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date date = null;//You will get date object relative to server/client timezone wherever it is parsed
        try {
            date = dateFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat formatDate = new SimpleDateFormat("EEE, d MMM yyyy"); //If you need time just put specific format for time like 'HH:mm:ss'
        DateFormat formatTime = new SimpleDateFormat("HH:mm");
        String[] date_time = {formatDate.format(date), formatTime.format(date)};

        return date_time;
    }

    private void backToHome(){
        startActivity(new Intent(getApplicationContext(), NavigationActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
    }
}
