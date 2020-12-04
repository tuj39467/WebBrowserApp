package edu.temple.webbrowserapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    ArrayList<PageViewerFragment> savedFragments;
    FragmentAdapter fa;
    ArrayList<String> pageTitles;
    ArrayList<String> savedPageTitles;
    ArrayList<String> Url;
    ArrayList<String> Url2;
    String url;
    ViewPager vp;
    BaseAdapter ListAdapter;
    String savedTitle;
    FragmentManager fm;
    String web;
    int pos;
    ImageButton sendButton;
    PopupMenu menu;
    Bundle saved;
    String action;
    Uri stored;
    Intent intentExternal;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saved = savedInstanceState;
        sendButton = (ImageButton) findViewById(R.id.sendButton);

        fm = getSupportFragmentManager();
        fragments = new ArrayList<>();

        vp=new ViewPager(this);

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
        savedFragments = new ArrayList<>();

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
        if (pv == null) {
            p = new PagerFragment();
            //p = PagerFragment.newInstance();
        //    pv = PageViewerFragment.newInstance();
            Bundle b = new Bundle();
            b.putParcelableArrayList("Array", fragments);
            p.setArguments(b);
            fm.beginTransaction().add(R.id.container_2, p).commit();
        }

        intentExternal = getIntent();
        action = intentExternal.getAction();
        stored = intentExternal.getData();

        Intent intent = getIntent();
        web = intent.getStringExtra("position");
        //Toast.makeText(getApplicationContext(),stored.toString(), Toast.LENGTH_LONG).show();
       // createNewInstance(" ");
        if(web != null) {
            createNewInstance();
            try {
                pv.setInfo(web);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }
       /* if(stored != null){
            createNewInstance("google.com");
            try {
                pv.setInfo("google.com");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }*/
       // onStart();
    }


    @Override
    protected void onStart(){
        super.onStart();
       // Toast.makeText(getApplicationContext(),"Now onStart() calls", Toast.LENGTH_LONG).show();
        //handleString(intentExternal);
        createNewInstance();
/*        try {
            pv.setInfo("https:www.temple.edu");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/
    }

    public void handleString(Intent i) throws MalformedURLException {
        String sharedText = i.getData().toString();
        Toast.makeText(getApplicationContext(),sharedText, Toast.LENGTH_LONG).show();
       // createNewInstance(sharedText);
        //pv.setInfo(sharedText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sendButton) {
            final Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            Uri uri = Uri.parse(url);
            sendIntent.putExtra(Intent.EXTRA_TEXT,uri.toString());
            sendIntent.setType("text/plain");
            final Intent shareIntent = Intent.createChooser(sendIntent,null);
            startActivity(shareIntent);
        }

        return super.onOptionsItemSelected(item);
    }
    public void attachedPage() throws MalformedURLException {
        Intent intentExt = getIntent();
        String url2 = intentExt.getDataString();
            if (url2.startsWith("http://")) {
                url2 = url2.replace("http://", "https://");
            }
        pv.setInfo(url2);
    }
    public void attachedPager(){
        p.createInstance();
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
        //pv= PageViewerFragment.newInstance(page);
        fragments.add(pv);
        p.createInstance();
        //pv.setInfo(page);
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

        savedPageTitles.add(savedTitle);
        Url2.add(url);
      //  savedFragments.add(pv);

        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for(String s: Url2){
            sb2.append(s);
            sb2.append(",");
        }

        for(String s: savedPageTitles){
            sb.append(s);
            sb.append(",");
        }

        SharedPreferences prefer = getSharedPreferences("element",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefer.edit();
        editor.putString("elements", sb.toString());
        editor.apply();

        SharedPreferences prefer2 = getSharedPreferences("element2",MODE_PRIVATE);
        SharedPreferences.Editor editor2 = prefer2.edit();
        editor2.putString("elements2", sb2.toString());
        editor2.apply();
    }

    public void buttonClicked() {

        savedPageTitles.removeAll(savedPageTitles);
        SharedPreferences pref = getSharedPreferences("element",MODE_PRIVATE);
        String web2 = pref.getString("elements", "Google");
        String[] items = web2.split(",");
        savedPageTitles.addAll(Arrays.asList(items));

        Url2.removeAll(Url2);
        SharedPreferences pref2 = getSharedPreferences("element2",MODE_PRIVATE);
        String Web3 = pref2.getString("elements2", "google.com");
        String[] items2 = Web3.split(",");
        Url2.addAll(Arrays.asList(items2));

        Intent ActivityIntent = new Intent(BrowserActivity.this, BookmarksActivity.class);
        ActivityIntent.putStringArrayListExtra("Save",savedPageTitles);
        ActivityIntent.putStringArrayListExtra("Url",Url2);
        ActivityIntent.putExtra("fragments",savedFragments);
        startActivityForResult(ActivityIntent,1);
    }
}

