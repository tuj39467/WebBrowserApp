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
    int position;
    passInterface parentActivity;
    ArrayList<PageViewerFragment> fragments2;
    FragmentAdapter fa;//= new FragmentAdapter(getChildFragmentManager(),fragments2);
    WebView wv;

    public PagerFragment() {

    }
   /* public static PageViewerFragment newInstance() {
        PageViewerFragment f = new PageViewerFragment();
        Bundle args = new Bundle();
        //args.putInt("position", position);
        f.setArguments(args);

        return f;
    }
*/
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
       //   parentActivity.createInstance2(vp);
        vp.setAdapter(fa);
        vp.getAdapter().notifyDataSetChanged();
    }
    public void saveViewer(WebView web){
        wv = web;
    }
    public void passPager(ArrayList<PageViewerFragment> p){
        fragments2 = p;
    }
   /* public static PagerFragment newInstance(ArrayList<PageViewerFragment> array) {
        PagerFragment p = new PagerFragment();
      //  fragments2 = new ArrayList<>();
        Bundle args = new Bundle();
     //   args.putSerializable("array", array);
        p.setArguments(args);
        return p;
    }*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            fragments2= getArguments().getParcelableArrayList("Array");
        }
      /*  Bundle b = new Bundle();
        if(b != null) {
          //  fragments2 = new ArrayList<>();

            fragments2 = (ArrayList<PageViewerFragment>) b.getSerializable("key");
            //vp.setCurrentItem(savedInstanceState.getInt("item"));
          //  vp.setCurrentItem(savedInstanceState.getInt("position"));
        }
        else{
            fragments2 = null;
        }*/
       // fa = new FragmentAdapter(getChildFragmentManager(),fragments2);
       // this.setRetainInstance(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View l = inflater.inflate(R.layout.fragment_pager, container, false);

        vp = l.findViewById(R.id.viewPager);

        fa = new FragmentAdapter(getChildFragmentManager(),fragments2);

  //      vp.setAdapter(fa);
//        vp.getAdapter().notifyDataSetChanged();

        if(savedInstanceState != null){
           fragments2 = savedInstanceState.getParcelableArrayList("Array");
            /* position = savedInstanceState.getInt("position");
            wv.restoreState(savedInstanceState);
           // fragments2 = new ArrayList<>();
            fa = new FragmentAdapter(getChildFragmentManager(),fragments2);
            vp.setCurrentItem(position);*/
        }
        vp.setAdapter(fa);
        vp.getAdapter().notifyDataSetChanged();
        return l;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
       // outState.putParcelableArrayList("Array",fragments2);
        outState.putParcelableArrayList("Array",fragments2);
        this.setRetainInstance(true);


    }

}