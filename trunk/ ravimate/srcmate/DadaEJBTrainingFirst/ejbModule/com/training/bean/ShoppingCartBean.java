/**
 * 
 */
package com.training.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

import com.training.iface.IShoppingCart;

/**
 * @author Deepak
 *
 */
@Stateful (mappedName="cart")
public class ShoppingCartBean implements IShoppingCart {
	List<String> bookList = new ArrayList<String>();
	
	@PostConstruct
	public void craeteCart(){
		System.out.println("In Side Post Construct");
	}
	/* (non-Javadoc)
	 * @see com.training.iface.IShoppingCart#addBook(java.lang.String)
	 */
	@Override
	public void addBook(String bookName) {
		bookList.add(bookName);

	}

	/* (non-Javadoc)
	 * @see com.training.iface.IShoppingCart#listBooksInCart()
	 */
	@Override
	public List<String> listBooksInCart() {
		
		return bookList;
	}

	/* (non-Javadoc)
	 * @see com.training.iface.IShoppingCart#removeBook(java.lang.String)
	 */
	@Override
	public void removeBook(String bookName) {
		if(bookList.contains(bookName)){
			bookList.remove(bookName);
		}

	}

}
