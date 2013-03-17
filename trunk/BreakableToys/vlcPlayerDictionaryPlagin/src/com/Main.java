package com;

import com.input.SubTitleReader;
import com.input.VLCPlayerStatusReader;
import com.input.bean.SubTitleBean;
import com.input.bean.VLCPlayerStatusBean;
import com.input.bean.VlcTime;
import com.ui.MainFrame;

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

		System.out.println("");
		System.out.println("");
		System.out.println("subTitle " + subTitleBean);
		mainFrame = new MainFrame(this);
		mainFrame.loadUI();
		mainFrame.setSubTitles(subTitleBean);
		mainFrame.repaint();
	}

	public void performReloadSubTitleEvent() {
		VLCPlayerStatusBean vlcPlayerStatusBean = vlcPlayerStatusReader
				.reloadCurrentPositionInTime();
		VlcTime time = vlcPlayerStatusBean.getCurrentPositionInTime();
		SubTitleBean subTitleBean = subTitleReader.getSubTitle(time);
		System.out.println("subTitle " + subTitleBean);
		mainFrame.setSubTitles(subTitleBean);
		mainFrame.repaint();
		mainFrame.revalidate();

	}

	public SubTitleReader getSubTitleReader() {
		return subTitleReader;
	}

	public void setSubTitleReader(SubTitleReader subTitleReader) {
		this.subTitleReader = subTitleReader;
	}

}
