package com.education.exceptions;

import org.expframework.exceptions.DataBaseException;


public class DBInvalidDBDriverException extends DataBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 785418450101061697L;

	public DBInvalidDBDriverException(String msg) {
		super(msg);
	}
}
