package edu.temple.webbrowserapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

public class PagerFragment extends Fragment {

    ViewPager vp;

    public PagerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void addInstance(){
         vp.getAdapter();//.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View l = inflater.inflate(R.layout.fragment_pager, container, false);

        vp = l.findViewById(R.id.viewPager);

    //    if(savedInstanceState != null){

      //  }




        return l;
    }
}