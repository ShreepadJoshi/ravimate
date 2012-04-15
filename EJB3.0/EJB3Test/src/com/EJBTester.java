package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EJBTester extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB(beanInterface = AdviseRemote.class, mappedName = "Advise/remote")
	private AdviseRemote remote;
	
	

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println("In servlet, Calling EJB <Br>");
		String message4mEJB = remote.getAdvise().toString();
		out.print("Message 4m EJB is ===>" + message4mEJB);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}