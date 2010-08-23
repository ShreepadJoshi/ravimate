package com.education.exceptions;

import org.expframework.exceptions.DataBaseException;

public class BussMasterDataMissingException extends DataBaseException{

	public BussMasterDataMissingException(String msg) {
		super(msg);
	}
}
