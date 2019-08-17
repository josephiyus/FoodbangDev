package id.foodbang;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.adapter.PackageAllAdapter;
import id.foodbang.common.FoodbangAppCompatActivity;
import id.foodbang.dialog.FilterDialog;
import id.foodbang.dialog.SortingDialog;
import id.foodbang.engine.AppController;
import id.foodbang.engine.interfaces.RetrofitCallback;
import id.foodbang.model.Package;
import id.foodbang.model.PackageData;
import id.foodbang.model.PackageSearchParam;
import id.foodbang.model.PackageSortParam;
import id.foodbang.utils.BottomNavigationBehavior;
import retrofit2.Response;

public class ListPackageActivity extends FoodbangAppCompatActivity
{

    @BindView(R.id.bnv_filter_sort_pkg)
    BottomNavigationView bnvPackage;

    @BindView(R.id.rv_package1)
    RecyclerView rvPackage;

    List<PackageData> packages = new ArrayList<>();

    private PackageSearchParam searchKey;
    private PackageSortParam sortKey;
    private AppController app;

    @Override
    protected void onDestroy() {
        if (this.packages != null) this.packages = null;
        if (this.sortKey != null) this.sortKey = null;
        if (this.app != null) this.app = null;

        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_package);
        ButterKnife.bind(this);

        this.app = new AppController();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("List Packages");
        }

        rvPackage.setHasFixedSize(true);
        rvPackage.setHorizontalScrollBarEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPackage.setLayoutManager(layoutManager);
        final PackageAllAdapter packageAdapter = new PackageAllAdapter(this, packages);
        rvPackage.setAdapter(packageAdapter);

        this.app.getAllPackage(new RetrofitCallback() {

            @Override
            public void onResponse(Response<?> response) {
                if(response.isSuccessful()){
                    Package pack = (Package) response.body();
                    assert pack != null;
                    packages.addAll(pack.getData());
                    packageAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "Ups! Something Wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String result) {
                Toast.makeText(getApplicationContext(), "Ups! Something Wrong!", Toast.LENGTH_SHORT).show();
            }
        });

        packageAdapter.notifyDataSetChanged();

        final FoodbangAppCompatActivity parent = this;
        Button bt = findViewById(R.id.btn_filter);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterDialog dialog = new FilterDialog();
                dialog.setParent(parent);
                dialog.show(getSupportFragmentManager(), "Filter Package List");
            }
        });

        bt = findViewById(R.id.btn_sort_by);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SortingDialog dialog = new SortingDialog();
                dialog.setParent(parent);
                dialog.show(getSupportFragmentManager(), "Sorting Package List");
            }
        });

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bnvPackage.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void parentProcess(Map<String, Object> param)
    {
        if(param.get("searchKey") != null)
        {
            searchKey = (PackageSearchParam) param.get("searchKey");
        }else if(param.get("sortKey") != null)
        {
            sortKey = (PackageSortParam) param.get("sortKey");
        }

        packages = new ArrayList<>();
        final PackageAllAdapter packageAdapter = new PackageAllAdapter(this, packages);
        rvPackage.setAdapter(packageAdapter);

        this.app.getPackageByParameter(
                new RetrofitCallback()
                {
                    @Override
                    public void onResponse(Response<?> response) {
                        if(response.isSuccessful())
                        {
                            Package pack = (Package) response.body();
                            assert pack != null;
                            packages.addAll(pack.getData());
                            packageAdapter.notifyDataSetChanged();
                        }
                        else {
                            String message;
                            try {
                                message = new String(response.errorBody().bytes());

                                Toast mtoast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
                                mtoast.setGravity(Gravity.CENTER, 0, 0);

                                TextView message_mtoast = mtoast.getView().findViewById(android.R.id.message);
                                message_mtoast.setTextSize(19);

                                mtoast.show();
                            } catch (IOException | NullPointerException e) {
                                e.printStackTrace();

                                message = "Ups! Something Wrong!";
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            }
                        }
                        show_list_packages(packages);
                    }

                    @Override
                    public void onFailure(String result) {
                        Toast.makeText(getApplicationContext(), "Ups! Something Wrong!", Toast.LENGTH_SHORT).show();
                        show_list_packages(packages);
                    }
                },
                searchKey,
                sortKey
        );
    }

    protected void show_list_packages(final List<PackageData> dtpackages) {
        RecyclerView rv_delivery_list = findViewById(R.id.rv_package1);
        TextView empty_text_view = findViewById(R.id.rv_empty_packages);

        if (dtpackages.isEmpty()) {
            rv_delivery_list.setVisibility(View.GONE);
            empty_text_view.setVisibility(View.VISIBLE);
        } else {
            rv_delivery_list.setVisibility(View.VISIBLE);
            empty_text_view.setVisibility(View.GONE);
        }
    }

}
