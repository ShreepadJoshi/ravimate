package com.ui.panel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	private JLabel playinglbl = new JLabel("path : - ");

	public void loadPanel() {
		this.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		this.setBackground(Color.white);
		this.add(playinglbl);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
