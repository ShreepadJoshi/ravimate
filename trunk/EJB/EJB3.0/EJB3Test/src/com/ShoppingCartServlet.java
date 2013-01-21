package com;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ShoppingCartServlet extends HttpServlet {
	
	@EJB(beanInterface = IShoppingCart.class, mappedName = "ShoppingCartBean/remote")
    private IShoppingCart 	cart;


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String message = null;
		// Get the Selected Book
		String bookName = request.getParameter("book");
		//Action  on Cart
		String action = request.getParameter("action");

		if (action.equals("add")) {
			cart.addBook(bookName);
			message = bookName + " Book Added in the Cart";
		}
		if (action.equals("remove")) {
			cart.removeBook(bookName);
			message = bookName + " Book Removed from the Cart";
		}
		if (action.equals("show")) {
			List<String> books = cart.listBooksInCart();
			message = "Book/s in your Cart "+books.toString();
			// response.setContentType("text/html");
			// PrintWriter out = response.getWriter();
			// out.println("The Books In your Cart are " + books.toString());
		}
				
		if(message!=null){
			request.setAttribute("message",message);
		}
		request.getRequestDispatcher("/shoppingCart.jsp").forward(request,response);
		
	}

}
