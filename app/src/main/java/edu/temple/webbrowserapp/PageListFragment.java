package edu.temple.webbrowserapp;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PageListFragment extends Fragment implements Parcelable {
    ArrayList<String> pageTitles;
    ArrayList<PageViewerFragment>fragments2;
    ListView list;
    selectInterface parentActivity;
    TextView tv;
    int position;
    private ArrayList<PageViewerFragment> pages;
    ListAdapter listAdapter;

    public PageListFragment() {

    }

    protected PageListFragment(Parcel in) {
        pageTitles = in.createStringArrayList();
        fragments2 = in.createTypedArrayList(PageViewerFragment.CREATOR);
        position = in.readInt();
        pages = in.createTypedArrayList(PageViewerFragment.CREATOR);
    }

    public static final Creator<PageListFragment> CREATOR = new Creator<PageListFragment>() {
        @Override
        public PageListFragment createFromParcel(Parcel in) {
            return new PageListFragment(in);
        }

        @Override
        public PageListFragment[] newArray(int size) {
            return new PageListFragment[size];
        }
    };

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
        parentActivity.passList(list);
      //  list.setAdapter(listAdapter);
       // list.getAdapter();

    }

    public void createInstance(){
        parentActivity.passList(list);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            fragments2 = getArguments().getParcelableArrayList("ArrayList");
        }
        this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View l = inflater.inflate(R.layout.fragment_page_list, container, false);
        list = l.findViewById(R.id.listView);
        listAdapter = new ListAdapter(getActivity(), pageTitles);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parentActivity.itemSelected(position,fragments2);
            }
        });

        return l;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        outState.putParcelableArrayList("ArrayList",fragments2);
        super.onSaveInstanceState(outState);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(pageTitles);
        dest.writeTypedList(fragments2);
        dest.writeInt(position);
        dest.writeTypedList(pages);
    }

    public interface selectInterface{
        void itemSelected(int item,ArrayList<PageViewerFragment> fragments2);
        void passList(ListView list);
    }
}