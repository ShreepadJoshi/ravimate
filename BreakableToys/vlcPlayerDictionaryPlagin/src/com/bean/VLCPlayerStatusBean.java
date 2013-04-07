package com.bean;

import com.reader.vlc.VlcTime;

/**
 * Class to hold data read from VLC player. (only which is need and in proper
 * format.)
 * 
 * @author shripad
 * 
 */
public class VLCPlayerStatusBean {

	private String serverURLOfHttpInterface;

	private String pathOfPlayingFile;

	private String nameOfFile;
	
	private VlcTime currentPositionInTime;
	
	private String pathOfSubTitleFile;

	public String getNameOfFile() {
		return nameOfFile;
	}

	public void setNameOfFile(String nameOfFile) {
		this.nameOfFile = nameOfFile;
	}

	public VlcTime getCurrentPositionInTime() {
		return currentPositionInTime;
	}

	public void setCurrentPositionInTime(VlcTime currentPositionInTime) {
		this.currentPositionInTime = currentPositionInTime;
	}

	public String getPathOfPlayingFile() {
		return pathOfPlayingFile;
	}

	public void setPathOfPlayingFile(String pathOfPlayingFile) {
		this.pathOfPlayingFile = pathOfPlayingFile;
	}

	public String getServerURLOfHttpInterface() {
		return serverURLOfHttpInterface;
	}

	public void setServerURLOfHttpInterface(String serverURLOfHttpInterface) {
		this.serverURLOfHttpInterface = serverURLOfHttpInterface;
	}

	public String getPathOfSubTitleFile() {
		return pathOfSubTitleFile;
	}

	public void setPathOfSubTitleFile(String pathOfSubTitleFile) {
		this.pathOfSubTitleFile = pathOfSubTitleFile;
	}

	@Override
	public String toString() {
		return "VLCPlayerStatusBean [currentPositionInTime="
				+ currentPositionInTime + ", pathOfPlayingFile="
				+ pathOfPlayingFile + "]";
	}

}
