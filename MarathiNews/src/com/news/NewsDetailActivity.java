package com.news;

import com.news.bean.NewsBean;
import com.news.util.MarathiFontUtil;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class NewsDetailActivity extends Activity {

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
		
		TextView detailView = (TextView) findViewById(R.id.detailPage_detail);
		detailView.setTypeface(marathiFont);
		detailView.setText(news.getDescription());	
		
		
	}

}
 	