package edu.temple.webbrowserapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.net.MalformedURLException;
 import java.util.ArrayList;
import java.util.Arrays;
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
    ArrayList<String> savedPageTitles;
    ArrayList<String> Url;
    ArrayList<String> Url2;
    String url;
    BaseAdapter ListAdapter;
    String savedTitle;
    String web;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        fragments = new ArrayList<>();

        if (fa == null) {
            fa = new FragmentAdapter(fm, fragments);
        }
        if (pageTitles == null) {
            pageTitles = new ArrayList<>();
        }
        if (savedPageTitles == null) {
            savedPageTitles = new ArrayList<>();
        }
        if (ListAdapter == null) {
            ListAdapter = new ListAdapter(this, pageTitles);
        }
        if (Url == null) {
            Url = new ArrayList<>();
        }
        if (Url2 == null) {
            Url2 = new ArrayList<>();
        }
        pc = (PageControlFragment) fm.findFragmentById(R.id.container_1);
        if (pc == null) {
            pc = new PageControlFragment();
            fm.beginTransaction().add(R.id.container_1, pc).commit();
        }

        bc = (BrowserControlFragment) fm.findFragmentById((R.id.container_3));
        if (bc == null) {
            bc = new BrowserControlFragment();
            fm.beginTransaction().add(R.id.container_3, bc).commit();
        }

        pl = (PageListFragment) fm.findFragmentById(R.id.container_4);
        if (pl == null) {
            pl = new PageListFragment();
            Bundle b = new Bundle();
            b.putParcelableArrayList("ArrayList",fragments);
            pl.setArguments(b);
            fm.beginTransaction().add(R.id.container_4, pl).commit();
        }

        p = (PagerFragment) fm.findFragmentById(R.id.container_2);
        if (p == null && pv == null) {
            p = new PagerFragment();
            Bundle b = new Bundle();
            b.putParcelableArrayList("Array", fragments);
            p.setArguments(b);
            fm.beginTransaction().add(R.id.container_2, p).commit();
        }

        Intent intent = getIntent();
        web = intent.getStringExtra("position");
        if(web != null) {
           // Log.d("Serial","p:" + web);
            try {
                createNewInstance();
                pv.setInfo(web);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle state)
    {
        super.onSaveInstanceState(state);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("Array",fragments);
        super.onRestoreInstanceState(outState);
    }
    public void DisplayInfo(String website) throws MalformedURLException {
        pv.setInfo(website);
    }
    public void changeTitle(String pageTitle){
        Objects.requireNonNull(getSupportActionBar()).setTitle(pageTitle);
        pl.createInstance();
    }

    public void countPages(String pageTitle){
        pageTitles.add(pageTitle);
        pl.passList(pageTitles);

    }

    public void goBackInfo() {
        pv.goBack();
    }
    public void goForwardInfo() {
        pv.goForward();
    }
    public void updateURL(String text) {
        url = text;
        Url.add(url);
        pc.updateTheURL(text);
    }

    public void createNewInstance(){
        pv = new PageViewerFragment();
        fragments.add(pv);
        p.createInstance();
    }

    public void itemSelected(int item,ArrayList<PageViewerFragment>f){
        p.display(item);
    }

    @Override
    public void passList(ListView list) {
        list.setAdapter(ListAdapter);
        list.getAdapter();
    }

    public void updateUrlSlide(int position) {
        changeTitle(pageTitles.get(position));
        updateURL(Url.get(position));
    }
    public void savedPageTitle(String title){
        savedTitle = title;
    }

    public void addPage(){//adds page to list of bookmarked pages after clicking "save page" button
        Toast.makeText(this, "Page Saved!", Toast.LENGTH_SHORT).show();

        StringBuilder sb = new StringBuilder();
        savedPageTitles.add(savedTitle);
        Url2.add(url);

      //  Url.add(url);
        StringBuilder sb2 = new StringBuilder();

        for(String s: Url2){
            sb2.append(s);
            sb2.append(",");
        }

        for(String s: savedPageTitles){
            sb.append(s);
            sb.append(",");
        }

        SharedPreferences prefer = getSharedPreferences("element",0);
        SharedPreferences.Editor editor = prefer.edit();
        editor.putString("elements", sb.toString());
        editor.apply();

        SharedPreferences prefer2 = getSharedPreferences("element2",0);
        SharedPreferences.Editor editor2 = prefer2.edit();
        editor2.putString("elements2", sb2.toString());
        editor2.apply();

    }

    public void buttonClicked(){

        savedPageTitles.removeAll(savedPageTitles);
        SharedPreferences pref = getSharedPreferences("element",MODE_PRIVATE);
        String web2 = pref.getString("elements", "Google");
        String[] items = web2.split(",");
        savedPageTitles.addAll(Arrays.asList(items));

        Url2.removeAll(Url2);
       // Url.removeAll(Url);
        SharedPreferences pref2 = getSharedPreferences("element2",MODE_PRIVATE);
        String web3 = pref2.getString("elements2", "google.com");
        String[] items2 = web3.split(",");
        Url2.addAll(Arrays.asList(items2));
      //  Url.addAll(Arrays.asList(items2));


        Intent ActivityIntent = new Intent(BrowserActivity.this, BookmarksActivity.class);
        ActivityIntent.putStringArrayListExtra("Save",savedPageTitles);
        ActivityIntent.putStringArrayListExtra("Url",Url2);
        startActivity(ActivityIntent);
    }
}

