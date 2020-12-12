package com.pedro.fakenewsdetector.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pedro.fakenewsdetector.R;
import com.pedro.fakenewsdetector.controller.FakeNewsController;

public class FakeNewsActivity extends AppCompatActivity {
    private FakeNewsController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new FakeNewsController(this);
        controller.init();

        if(!controller.shownTutorial()) {
            startActivity(new Intent(this, TutorialActivity.class));
            return;
        }

        controller.checkFakeNews(
                "https://noticias.uol.com.br/colunas/josias-de-souza/2020/12/11/governo-bolsonaro-agora-cogita-confiscar-vacinas.htm",
                "Teste"
            );
    }
}