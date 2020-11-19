package edu.temple.webbrowserapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SaveListAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> savedPageTitles;

    public SaveListAdapter(Context context, ArrayList<String> savedPageTitles){
        this.savedPageTitles = savedPageTitles;
        this.context = context;
    }

    @Override
    public int getCount() {
        return savedPageTitles.size();
    }

    @Override
    public Object getItem(int position) {
        return savedPageTitles.get(position);
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
