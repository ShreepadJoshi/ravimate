/**
 * 
 */
package com;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateful;



/**
 * @author Deepak
 *
 */
@Stateful
@Remote(IShoppingCart.class)

public class ShoppingCartBean {
	List<String> bookList = new ArrayList<String>();
	
	@PostConstruct
	public void craeteCart(){
		System.out.println("In Side Post Construct");
	}
	
	public void addBook(String bookName) {
		bookList.add(bookName);

	}

	
	public List<String> listBooksInCart() {
		
		return bookList;
	}

	
	public void removeBook(String bookName) {
		if(bookList.contains(bookName)){
			bookList.remove(bookName);
		}

	}

}
