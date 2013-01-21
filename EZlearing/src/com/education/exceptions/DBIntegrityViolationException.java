package com.education.exceptions;

import org.expframework.exceptions.DataBaseException;

public class DBIntegrityViolationException extends DataBaseException{

	public DBIntegrityViolationException(String msg) {
		super(msg);
	}
}
