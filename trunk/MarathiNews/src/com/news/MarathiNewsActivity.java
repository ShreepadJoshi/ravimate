package com.news;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.ListView;

import com.news.adapter.NewsViewDataAdapter;

public class MarathiNewsActivity extends Activity {
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        ListView listView = (ListView) findViewById(R.id.newsListView);
        NewsViewDataAdapter adapter = new NewsViewDataAdapter(getAssets());
		listView.setAdapter(adapter);
        
    }
}