package edu.temple.webbrowserapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import java.net.MalformedURLException;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.passInfoInterface{

    PageControlFragment pc = new PageControlFragment();
    PageViewerFragment pv = new PageViewerFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_1,pc)
                .add(R.id.container_2,pv)
                .commit();
    }

    public void DisplayInfo(String website) throws MalformedURLException {
        pv.setInfo(website);
    }
}