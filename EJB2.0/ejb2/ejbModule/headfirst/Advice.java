package headfirst;

import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.ejb.EJBObject;

public interface Advice extends EJBObject {
	public String getAdvice() throws RemoteException;
}
