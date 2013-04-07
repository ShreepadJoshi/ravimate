package com.reader;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.bean.VLCPlayerStatusBean;
import com.logger.UILogger;
import com.reader.vlc.xml.Node;
import com.reader.vlc.xml.RootNode;
import com.reader.vlc.xml.Status;

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

	public VLCPlayerStatusBean reloadPlayerStatusBean() {
		return getStatusOfVLCPlayerFromXMLs();
	}

	public VLCPlayerStatusBean getStatusOfVLCPlayerFromXMLs() {
		URL serverURLOfHttpInterface = getUrlForVLCPlayerRunningLocally("");
		vlcPlayerStatusBean
				.setServerURLOfHttpInterface(serverURLOfHttpInterface
						.toString());

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
		Status statusBean = readStatusXML(urlOfXml);
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

	private Status readStatusXML(URL urlOfXml) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Status.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Status statusBean = (Status) jaxbUnmarshaller
					.unmarshal(urlOfXml);
			UILogger.log(statusBean);
			return statusBean;

		} catch (JAXBException e) {
			UILogger.log(e);
			e.printStackTrace();

		}
		return new Status();
	}

	private RootNode readPlaylistXML() {
		RootNode rootNode = new RootNode();
		URL urlOfXml = getUrlForVLCPlayerRunningLocally(PLAYLIST_PAGE);

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(RootNode.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			rootNode = (RootNode) jaxbUnmarshaller.unmarshal(urlOfXml);
			UILogger.log(rootNode);

		} catch (JAXBException e) {
			UILogger.log(e);
			e.printStackTrace();

		}
		return rootNode;
	}

}
