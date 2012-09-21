package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ReadeSakal {

	String location = "http://72.78.249.125/esakal/index.htm";
	private String titles[];
	private String urls[];
	
	public String readPaper(String location) {

		System.out.println("Reading --> "+ location);
		try {
			StringBuffer buffer = new StringBuffer();
			URL yahoo = new URL(location);
			BufferedReader in = new BufferedReader(new InputStreamReader(yahoo
					.openStream(), "UTF-8"));

			String inputLine;

			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					new FileOutputStream(new File("paper.html")), "UTF-8");
			PrintWriter writer = new PrintWriter(outputStreamWriter);

			while ((inputLine = in.readLine()) != null) {

				writer.print(inputLine);
				buffer.append(inputLine);
				// System.out.println(inputLine);
			}
			in.close();
			outputStreamWriter.close();
			writer.close();
			return buffer.toString();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done");
		return "PoPat zaala";
	}

	/**
	 * @param mainUrl
	 */
	public void getURLs(String mainUrl) {
		ArrayList<String> urlList = new ArrayList<String>();
		ArrayList<String> titleList = new ArrayList<String>();

		StringBuffer buffer = new StringBuffer();
		URL esakalHomePage;
		try {
			esakalHomePage = new URL(mainUrl);

			BufferedReader in = new BufferedReader(new InputStreamReader(esakalHomePage.openStream(), "UTF-8"));
			String inputLine;
			boolean save =false;
			while ((inputLine = in.readLine()) != null) {
				if(inputLine.indexOf("LatestNews.htm") != -1){
					save = !save;
				}
				if(save == true){
					if(inputLine.indexOf("<li><a href=") != -1){
						int beginIndex = "<li><a href=".length() + 1;
						int endIndex = inputLine.indexOf("htm") + 3;
						
						buffer.append("http://epaper.esakal.com/esakal/");
						buffer.append(inputLine.substring(beginIndex, endIndex));
						//System.out.println(buffer);
						urlList.add(buffer.toString());
						buffer.delete(0, buffer.length());
						
						
						beginIndex = inputLine.indexOf("class=") + 13;
						endIndex = inputLine.indexOf("</a>");
						titleList.add(inputLine.substring(beginIndex, endIndex));
					}
				}
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		titles = titleList.toArray(new String[0]);
		urls =  urlList.toArray(new String[0]);
	}
	
	

	public String[] getTitles() {
		return titles;
	}

	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	public String[] getUrls() {
		return urls;
	}

	public void setUrls(String[] urls) {
		this.urls = urls;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
