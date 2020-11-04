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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
  //  ViewPager vp;
 //   ListView list;

    FragmentManager fm;
    ArrayList<PageViewerFragment> fragments;
    FragmentAdapter fa;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments = new ArrayList<>();

        FragmentManager fm = getSupportFragmentManager();
        fa = new FragmentAdapter(fm,fragments);

        pc = (PageControlFragment)fm.findFragmentById(R.id.container_1);
        if(pc == null){
            pc = new PageControlFragment();
            fm.beginTransaction().add(R.id.container_1,pc).commit();
        }
        pv = (PageViewerFragment) fm.findFragmentById(R.id.container_2);
        if(pv == null){
            pv = new PageViewerFragment();
            fragments.add(pv);
           // fm.beginTransaction().add(R.id.container_2,pv).commit();
        }

        bc = (BrowserControlFragment)fm.findFragmentById((R.id.container_3));
        bc = new BrowserControlFragment();
        fm.beginTransaction().add(R.id.container_3,bc).commit();

        p = (PagerFragment) fm.findFragmentById(R.id.container_2);
        p = new PagerFragment();
        fm.beginTransaction().add(R.id.container_2, p).commit();
       /* if(p == null) {
            p = new PagerFragment();
            fm.beginTransaction().add(R.id.container_5, p).commit();
           // fm.beginTransaction().add(R.id.container_5, p).commit();
        }
*/
        /* pl = (PageListFragment)fm.findFragmentById(R.id.container_4);
        if(pl == null){
            pl = new PageListFragment();
            fm.beginTransaction().add(R.id.container_4,pl).commit();
        }*/
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
        fragments.add(new PageViewerFragment());
//        fm.beginTransaction().add(R.id.container_2,pv).commit();
        p.createInstance();
    }
    public void createInstance2(ViewPager vp){
        vp.setAdapter(fa);
        vp.getAdapter().notifyDataSetChanged();
    }
    public void itemSelected(int item){
    //   pv = fragments.get(item);

    }

}




