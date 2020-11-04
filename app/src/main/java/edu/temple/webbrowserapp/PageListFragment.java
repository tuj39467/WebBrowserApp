package edu.temple.webbrowserapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PageListFragment extends Fragment {

    ListView list;
    selectInterface parentActivity;
    TextView tv;
    private ArrayList<PageViewerFragment> pages;

    public PageListFragment() {

    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof PageListFragment.selectInterface) {
            parentActivity = (PageListFragment.selectInterface) context;
        } else {
            throw new RuntimeException("You must implement selectInterface to attach this fragment");
        }

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
      //      pages = (ArrayList<PageViewerFragment>) getArguments().getBundle();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View l = inflater.inflate(R.layout.fragment_page_list, container, false);

        //list = l.findViewById(R.id.listView);
        if(l instanceof ListView){
            Context context = l.getContext();
            list = (ListView)l;
         //   list.setAdapter(new FragmentAdapter(getFragmentManager()));
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parentActivity.itemSelected(position);
            }
        });

        return l;
    }


    public interface selectInterface{
        void itemSelected(int item);
    }
}