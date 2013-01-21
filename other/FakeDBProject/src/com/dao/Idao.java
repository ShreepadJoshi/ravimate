package com.dao;

import java.util.List;

import com.pojo.Book;

public interface Idao {

	public List<Book> getBooks();

	public void addBook(Book book);

}
