package com.shree;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shree.parser.IotdHandler;

public class NASAImageActivity extends Activity {
	private static final String URL = "http://www.nasa.gov/rss/image_of_the_day.rss";
	private IotdHandler iotdHandler = new IotdHandler();
	private Handler handler;
	Bitmap image;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		handler = new Handler();
		// reloadFormValues();
	}

	public void onRefreshClick(View view) {
		reloadFormValues();
	}

	public void onSetWallpaperClick(View view) {
		Thread thread = new Thread() {
			public void run() {
				WallpaperManager wallpaperManager = WallpaperManager
						.getInstance(NASAImageActivity.this);
				try {
					wallpaperManager.setBitmap(image);
					handler.post(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(NASAImageActivity.this,
									"Wallper is set", Toast.LENGTH_SHORT);

						}
					});

				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		};
		thread.start();
		Toast.makeText(NASAImageActivity.this,
				"Wallper is set", Toast.LENGTH_SHORT);
	}

	private void reloadFormValues() {
		final Context context = this;
		final ProgressDialog progressDialog = ProgressDialog.show(this,
				"Loading", "Loading image od the day");
		Thread thread = new Thread() {
			public void run() {
				try {
					iotdHandler.processFeed(context, new URL(URL));

					handler.post(new Runnable() {
						@Override
						public void run() {
							image = getBitmap(iotdHandler.getUrl());

							resetDisplay(iotdHandler.getTitle(),
									iotdHandler.getDate(),
									iotdHandler.getUrl(),
									iotdHandler.getDescription());
							progressDialog.dismiss();
						}
					});

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

			}
		};
		thread.start();

	}

	private void resetDisplay(String title, String date, String imageUrl,
			String description) {
		TextView titleView = (TextView) findViewById(R.id.imageTitle);
		titleView.setText(title);

		TextView dateView = (TextView) findViewById(R.id.imageDate);
		dateView.setText(date);

		ImageView imageView = (ImageView) findViewById(R.id.imageDisplay);
		imageView.setImageBitmap(getBitmap(imageUrl));

		TextView descriptionView = (TextView) findViewById(R.id.imageDescription);
		descriptionView.setText(description);
	}

	private Bitmap getBitmap(String url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url)
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(input);
			input.close();
			connection.disconnect();
			return bitmap;
		} catch (IOException ioe) {
			return null;
		}
	}

}