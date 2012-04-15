/**
 * 
 */
package com.training.iface;

import java.util.List;

import javax.ejb.Remote;

/**
 * @author Deepak
 *
 */
@Remote
public interface IShoppingCart {
	
	public void addBook(String bookName);
	public void removeBook(String bookName);
	public  List<String> listBooksInCart();
	
}
