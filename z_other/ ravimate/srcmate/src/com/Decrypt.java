package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.JOptionPane;

public class Decrypt {

	public static void main(String[] args) {
		String password = JOptionPane.showInputDialog("Please enter password: ");
		writeDecryptedDateToFile(password);
	}

	
	public static void writeDecryptedDateToFile(String password) {
		AES decrypter = new AES(password);

		File eoutput = new File("encrypted.txt");
		File doutput = new File("decrypted.txt");

		try {
			decrypter.ReadEncryptedFile(new FileInputStream(eoutput), new FileOutputStream(doutput));
			System.out.println("decryption done " + doutput.getName());

		} catch (IllegalBlockSizeException | BadPaddingException | IOException e) {
			System.out.println("Password is not correct ");
			e.printStackTrace();
		}

	}
}
