package edu.temple.webbrowserapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;


import java.net.MalformedURLException;

public class BrowserActivity extends AppCompatActivity implements PageViewerFragment.updateInterface, PageControlFragment.passInfoInterface{

    PageControlFragment pc;
    PageViewerFragment pv;
    BrowserControlFragment bc;
    PageListFragment pl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();

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

        pl = (PageListFragment)fm.findFragmentById(R.id.container_4);
        if(pl == null){
            pl = new PageListFragment();
            fm.beginTransaction().add(R.id.container_4,pl).commit();
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

    public void createNewInstance(Boolean b){
        if(b = true){
            pv = new PageViewerFragment();
        }
    }
}
