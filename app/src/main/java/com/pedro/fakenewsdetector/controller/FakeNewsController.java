package com.pedro.fakenewsdetector.controller;

import android.util.Log;

import com.pedro.fakenewsdetector.api.service.FakeNewsService;
import com.pedro.fakenewsdetector.api.service.ParseUrlService;
import com.pedro.fakenewsdetector.model.FakeNewsModel;
import com.pedro.fakenewsdetector.model.SystemModel;
import com.pedro.fakenewsdetector.model.dao.FakeNewsDAO;
import com.pedro.fakenewsdetector.model.dao.SystemDAO;
import com.pedro.fakenewsdetector.view.FakeNewsActivity;

import java.util.Date;

import okhttp3.ResponseBody;

public class FakeNewsController {
    private FakeNewsActivity view;
    private ParseUrlService parseUrlService;
    private FakeNewsService fakeNewsService;
    private FakeNewsDAO fakeNewsDAO;
    private SystemDAO systemDAO;

    public FakeNewsController(FakeNewsActivity view) {
        this.view = view;
    }

    public void init() {
        parseUrlService = new ParseUrlService();
        fakeNewsService = new FakeNewsService();
        fakeNewsDAO = new FakeNewsDAO(view);
        systemDAO = new SystemDAO(view);
    }

    public void checkFakeNews(String url, String title) {
        parseUrlService.parse(url, data1 -> {
            fakeNewsService.checkPortuguese(data1.getContent(), data2 -> {
                fakeNewsDAO.create(new FakeNewsModel(
                        url,
                        title,
                        data1.getContent(),
                        new Date(),
                        data2.getPrediction())
                );
                Log.d("fake_news_detector", fakeNewsDAO.list().toString());
            }, this::onApiError);
        }, this::onApiError);
    }

    private void onApiError(int code, ResponseBody body, String errorBody) {
        Log.e("fake_news_detector", String.format("Error: code(%d), body(%s), errorBody(%s)", code, body, errorBody));
    }

    public boolean shownTutorial() {
        return systemDAO.get().isTutorialSeen();
    }

    public void setTutorialAsShown() {
        SystemModel obj = systemDAO.get();
        obj.setTutorialSeen(true);
        systemDAO.update(obj);
    }
}
