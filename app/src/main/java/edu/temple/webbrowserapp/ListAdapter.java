package edu.temple.webbrowserapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> pageTitles;

    public ListAdapter(Context context, ArrayList<String> pageTitles){
         this.pageTitles = pageTitles;
         this.context = context;
    }

    @Override
    public int getCount() {
        return pageTitles.size();
    }

    @Override
    public Object getItem(int position) {
        return pageTitles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        TextView textView;
        if(convertView == null) {
            textView = new TextView(context);
        }
        else{
            textView = (TextView) convertView;
        }
        textView.setText(getItem(position).toString());

        return textView;
    }




}