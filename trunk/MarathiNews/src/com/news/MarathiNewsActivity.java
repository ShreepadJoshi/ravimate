package com.news;

import android.Manifest.permission;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import com.news.adapter.NewsViewDataAdapter;

public class MarathiNewsActivity extends Activity {

	Handler handler = new Handler();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		PackageManager pm = getPackageManager();
		if (pm.checkPermission(permission.INTERNET, getPackageName()) == PackageManager.PERMISSION_GRANTED) {
			
			loadNewsInNewThread();

		} else {
			Toast.makeText(MarathiNewsActivity.this,
					"Please grante the permission for internet",
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * @return
	 */
	private void loadNewsInNewThread() {
		final ProgressDialog dialog = ProgressDialog.show(this, "Loading",
				"loading news");

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				final NewsViewDataAdapter adapter = new NewsViewDataAdapter(
						getAssets());
				
				handler.post(new Runnable() {
					@Override
					public void run() {
						ListView listView = (ListView) findViewById(R.id.newsListView);
						listView.setAdapter(adapter);
					}
				});

				dialog.dismiss();
			}
		});
		t.start();

	}
}