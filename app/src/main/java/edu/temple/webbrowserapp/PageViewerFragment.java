package edu.temple.webbrowserapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.MalformedURLException;

public class PageViewerFragment extends Fragment {
    WebView wv;
    updateInterface parentActivity;
    public PageViewerFragment() {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        wv.saveState(outState);
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
        WebViewClient wc = new WebViewClient() {

            public void updateURL(WebView web, String url, boolean reload) {
                parentActivity.updateURL(url);
            }

            public void onLoad(WebView web, String url) {
                super.onLoadResource(web, url);
            }
        };

        wv.setWebViewClient(wc);
        wv.getSettings().setJavaScriptEnabled(true);

        if(savedInstanceState != null){
            wv.restoreState(savedInstanceState);
        }

       /* wv.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                parentActivity.updateURL(wv.getUrl());
                return true;
            }*/
//        });



        return l;
    }

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       this.setRetainInstance(true);
   }
    public interface updateInterface{
        void updateURL(String text);

       // void setInfo(String url) throws MalformedURLException;
    }

}