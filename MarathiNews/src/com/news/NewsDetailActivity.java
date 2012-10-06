package com.news;

import com.news.bean.NewsBean;

import android.app.Activity;
import android.os.Bundle;

public class NewsDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_details_view);
		Bundle extras = getIntent().getExtras();
		NewsBean news = (NewsBean) extras.get("news");
		System.out.println(news.getTitle());
	}

}
 	