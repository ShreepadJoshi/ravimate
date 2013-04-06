package com.ui.log;

import com.ui.panel.LogPlanel;

public class UILogger {

	public static void log(String log) {
		LogPlanel.getInstance().displayLog(log);
		System.out.println(log);
	}
	
	public static void log(Object log) {
		log(log.toString());
	}

}
