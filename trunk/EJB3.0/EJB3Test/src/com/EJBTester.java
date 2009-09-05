package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EJBTester
 */
public class EJBTester extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AdviseRemote remote;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EJBTester() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("In servlet");
		
		
//		Properties properties = new Properties();
//		properties.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
//		properties.put("java.naming.factory.url.pkgs","=org.jboss.naming:org.jnp.interfaces");
//		properties.put("java.naming.provider.url","localhost:1099");
	
	/*	Context context;
		try
		{
			 context = new InitialContext();
			 AdviseRemote beanRemote = (AdviseRemote) context.lookup(Advise.class.getSimpleName()+"/remote");
			 out.println("Chetan "+beanRemote.getAdvise());
		} catch (NamingException e)
		{
			e.printStackTrace();
		} */
		
		out.print("Google===>"+remote.getAdvise());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("In servlet");
	}

}
