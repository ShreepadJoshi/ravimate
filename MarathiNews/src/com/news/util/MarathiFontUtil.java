package com.news.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.news.bean.NewsBean;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.AsyncTask;

public class MarathiFontUtil {

	/**
	 * Load Marathi font
	 * 
	 * @return
	 */
	public static Typeface getMarathiFont(AssetManager assetManager) {
		return Typeface.createFromAsset(assetManager, "fonts/mangal.ttf");
	}
	
	
	public void readWebpage(NewsBean news) {
	    DownloadWebPageTask task = new DownloadWebPageTask();
	    task.execute(new String[] { "http://www.vogella.com" });
	  }
	
	
	private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
	    @Override
	    protected String doInBackground(String... urls) {
	      String response = "";
	      for (String url : urls) {
	        DefaultHttpClient client = new DefaultHttpClient();
	        HttpGet httpGet = new HttpGet(url);
	        try {
	          HttpResponse execute = client.execute(httpGet);
	          InputStream content = execute.getEntity().getContent();

	          BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
	          String s = "";
	          while ((s = buffer.readLine()) != null) {
	            response += s;
	          }

	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	      }
	      return response;
	    }

	    @Override
	    protected void onPostExecute(String result) {
	      //textView.setText(result);
	    }
	  }


}
