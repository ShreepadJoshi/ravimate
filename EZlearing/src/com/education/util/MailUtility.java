package com.education.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class MailUtility {

	/**
	 * This method will be called by Utility class to send a Mail.
	 *   
	 * @param userName
	 * @param passWord
	 * @param host
	 * @param port
	 * @param starttls
	 * @param auth
	 * @param debug
	 * @param socketFactoryClass
	 * @param fallback
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param text
	 * @return result of mail sending. 
	 * @author Shree	  
	 */
	public static synchronized boolean sendMail(String userName,
			String passWord, String host, String port, String starttls,
			String auth, boolean debug, String socketFactoryClass,
			String fallback, String[] to, String[] cc, String[] bcc,
			String subject, String text) {
		Transport transport = null;
		Properties props = new Properties();
		// Properties props=System.getProperties();
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.host", host);
		if (!"".equals(port))
			props.put("mail.smtp.port", port);
		if (!"".equals(starttls))
			props.put("mail.smtp.starttls.enable", starttls);
		props.put("mail.smtp.auth", auth);
		if (debug) {
			props.put("mail.smtp.debug", "true");
		} else {
			props.put("mail.smtp.debug", "false");
		}
		if (!"".equals(port))
			props.put("mail.smtp.socketFactory.port", port);
		if (!"".equals(socketFactoryClass))
			props.put("mail.smtp.socketFactory.class", socketFactoryClass);
		if (!"".equals(fallback))
			props.put("mail.smtp.socketFactory.fallback", fallback);

		try {
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(debug);
			MimeMessage msg = new MimeMessage(session);
			msg.setContent(text, "text/html;charset=UTF-8");
			msg.setHeader("Content-Transfer-Encoding", "base64");
			msg.setSubject(subject, "UTF8");
			msg.setFrom(new InternetAddress(userName));
			for (int i = 0; i < to.length; i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						to[i]));
			}
			if (cc != null && cc.length > 1) {
				for (int i = 0; i < cc.length; i++) {
					msg.addRecipient(Message.RecipientType.CC,
							new InternetAddress(cc[i]));
				}
			}
			if (bcc != null && bcc.length > 1) {
				for (int i = 0; i < bcc.length; i++) {
					msg.addRecipient(Message.RecipientType.BCC,
							new InternetAddress(bcc[i]));
				}
			}
			msg.saveChanges();
			if (transport == null) {
				transport = session.getTransport("smtp");
				transport.connect(host, userName, passWord);
			}
			transport.sendMessage(msg, msg.getAllRecipients());
			// transport.sendMessage(msg, msg.getAllRecipients());
			// transport.close();
			return true;
		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}
	}
}