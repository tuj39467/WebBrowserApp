package edu.temple.webbrowserapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.EditText;

import java.net.MalformedURLException;

public class BrowserActivity extends AppCompatActivity implements PageViewerFragment.updateInterface, PageControlFragment.passInfoInterface{

    PageControlFragment pc = new PageControlFragment();
    PageViewerFragment pv = new PageViewerFragment();
    FragmentManager fm = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fm.beginTransaction()
                .add(R.id.container_1,pc)
                .add(R.id.container_2,pv)
                .addToBackStack(null)
                .commit();
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

    @Override
    public void updateURL(String text) {
       pc.updateTheURL(text);
    }
}