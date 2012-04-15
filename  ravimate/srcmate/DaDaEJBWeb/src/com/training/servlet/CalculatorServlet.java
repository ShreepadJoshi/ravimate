package com.training.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.iface.ICalculator;

public class CalculatorServlet extends HttpServlet{
	
	@EJB
	private ICalculator calc;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Do your preprocessing task here.
		
		
		double result =0;	
		
		String first = request.getParameter("first");
		System.out.println("First Operand: "+first);
		String second = request.getParameter("second");
		System.out.println("Second Operand: "+ second);
		String operation = request.getParameter("operation");
		System.out.println("Operation : "+ operation);
		
		if(operation.equals("add")){
			result = calc.add(Double.parseDouble(first),Double.parseDouble(second));
			
		}else{
			result = calc.sub(Double.parseDouble(first),Double.parseDouble(second));
		}
		
		
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println("The Result is "+result);
	 
	}

}
