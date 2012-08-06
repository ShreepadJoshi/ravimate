package com.dao.fake;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pojo.Book;

public class FakeDB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static FakeDB fakeDB = new FakeDB();

	private List<Book> booksTable = new ArrayList<Book>();

	
	public static FakeDB getObject() {
		return fakeDB;
	}

	public List<Book> getBooksTable() {
		return booksTable;
	}

	public void setBooksTable(List<Book> booksTable) {
		this.booksTable = booksTable;
	}

	private FakeDB() {

	}

}
