package com.pedro.fakenewsdetector.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.pedro.fakenewsdetector.R;
import com.pedro.fakenewsdetector.controller.FakeNewsController;

public class FakeNewsActivity extends AppCompatActivity {
    private FakeNewsController controller;
    private FakeNewsPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_news);

        confController();
        if(!controller.shownTutorial()) {
            startActivity(new Intent(this, TutorialActivity.class));
            return;
        }

        confTabLayout();
    }

    private void confController() {
        controller = new FakeNewsController(this);
        controller.init();

//        controller.checkFakeNews(
//            "https://noticias.uol.com.br/colunas/josias-de-souza/2020/12/11/governo-bolsonaro-agora-cogita-confiscar-vacinas.htm",
//            "Teste"
//        );
    }

    private void confTabLayout() {
        ViewPager pager = findViewById(R.id.fake_news_pager);
        TabLayout tabLayout = findViewById(R.id.fake_news_options);
        adapter = new FakeNewsPagerAdapter(getSupportFragmentManager(), this, controller);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 1) {
                    adapter.refresh();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}