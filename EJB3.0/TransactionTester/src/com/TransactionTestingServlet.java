package com;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TransactionTestingServlet
 */
public class TransactionTestingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB(beanInterface = PaymentRemote.class, mappedName = "PaymentBean/remote")
	private PaymentRemote paymentRemote;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		paymentRemote.transferMoneyWithECS();
	}

}
