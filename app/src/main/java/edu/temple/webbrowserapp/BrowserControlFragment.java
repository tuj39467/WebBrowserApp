package edu.temple.webbrowserapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class BrowserControlFragment extends Fragment {

    ImageButton page_Button;
    ImageButton savePage;
    ImageButton savedList;

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
        this.setRetainInstance(true);

    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
       // outState.putInt("position",position);
        outState.putAll(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View l = inflater.inflate(R.layout.fragment_browser_control, container, false);
        page_Button = (ImageButton) l.findViewById(R.id.pageButton);
        savePage = (ImageButton) l.findViewById(R.id.saveButton);
        savedList = (ImageButton)l.findViewById(R.id.savedListButton);

        page_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               viewPagerFace.createNewInstance();
            }
        });

        savePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPagerFace.addPage();
            }
        });

        savedList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                viewPagerFace.buttonClicked();
            }
        });

        return l;
    }
    //public void newPage(){
  //      viewPagerFace.createNewInstance();
    //}

    public interface ViewPagerInterface{
        void createNewInstance();
        void addPage();
        void buttonClicked();
    }
}