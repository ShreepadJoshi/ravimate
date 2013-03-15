package com.input.bean;

import static com.util.Constants.NEW_LINE;;

public class SubTitleBean {

	private String id;
	private String timeString;
	private String test = "";

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

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		if (this.test.length() == 0) {
			this.test = test;
		} else {
			this.test = this.test + NEW_LINE + test;
		}

	}

}
