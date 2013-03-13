package com;

import com.input.SubTitleReader;
import com.input.VLCReader;
import com.input.bean.VLCPlayerStatusBean;
import com.input.bean.VlcTime;


public class Main {

	public static void main(String argv[]) {
		
		VLCReader vlcReader = new VLCReader(); 
		VLCPlayerStatusBean vlcPlayerStatusBean  = vlcReader.getStatusOfVLCPlayer();
		
		String path = vlcPlayerStatusBean.getPathOfPlayingFile();
		VlcTime time = vlcPlayerStatusBean.getCurrentPositionInTime();
		
		SubTitleReader subTitleReader = new SubTitleReader(path, time);
		String subTitle = subTitleReader.getSubTitle();
		System.out.println("subTitle " + subTitle);

	}

}
