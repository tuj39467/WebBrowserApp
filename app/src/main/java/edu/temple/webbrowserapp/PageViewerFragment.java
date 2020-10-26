package edu.temple.webbrowserapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class PageViewerFragment extends Fragment {
    WebView wv;
    updateInterface parentActivity;
    public PageViewerFragment() {

    }

    public void setInfo(String urlString) throws MalformedURLException {
        wv.loadUrl(urlString);
    }
    public void goBack(){
        if(wv.canGoBack()){
            wv.canGoBack();
            wv.goBack();
        }
    }
    public void goForward(){
        if(wv.canGoForward()){
            wv.canGoForward();
            wv.goForward();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View l = inflater.inflate(R.layout.fragment_page_viewer, container, false);


        wv = (WebView) l.findViewById(R.id.WebView);
        wv.setWebViewClient(new WebViewClient());
       /* wv.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                parentActivity.updateURL(wv.getUrl());
                return true;
            }
        });*/



        return l;
    }

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
   }
    public interface updateInterface{
        void updateURL(String text);
    }

}