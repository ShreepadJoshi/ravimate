package com.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import com.Main;
import com.bean.SubTitleBean;
import com.bean.VLCPlayerStatusBean;
import com.ui.panel.LogPlanel;
import com.ui.panel.StatusPanel;
import com.ui.panel.SubTitlePanal;

public class MainFrame extends JFrame {

	Main main;

	public MainFrame(Main main) {
		this.main = main;
	}

	StatusPanel statusPanel = new StatusPanel(this);
	SubTitlePanal subTitlePanal = new SubTitlePanal();
	LogPlanel logPlanel = LogPlanel.getInstance();
	int width = 1024;
	int height = 680;

	public void loadUI() {
		this.setTitle("VLC Player Dictionary Plagin");
		// this.setExtendedState(JFrame.MAXIMIZED_VERT);
		this.setSize(width, height);
		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());

		contentPane.add(statusPanel, BorderLayout.NORTH);
		statusPanel.loadPanel();		

		contentPane.add(subTitlePanal, BorderLayout.CENTER);
		subTitlePanal.loadPanel();

		contentPane.add(logPlanel, BorderLayout.SOUTH);
		logPlanel.loadPanel();

	}
	
	public void setStatus(VLCPlayerStatusBean statusBean) {
		statusPanel.setStatus(statusBean);
	}

	public void setSubTitles(SubTitleBean subTitleBean) {
		subTitlePanal.setSubTitles(subTitleBean);
	}

	
	public void actionPerformed(ActionEvent e) {
		this.main.performReloadEvent();
	}

	/**
	 */
	private static final long serialVersionUID = 1L;
}
