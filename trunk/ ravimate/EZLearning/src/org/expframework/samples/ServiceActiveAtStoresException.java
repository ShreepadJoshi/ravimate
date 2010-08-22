package org.expframework.samples;

import org.expframework.exceptions.BusinessException;

public class ServiceActiveAtStoresException extends BusinessException {
	static final long serialVersionUID = 8977794489416639569L;

	public ServiceActiveAtStoresException(String msg) {
		super(msg);
	}
}
