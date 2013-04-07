package com.ui.fields;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.event.MouseInputListener;

public class SubTitleLable extends JLabel implements MouseInputListener {

	final private String wordInSubTitle;

	public SubTitleLable(String word) {
		super(word);
		wordInSubTitle = word;
		setText(wordInSubTitle);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Desktop desktop = java.awt.Desktop.getDesktop();
		URI url;
		try {
			url = new URI(
					"http://www.google.co.in/search?q=what+is+meaning+of+"
							+ wordInSubTitle);
			desktop.browse(url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.setForeground(Color.BLUE);

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.setForeground(Color.BLACK);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Logger.log("mousePressed");

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Logger.log("mouseReleased");

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// Logger.log("mouseDragged");

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// Logger.log("mouseMoved");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
