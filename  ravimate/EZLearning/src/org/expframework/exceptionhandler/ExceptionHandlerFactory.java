package org.expframework.exceptionhandler;

import org.expframework.AppConfig;

/**
 * It's a Factory implementation for creating the concrete implementation of
 * <code>IExceptionHandler</code>.
 * 
 * @author ShriKant
 * 
 * @version 1.0
 */
public class ExceptionHandlerFactory {

	/**
	 * <code>FACTORY</code> is a singleton instance.
	 */
	private static final ExceptionHandlerFactory FACTORY = new ExceptionHandlerFactory();

	/**
	 * Creates a ExceptionHandlerFactory object
	 */
	private ExceptionHandlerFactory() {
	}

	/**
	 * Gets the only instance of this class
	 * 
	 * @return ExceptionHandlerFactory the single instance of
	 *         <code>ExceptionHandlerFactory</code>
	 */
	public static ExceptionHandlerFactory getInstance() {
		return FACTORY;
	}

	/**
	 * The name of the key for which the <code>IExceptionHandler</code> class
	 * name to be retrieved from <code>app.properties</code>.
	 */
	public static final String EXCEPTION_HANDLER_KEY = "app.exceptionhandler.type";

	/**
	 * The value of <code>EXCEPTION_HANDLER_KEY</code> to be used for getting
	 * the file based <code>IExceptionHandler</code> concrete instance.
	 */
	public static final String EXCP_HANDLER_FILE = "file";

	/**
	 * Creates the default <code>IExceptionHandler</code> concrete
	 * implimentation based on the
	 * <code>IExceptionHandlerFactory.EXCEPTION_HANDLER_KEY</code> parameter
	 * specified in <code>app.properties</code>.
	 * 
	 * @return IExceptionHandler instance based on parameter
	 *         specified in <code>app.properties</code>
	 */
	public IExceptionHandler create() {
		AppConfig cfg = AppConfig.getInstance();
		String exceptionHandlerType = cfg.get(EXCEPTION_HANDLER_KEY);
		return create(exceptionHandlerType);
	}

	/**
	 * Creates the IExceptionHandler concrete implimentation based on the
	 * parameter passed.
	 * 
	 * @param exceptionHandlerType
	 * 
	 * @return IExceptionHandler instance based on parameter
	 *         passed
	 * 
	 */
	public IExceptionHandler create(String exceptionHandlerType) {
		if (EXCP_HANDLER_FILE.equals(exceptionHandlerType))
			return new FileLoggingExceptionHandler();
		return null;
	}
}