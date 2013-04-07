package com.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bean.VLCPlayerStatusBean;
import com.ui.MainFrame;
import com.ui.fields.ReloadButton;

public class StatusPanel extends JPanel implements ActionListener {

	private MainFrame mainFrame;

	public StatusPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	private JPanel runningOnServerPanel = new JPanel();
	private JLabel runningOnServerLbl = new JLabel("Running On Server: - ");
	private TextField runningOnServerTxt = new TextField("");
	private ReloadButton reloadButton = new ReloadButton();

	private JPanel playingFilePanel = new JPanel();
	private JLabel playingFilelbl = new JLabel("Playing File: - ");
	private TextField playingFileTxt = new TextField("");

	private JPanel subTitlePathPanel = new JPanel();
	private JLabel subTitlePathlbl = new JLabel("Reading SubTitle file: - ");
	private TextField subTitlePathTxt = new TextField("");

	public void loadPanel() {
		this.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());

		runningOnServerPanel.add(runningOnServerLbl);
		runningOnServerPanel.add(runningOnServerTxt);
		runningOnServerPanel.add(reloadButton);
		reloadButton.addActionListener(this);
		this.add(runningOnServerPanel, BorderLayout.NORTH);

		playingFilePanel.add(playingFilelbl);
		playingFilePanel.add(playingFileTxt);
		this.add(playingFilePanel, BorderLayout.CENTER);

		subTitlePathPanel.add(subTitlePathlbl);
		subTitlePathPanel.add(subTitlePathTxt);
		this.add(subTitlePathPanel, BorderLayout.SOUTH);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setStatus(VLCPlayerStatusBean statusBean) {
		runningOnServerTxt.setText(statusBean.getServerURLOfHttpInterface());
		playingFileTxt.setText(statusBean.getPathOfPlayingFile());
		subTitlePathTxt.setText("");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.mainFrame.actionPerformed(e);
	}

}
