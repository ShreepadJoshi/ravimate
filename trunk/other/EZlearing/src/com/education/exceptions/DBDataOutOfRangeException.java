package com.education.exceptions;

import org.expframework.exceptions.DataBaseException;

public class DBDataOutOfRangeException extends DataBaseException{

	String columnName = null;
	
	public DBDataOutOfRangeException(String msg) {
		super(msg);
	}
	
	public DBDataOutOfRangeException(String msg,String columnName) {
		this(msg);
		this.columnName = columnName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
}

