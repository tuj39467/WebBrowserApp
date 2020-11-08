package edu.temple.webbrowserapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
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
    ArrayList<String> Url;
    ListView list2;
    BaseAdapter ListAdapter;
    String url;
    String PageTitle;

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
            // pv = new PageViewerFragment();
            // fragments.add(pv);
            Bundle b = new Bundle();
            b.putParcelableArrayList("Array", fragments);
            p.setArguments(b);
            fm.beginTransaction().add(R.id.container_2, p).commit();
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
        // pl.createInstance();
        final String TAG1 = "test";
        pageTitles.add(pageTitle);
        pl.passList(pageTitles);
        Log.d(TAG1,"List array size: " + pageTitles.size() );
      //  pc.updateTheURL(url);
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
       // Url.add(text);
//        list2.setAdapter(UrlAdapter);
  //      list2.getAdapter();
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
        //updateURL(Url.get(position));

        changeTitle(pageTitles.get(position));
        updateURL(Url.get(position));
        //p.changeTitles(position)
    }
}

