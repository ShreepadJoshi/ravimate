package com.news;

import android.Manifest.permission;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.news.adapter.NewsViewDataAdapter;

public class MarathiNewsActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		PackageManager pm = getPackageManager();
		if (pm.checkPermission(permission.INTERNET, getPackageName()) == PackageManager.PERMISSION_GRANTED) {
		
			ListView listView = (ListView) findViewById(R.id.newsListView);
			NewsViewDataAdapter adapter = new NewsViewDataAdapter(getAssets());
			listView.setAdapter(adapter);
			
		} else {
			Toast.makeText(MarathiNewsActivity.this,
					"Please grante the permission for internet",
					Toast.LENGTH_LONG);
		}
	}
}