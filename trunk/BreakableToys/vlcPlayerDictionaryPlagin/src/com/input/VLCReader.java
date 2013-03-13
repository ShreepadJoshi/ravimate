package com.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.input.bean.VLCPlayerStatusBean;
import com.input.bean.VlcTime;

/**
 * Class Reads status of VLC player.
 * 
 * @author shripad
 * 
 */
public class VLCReader {

	final static String STATUS_PAGE = "/requests/status.xml";
	final static String PLAYLIST_PAGE = "/requests/playlist.xml";
	
	
	

	public VLCPlayerStatusBean getStatusOfVLCPlayer() {

		URL localURL = getUrlForVLCPlayerRunningLocally(STATUS_PAGE);
		return getStatusOfVLCPlayer(localURL);
	}

	public VLCPlayerStatusBean getStatusOfVLCPlayer(URL url) {
		VLCPlayerStatusBean vlcPlayerStatusBean = new VLCPlayerStatusBean();

		StringBuilder buffer = readAsHTMLPage(url);
		String currentPosition = getValueByNode(buffer, "time");
		VlcTime currentTime = new VlcTime(currentPosition);
		System.out.println(currentTime.getPreviousSeconds());
		System.out.println(currentTime);
		System.out.println(currentTime.getNextSeconds());

		vlcPlayerStatusBean.setCurrentPositionInTime(currentTime);

		URL playlistURL = getUrlForVLCPlayerRunningLocally(PLAYLIST_PAGE);
		StringBuilder playListPageBuffer = readAsHTMLPage(playlistURL);
		int indexOfCurrent = playListPageBuffer.indexOf("current=\"current\"");
		
		playListPageBuffer.delete(indexOfCurrent, playListPageBuffer.length());
		
		int startIndexOfUrl = playListPageBuffer.lastIndexOf("uri=") + "uri=".length() +1 ;
		int endIndexOfUrl = playListPageBuffer.lastIndexOf("\"");
		
		String path =  playListPageBuffer.substring(startIndexOfUrl, endIndexOfUrl);
		vlcPlayerStatusBean.setPathOfPlayingFile(path);
		System.out.println(path);

		return vlcPlayerStatusBean;
		
		
		

	}

	private String getValueByNode(StringBuilder buffer, String node) {
		String startTag = "<" + node + ">";
		String endTag = "</" + node + ">";
		int start = buffer.indexOf(startTag) + startTag.length();
		int end = buffer.indexOf(endTag);

		return buffer.substring(start, end);
	}

	/**
	 * To read html page of news.
	 * 
	 * @param newUrl
	 * @return newsAsHTML
	 */
	public StringBuilder readAsHTMLPage(URL url) {
		System.out.println("Reading --> " + url);
		StringBuilder buffer = new StringBuilder();
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream(), "UTF-8"));

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
		return new StringBuilder("Error reading HTMP page from given URL");

	}

	public URL getUrlForVLCPlayerRunningLocally(String page) {
		URL localURL = null;
		try {
			localURL = new URL("http", "localhost", 8080, page);
		} catch (MalformedURLException e) {
			e.printStackTrace(); // TODO Change This...
		}
		return localURL;
	}

}
