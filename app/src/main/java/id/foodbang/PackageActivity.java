package id.foodbang;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.adapter.MenuAdapter;
import id.foodbang.engine.AppController;
import id.foodbang.engine.interfaces.RetrofitCallback;
import id.foodbang.model.DetailPackage;
import id.foodbang.model.Menu;
import id.foodbang.utils.BottomNavigationBehavior;
import id.foodbang.utils.ThousandSeparator;
import retrofit2.Response;

public class PackageActivity extends AppCompatActivity implements Target{

    @BindView(R.id.toolbar_package)
    Toolbar toolbar;
    @BindView(R.id.ct_layout)
    CollapsingToolbarLayout ctlLayout;
    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;
    @BindView(R.id.bnv_package)
    BottomNavigationView bnvPackage;
    @BindView(R.id.btn_order)
    Button btnOrder;
    @BindView(R.id.tv_package_name)
    TextView tvPackageName;
    @BindView(R.id.tv_package_price)
    TextView tvPackagePrice;
    @BindView(R.id.tv_package_description)
    TextView tvPackageDescription;
    @BindView(R.id.tv_package_portion)
    TextView tvPackagePortion;
    @BindView(R.id.tv_merchant_name)
    TextView tvMerchantName;
    @BindView(R.id.tv_merchant_address)
    TextView tvMerchantAddress;

    List<Menu> menus = new ArrayList<>();

    int package_id, portion, package_price;
    String package_image, package_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ctlLayout.setTitle("Package Name");

        if (getIntent().getStringExtra("package_image") == null)
            Picasso.with(this).load(getUriDrawable(R.drawable.no_image))
                    .into(this);
        else
            Picasso.with(this).load(getIntent().getStringExtra("package_image"))
                    .into(this);

        rvMenu.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvMenu.setLayoutManager(layoutManager);
        final MenuAdapter menuAdapter = new MenuAdapter(this, menus, false);
        rvMenu.setAdapter(menuAdapter);

        int id = getIntent().getIntExtra("package_id", 0);

        AppController app = new AppController();
        app.getPackage(id, new RetrofitCallback() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Response<?> response) {
                if (response.isSuccessful()){
                    DetailPackage detailPackage = (DetailPackage) response.body();
                    assert detailPackage != null;
                    package_id = detailPackage.get_package().getId();
                    String[] files = detailPackage.get_package().getFiles();
                    if(files.length > 0)
                        package_image = files[0];
                    package_name = detailPackage.get_package().getName();
                    package_price = detailPackage.get_package().getPrice();
                    portion = detailPackage.get_package().getPortion();

                    ctlLayout.setTitle(detailPackage.get_package().getName());
                    tvPackageName.setText(package_name);
                    tvPackageDescription.setText(detailPackage.get_package().getDescription());
                    tvPackagePrice.setText("Rp. " + ThousandSeparator.createCurrency(String.valueOf(package_price)));
                    tvPackagePortion.setText("Min porsi: " + portion);
                    tvMerchantName.setText(detailPackage.get_package().getMerchant());

                    menus.addAll(detailPackage.get_package().getMenus());
                    menuAdapter.notifyDataSetChanged();

                }else{
                    Toast.makeText(getApplicationContext(), "Ups! Something Wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String result) {
                Toast.makeText(getApplicationContext(), "Ups! Something Wrong!", Toast.LENGTH_SHORT).show();
            }
        });

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bnvPackage.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                intent.putExtra("package_id", package_id);
                intent.putExtra("package_image", package_image);
                intent.putExtra("package_name", package_name);
                intent.putExtra("package_price", package_price);
                intent.putExtra("portion", portion);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ctlLayout.setBackground(drawable);
        }
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private Uri getUriDrawable(int resId){
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getResources().getResourcePackageName(resId)
                + '/' + getResources().getResourceTypeName(resId) + '/' + getResources().getResourceEntryName(resId));
    }
}
