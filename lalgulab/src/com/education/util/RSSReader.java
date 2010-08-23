package com.education.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RSSReader {
	public static SyndFeed readRssFeed(URL url)
	{
		SyndFeedInput input = null;
		SyndFeed feed = null;
		try {
			input = new SyndFeedInput();
			feed = input.build(new XmlReader(url));
//			feed.get
			for(Object entry : feed.getEntries())
			{
				SyndEntry impl = (SyndEntry)entry;
				System.out.println("Links==>"+impl.getTitle()+" Date"+impl.getPublishedDate().toString());
			}
//			feed.g
			return feed;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String... args) throws MalformedURLException
	{
		readRssFeed(new URL("http://news.bbc.co.uk/rss/newsonline_world_edition/business/rss.xml"));
	}
}
