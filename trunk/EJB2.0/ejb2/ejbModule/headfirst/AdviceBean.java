package headfirst;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;


public class AdviceBean implements SessionBean {

	public void ejbActivate() throws EJBException, RemoteException {
		System.out.println("SHREE:--"+"ejbActivate");

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		System.out.println("SHREE:--"+"ejbPassivate");

	}

	public void ejbRemove() throws EJBException, RemoteException {
		System.out.println("SHREE:--"+"ejbRemove");

	}

	public void setSessionContext(SessionContext arg0) throws EJBException,
			RemoteException {
		System.out.println("SHREE:--"+"setSessionContext");

	}
	public String getAdvice(){
		return "I am in ejb 2.0";
	}
	
	public void ejbCreate(){
		System.out.println("In ejb create");
	}

}
