package com.reader.subtitle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.bean.SubTitleBean;
import com.logger.UILogger;
import com.reader.vlc.VlcTime;

public class SubTitleReader {

	private Map<String, SubTitleBean> timeSubTitleMap = new HashMap<String, SubTitleBean>();

	private File subTitleFile;

	public SubTitleReader(String pathOfSubTitle) {
		this.subTitleFile = new File(pathOfSubTitle);
	}

	/**
	 * @param newFilePath
	 * @return
	 */
	public Map<String, SubTitleBean> reLoadSubTitles(String newPathOfSubTitle) {
		this.subTitleFile = new File(newPathOfSubTitle);
		if (subTitleAvailable()) {
			this.timeSubTitleMap = loadSubTitles();
		} else {
			this.timeSubTitleMap = new HashMap<String, SubTitleBean>();
		}
		return timeSubTitleMap;
	}

	public Map<String, SubTitleBean> loadSubTitles() {

		if (subTitleAvailable()) {

			BufferedReader bufferedReader = null;
			try {

				String currentLine;
				//bufferedReader = new BufferedReader(new FileReader(subTitleFile));
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(subTitleFile), Charset.forName("UTF-8")));

				SubTitleBean subTitleBean = new SubTitleBean();

				while ((currentLine = bufferedReader.readLine()) != null) {

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

			} catch (FileNotFoundException fileNotFoundException) {
				UILogger.logError("SubTitle not available..."
						+ fileNotFoundException);
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
		}

		UILogger.log(subTitleFile);

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

	public boolean isSubTitleAvailable() {
		return subTitleAvailable();
	}

	private boolean subTitleAvailable() {
		return subTitleFile.exists();
	}
}
