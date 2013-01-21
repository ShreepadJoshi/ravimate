package mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: MyMessageDrivenBean
 * 
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/MyMessageDrivenBean") })
public class MyMessageDrivenBean implements MessageListener {

	/**
	 * Default constructor.
	 */
	public MyMessageDrivenBean() {

		/**
		 * Please add entry in
		 * jboss-5.1.0.GA\server\default\deploy\messagin\destinations
		 * -service.xml
		 * 
		 * Just copy any <mbean> tag and replace name=MyMessageDrivenBean.
		 * 
		 */
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		System.out.println("----------------");
		System.out.println("Received message : ");
		try {
			System.out.println(((TextMessage) message).getText());
		} catch (JMSException e) {			
			e.printStackTrace();
		}
		System.out.println("----------------");

	}

}
