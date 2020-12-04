package edu.temple.webbrowserapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Objects;

public class PagerFragment extends Fragment {

   // public static ViewPager vp;
    // int position;
    Context context;
    passInterface parentActivity;
    ArrayList<PageViewerFragment> fragments2;
    ViewPager vp;
    FragmentAdapter fa;//= new FragmentAdapter(getChildFragmentManager(),fragments2);
    WebView wv;

    public PagerFragment() {

    }

    public interface passInterface{
        void updateUrlSlide(int position);
      //  void passSave(int pos);
        void attachedPager() throws MalformedURLException;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof PagerFragment.passInterface) {
            parentActivity = (PagerFragment.passInterface) context;
        } else {
            throw new RuntimeException("You must implement selectInterface to attach this fragment");
        }

    }
   /* public static PagerFragment newInstance() {
        PagerFragment p = new PagerFragment();

       // Bundle bundle = new Bundle();
        //bundle.putParcelableArrayList("f", f);
        //p.setArguments(bundle);
        return p;
    }*/

    public void display(int item){
        vp.setCurrentItem(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
           // fragments2 = getArguments().getParcelableArrayList("f");
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

        try {
            parentActivity.attachedPager();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return l;
    }
    public void createInstance(){
        vp.setAdapter(fa);
        Objects.requireNonNull(vp.getAdapter()).notifyDataSetChanged();
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        outState.putParcelableArrayList("Array",fragments2);
        this.setRetainInstance(true);
    }
}