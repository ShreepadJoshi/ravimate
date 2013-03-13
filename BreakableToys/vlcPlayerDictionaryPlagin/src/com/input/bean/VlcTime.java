package com.input.bean;

import java.text.DecimalFormat;

public class VlcTime {

	int hours;
	int minutes;
	int seconds;
	final DecimalFormat decimalFormat = new DecimalFormat("00");
	private String time;

	public VlcTime(String time) {
		this.time = time;		
		int timeInLong = Integer.parseInt(time);

		seconds = timeInLong % 60;

		minutes = timeInLong / 60;
		if (minutes > 60) {
			hours = minutes / 60;
			minutes = minutes % 60;
		}

	}

	@Override
	public String toString() {

		return "" + decimalFormat.format(hours) + ":"
				+ decimalFormat.format(minutes) + ":"
				+ decimalFormat.format(seconds);
	}
	
	public String getNextSeconds(){		
		int timeInLong = Integer.parseInt(time);
		timeInLong = timeInLong + 1;
		VlcTime vlcTime = new VlcTime("" + timeInLong);
		return vlcTime.toString();		
	}
	
	public String getPreviousSeconds(){		
		int timeInLong = Integer.parseInt(time);
		timeInLong = timeInLong - 1;
		VlcTime vlcTime = new VlcTime("" + timeInLong);
		return vlcTime.toString();		
	}

}
