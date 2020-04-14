package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.JOptionPane;

public class Encrypt {

	public static void main(String[] args) {
		/*
		 * System.out.println("Enter the password : "); Scanner reader = new
		 * Scanner(System.in); String password = reader.next();
		 */

		String password = JOptionPane.showInputDialog("Please enter password: ");
		writeEncryptedDateToFile(password);

	}

	public static void writeEncryptedDateToFile(String password) {
		Crypter en = new Crypter(password);

		File input = new File("input.txt");
		File eoutput = new File("encrypted.txt");

		try {
			en.WriteEncryptedFile(new FileInputStream(input), new FileOutputStream(eoutput));

		} catch (IllegalBlockSizeException | BadPaddingException | IOException e) {
			e.printStackTrace();
		}

	}

}
