package edu.temple.webbrowserapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PageControlFragment pc = new PageControlFragment();
        PageViewerFragment pv = new PageViewerFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_1,pc)
                .add(R.id.container_2,pv)
                .commit();

    }
}