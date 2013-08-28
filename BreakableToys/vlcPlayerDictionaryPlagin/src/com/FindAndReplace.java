package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FindAndReplace {

	private static final String filePath = "/media/shripad/RND/Shreejava/temp/temp/com/ui/MainFrame.java";

	File file = new File(filePath);

	final static String endOfLine = System.getProperty("line.separator");

	// customers.toArray(new Customer[0]);

	// customers.toArray(new Customer[customers.size()]);

	// private static List<Customer> customers ;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		new FindAndReplace().start();

	}

	private void start() throws IOException {
		StringBuffer buffer = new StringBuffer();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String currentLine;
		while ((currentLine = br.readLine()) != null) {
			String updatedLine = processTheLine(currentLine);
			buffer.append(updatedLine);
		}
		br.close();

		
		System.out.println(buffer.toString());
	}

	private String processTheLine(String currentLine) {
		if (lineHavingToArray(currentLine)) {
			if (canWeReplaceIt(currentLine) == true) {
				replaceIt();
			}
		}
		return currentLine;
	}

	private boolean lineHavingToArray(final String line) {
		int indexOfToArray = line.indexOf("toArray(new");
		if (indexOfToArray > -1) {
			if (lineHasZero(line)) {
				return true;
			}
		}
		return false;
	}

	private boolean lineHasZero(String line) {
		int indexOfZero = line.indexOf("[0]");
		if (indexOfZero > -1) {
			return true;
		}
		return false;

	}

	private void replaceIt() {
		// TODO Auto-generated method stub

	}

	private boolean canWeReplaceIt(String currentLine) {
		int result = JOptionPane.showConfirmDialog(null, currentLine);
		System.out.println(result); //TODO update it 
		return false;
	}



	private String loadFile(String filepath) {
		String content = null;
		File file = new File(filepath);
		try {
			FileReader reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;

	}

}
