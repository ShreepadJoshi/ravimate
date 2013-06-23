package com;

import com.bean.SubTitleBean;
import com.bean.VLCPlayerStatusBean;
import com.logger.UILogger;
import com.reader.subtitle.SubTitleReader;
import com.reader.vlc.VLCPlayerStatusReader;
import com.reader.vlc.VlcTime;
import com.ui.MainFrame;

public class Main {

	VLCPlayerStatusReader vlcPlayerStatusReader;
	SubTitleReader subTitleReader;
	VLCPlayerStatusBean vlcPlayerStatusBean;
	MainFrame mainFrame;

	public static void main(String argv[]) {
		Main main = new Main();
		main.mainFrame = new MainFrame(main);
		main.mainFrame.loadUI();

		main.vlcPlayerStatusReader = new VLCPlayerStatusReader();
		main.reLoadAll();

	}

	void reLoadAll() {

		vlcPlayerStatusBean = vlcPlayerStatusReader
				.getStatusOfVLCPlayerFromXMLs();

		String pathForSubtitles = vlcPlayerStatusBean.getPathOfSubTitleFile();
		VlcTime time = vlcPlayerStatusBean.getCurrentPositionInTime();

		subTitleReader = new SubTitleReader(pathForSubtitles);

		if (subTitleReader.isSubTitleAvailable()) {
			subTitleReader.loadSubTitles();
			SubTitleBean subTitleBean = subTitleReader.getSubTitle(time);
			mainFrame.setSubTitles(subTitleBean);
			UILogger.log("");
			UILogger.log("subTitle " + subTitleBean);
			UILogger.log("");

		} else {
			SubTitleBean subTitleBean = new SubTitleBean();
			subTitleBean.setSubtitleNotAvailable();
			mainFrame.setSubTitles(subTitleBean);
		}

		mainFrame.setStatus(vlcPlayerStatusBean);
		mainFrame.repaint();
		mainFrame.revalidate();
	}

	public void performReloadEvent() {

		if (isFileBeingPlayedChanged()) {			
			reLoadAll();			
		} else {
			VlcTime time = vlcPlayerStatusBean.getCurrentPositionInTime();
			SubTitleBean subTitleBean = subTitleReader.getSubTitle(time);
			UILogger.log("subTitle " + subTitleBean);
			mainFrame.setSubTitles(subTitleBean);
			mainFrame.repaint();
			mainFrame.revalidate();
		}

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
