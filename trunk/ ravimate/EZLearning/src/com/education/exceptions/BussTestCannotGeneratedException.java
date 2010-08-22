package com.education.exceptions;

import org.expframework.exceptions.DataBaseException;

public class BussTestCannotGeneratedException extends DataBaseException{

	public BussTestCannotGeneratedException(String msg) {
		super(msg);
	}
}
