package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MDBTester extends HttpServlet {

	@Resource(mappedName = "queue/MyMessageDrivenBean")
	Queue queue;
	@Resource(mappedName = "ConnectionFactory")
	ConnectionFactory connectionFactory;

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.println("In MDB servlet.. Sending Message...");
		sendMessage();
		out.println("<Br> Message is send..");
	}

	private void sendMessage() {
		
		// client creates the connection, session, and message sender:
		try {
			Session session = connectionFactory.createConnection()
					.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			MessageProducer myMsgProducer = session.createProducer(queue);
			// create and set a message to send
			TextMessage msg = session.createTextMessage("Hello World");
			myMsgProducer.send(msg);
		} catch (JMSException e) {			
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}