package com.news;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.news.bean.NewsBean;
import com.news.esakal.ReadESakal;
import com.news.util.MarathiFontUtil;

public class NewsDetailActivity extends Activity {

	TextView detailView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Typeface marathiFont = MarathiFontUtil.getMarathiFont(getAssets());
		setContentView(R.layout.news_details_view);
		Bundle extras = getIntent().getExtras();
		NewsBean news = (NewsBean) extras.get("news");
		System.out.println(news.getTitle());

		TextView titleView = (TextView) findViewById(R.id.detailPage_title);
		titleView.setTypeface(marathiFont);
		titleView.setText(news.getTitle());

		detailView = (TextView) findViewById(R.id.detailPage_detail);
		detailView.setTypeface(marathiFont);
		// detailView.setText(news.getDescription());

		readNewsFromWebPage(news);

	}

	public void readNewsFromWebPage(NewsBean news) {
		DownloadWebPageTask task = new DownloadWebPageTask();
		task.execute(new String[] { news.getLink().toString() });
	}

	private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			String responseInHTML = "";
			for (String url : urls) {
				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(url);
				try {
					HttpResponse execute = client.execute(httpGet);
					InputStream content = execute.getEntity().getContent();

					BufferedReader buffer = new BufferedReader(
							new InputStreamReader(content));
					String s = "";
					while ((s = buffer.readLine()) != null) {
						responseInHTML += s;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return ReadESakal.extractNewsFromHTML(new StringBuilder(
					responseInHTML));
		}

		@Override
		protected void onPostExecute(String result) {
			detailView.setText(result);
		}
	}

}
