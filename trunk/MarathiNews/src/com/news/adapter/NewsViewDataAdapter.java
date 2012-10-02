package com.news.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.news.R;
import com.news.bean.NewsBean;
import com.news.parser.SaxFeedParser;
import com.news.util.RSSNewsCleaner;

public class NewsViewDataAdapter extends BaseAdapter {

	private List<NewsBean> newsList = new ArrayList<NewsBean>();
	AssetManager asset;

	public NewsViewDataAdapter(AssetManager assetManager) {
		asset = assetManager;
		SaxFeedParser saxFeedParser = new SaxFeedParser(
				"http://online2.esakal.com/esakal/RSS/LatestNews.xml");
		newsList = saxFeedParser.parse();
		newsList = RSSNewsCleaner.removeDuplicateNews(newsList);

	}

	@Override
	public int getCount() {
		return newsList.size();
	}

	@Override
	public Object getItem(int index) {
		return newsList.get(index);
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
		final NewsBean news = newsList.get(index);

		Typeface marathiFont = getMarathiFont();

		TextView timeTextView = (TextView) view.findViewById(R.id.newsTitle);
		//timeTextView.setClickable(true);
		timeTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("ON Touch.." + news.getTitle() + v);
			}
		});

		timeTextView.setTypeface(getMarathiFont());
		timeTextView.setText(news.getTitle());

		TextView notesView = (TextView) view.findViewById(R.id.newsDate);
		notesView.setTypeface(marathiFont);
		notesView.setText(news.getDate().toLocaleString());

		TextView descriptionView = (TextView) view
				.findViewById(R.id.newsDescription);
		descriptionView.setTypeface(marathiFont);
		String description = RSSNewsCleaner.getDescriptionFromHTML(news
				.getDescription());
		descriptionView.setText(description);

		return view;
	}

	/**
	 * Load Marathi font
	 * 
	 * @return
	 */
	private Typeface getMarathiFont() {
		return Typeface.createFromAsset(getAssets(), "fonts/mangal.ttf");
	}

	private AssetManager getAssets() {
		return asset;
	}

}
