package com.reader.vlc;

import static com.util.Constants.PASSWORD;
import static com.util.Constants.USER_NAME;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.codec.binary.Base64;

import com.bean.VLCPlayerStatusBean;
import com.logger.UILogger;
import com.reader.vlc.xml.Node;
import com.reader.vlc.xml.RootNode;
import com.reader.vlc.xml.Status;

;

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

		String pathOfSubTitle = getPathOfSubTitle(pathOfPlayingFile);
		vlcPlayerStatusBean.setPathOfSubTitleFile(pathOfSubTitle);

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

	private Status readStatusXML1(URL urlOfXml) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Status.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Status statusBean = (Status) jaxbUnmarshaller.unmarshal(urlOfXml);
			UILogger.log(statusBean);
			return statusBean;

		} catch (JAXBException e) {
			UILogger.log(e);
			e.printStackTrace();

		}
		return new Status();
	}

	/**
	 * To get the InputStream of given Base64 encode URL.
	 * 
	 * @param urlOfXml
	 *            to be open
	 * @return InputStream of given Base64 encode URL
	 */
	private InputStream getInputStreamOfEncodedConnection(final URL urlOfXml) {
		URLConnection connection = null;
		InputStream inputStream = null;
		String userPassword = USER_NAME + ":" + PASSWORD;

		byte[] authEncBytes = Base64.encodeBase64(userPassword.getBytes());
		String encoding = new String(authEncBytes);
		try {
			connection = urlOfXml.openConnection();
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			connection.connect();
			inputStream = connection.getInputStream();

		} catch (IOException e) {
			UILogger.log("getBase64EncodedConnectionForURl --  " + e);
			e.printStackTrace();
		}
		return inputStream;
	}

	/**
	 * To read the status xml
	 * 
	 * @param urlOfXml
	 * @return
	 */
	private Status readStatusXML(URL urlOfXml) {

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Status.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			InputStream inputStream = getInputStreamOfEncodedConnection(urlOfXml);

			Status statusBean = (Status) jaxbUnmarshaller
					.unmarshal(inputStream);

			UILogger.log(statusBean);
			return statusBean;

		} catch (JAXBException ex) {
			System.out.println(ex);
			UILogger.log(ex);
			ex.printStackTrace();

		}
		return new Status();
	}

	private RootNode readPlaylistXML() {
		RootNode rootNode = new RootNode();
		URL urlOfXml = getUrlForVLCPlayerRunningLocally(PLAYLIST_PAGE);

		try {
			InputStream inputStream = getInputStreamOfEncodedConnection(urlOfXml);

			JAXBContext jaxbContext = JAXBContext.newInstance(RootNode.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			rootNode = (RootNode) jaxbUnmarshaller.unmarshal(inputStream);
			UILogger.log(rootNode);

		} catch (JAXBException e) {
			UILogger.log(e);
			e.printStackTrace();

		}
		return rootNode;
	}

	private String getPathOfSubTitle(String pathOfPlayingFile) {
		// added .srt
		int indexOfDot = pathOfPlayingFile.lastIndexOf(".");
		String pathOfSubTitle = pathOfPlayingFile.substring(0, indexOfDot)
				+ ".srt";

		// decode url (remove 20%)
		try {
			pathOfSubTitle = java.net.URLDecoder.decode(pathOfSubTitle,
					"ISO-8859-1");
			UILogger.log(pathOfSubTitle);

			pathOfSubTitle = removeNotNeedTags(pathOfSubTitle);
		} catch (UnsupportedEncodingException e1) {
			// TODO change this
			e1.printStackTrace();
		}
		return pathOfSubTitle;
	}

	private String removeNotNeedTags(String pathOfSubTitle) {
		int index = pathOfSubTitle.indexOf("file://");
		if (index > -1) {
			int beginIndex = index + "file://".length();
			return pathOfSubTitle
					.substring(beginIndex, pathOfSubTitle.length());
		}
		return pathOfSubTitle;
	}

}
