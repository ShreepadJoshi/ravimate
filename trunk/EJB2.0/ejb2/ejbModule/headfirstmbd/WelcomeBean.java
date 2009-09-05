package headfirstmbd;

import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class WelcomeBean implements MessageDrivenBean, MessageListener {

	private static final long serialVersionUID = 1L;

	private MessageDrivenContext context;
	private QueueConnection conn;
    private QueueSession session;

	public void ejbCreate() {
		System.out.println("MDB created");
		
		try {
		InitialContext iniCtx = new InitialContext();
        Object tmp = iniCtx.lookup("java:comp/env/jms/QCF");
        QueueConnectionFactory qcf = (QueueConnectionFactory) tmp;
        conn = qcf.createQueueConnection();
        session = conn.createQueueSession(false,
                                          QueueSession.AUTO_ACKNOWLEDGE);
        conn.start();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

	public void ejbRemove() throws EJBException {
		System.out.println("MDB removed");
	}

	public void setMessageDrivenContext(MessageDrivenContext arg0)
			throws EJBException {
		context = arg0;
		System.out.println(context.getClass().getName());
	}

	public void onMessage(Message arg0) {
		
			try {
				if (arg0 instanceof TextMessage) {
					TextMessage txtMsg = (TextMessage) arg0;
				System.out.println(txtMsg.getText());
				}
				
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
