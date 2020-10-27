package edu.temple.webbrowserapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import static androidx.core.content.ContextCompat.getSystemService;

public class PageControlFragment extends Fragment {


    EditText urlText;
    ImageButton goButton;
    ImageButton nextButton;
    ImageButton backButton;

    passInfoInterface parentActivity;

    public PageControlFragment() {

    }
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof passInfoInterface) {
            parentActivity = (passInfoInterface) context;
        } else {
            throw new RuntimeException("You must implement passInfoInterface to attach this fragment");
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }
    public void updateTheURL(String text){
        urlText.setText(text);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View l = inflater.inflate(R.layout.fragment_page_control, container, false);

        urlText = (EditText) l.findViewById(R.id.EditTextURL);
        goButton = (ImageButton) l.findViewById(R.id.Go);
        nextButton = (ImageButton) l.findViewById(R.id.Next);
        backButton = (ImageButton) l.findViewById(R.id.Back);


        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread();
                String urlString = urlText.getText().toString();
                if(!urlString.startsWith("https://")){

                    urlString = "https://" + urlString;
                    urlText.setText(urlString);
                }

                try {
                    parentActivity.DisplayInfo(urlString);

                }
                catch(MalformedURLException u) {
                    u.printStackTrace();
                }
                t.start();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              parentActivity.goBackInfo();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                parentActivity.goForwardInfo();
            }
        });

        return l;
    }


    public interface passInfoInterface{
        void DisplayInfo(String website) throws MalformedURLException;
        void goBackInfo();
        void goForwardInfo();
    }
}