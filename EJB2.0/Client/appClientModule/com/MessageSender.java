package com;

import java.util.Properties;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MessageSender {
	QueueConnection conn;
	QueueSession session;
	Queue queA;

	public static void main(String[] args) {
		new MessageSender().go();
	}

	private void go() {
		InitialContext iniCtx;
		try {
			Properties pro = new Properties();
			pro.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
			pro.put("java.naming.provider.url","localhost:1099");
			pro.put("java.naming.factory.url.pkgs","org.jboss.naming");		
			
			
			iniCtx = new InitialContext(pro);

			Object tmp = iniCtx.lookup("ConnectionFactory");
			QueueConnectionFactory qcf = (QueueConnectionFactory) tmp;
			conn = qcf.createQueueConnection();
			queA = (Queue) iniCtx.lookup("queue/A");
			session = conn.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);
			conn.start();
			
			QueueSender send = session.createSender(queA);
			System.out.println("Sending message....");
			for(int i=0;i<10;i++){
				TextMessage tm = session.createTextMessage("Ravi mar khaaye gaaa.......!!!"+i);			
				send.send(tm);
			}
			conn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
