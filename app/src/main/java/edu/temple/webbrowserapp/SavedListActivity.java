package edu.temple.webbrowserapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SavedListActivity extends AppCompatActivity {
    ListView savedList;
    BaseAdapter SaveListAdapter;
    TextView text;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_list);

        Intent intent = getIntent();
        ArrayList<String> savedPageTitles = intent.getStringArrayListExtra("Save");

        //TextView text = new TextView(this);
        text = findViewById(R.id.textView);
        text.setGravity(4);
        text.setTextSize(30);

        SaveListAdapter = new SaveListAdapter(this, savedPageTitles);
        savedList = findViewById(R.id.list);

        savedList.setAdapter(SaveListAdapter);
        savedList.getAdapter();

    }
}