package edu.temple.webbrowserapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    TextView display;
    EditText urlText;
    ImageButton goButton;
    ImageButton nextButton;
    ImageButton backButton;

    passInfoInterface parentActivity;

    public PageControlFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View l = inflater.inflate(R.layout.fragment_page_control, container, false);

        display = (TextView)l.findViewById(R.id.textView);
        urlText = (EditText) l.findViewById(R.id.EditTextURL);
        goButton = (ImageButton) l.findViewById(R.id.Go);
        nextButton = (ImageButton) l.findViewById(R.id.Next);
        backButton = (ImageButton) l.findViewById(R.id.Back);

        final Handler responseHandler = new Handler(new Handler.Callback() {

            @Override
            public boolean handleMessage(Message msg) {
                display.setText((String) msg.obj);
                return false;
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String urlString = "temple.edu";

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
        });

        return l;
    }
    public interface passInfoInterface{
      //  void displayColorInfo(int color);
    }



}