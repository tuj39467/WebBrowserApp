package edu.temple.webbrowserapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Objects;

public class BrowserActivity extends AppCompatActivity implements PagerFragment.passInterface, PageViewerFragment.updateInterface, PageControlFragment.passInfoInterface, BrowserControlFragment.ViewPagerInterface{

    PageControlFragment pc;
    PageViewerFragment pv;
    BrowserControlFragment bc;
    PageListFragment pl;
    PagerFragment p;
    ViewPager vp;

    ArrayList<PageViewerFragment> fragments;
   // ViewPager vp;
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
            fm.beginTransaction().add(R.id.container_2,pv).commit();
        }

        bc = (BrowserControlFragment)fm.findFragmentById((R.id.container_3));
        bc = new BrowserControlFragment();
        fm.beginTransaction().add(R.id.container_3,bc).commit();

        p = (PagerFragment) fm.findFragmentById(R.id.container_5);
        if(p == null) {
            p = new PagerFragment();
            fm.beginTransaction().add(R.id.container_5, p).commit();
        }


       // vp = findViewById(R.id.container_5);
      /*  vp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
            @Override
            public int getCount() {
                return fragments.size();
            }

        });
        findViewById(R.id.container_3).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fragments.add(new PageViewerFragment());
                vp.getAdapter().notifyDataSetChanged();
            }
        });
*/



   /*     pl = (PageListFragment)fm.findFragmentById(R.id.container_4);
        if(pl == null){
            pl = new PageListFragment();
            fm.beginTransaction().add(R.id.container_4,pl).commit();
        }*/


                //   vp=findViewById(R.id.container_5);
                // vp.setAdapter(fa);
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
        fragments.add(new PageViewerFragment());
        p.createInstance();
    }
    public void createInstance2(ViewPager vp){
        vp.setAdapter(fa);
        vp.getAdapter().notifyDataSetChanged();
    }
    public void itemSelected(int item){

    }
}
