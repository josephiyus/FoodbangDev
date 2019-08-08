package id.foodbang.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.ListPackageActivity;
import id.foodbang.R;
import id.foodbang.adapter.BannerAdapter;
import id.foodbang.adapter.PackageAdapter;
import id.foodbang.engine.AppController;
import id.foodbang.engine.interfaces.RetrofitCallback;
import id.foodbang.model.BannerPromo;
import id.foodbang.model.Package;
import id.foodbang.model.PackageData;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.vp_banner)
    ViewPager vpBanner;
    @BindView(R.id.rv_package)
    RecyclerView rvPackage;
    @BindView(R.id.btn_see_all)
    Button btnSeeAll;

    List<BannerPromo> promos;
    int cur_pos = 0;

    final Handler handler = new Handler();
    Runnable runnable;
    final Timer timer = new Timer();

    List<PackageData> packData = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        promos = new ArrayList<>();
        promos.add(new BannerPromo("Promo One", "https://previews.123rf.com/images/jchizhe/jchizhe1809/jchizhe180900137/108596600-fast-food-banner-juicy-meat-burgers-with-cheese-lettuce-on-yellow-background-take-away-meal-unhealth.jpg"));
        promos.add(new BannerPromo("Promo Two", "https://previews.123rf.com/images/lubovchipurko/lubovchipurko1804/lubovchipurko180400037/99923427-hand-drawn-fast-food-banner-street-food-top-view-background-hand-drawn-vector-illustration-can-be-us.jpg"));
        promos.add(new BannerPromo("Promo Three", "https://kulchaexpress.co.uk/wp-content/uploads/2018/06/BannerImage.jpg"));
        promos.add(new BannerPromo("Promo Four", "http://www.sagarseaham.com/wp-content/uploads/cropped-Indian-Food-wikicont.jpg"));

        BannerAdapter bannerAdapter = new BannerAdapter(getContext(), promos);
        vpBanner.setAdapter(bannerAdapter);
//        bannerSlideShow();

        vpBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                cur_pos = vpBanner.getCurrentItem();
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        rvPackage.setHasFixedSize(true);
        rvPackage.setHorizontalScrollBarEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvPackage.setLayoutManager(layoutManager);
        final PackageAdapter packageAdapter = new PackageAdapter(getContext(), packData);
        rvPackage.setAdapter(packageAdapter);

        btnSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ListPackageActivity.class));
            }
        });

        AppController app = new AppController();

        app.getAllPackage(new RetrofitCallback() {
            @Override
            public void onResponse(Response<?> response) {
                if (response.isSuccessful()){
                    Package pack = (Package) response.body();
                    assert pack != null;
                    packData.addAll(pack.getData());
                    packageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(String result) {

            }
        });

        return view;
    }

    private void bannerSlideShow(){
        runnable = new Runnable() {
            @Override
            public void run() {
                if(cur_pos == promos.size())
                    cur_pos = 0;
                vpBanner.setCurrentItem(cur_pos++, true);
            }
        };

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 1000, 2000);
    }

}
