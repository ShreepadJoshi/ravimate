package org.expframework.samples;

import org.expframework.exceptions.BusinessException;

public class NoAreasFoundException extends BusinessException {
	static final long serialVersionUID = 7069474763847517950L;

	public NoAreasFoundException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
}
