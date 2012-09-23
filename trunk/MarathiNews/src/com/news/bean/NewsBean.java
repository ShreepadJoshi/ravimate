package com.news.bean;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsBean {
	static SimpleDateFormat FORMATTER = new SimpleDateFormat(
			"EEE, dd MMM yyyy HH:mm:ss Z");
	private String title;
	private URL link;
	private String description;
	//private Date date;
	private String date;

	// getters and setters omitted for brevity
	public void setLink(String link) {
		try {
			this.link = new URL(link);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public String getDate() {
		//return FORMATTER.format(this.date);
		return date;
	}

	public void setDate(String date) {
		this.date = date;
		
		// pad the date if necessary
//		while (!date.endsWith("00")) {
//			date += "0";
//		}
//		try {
//			this.date = FORMATTER.parse(date.trim());
//		} catch (ParseException e) {
//			throw new RuntimeException(e);
//		}
	}

	// sort by date
	public int compareTo(NewsBean another) {
		if (another == null)
			return 1;
		// sort descending, most recent first
		return another.date.compareTo(date);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public URL getLink() {
		return link;
	}

	@Override
	public String toString() {
		return "NewsBean [title=" + title + ", link=" + link + ", description="
				+ description + ", date=" + date + "]";
	}

}
