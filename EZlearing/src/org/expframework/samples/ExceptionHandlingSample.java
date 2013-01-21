package org.expframework.samples;

import org.expframework.data.ExceptionDTO;
import org.expframework.exceptionhandler.ExceptionHandlerFactory;
import org.expframework.exceptionhandler.IExceptionHandler;
import org.expframework.exceptions.BaseAppException;

public class ExceptionHandlingSample {
	public static void main(String[] args) {
		try {
			throw new NoAreasFoundException("No Areas could be found");
		} catch (BaseAppException ex) {
			IExceptionHandler eh = ExceptionHandlerFactory.getInstance()
					.create();
			ExceptionDTO exDTO = eh.handleException("sample", "System", ex);
			System.out.println("The dto is:" + exDTO);
		}
	}
}