package com.input.bean;

public class VLCPlayerStatusBean {
	
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

	@Override
	public String toString() {
		return "VLCPlayerStatusBean [currentPositionInTime="
				+ currentPositionInTime + ", pathOfPlayingFile="
				+ pathOfPlayingFile + "]";
	}

	
	

}
