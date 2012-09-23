package com.news.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.news.R;
import com.news.bean.NewsBean;
import com.news.parser.SaxFeedParser;

public class NewsViewDataAdapter extends BaseAdapter {

	private List<NewsBean> list = new ArrayList<NewsBean>();
	AssetManager asset; 

	public NewsViewDataAdapter(AssetManager assetManager ) {
		asset = assetManager;
		SaxFeedParser saxFeedParser = new SaxFeedParser("http://online2.esakal.com/esakal/RSS/LatestNews.xml");
		list = saxFeedParser.parse();
		System.out.println(list);
		
	}
		

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int index) {
		return list.get(index);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			view = inflater.inflate(R.layout.news_list_view, parent, false);

		}

		NewsBean news = list.get(index);
		TextView timeTextView = (TextView) view.findViewById(R.id.newsTitle);
		timeTextView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mangal.ttf")); 
		timeTextView.setText(news.getTitle());

		TextView notesView = (TextView) view.findViewById(R.id.newsDate);
		notesView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mangal.ttf")); 
		notesView.setText(news.getDate());

		return view;
	}


	private AssetManager getAssets() {
		// TODO Auto-generated method stub
		return asset;
	}

}
