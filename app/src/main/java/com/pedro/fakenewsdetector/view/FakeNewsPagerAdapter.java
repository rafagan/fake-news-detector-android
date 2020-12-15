package com.pedro.fakenewsdetector.view;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pedro.fakenewsdetector.R;
import com.pedro.fakenewsdetector.controller.FakeNewsController;

public class FakeNewsPagerAdapter extends FragmentPagerAdapter {
    private final Fragment[] fragments = new Fragment[]{
            new FakeNewsSearchFragment(),
            new FakeNewsHistoryFragment()
    };
    private final int[] titles = new int[]{
            R.string.search,
            R.string.history
    };

    private final Context context;
    private final FakeNewsController controller;

    public FakeNewsPagerAdapter(@NonNull FragmentManager fm, Context context, FakeNewsController controller) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        this.controller = controller;
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getString(titles[position]);
    }

    public void refresh() {
        ((FakeNewsHistoryFragment)fragments[1]).getAdapter().refresh(controller.getAllFakeNews());
        notifyDataSetChanged();
    }
}
