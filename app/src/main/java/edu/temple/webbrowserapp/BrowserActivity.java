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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    String url;
    BaseAdapter ListAdapter;
    String savedTitle;
    SharedPreferences preferences;
  //  SharedPreferences.Editor editor;
    File file;
    String filename;
    String web;
    Boolean autoSave;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // filename = "myFile";
        //file = new File(getFilesDir(),filename);
      //  preferences = getPreferences(MODE_PRIVATE);
       /* autoSave = preferences.getBoolean("autoSave",false);

        if(autoSave && file.exists()){

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
*/
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
        web = intent.getStringExtra("position");
        if(web != null) {
            try {
                //editor.putString("key",web);
                //editor.apply();
                createNewInstance();
                pv.setInfo(web);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
       // }
      /*  if (autoSave){
            try {
                FileOutputStream outputStream  = new FileOutputStream(file);
                outputStream.write(web.toString().getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            file.delete();
        }}*/
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

    public void updateUrlSlide(int position) {
        changeTitle(pageTitles.get(position));
        updateURL(Url.get(position));
    }
    public void savedPageTitle(String title){
        savedTitle = title;
    }

    public void addPage(){//adds page to list of bookmarked pages after clicking "save page" button
        Toast.makeText(this, "Page bookmarked", Toast.LENGTH_SHORT).show();
       // savedPageTitles.add(savedTitle);
        Url.add(url);

        SharedPreferences pref = getSharedPreferences("autoSave",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("element",savedTitle);
        editor.apply();
    }

    public void buttonClicked(){
        SharedPreferences pref = getSharedPreferences("autoSave",MODE_PRIVATE);
        String web2 = pref.getString("element",savedTitle);
        savedPageTitles.add(savedTitle);


        Intent ActivityIntent = new Intent(BrowserActivity.this, BookmarksActivity.class);
        ActivityIntent.putStringArrayListExtra("Save",savedPageTitles);
        ActivityIntent.putStringArrayListExtra("Url",Url);
        startActivity(ActivityIntent);
    }
}

