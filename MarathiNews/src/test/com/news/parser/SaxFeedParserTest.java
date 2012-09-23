package test.com.news.parser;

import java.util.List;

import junit.framework.TestCase;

import com.news.bean.NewsBean;
import com.news.parser.SaxFeedParser;

public class SaxFeedParserTest extends TestCase {

	public void testParse() {
		SaxFeedParser saxFeedParser = new SaxFeedParser("http://online2.esakal.com/esakal/RSS/LatestNews.xml");
		List<NewsBean> news = saxFeedParser.parse();
		news.toString();
		assertNull(news);
	}	
	

}
