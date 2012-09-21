package com;

//set CLASSPATH=%CLASSPATH%;activation.jar;mail.jar
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class GMail1 {
	/*
	 * String d_email = "shreepadjoshi@gmail.com", d_password = "*paass",
	 * d_host = "smtp.gmail.com", d_port = "465", m_to =
	 * "shreepad.joshi@yahoo.co.in", m_subject = "Testing", m_text =
	 * "Hey, this is the testing email using smtp.gmail.com.";
	 */
	Transport transport;
	
	String userName = "wadagroup2";
	String passWord = "paaasword";
	String subject = "Esakal :- ";
	String mailBody = "data";
	
	static String[] to =  { "shripad.joshi@cognizant.com" };
	//static String[] to =  { "shreepadjoshi@gmail.com" };
	static String[] cc =  new String[0];
	static String[] bcc = new String[0]; 
	
	
	
	
	/*
	public static void main(String[] args) {
		
		// This is for google
		
		GMail me = new GMail();
		new GMail().sendMail(me.userName,me.passWord, "smtp.gmail.com", "465",
				"true", "true", true, "javax.net.ssl.SSLSocketFactory",
				"false", to, cc, bcc, me.subject, me.mailBody);
	}
	*/
	
	public void sendMail(String subject,String mailBody){
		this.sendMail(userName,passWord, "smtp.gmail.com", "465",
				"true", "true", false, "javax.net.ssl.SSLSocketFactory",
				"false", to, cc, bcc, this.subject+subject, mailBody);
	}

	synchronized boolean sendMail(String userName,
			String passWord, String host, String port, String starttls,
			String auth, boolean debug, String socketFactoryClass,
			String fallback, String[] to, String[] cc, String[] bcc,
			String subject, String text) {
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
			//msg.setContent(text, "text/html;charset=UTF-8");			
			msg.setHeader("Content-Transfer-Encoding","8bit");//update
			msg.setSubject(subject,"UTF8");
			msg.setFrom(new InternetAddress("wadagroup2@gmail.com"));			
			for (int i = 0; i < to.length; i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						to[i]));
			}
			for (int i = 0; i < cc.length; i++) {
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(
						cc[i]));
			}
			for (int i = 0; i < bcc.length; i++) {
				msg.addRecipient(Message.RecipientType.BCC,
						new InternetAddress(bcc[i]));
			}
			
			MimeMultipart content = new MimeMultipart("alternative");
			MimeBodyPart txt = new MimeBodyPart();
			MimeBodyPart html = new MimeBodyPart();
			txt.setText( text ,"UTF-8");
			html.setContent(text, "text/html;charset=UTF-8");
			content.addBodyPart(txt);
			//content.addBodyPart(html);
			msg.setContent( content );
			
			msg.saveChanges();
			if(transport ==null){
				transport = session.getTransport("smtp");
				transport.connect(host, userName, passWord);
			}			
			transport.sendMessage(msg, msg.getAllRecipients());
			//transport.sendMessage(msg, msg.getAllRecipients());
			//transport.close();
			return true;
		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}
	}
	
	public void closeConnection(){
		try {
			transport.close();
		} catch (MessagingException e) {			
			e.printStackTrace();
		}
	}

}