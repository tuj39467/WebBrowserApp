package edu.temple.webbrowserapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.net.MalformedURLException;

public class BrowserControlFragment extends Fragment {

    ImageButton page_Button;

    ViewPagerInterface viewPagerFace;

    public BrowserControlFragment() {

    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof BrowserControlFragment.ViewPagerInterface) {
            viewPagerFace = (BrowserControlFragment.ViewPagerInterface) context;
        } else {
            throw new RuntimeException("You must implement ViewPagerInterface to attach this fragment");
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View l = inflater.inflate(R.layout.fragment_browser_control, container, false);
        page_Button = (ImageButton) l.findViewById(R.id.pageButton);

        page_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               viewPagerFace.createNewInstance();
            }
        });

        return l;
    }

    public interface ViewPagerInterface{
        void createNewInstance();
    }
}