package com.news;

import android.Manifest.permission;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.news.adapter.NewsViewDataAdapter;
import com.news.bean.NewsBean;

public class MarathiNewsActivity extends Activity implements OnItemClickListener {

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
						listView.setOnItemClickListener(MarathiNewsActivity.this);
					}
				});

				dialog.dismiss();
			}
		});
		t.start();

	}


	@Override
	public void onItemClick(AdapterView<?> listView, View view, int position, long arg3) {
		System.out.println("ON Touch.." + position);
		Intent intent = new Intent(this, NewsDetailActivity.class);
		NewsViewDataAdapter newsViewDataAdapter = (NewsViewDataAdapter) listView.getAdapter();
		NewsBean news = (NewsBean) newsViewDataAdapter.getItem(position);
		intent.putExtra("news", news);
		startActivity(intent);
		
	}
}