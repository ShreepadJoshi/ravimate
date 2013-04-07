package com.reader.subtitle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.bean.SubTitleBean;
import com.logger.UILogger;
import com.reader.vlc.VlcTime;

public class SubTitleReader {

	String NEW_LINE = System.getProperty("line.separator");

	private String path;
	
	Map<String, SubTitleBean> timeSubTitleMap = new HashMap<String, SubTitleBean>();

	public SubTitleReader(String path, VlcTime time) {
		this.path = path;		
	}

	public Map<String, SubTitleBean> reLoadSubTitles(String newFilePath) {
		this.path = newFilePath;	
		return loadSubTitles();
	}

	public Map<String, SubTitleBean> loadSubTitles() {
		String pathOfSubTitle = getPathOfSubTitle();

		File file = new File(pathOfSubTitle);

		BufferedReader bufferedReader = null;

		try {

			// List<SubTitleBean> subTitleBeans = new ArrayList<SubTitleBean>();

			String currentLine;
			bufferedReader = new BufferedReader(new FileReader(file));
			SubTitleBean subTitleBean = new SubTitleBean();

			while ((currentLine = bufferedReader.readLine()) != null) {
				// Logger.log(currentLine);

				if (isOnlyNumberInLine(currentLine)) {
					subTitleBean.setId(currentLine);
				} else if (currentLine.indexOf("-->") != -1) {
					String time = getTimeFromRowLine(currentLine);
					subTitleBean.setTimeString(time);
				} else if (currentLine.equals("")) {
					timeSubTitleMap.put(subTitleBean.getTimeString(),
							subTitleBean);
					// subTitleBeans.add(subTitleBean);
					subTitleBean = new SubTitleBean();
				} else {
					subTitleBean.setSubTitleText(currentLine);
				}

			}

		} catch (IOException e) {
			UILogger.log(e);
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		UILogger.log(file);

		return timeSubTitleMap;
	}

	private String getTimeFromRowLine(String currentLine) {
		int endIndex = currentLine.indexOf(",");
		return currentLine.substring(0, endIndex);
	}

	private boolean isOnlyNumberInLine(String currentLine) {

		try {
			Integer.parseInt(currentLine);
			return true;
		} catch (NumberFormatException exception) {
			return false;
		}
	}

	private String getPathOfSubTitle() {
		// added .srt
		int indexOfDot = path.lastIndexOf(".");
		String pathOfSubTitle = path.substring(0, indexOfDot) + ".srt";

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

	public SubTitleBean getSubTitle(VlcTime vlcTime) {
		SubTitleBean subTitleBean = new SubTitleBean();

		if (timeSubTitleMap.containsKey(vlcTime.toString())) {
			subTitleBean = timeSubTitleMap.get(vlcTime.toString());
		} else {
			VlcTime lastVlcTime = vlcTime.getPreviousSeconds();
			String lastTime = lastVlcTime.toString();

			while (timeSubTitleMap.containsKey(lastTime) == false) {
				lastVlcTime = lastVlcTime.getPreviousSeconds();
				lastTime = lastVlcTime.toString();
				UILogger.log("Trying " + lastTime);
			}
			subTitleBean = timeSubTitleMap.get(lastTime);
		}

		return subTitleBean;
	}
}
