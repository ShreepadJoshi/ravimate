package com.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.input.bean.SubTitleBean;
import com.input.bean.VlcTime;

public class SubTitleReader {
	
	String NEW_LINE = System.getProperty("line.separator");

	private String path;
	private VlcTime time;

	public SubTitleReader(String path, VlcTime time) {
		this.path = path;
		this.time = time;
	}

	public String getSubTitle() {
		String pathOfSubTitle = getPathOfSubTitle();

		File file = new File(pathOfSubTitle);

		BufferedReader bufferedReader = null;

		try {

			String currentLine;

			bufferedReader = new BufferedReader(new FileReader(file));
			
			List<SubTitleBean> subTitleBeans = new ArrayList<SubTitleBean>();
			SubTitleBean subTitleBean = new SubTitleBean();
			while ((currentLine = bufferedReader.readLine()) != null) {
				System.out.println(currentLine);

				if (isOnlyNumberInLine(currentLine)) {
					subTitleBean.setId(currentLine);
				} else if (currentLine.indexOf("-->") != -1) {
					subTitleBean.setTimeString(currentLine);
				} else if (currentLine.equals("")) {
					subTitleBeans.add(subTitleBean);
					subTitleBean = new SubTitleBean();
				}
				else {
					subTitleBean.setTest(currentLine);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		System.out.println(file);

		return pathOfSubTitle;
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
			System.out.println(pathOfSubTitle);

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
