package com.yxkj.kzzx.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yxkj.kzzx.contract.VideoContract;
import com.yxkj.kzzx.ui.fragment.AudioFragment;
import com.yxkj.kzzx.ui.fragment.PPTFragment;
import com.yxkj.kzzx.ui.fragment.VideoFragment;

import java.lang.annotation.Retention;

/**
 * Created by RaoKui on 2017/7/21.
 */

public class PagerFragmentAdapter extends FragmentPagerAdapter {

    public PagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PPTFragment();
            case 1:
                return new AudioFragment();
            case 2:
                return new VideoFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
