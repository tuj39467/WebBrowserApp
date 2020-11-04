package edu.temple.webbrowserapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.MalformedURLException;
import java.net.URL;

public class PageViewerFragment extends Fragment {
    WebView wv;
    updateInterface parentActivity;

    public PageViewerFragment() {

    }
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof PageControlFragment.passInfoInterface) {
            parentActivity = (PageViewerFragment.updateInterface) context;
        } else {
            throw new RuntimeException("You must implement passInfoInterface to attach this fragment");
        }

    }
    public interface updateInterface{
        void updateURL(String text);
        void changeTitle(String pageTitle);
      //  void createNewInstance();
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        wv.saveState(outState);
    }

    public void setInfo(final String urlString) throws MalformedURLException {
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
            @Override
            public void onPageStarted(WebView w, String url, Bitmap favicon) {
                super.onPageStarted(w, url, favicon);
                String pageTitle = w.getTitle();
                parentActivity.changeTitle(pageTitle);
//                parentActivity.createNewInstance();
            }
            @Override
            public void doUpdateVisitedHistory(WebView web, String url, boolean reload) {
                parentActivity.updateURL(url);
                String pageTitle = web.getTitle();
                parentActivity.changeTitle(pageTitle);
            }
            @Override
            public void onPageFinished(WebView w, String url){
                super.onPageFinished(w,url);
                String pageTitle = w.getTitle();
                parentActivity.changeTitle(pageTitle);
            }
            @Override
            public void onLoadResource(WebView web, String url) {
                super.onLoadResource(web, url);
            }
        };

        wv.setWebViewClient(wc);
        wv.getSettings().setJavaScriptEnabled(true);

        if(savedInstanceState != null){
            wv.restoreState(savedInstanceState);
        }

        return l;
    }

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       this.setRetainInstance(true);
   }


}