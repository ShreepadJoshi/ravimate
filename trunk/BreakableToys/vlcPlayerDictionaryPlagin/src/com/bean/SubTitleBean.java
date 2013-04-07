package com.bean;

import static com.util.Constants.NEW_LINE;;

public class SubTitleBean {

	private String id;
	private String timeString;
	private String subTitleText = "";

	@Override
	public String toString() {
		return "SubTitleBean [id=" + id + ", timeString=" + timeString
				+ ", test=" + NEW_LINE + subTitleText + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public String getSubTitleText() {
		return subTitleText;
	}

	public void setSubTitleText(String test) {
		if (this.subTitleText.length() == 0) {
			this.subTitleText = test;
		} else {
			this.subTitleText = this.subTitleText + NEW_LINE + test;
		}

	}

}
