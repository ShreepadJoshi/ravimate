package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EJBTester extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdviseRemote remote;
	Context context;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("In servlet"+"<br>");
		// out.print((new
		// StringBuilder("Google===>")).append(remote.getAdvise()).toString());
		
		  Properties properties = new Properties();
		  properties.put("java.naming.factory.initial" ,"org.jnp.interfaces.NamingContextFactory");
		  properties.put("java.naming.factory.url.pkgs","=org.jboss.naming:org.jnp.interfaces");
		  properties.put("java.naming.provider.url","localhost:1099");
		 

		try {
			
			context = new InitialContext(properties);
			String jndiName = Advise.class.getSimpleName() + "/remote";			
			
			AdviseRemote adviseBeanRemote = (AdviseRemote) context
					.lookup(jndiName);
			out.print("Advice by EJB 3.0" + adviseBeanRemote.getAdvise());

		} catch (NamingException e) {			
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("In servlet");
	}

}