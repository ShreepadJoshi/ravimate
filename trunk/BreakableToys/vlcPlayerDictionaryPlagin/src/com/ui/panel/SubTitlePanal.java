package com.ui.panel;

import java.awt.Color;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.input.bean.SubTitleBean;
import com.ui.SubTitleLable;
import static com.util.Constants.NEW_LINE;

;

public class SubTitlePanal extends JPanel {

	public void loadPanel() {
		this.setBorder(BorderFactory.createLineBorder(Color.darkGray, 3));
		// this.setBackground(Color.BLACK);
	}

	public void setSubTitles(SubTitleBean subTitleBean) {
		this.removeAll();
		String text = subTitleBean.getSubTitleText();
		StringBuffer stringBuffer = new StringBuffer(text);

		text = removeNewLineCharacter(stringBuffer);

		StringTokenizer it = new StringTokenizer(text, " ");
		while (it.hasMoreElements()) {
			String word = (String) it.nextElement();
			SubTitleLable subTitleLable = new SubTitleLable(word);
			this.add(subTitleLable);
		}

	}

	private String removeNewLineCharacter(StringBuffer stringBuffer) {
		int indexOfNewLineCharacter = stringBuffer.indexOf(NEW_LINE);
		if (indexOfNewLineCharacter != -1) {
			stringBuffer.deleteCharAt(indexOfNewLineCharacter);
		}
		return stringBuffer.toString();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
