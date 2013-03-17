package com.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.Main;
import com.input.bean.SubTitleBean;
import com.ui.panel.ButtonPanel;
import com.ui.panel.ReloadButton;
import com.ui.panel.SubTitlePanal;

public class MainFrame extends JFrame implements ActionListener {

	Main main;

	public MainFrame(Main main) {
		this.main = main;
	}

	ButtonPanel buttonPanel = new ButtonPanel();
	SubTitlePanal subTitlePanal = new SubTitlePanal();

	public void loadUI() {
		this.setTitle("VLC Player Dictionary Plagin");
		// this.setExtendedState(JFrame.MAXIMIZED_VERT);
		this.setSize(800, 400);
		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new FlowLayout());

		contentPane.add(buttonPanel);
		buttonPanel.loadPanel();
		ReloadButton reloadButton = new ReloadButton();
		reloadButton.addActionListener(this);
		buttonPanel.add(reloadButton);

		contentPane.add(subTitlePanal);
		subTitlePanal.loadPanel();

	}

	public void setSubTitles(SubTitleBean subTitleBean) {
		subTitlePanal.setSubTitles(subTitleBean);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.main.performReloadSubTitleEvent();
	}

	/**
	 */
	private static final long serialVersionUID = 1L;
}
