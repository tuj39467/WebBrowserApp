package edu.temple.webbrowserapp;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PageListFragment extends Fragment {
    ArrayList<String> pageTitles;
    ListView list;
    selectInterface parentActivity;
    TextView tv;
    int position;
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

    public void passList(ArrayList<String> pageList){
        pageTitles = pageList;
    }

    public void createInstance(){
        parentActivity.passList(list);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            //vp.setCurrentItem(savedInstanceState.getInt("item"));
          //  list.setCsavedInstanceState.getInt("position"));
        }
        this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View l = inflater.inflate(R.layout.fragment_page_list, container, false);
        list = l.findViewById(R.id.listView);
        if(savedInstanceState != null){
            position = savedInstanceState.getInt("position");
//            fragments2.get(position);
//            vp.setAdapter(fa);
            //          vp.getAdapter().notifyDataSetChanged();
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //    tv.setText(parent.getItemAtPosition(position).toString());
                //parentActivity.itemSelected(position);
            }
        });



        return l;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("position",position);
        outState.putAll(outState);
    }

    public interface selectInterface{
        void itemSelected(int item);
        void passList(ListView list);
    }
}