package edu.temple.webbrowserapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


import java.net.MalformedURLException;
import java.util.ArrayList;

public class BrowserActivity extends AppCompatActivity implements PageViewerFragment.updateInterface, PageControlFragment.passInfoInterface, BrowserControlFragment.ViewPagerInterface{

    PageControlFragment pc;
    PageViewerFragment pv;
    BrowserControlFragment bc;
    PageListFragment pl;
    PagerFragment p;

    ArrayList<PageViewerFragment> fragments;
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments = new ArrayList<>();

        FragmentManager fm = getSupportFragmentManager();

        FragmentAdapter fa = new FragmentAdapter(fm);



        pc = (PageControlFragment)fm.findFragmentById(R.id.container_1);
        if(pc == null){
            pc = new PageControlFragment();
            fm.beginTransaction().add(R.id.container_1,pc).commit();
        }
        pv = (PageViewerFragment) fm.findFragmentById(R.id.container_2);
        if(pv == null){
            pv = new PageViewerFragment();
            fm.beginTransaction().add(R.id.container_2,pv).commit();
        }
        bc = (BrowserControlFragment)fm.findFragmentById((R.id.container_3));
        bc = new BrowserControlFragment();
        fm.beginTransaction().add(R.id.container_3,bc).commit();

        pl = (PageListFragment)fm.findFragmentById(R.id.container_4);
        if(pl == null){
            pl = new PageListFragment();
            fm.beginTransaction().add(R.id.container_4,pl).commit();
        }
        p = (PagerFragment) fm.findFragmentById(R.id.container_5);
        if(p == null){
            p = new PagerFragment();
            fm.beginTransaction().add(R.id.container_5,p).commit();
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
        fragments.add(new PageViewerFragment());
        p.addInstance();
    }
    public void itemSelected(int item){

    }
}
