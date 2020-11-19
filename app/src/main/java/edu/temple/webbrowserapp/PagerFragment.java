package edu.temple.webbrowserapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class PagerFragment extends Fragment {
    ViewPager vp;
   // int position;
    passInterface parentActivity;
    ArrayList<PageViewerFragment> fragments2;
    FragmentAdapter fa;//= new FragmentAdapter(getChildFragmentManager(),fragments2);
    WebView wv;

    public PagerFragment() {

    }

    public interface passInterface{
        void updateUrlSlide(int position);
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof PagerFragment.passInterface) {
            parentActivity = (PagerFragment.passInterface) context;
        } else {
            throw new RuntimeException("You must implement selectInterface to attach this fragment");
        }

    }
    public void createInstance(){
        vp.setAdapter(fa);
        vp.getAdapter().notifyDataSetChanged();
    }

    public void display(int item){
        vp.setCurrentItem(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            fragments2 = getArguments().getParcelableArrayList("Array");
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View l = inflater.inflate(R.layout.fragment_pager, container, false);

        vp = l.findViewById(R.id.viewPager);

        fa = new FragmentAdapter(getChildFragmentManager(), fragments2);

        if (savedInstanceState != null) {
            fragments2 = savedInstanceState.getParcelableArrayList("Array");
            vp.setAdapter(fa);
            vp.getAdapter().notifyDataSetChanged();

            vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    // parentActivity.updateUrlSlide(position);
                }

                @Override
                public void onPageSelected(int position) {
                    parentActivity.updateUrlSlide(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    // parentActivity.updateUrlSlide(state);
                }
            });
        }

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // parentActivity.updateUrlSlide(position);
            }

            @Override
            public void onPageSelected(int position) {
                parentActivity.updateUrlSlide(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // parentActivity.updateUrlSlide(state);
            }
        });



        return l;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        outState.putParcelableArrayList("Array",fragments2);
        this.setRetainInstance(true);
    }
}