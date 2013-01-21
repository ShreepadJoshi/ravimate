package com.education.exceptions;

import org.expframework.exceptions.DataBaseException;

public class DBInvalidDataInsertionException extends DataBaseException{

	String columnName = null;
	String columnValue = null;
	
	public DBInvalidDataInsertionException(String msg) {
		super(msg);
	}
	
	public DBInvalidDataInsertionException(String msg,String columnName) {
		this(msg);
		this.columnName = columnName;
	}
	
	public DBInvalidDataInsertionException(String msg,String columnName,String value) {
		this(msg);
		this.columnName = columnName;
		this.columnValue = value;
	}
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}
}
