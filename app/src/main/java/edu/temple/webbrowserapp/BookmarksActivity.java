package edu.temple.webbrowserapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookmarksActivity extends AppCompatActivity {
    ListView savedList;
    BaseAdapter SaveListAdapter;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_list);

        Intent intent = getIntent();
        final ArrayList<String> savedPageTitles = intent.getStringArrayListExtra("Save");
        final ArrayList<String> savedUrls = intent.getStringArrayListExtra("Url");

        //TextView text = new TextView(this);
        text = findViewById(R.id.textView);
        text.setGravity(4);
        text.setTextSize(30);

        SaveListAdapter = new SaveListAdapter(this, savedPageTitles);
        savedList = findViewById(R.id.list);

        savedList.setAdapter(SaveListAdapter);
        savedList.getAdapter();

        savedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ReverseActivityIntent = new Intent(BookmarksActivity.this,BrowserActivity.class);
                ReverseActivityIntent.putExtra("position",savedUrls.get(position));
                //ReverseActivityIntent.putStringArrayListExtra("")
                //ReverseActivityIntent.putExtra("position",position);
                startActivity(ReverseActivityIntent);
            }
        });


    }



}