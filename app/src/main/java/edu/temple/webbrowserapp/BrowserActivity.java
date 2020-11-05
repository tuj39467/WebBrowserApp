package edu.temple.webbrowserapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Objects;

public class BrowserActivity extends AppCompatActivity implements PageListFragment.selectInterface, PagerFragment.passInterface, PageViewerFragment.updateInterface, PageControlFragment.passInfoInterface, BrowserControlFragment.ViewPagerInterface{

    PageControlFragment pc;
    PageViewerFragment pv;
    BrowserControlFragment bc;
    PageListFragment pl;
    PagerFragment p;
    ArrayList<PageViewerFragment> fragments;
    FragmentAdapter fa;
    ArrayList<String> pageTitles;
    BaseAdapter ListAdapter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments = new ArrayList<>();
        pageTitles = new ArrayList<>();
        ListAdapter = new ListAdapter(this, pageTitles);


        FragmentManager fm = getSupportFragmentManager();
   //     fa = new FragmentAdapter(fm,fragments);
        fa = new FragmentAdapter(fm,fragments);

        pc = (PageControlFragment)fm.findFragmentById(R.id.container_1);
        if(pc == null){
            pc = new PageControlFragment();
            fm.beginTransaction().add(R.id.container_1,pc).commit();
        }
        bc = (BrowserControlFragment) fm.findFragmentById((R.id.container_3));
        if(bc == null) {
          //  bc = (BrowserControlFragment) fm.findFragmentById((R.id.container_3));
            bc = new BrowserControlFragment();
            fm.beginTransaction().add(R.id.container_3, bc).commit();
        }

        pl = (PageListFragment)fm.findFragmentById(R.id.container_4);
        if(pl == null){
            pl = new PageListFragment();
            fm.beginTransaction().add(R.id.container_4,pl).commit();
        }
/*
        if(pv == null) {
           // p = new PagerFragment();
            pv = new PageViewerFragment();
            fragments.add(pv);
            // fm.beginTransaction().add(R.id.container_2, pv).commit();
        }
        else{

        }
*/
        p = (PagerFragment) fm.findFragmentById(R.id.container_2);
        if(p == null) {
            p = new PagerFragment();
            fm.beginTransaction().add(R.id.container_2, p).commit();
        }
    }

    public void onSaveInstanceState(@NonNull Bundle state)
    {
        super.onSaveInstanceState(state);
        state.putAll(state);
    }
    public void DisplayInfo(String website) throws MalformedURLException {
        pv.setInfo(website);
    }
    public void changeTitle(String pageTitle){
        Objects.requireNonNull(getSupportActionBar()).setTitle(pageTitle);

        final String TAG1 = "test";

        pageTitles.add(pageTitle);
        Log.d(TAG1,"List array size: " + pageTitles.size() );
        pageTitles.add(pageTitle);
       // pl.passList(pageTitles);

    }
    public void goBackInfo() {
       pv.goBack();
    }
    public void goForwardInfo() {
        pv.goForward();
    }
    public void updateURL(String text) {
       pc.updateTheURL(text);
    }

    public void createNewInstance(){
        final String TAG = "test";
        Log.d(TAG,"Fragment array size: " + fragments.size() );
        pv = new PageViewerFragment();
        //fragments.add(new PageViewerFragment());
        fragments.add(pv);
        pl.createInstance();
        pl.passList(pageTitles);
        p.createInstance();
    }
    public void createInstance2(ViewPager vp){
        vp.setAdapter(fa);
        vp.getAdapter().notifyDataSetChanged();
    }
    public void itemSelected(int item){

    }

    @Override
    public void passList(ListView list) {
        list.setAdapter(ListAdapter);
        list.getAdapter();
    }

}




