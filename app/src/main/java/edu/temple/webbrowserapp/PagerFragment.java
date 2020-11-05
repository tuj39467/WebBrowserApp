package edu.temple.webbrowserapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class PagerFragment extends Fragment {
    ViewPager vp;
    ArrayList<PageViewerFragment>fragments2;
    int position;
    passInterface parentActivity;
    FragmentAdapter fa;

    public PagerFragment() {

    }
    public interface passInterface{
       // void itemSelected(int item);
        void createInstance2(ViewPager vp);
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
//        vp.getAdapter().notifyDataSetChanged();
          parentActivity.createInstance2(vp);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View l = inflater.inflate(R.layout.fragment_pager, container, false);

        vp = l.findViewById(R.id.viewPager);

       // fa = new FragmentAdapter(getChildFragmentManager(),fragments2);

//        vp.setAdapter(fa);
  //      vp.getAdapter().notifyDataSetChanged();

        if(savedInstanceState != null){
            position = savedInstanceState.getInt("position");
            vp.setCurrentItem(position);
//            fragments2.get(position);
//            vp.setAdapter(fa);
  //          vp.getAdapter().notifyDataSetChanged();
        }
//        vp.setAdapter(fa);
//        fragments2.get(position);

        return l;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("position",position);
    }

}