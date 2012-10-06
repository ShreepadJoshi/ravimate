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
	
	



}
