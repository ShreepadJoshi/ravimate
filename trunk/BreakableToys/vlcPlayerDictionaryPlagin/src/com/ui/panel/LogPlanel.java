package com.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * To display log in log-panel
 * 
 * @author shripad
 * 
 */
public class LogPlanel extends JPanel {

	private static LogPlanel logPlanel = new LogPlanel();
	JTextArea logTextArea = new JTextArea("please remove this text"); // , 8, 80
	//JScrollPane scrollPane = new JScrollPane(logTextArea);

	private LogPlanel() {

	}

	public void loadPanel() {
		this.setBorder(BorderFactory.createLineBorder(Color.darkGray, 3));
		this.setBackground(Color.BLACK);
		this.setAutoscrolls(true);
		this.setPreferredSize(new Dimension(350, 190));
		this.setLayout(new BorderLayout());

		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//this.add(scrollPane, BorderLayout.CENTER);

		logTextArea.setLineWrap(true);
		logTextArea.setAutoscrolls(true);
		// scrollPane.setPreferredSize(new Dimension(350, 190));
		//scrollPane.add(logTextArea);
		this.add(logTextArea);
		

	}

	public void displayLog(String log) {
		logTextArea.append(log);
		this.repaint();
		this.revalidate();
	}

	public static LogPlanel getInstance() {
		return logPlanel;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
