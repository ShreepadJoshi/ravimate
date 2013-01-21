package com.news.util;

import android.content.res.AssetManager;
import android.graphics.Typeface;

public class MarathiFontUtil {

	/**
	 * Load Marathi font
	 * 
	 * @return
	 */
	public static Typeface getMarathiFont(AssetManager assetManager) {
		return Typeface.createFromAsset(assetManager, "fonts/mangal.ttf");
	}
	
	



}
