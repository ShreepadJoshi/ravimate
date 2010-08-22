package org.expframework.exceptionhandler;

import org.expframework.data.ExceptionDTO;

/**
 * This class is used for logging the exceptions in a file using a logger as
 * well as getting the error code against a checked application exception.
 * 
 * @author ShriKant
 * 
 * @version 1.0
 */
public class FileLoggingExceptionHandler implements IExceptionHandler {

	/**
	 * No argument constructor
	 */
	public FileLoggingExceptionHandler() {
	}

	/**
	 * @see org.expframework.exceptionhandler.IExceptionHandler#handleException(java.lang.String,java.lang.String,
	 *      java.lang.Throwable)
	 */
	public ExceptionDTO handleException(String context, String userId,
			Throwable exp) {
		ExceptionDTO exDTO = ExceptionUtil.getExceptionDetails(context, exp);
		ExceptionUtil.logException(this.getClass(), exp, userId, exDTO
				.getLoggingType());
		return exDTO;
	}

}