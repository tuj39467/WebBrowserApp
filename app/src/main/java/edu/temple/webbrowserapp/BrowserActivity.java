package edu.temple.webbrowserapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//import android.annotation.NonNull;
import android.content.ClipData;
import android.content.res.Configuration;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;

public class BrowserActivity extends AppCompatActivity implements PageViewerFragment.updateInterface, PageControlFragment.passInfoInterface{

    boolean twoPanes;

    PageControlFragment pc; //= new PageControlFragment();
    PageViewerFragment pv; //= new PageViewerFragment();
    //= getSupportFragmentManager();
    //FragmentTransaction ft = fm.beginTransaction();

    //private static final String TAG = "task";
  //  private PageViewerFragment webViewFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        twoPanes = (findViewById(R.id.container_2) != null);

        FragmentManager fm = getSupportFragmentManager();

        pc = (PageControlFragment)fm.findFragmentById(R.id.container_1);
        if(pc == null){
            pc = new PageControlFragment();
            fm.beginTransaction().add(R.id.container_1,pc).commit();
        }
        pv = (PageViewerFragment) fm.findFragmentById(R.id.container_2);
        if(pv == null){
       // if(
            pv = new PageViewerFragment();
            fm.beginTransaction().add(R.id.container_2,pv).commit();
        }

     //   loadFragment(R.id.container_1,pc);
/*
        if(twoPanes){
            pv = new PageViewerFragment();
            loadFragment(R.id.container_2,pv);
        }
*/
        /*if(!(fm.findFragmentById(R.id.container_1) instanceof PageControlFragment)){
            pc = new PageControlFragment();
            fm.beginTransaction()
                    .add(R.id.container_1,pc)
               //     .add(R.id.container_2,new PageViewerFragment())
                  //  .addToBackStack(null
                    .commit();
           // pc.setRetainInstance(true);
            //pv.setRetainInstance(true);
        }
        if(findViewById(R.id.container_2) != null){
          //  pc = new PageControlFragment();
           // pv = new PageViewerFragment();
            fm.beginTransaction()
                   // .add(R.id.container_1,pc)
                    .add(R.id.container_2,pv)
                    //  .addToBackStack(null)
                    .commit();
        }*/
       /* PageViewerFragment webViewFrag = (PageViewerFragment) fm.findFragmentByTag(TAG);

        if(webViewFrag == null){
            webViewFrag = new PageViewerFragment();
            fm.beginTransaction().add(webViewFrag,TAG).commit();
        }
*/
    }
/*
    private void loadFragment(int paneId, Fragment f){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction().replace(paneId,f);
      //  fm.beginTransaction().add(R.id.container_1,pc);
        ft.addToBackStack(null);
        ft.commit();
        fm.executePendingTransactions();
    }
*/

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

    @Override
    public void updateURL(String text) {
       pc.updateTheURL(text);
    }

  //  @Override
   /* public void setInfo(String url) throws MalformedURLException {
        pv.setInfo(url);
    }*/
}
