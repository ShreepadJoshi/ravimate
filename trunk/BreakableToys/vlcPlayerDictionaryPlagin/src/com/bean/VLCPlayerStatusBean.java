package com.bean;

import com.reader.VlcTime;

/**
 * Class to hold data read from VLC player. (only which is need and in proper
 * format.)
 * 
 * @author shripad
 * 
 */
public class VLCPlayerStatusBean {

	private String serverURLOfHttpInterface;

	private VlcTime currentPositionInTime;

	private String pathOfPlayingFile;

	private String nameOfFile;

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

	@Override
	public String toString() {
		return "VLCPlayerStatusBean [currentPositionInTime="
				+ currentPositionInTime + ", pathOfPlayingFile="
				+ pathOfPlayingFile + "]";
	}

}
