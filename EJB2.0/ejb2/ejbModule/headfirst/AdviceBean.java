package headfirst;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdviceBean implements SessionBean {

	public void ejbActivate() throws EJBException, RemoteException {
		System.out.println("SHREE:--" + "ejbActivate");

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		System.out.println("SHREE:--" + "ejbPassivate");

	}

	public void ejbRemove() throws EJBException, RemoteException {
		System.out.println("SHREE:--" + "ejbRemove");

	}

	public void setSessionContext(SessionContext arg0) throws EJBException,
			RemoteException {
		System.out.println("SHREE:--" + "setSessionContext");

	}

	public String getAdvice()  {
		Connection con = null;
		try {
			con = getConnection();
			method1(con);
			method2(con);
			method3(con);
			con.close();
		}catch(Exception e){
			throw new EJBException("Ravi Error");
		}		
		finally{
			try {
				System.out.println("befor close");
				con.close();				
				System.out.println("after close");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return "I am in ejb 2.0";
	}

	private void method1(Connection con) throws SQLException {
		con = getConnection();
		PreparedStatement st = con.prepareStatement("INSERT INTO BOOK values (1,'HEAD FIRST EJB1')");
		boolean rs = st.execute();		
		con.close();
	}

	private void method2(Connection con) throws SQLException {
		con = getConnection();
		PreparedStatement st = con.prepareStatement("INSERT INTO BOOK values (2,'HEAD FIRST EJB2')");
		boolean rs = st.execute();	
		con.close();
	}

	private void method3(Connection con) throws SQLException {
		con = getConnection();
		PreparedStatement st = con.prepareStatement("INSERT INTO BOOK values (2,'HEAD FIRST EJB3')");
		boolean rs = st.execute();
		con.close();		
	}

	private Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:PortalDS");
			return ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void ejbCreate() {
		System.out.println("In ejb create");
	}

}
