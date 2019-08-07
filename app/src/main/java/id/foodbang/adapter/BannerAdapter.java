package id.foodbang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.R;
import id.foodbang.model.BannerPromo;

public class BannerAdapter extends PagerAdapter {
    private Context context;
    private List<BannerPromo> promos;

    @BindView(R.id.iv_banner) ImageView ivBanner;


    public BannerAdapter(Context context, List<BannerPromo> promos) {
        this.context = context;
        this.promos = promos;
    }

    @Override
    public int getCount() {
        return promos.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_coursel_banner, null);
        ButterKnife.bind(this, view);

        Picasso.with(context).load(promos.get(position).getUrl()).into(ivBanner);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
