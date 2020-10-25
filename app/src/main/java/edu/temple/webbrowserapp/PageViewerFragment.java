package edu.temple.webbrowserapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class PageViewerFragment extends Fragment {
    TextView display;

    public PageViewerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View l = inflater.inflate(R.layout.fragment_page_viewer, container, false);

       // display = (TextView)l.findViewById(R.id.textView);

       return l;


    }*/

    final Handler responseHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            display.setText((String) msg.obj);
            return false;
        }
    });
    public void setInfo(final String urlString) {
        Thread t = new Thread() {
            public void run() {
                try {
                    URL url = new URL(urlString);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                    String response = reader.readLine();
                    Message msg = Message.obtain();
                    msg.obj = response;
                    responseHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}