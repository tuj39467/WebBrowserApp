package edu.temple.webbrowserapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class PageListFragment extends Fragment {

    ListView list;
    selectInterface parentActivity;
    TextView tv;

    public PageListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View l = inflater.inflate(R.layout.fragment_page_list, container, false);

        list = l.findViewById(R.id.listView);

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