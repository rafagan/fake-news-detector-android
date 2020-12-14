package com.pedro.fakenewsdetector.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.pedro.fakenewsdetector.R;

import java.util.ArrayList;
import java.util.List;

public class TutorialPageAdapter extends PagerAdapter {
    List<Integer> tutorialPages = new ArrayList<>();

    public TutorialPageAdapter() {
        tutorialPages.add(R.layout.cell_tutorial_1);
        tutorialPages.add(R.layout.cell_tutorial_2);
        tutorialPages.add(R.layout.cell_tutorial_3);
        tutorialPages.add(R.layout.cell_tutorial_4);
    }

    @Override
    public int getCount() {
        return tutorialPages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(tutorialPages.get(position), null);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }
}
