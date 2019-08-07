package id.foodbang.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.R;

public class IntroductionAdapter extends PagerAdapter {
    private Context context;

    @BindView(R.id.iv_intro)
    ImageView ivIntro;
    @BindView(R.id.tv_intro_desc)
    TextView tvIntro;

    public IntroductionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return content.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.introduction_layout, null);
        ButterKnife.bind(this, view);

        Picasso.with(context).load(getUriDrawable(content[position])).into(ivIntro);
        tvIntro.setText(descriptions[position]);

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

    private int[] descriptions = {
            R.string.welcome_screen_one,
            R.string.welcome_screen_two,
            R.string.welcome_screen_three
    };

    private int[] content = {
            R.drawable.welcome_screen_1,
            R.drawable.welcome_screen_2,
            R.drawable.welcome_screen_3
    };

    private Uri getUriDrawable(int resId){
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(resId)
                + '/' + context.getResources().getResourceTypeName(resId) + '/' + context.getResources().getResourceEntryName(resId));
    }
}
