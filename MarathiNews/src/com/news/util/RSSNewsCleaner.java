package com.news.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import android.util.Log;

import com.news.bean.NewsBean;

public class RSSNewsCleaner {

	/**
	 * remove Duplicate With keeping Order same
	 * 
	 * @param newsList
	 * @return
	 */
	public static List<NewsBean> removeDuplicateNews(List<NewsBean> newsList) {
		Log.d(RSSNewsCleaner.class.getName() + ".removeDuplicateNewsFrom() ",
				"before count " + newsList.size());
		
		newsList.remove(0);// 1st is esakal ..and all.. 
		LinkedHashSet<NewsBean> set = new LinkedHashSet<NewsBean>(newsList);
		newsList.clear();
		newsList.addAll(set);

		Log.d(RSSNewsCleaner.class.getName() + ".removeDuplicateNewsFrom() ",
				"after count " + newsList.size());
		return newsList;

	}

	public static String getDescriptionFromHTML(String descriptionInHTML) {
		String descriptionOfnews = "";
		int descriptionStartIndex = descriptionInHTML.indexOf("/>")
				+ "/>".length();
		int descriptionEndIndex = descriptionInHTML.length();

		
		if (descriptionStartIndex > 0) {// then
			descriptionOfnews = descriptionInHTML.substring(
					descriptionStartIndex, descriptionEndIndex);
		}

		return descriptionOfnews;
	}

	/**
	 * remove Duplicate With keeping Order same
	 * 
	 * @param newsList
	 * @return
	 */
	public static List<NewsBean> removeDuplicateNewsFrom2(
			List<NewsBean> newsList) {
		HashSet<NewsBean> set = new HashSet<NewsBean>(newsList);
		List<NewsBean> newList = new ArrayList<NewsBean>();
		for (Iterator<NewsBean> iter = newsList.iterator(); iter.hasNext();) {
			NewsBean news = iter.next();
			if (set.add(news) == true) {
				newList.add(news);
			}

		}
		newsList.clear();
		newsList.addAll(newList);
		return newsList;
	}
}
