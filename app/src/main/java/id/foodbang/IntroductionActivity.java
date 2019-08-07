package id.foodbang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.rd.PageIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.adapter.IntroductionAdapter;
import id.foodbang.engine.session.LoginSession;

public class IntroductionActivity extends AppCompatActivity {

    @BindView(R.id.vp_intro)
    ViewPager vpIntro;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.btn_finish)
    Button btnFinish;
    @BindView(R.id.dots)
    PageIndicatorView dots;

    int cur_pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        ButterKnife.bind(this);

        final LoginSession loginSession = new LoginSession(getApplicationContext());

        if (loginSession.isLogin(LoginSession.IS_LOGIN)){
            startActivity(new Intent(getApplicationContext(), NavigationActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
        }

        checkState();

        IntroductionAdapter adapter = new IntroductionAdapter(this);
        vpIntro.setAdapter(adapter);

        vpIntro.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                cur_pos = vpIntro.getCurrentItem();
                checkState();
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cur_pos--;
                vpIntro.setCurrentItem(cur_pos, true);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cur_pos++;
                vpIntro.setCurrentItem(cur_pos, true);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
            }
        });
    }

    private void checkState(){
        if(cur_pos == 2){
            btnNext.setVisibility(View.GONE);
            btnFinish.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.VISIBLE);
        } else if(cur_pos == 0){
            btnNext.setVisibility(View.VISIBLE);
            btnFinish.setVisibility(View.GONE);
            btnBack.setVisibility(View.GONE);
        }else{
            btnNext.setVisibility(View.VISIBLE);
            btnFinish.setVisibility(View.GONE);
            btnBack.setVisibility(View.VISIBLE);
        }
    }

}
