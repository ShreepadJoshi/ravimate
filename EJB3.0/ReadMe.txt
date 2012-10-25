This is was start at 05-Sep-2009 At shripad Home,
after beating Ravi,

You need to have Eclipse Version: 3.4.2 (GANYMEDE)
there are two project 

after checkout rename 
1 EJB3SessionBean 
2 EJB3Test
TO
1 EJB3SessionBean1 
2 EJB3Test1


then 

for EJB3SessionBean we need to create "EJB project" (with name EJB3SessionBean)
set jboss 5 
and select EJB version 3.0
click OK.


for EJB3Test we need to create web - "Dynamic Web project" (EJB3Test)
set jboss 5 
click OK.


after this copy java, and xml file at there location from EJB3SessionBean1 and EJB3Test1.
copying .SVN folder will set the SVN also



 
"If you find better of doing this please update this file "
 



--Shripad


add this in new java project.
.
import java.util.Properties;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;


public class TestMDB {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
	      Properties props = new Properties();
	      props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
	      props.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
	      props.setProperty(Context.PROVIDER_URL, "jnp://localhost:1099");
	      props.setProperty("j2ee.clientName", "MessageClient");
	      
	      InitialContext initialContext = new InitialContext(props);
	      QueueConnectionFactory queueConnectionFactory = 
	          (QueueConnectionFactory) initialContext.lookup("ConnectionFactory");

	      
	      System.out.println("QueueConnectionFactory " + queueConnectionFactory);
	      Queue queue = (Queue) initialContext.lookup("queue/MyMessageDrivenBean");
	      
	      System.out.println("Queue " + queue);

	      QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
	      QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	      QueueSender queueSender = queueSession.createSender(queue);

	      
	      TextMessage textMessage = queueSession.createTextMessage();
	      textMessage.setText("Message from Main Method...");

	      queueSender.send(textMessage);

	}

}
