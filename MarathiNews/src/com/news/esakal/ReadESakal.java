package com.news.esakal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadESakal {


	/**
	 * To read html page of news.
	 * 
	 * @param newUrl
	 * @return newsAsHTML
	 */
	public static StringBuilder readHTMLPageOfNews(String newUrl) {
		System.out.println("Reading --> " + newUrl);
		StringBuilder buffer = new StringBuilder();
		try {
			URL location = new URL(newUrl);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					location.openStream(), "UTF-8"));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				buffer.append(inputLine);
			}
			in.close();
			return buffer;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
		return new StringBuilder("Error reading news");

	}

	public static String extractNewsFromHTML(StringBuilder newsHTML) {		
		int newsStartTagIndex= newsHTML.indexOf("<!-- News Details Start --->");	
		int newsEndTagIndex  = newsHTML.indexOf("<!-- News Details End --->");		
		newsEndTagIndex  = newsEndTagIndex - "<!-- News Details End --->".length();
		newsEndTagIndex  = newsEndTagIndex - "</div>".length();		
		
		
		if (newsStartTagIndex  != -1 && newsEndTagIndex != -1) {
			String divTagOfnews = newsHTML.substring(newsStartTagIndex, newsEndTagIndex);
			divTagOfnews = divTagOfnews.replaceAll("<br>", "");
			divTagOfnews = divTagOfnews.replaceAll("&npsp;", "");
			
			int beginIndex = divTagOfnews.lastIndexOf(">") + 1;
			return divTagOfnews.substring(beginIndex, divTagOfnews.length());			
		}
		return "";
	}
	
}
