package edu.temple.webbrowserapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

public class PageViewerFragment extends Fragment implements Parcelable {
    WebView wv;
    updateInterface parentActivity;
    int position;
    String url2;

    public PageViewerFragment() {

    }

    protected PageViewerFragment(Parcel in) {
        position = in.readInt();
    }


    public static final Creator<PageViewerFragment> CREATOR = new Creator<PageViewerFragment>() {
        @Override
        public PageViewerFragment createFromParcel(Parcel in) {
            return new PageViewerFragment(in);
        }

        @Override
        public PageViewerFragment[] newArray(int size) {
            return new PageViewerFragment[size];
        }
    };

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof PageControlFragment.passInfoInterface) {
            parentActivity = (PageViewerFragment.updateInterface) context;
        } else {
            throw new RuntimeException("You must implement passInfoInterface to attach this fragment");
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(position);
    }

    public interface updateInterface{
        void updateURL(String text);
        void changeTitle(String pageTitle);
        void countPages(String pageTitle);
        void savedPageTitle(String title);
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        wv.saveState(outState);
    }

    public String updateUrl2(){
        return url2;
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
                parentActivity.savedPageTitle(pageTitle);
            }
            @Override
            public void doUpdateVisitedHistory(WebView web, String url, boolean reload) {

                String pageTitle = web.getTitle();
                parentActivity.changeTitle(pageTitle);
                parentActivity.savedPageTitle(pageTitle);
                parentActivity.countPages(pageTitle);
                parentActivity.updateURL(url);
                url2 = url;
            }
            @Override
            public void onPageFinished(WebView w, String url){
                super.onPageFinished(w,url);
                String pageTitle = w.getTitle();
                parentActivity.changeTitle(pageTitle);
                parentActivity.savedPageTitle(pageTitle);
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