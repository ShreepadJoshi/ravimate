package com.news.parser;

import java.util.List;

import com.news.bean.NewsBean;

public interface FeedParser {
	List<NewsBean> parse();
}
