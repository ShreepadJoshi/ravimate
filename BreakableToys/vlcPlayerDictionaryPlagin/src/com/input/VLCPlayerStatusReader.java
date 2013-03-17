package com.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.input.bean.VLCPlayerStatusBean;
import com.input.bean.VlcTime;
import com.vlc.xml.bean.Node;
import com.vlc.xml.bean.RootNode;
import com.vlc.xml.bean.StatusBean;

/**
 * Class Reads status of VLC player.
 * 
 * @author shripad
 * 
 */
public class VLCPlayerStatusReader {

	final static String STATUS_PAGE = "/requests/status.xml";
	final static String PLAYLIST_PAGE = "/requests/playlist.xml";

	VLCPlayerStatusBean vlcPlayerStatusBean = new VLCPlayerStatusBean();

	public VLCPlayerStatusBean getStatusOfVLCPlayerFromXMLs() {

		URL localURL = getUrlForVLCPlayerRunningLocally(STATUS_PAGE);
		this.vlcPlayerStatusBean = getStatusOfVLCPlayerFromXMLs(localURL);
		return this.vlcPlayerStatusBean;
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

	public VLCPlayerStatusBean getStatusOfVLCPlayerFromXMLs(URL urlOfXml) {

		readStatusXMLandFatchCurrentPositionInTime(urlOfXml);

		// read playlist
		RootNode rootNodeBean = readPlaylistXML();
		String pathOfPlayingFile = fatchPathOfRunningFile(rootNodeBean);
		vlcPlayerStatusBean.setPathOfPlayingFile(pathOfPlayingFile);

		return vlcPlayerStatusBean;
	}

	private void readStatusXMLandFatchCurrentPositionInTime(URL urlOfXml) {
		// read status
		StatusBean statusBean = readStatusXML(urlOfXml);
		String time = statusBean.getTime();
		VlcTime currentPositionInTime = new VlcTime(time);
		this.vlcPlayerStatusBean
				.setCurrentPositionInTime(currentPositionInTime);
	}

	private String fatchPathOfRunningFile(RootNode rootNodeBean) {
		String path = "";
		ArrayList<Node> nodeList = rootNodeBean.getNodeList();
		for (Node node : nodeList) {
			if (node.getLeaf().getCurrent().equals("current")) {
				path = node.getLeaf().getUri();
				break;
			}
		}

		String encodedUrl = "";
		try {
			encodedUrl = java.net.URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encodedUrl;
	}

	public VLCPlayerStatusBean reloadCurrentPositionInTime() {
		URL localURL = getUrlForVLCPlayerRunningLocally(STATUS_PAGE);
		readStatusXMLandFatchCurrentPositionInTime(localURL);
		return this.vlcPlayerStatusBean;
	}

	private StatusBean readStatusXML(URL urlOfXml) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(StatusBean.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StatusBean statusBean = (StatusBean) jaxbUnmarshaller
					.unmarshal(urlOfXml);
			System.out.println(statusBean);
			return statusBean;

		} catch (JAXBException e) {
			e.printStackTrace();

		}
		return new StatusBean();
	}

	private RootNode readPlaylistXML() {
		RootNode rootNode = new RootNode();
		URL urlOfXml = getUrlForVLCPlayerRunningLocally(PLAYLIST_PAGE);

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(RootNode.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			rootNode = (RootNode) jaxbUnmarshaller.unmarshal(urlOfXml);
			System.out.println(rootNode);

		} catch (JAXBException e) {
			e.printStackTrace();

		}
		return rootNode;
	}

	@Deprecated
	// use getStatusOfVLCPlayerFromXMLs
	public VLCPlayerStatusBean getStatusOfVLCPlayer() {

		URL localURL = getUrlForVLCPlayerRunningLocally(STATUS_PAGE);
		return getStatusOfVLCPlayer(localURL);
	}

	@Deprecated
	// use getStatusOfVLCPlayerFromXMLs
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

		int startIndexOfUrl = playListPageBuffer.lastIndexOf("uri=")
				+ "uri=".length() + 1;
		int endIndexOfUrl = playListPageBuffer.lastIndexOf("\"");

		String path = playListPageBuffer.substring(startIndexOfUrl,
				endIndexOfUrl);
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
	private StringBuilder readAsHTMLPage(URL url) {
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

}
