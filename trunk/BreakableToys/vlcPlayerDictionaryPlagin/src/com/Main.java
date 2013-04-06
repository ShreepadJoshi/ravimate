package com;

import com.input.SubTitleReader;
import com.input.VLCPlayerStatusReader;
import com.input.bean.SubTitleBean;
import com.input.bean.VLCPlayerStatusBean;
import com.input.bean.VlcTime;
import com.ui.MainFrame;
import com.ui.log.UILogger;

public class Main {

	VLCPlayerStatusReader vlcPlayerStatusReader;
	SubTitleReader subTitleReader;
	VLCPlayerStatusBean vlcPlayerStatusBean;
	MainFrame mainFrame;

	public static void main(String argv[]) {
		Main main = new Main();
		main.reLoadAll();

	}

	void reLoadAll() {
		vlcPlayerStatusReader = new VLCPlayerStatusReader();
		vlcPlayerStatusBean = vlcPlayerStatusReader
				.getStatusOfVLCPlayerFromXMLs();

		String path = vlcPlayerStatusBean.getPathOfPlayingFile();
		VlcTime time = vlcPlayerStatusBean.getCurrentPositionInTime();

		subTitleReader = new SubTitleReader(path, time);
		subTitleReader.loadSubTitles();
		SubTitleBean subTitleBean = subTitleReader.getSubTitle(time);

		UILogger.log("");
		UILogger.log("subTitle " + subTitleBean);
		UILogger.log("");

		mainFrame = new MainFrame(this);
		mainFrame.loadUI();
		mainFrame.setSubTitles(subTitleBean);
		mainFrame.repaint();
	}

	public void performReloadSubTitleEvent() {		

		if (isFileBeingPlayedChanged()) {
			String newFilePath = vlcPlayerStatusBean.getPathOfPlayingFile();
			subTitleReader.reLoadSubTitles(newFilePath);
		}

		VlcTime time = vlcPlayerStatusBean.getCurrentPositionInTime();
		SubTitleBean subTitleBean = subTitleReader.getSubTitle(time);
		UILogger.log("subTitle " + subTitleBean);
		mainFrame.setSubTitles(subTitleBean);
		mainFrame.repaint();
		mainFrame.revalidate();

	}

	/**
	 * if oldFilePath is NOT equals to currentFilePath then file being played
	 * changed
	 * 
	 * @param oldFilePath
	 * @param currentFilePath
	 * @return isFileBeingPlayedChanged OR not
	 */
	private boolean isFileBeingPlayedChanged() {

		String oldFilePath = vlcPlayerStatusBean.getPathOfPlayingFile();

		vlcPlayerStatusBean = vlcPlayerStatusReader.reloadPlayerStatusBean();

		String currentFilePath = vlcPlayerStatusBean.getPathOfPlayingFile();

		if (oldFilePath.equals(currentFilePath)) {
			return false;
		}
		return true;
	}

	public SubTitleReader getSubTitleReader() {
		return subTitleReader;
	}

	public void setSubTitleReader(SubTitleReader subTitleReader) {
		this.subTitleReader = subTitleReader;
	}

}
