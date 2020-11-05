package edu.temple.webbrowserapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    ArrayList<PageViewerFragment> fragments;
   // Activity activity;

    public FragmentAdapter(@NonNull FragmentManager fm,ArrayList<PageViewerFragment> fragments2) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragments = fragments2;
    }
    @Override
    public int getCount() {
        return fragments.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

}
