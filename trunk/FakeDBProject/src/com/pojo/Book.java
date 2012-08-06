package com.pojo;

import java.io.Serializable;

public class Book implements Serializable {
	
	private int Id;
	private String bookName;

	@Override
	public String toString() {
		return "Book [Id=" + Id + ", bookName=" + bookName + "]";
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

}
