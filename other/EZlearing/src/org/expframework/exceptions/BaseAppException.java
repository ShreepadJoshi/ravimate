package org.expframework.exceptions;

/**
 * A base class for all checked exceptions in the application.
 * 
 * @author ShriKant
 * 
 * @version 1.0
 */
public class BaseAppException extends Exception {

    static final long serialVersionUID = -5829545098534135052L;

    /**
     * the message of the BaseAppException.
     */
    private String exceptionMessage;
    private boolean isShowException = false;

    /**
     * A public constructor for BaseAppException containing no arguments.
     *  
     */
    public BaseAppException() {
    }

    /**
     * A public constructor for BaseAppException specifying exception message.
     * <p>
     * 
     * @param msg
     *            exception message.
     *  
     */
    public BaseAppException(String msg) {
        this.exceptionMessage = msg;
    }
    
    
    public BaseAppException(String msg, boolean showException)
    {
    	this(msg);
    	this.isShowException = showException;
    }

    /**
     * A public constructor of <code>BaseAppException</code> containing
     * message and root cause (as <code>Throwable</code>) of the exception.
     * 
     * @param msg
     *            exception message.
     * @param e
     *            Throwable object.
     *  
     */
    public BaseAppException(String msg, Throwable e) {
        this.exceptionMessage = msg;
        this.initCause(e);
    }

    /**
     * sets the root cause of the exception. Used for setting Java built in
     * exception in <code>BaseAppException</code>.
     * 
     * @param e
     *            Throwable object.
     *  
     */
    public void setCause(Throwable e) {
        this.initCause(e);
    }

    /*
     * Gets the class name and exception message.
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String s = getClass().getName();
        return s + ": " + exceptionMessage;
    }

    /*
     * Gets the message of the exception. equivalent to Exception.getMessage().
     * 
     * @see java.lang.Throwable#getMessage()
     */
    public String getMessage() {
        return exceptionMessage;
    }
    
    
    /**
     * Method to check if we need to show user defined exception.
     * @return
     */
    public boolean isShowException()
    {
    	return isShowException;
    }
}