package com.news;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.news.bean.NewsBean;
import com.news.esakal.ReadESakal;
import com.news.util.MarathiFontUtil;

public class NewsDetailActivity extends Activity {

	TextView detailView;
	static int fontsize = 10;// TODO this value must save and reload from DB

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
		detailView.setTextSize(fontsize);
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
			StringBuilder responseInHTML = new StringBuilder("");

			for (String url : urls) {

				/*
				 * DefaultHttpClient client = new DefaultHttpClient(); HttpGet
				 * httpGet = new HttpGet(url); try { HttpResponse execute =
				 * client.execute(httpGet); InputStream content =
				 * execute.getEntity().getContent();
				 * 
				 * BufferedReader buffer = new BufferedReader( new
				 * InputStreamReader(content)); String s = ""; while ((s =
				 * buffer.readLine()) != null) { responseInHTML.append(s); }
				 * 
				 * } catch (Exception e) { e.printStackTrace(); }
				 */

				responseInHTML = ReadESakal.readHTMLPageOfNews(url);
			}
			return ReadESakal.extractNewsFromHTML(responseInHTML);
		}

		@Override
		protected void onPostExecute(String result) {
			detailView.setText(result);
		}
	}

	/**
	 * Key down even listener
	 * 
	 * we will use this to get Volume UP and DOWN action, method will increase
	 * font size on volume up and down event.
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		super.onKeyDown(keyCode, event);

		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			System.out.println("key presss KEYCODE_VOLUME_UP");
			fontsize = fontsize + 2;
			return true;
		}
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			fontsize = fontsize - 2;
			System.out.println("key presss KEYCODE_VOLUME_DOWN");
			return true;
		}
		detailView.setTextSize(fontsize);
		return true;
	}

}
