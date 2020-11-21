package edu.temple.webbrowserapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    ArrayList<String> savedPageTitles;
    ArrayList<String> Url;
    BaseAdapter ListAdapter;
    String savedTitle;
    SharedPreferences preferences;
    File file;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        file = new File(getFilesDir(),"myFile");
        preferences = getPreferences(MODE_PRIVATE);

        if(file.exists()){
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                StringBuilder text = new StringBuilder();
                String line;
                while((line = br.readLine()) != null){
                    text.append(line);
                    text.append('\n');
                }
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
           //  pv = new PageViewerFragment();
            // fragments.add(pv);
            Bundle b = new Bundle();
            b.putParcelableArrayList("Array", fragments);
            p.setArguments(b);
            fm.beginTransaction().add(R.id.container_2, p).commit();
        }

        Intent intent = getIntent();
        String web = intent.getStringExtra("position");
        if(web != null) {
            try {
               // pv = new PageViewerFragment();
                //pv.setInfo(web);
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
        Url.add(text);
        pc.updateTheURL(text);
    }

    public void createNewInstance(){
        final String TAG = "test";

        pv = new PageViewerFragment();
        fragments.add(pv);

        Log.d(TAG,"Fragment array size: " + fragments.size() );

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

   // @Override
    public void updateUrlSlide(int position) {
        changeTitle(pageTitles.get(position));
        updateURL(Url.get(position));
    }
    public void savedPageTitle(String title){
        savedTitle = title;
    }

    public void addPage(){//adds page to list of bookmarked pages after clicking "save page" button
        final String TAG1 = "test";
        savedPageTitles.add(savedTitle);
        Toast.makeText(this, "Page bookmarked", Toast.LENGTH_SHORT).show();
        Log.d(TAG1,"List array size: " + pageTitles.size() );
    }

    public void buttonClicked(){
        Intent ActivityIntent = new Intent(BrowserActivity.this, BookmarksActivity.class);
        ActivityIntent.putStringArrayListExtra("Save",savedPageTitles);
        ActivityIntent.putStringArrayListExtra("Url",Url);
        startActivity(ActivityIntent);
    }
}

