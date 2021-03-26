package com.gdtidm.bacaonline.Intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.gdtidm.bacaonline.Intro.ComponentsIntro.AdapterIntro;
import com.gdtidm.bacaonline.Intro.ComponentsIntro.ItemIntro;
import com.gdtidm.bacaonline.MainActivity;
import com.gdtidm.bacaonline.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    private TabLayout tabIndicator;
    private Button btnNext;
    private TextView tvSkip;
    private CardView cdGetStarted;

    int position = 0;
    Animation anim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (restorePrefData()) {
            Intent intent   =   new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_intro);

        btnNext         =   findViewById(R.id.btn_next);
        cdGetStarted    =   findViewById(R.id.cd_get_started);
        tabIndicator    =   findViewById(R.id.tab_indicator);
        anim            =   AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
        tvSkip          =   findViewById(R.id.tv_skip);

        List<ItemIntro> listIntro = new ArrayList<>();
        listIntro.add(new ItemIntro("Halo\nSelamat Datang!", "Salam kenal. Selamat datang di E-Baca.\nSelamat menikmati, yaa!", R.drawable.image01));
        listIntro.add(new ItemIntro("Cepat, Mudah, dan Interaktif", "E-Baca adalah sebuah media artikel di Android yang bertujuan meningkatkan minat baca anda", R.drawable.image02));
        listIntro.add(new ItemIntro("Mendukung Aktivitas Anda", "Anda dapat membaca artikel pada E-Baca kapan saja. Jadi, anda tidak perlu risau untuk membaca artikel di E-Baca", R.drawable.image03));

        screenPager = findViewById(R.id.screen_viewpager);
        AdapterIntro adapter = new AdapterIntro(this, listIntro);
        screenPager.setAdapter(adapter);
        tabIndicator.setupWithViewPager(screenPager);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < listIntro.size()) {
                    position++;
                    screenPager.setCurrentItem(position);
                }
                if (position == listIntro.size()-1) {
                    loadLastScreen();
                }
            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == listIntro.size() -1) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        cdGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent   =   new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                savePrefsData();
                finish();
            }
        });

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenPager.setCurrentItem(listIntro.size());
            }
        });

    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();
    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        cdGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        cdGetStarted.setAnimation(anim);
    }

}