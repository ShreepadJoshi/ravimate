package com.reader;

import java.text.DecimalFormat;

public class VlcTime {

	int hours;
	int minutes;
	int seconds;
	final DecimalFormat decimalFormat = new DecimalFormat("00");
	private long timeInLong = 0;

	public VlcTime(String time) {				
		this.timeInLong = Long.parseLong(time);
		seconds = (int)timeInLong % 60;

		minutes = (int)timeInLong / 60;
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
	
	public VlcTime getNextSeconds(){		
		return new VlcTime("" + (timeInLong + 1));		
	}
	
	public VlcTime getPreviousSeconds(){		
		return new VlcTime("" + (timeInLong - 1));		
	}

}
