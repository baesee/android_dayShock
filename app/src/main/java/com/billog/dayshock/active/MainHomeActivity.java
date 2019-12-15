package com.billog.dayshock.active;

import android.animation.ArgbEvaluator;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.FrameLayout;

import com.billog.dayshock.R;
import com.billog.dayshock.fragment.ChallengeFragment;
import com.billog.dayshock.fragment.HomeFragment;
import com.billog.dayshock.fragment.SettingFragment;
import com.billog.dayshock.fragment.WriteFragment;
import com.billog.dayshock.model.Article;
import com.billog.dayshock.util.BottomNavigationHelper;
import com.billog.dayshock.view.IMainHomeView;

import java.util.ArrayList;
import java.util.List;

public class MainHomeActivity extends AppCompatActivity implements IMainHomeView {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    private HomeFragment homeFragment;
    private ChallengeFragment challengeFragment;
    private WriteFragment writeFragment;
    private SettingFragment settingFragment;

    ViewPager viewPager;
    MainHomeAdapter adapter;
    List<Article> articles;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        frameLayout = (FrameLayout)findViewById(R.id.main_frame);

        // 바텀 네비게이션뷰의 위치가 변하지 않도록 하기 위함.
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.main_nav);
        BottomNavigationHelper bottomNavigationHelper = new BottomNavigationHelper();
        bottomNavigationHelper.disableShiftMode(bottomNavigationView);

        homeFragment = new HomeFragment();
        challengeFragment = new ChallengeFragment();
        writeFragment = new WriteFragment();
        settingFragment = new SettingFragment();

        setFragment(homeFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_home:
                        bottomNavigationView.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(homeFragment);
                        return true;

                    case R.id.nav_challenge:
                        bottomNavigationView.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(challengeFragment);
                        return true;

                    case R.id.nav_write:
                        bottomNavigationView.setItemBackgroundResource(R.color.color4);
                        setFragment(writeFragment);
                        return true;

                    case R.id.nav_setting:
                        bottomNavigationView.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(settingFragment);
                        return true;

                    default:
                        return false;
                }

            }


        });

        articles = new ArrayList<>();
        articles.add(new Article(R.drawable.brochure, "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
        articles.add(new Article(R.drawable.sticker, "Sticker", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"));
        articles.add(new Article(R.drawable.poster, "Poster", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface."));
        articles.add(new Article(R.drawable.namecard, "Namecard", "Business cards are cards bearing business information about a company or individual."));

        adapter = new MainHomeAdapter(articles, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (adapter.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                } else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void setFragment(Fragment fragment){
        Log.e("BBBBBBB","========> Clicked Fragment : " + fragment);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onLoginSuccess(String message) {

    }

    @Override
    public void onLoginError(String message) {

    }


}
