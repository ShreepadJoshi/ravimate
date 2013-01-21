package org.expframework.exceptionhandler;

import org.expframework.data.ExceptionDTO;

/**
 * An abstraction for handling <code>BaseAppException</code>.
 * <p>
 * <b>Overview: </b>
 * <p>
 * It's used by Controller to log the exception and getting an instance of
 * <code>ExceptionDTO</code>.
 * 
 * @author ShriKant
 * 
 * @version 1.0
 */
public interface IExceptionHandler {
    /**
     * Logs the exception as well as creates an instance of
     * <code>ExceptionDTO</code> which contains the attributes that are used
     * to show a message to the end user.
     * 
     * @param context
     *            String object.
     * @param userId
     *            User id as String.
     * @param th
     *            Throwable object.
     * @return <code>ExceptionDTO</code> which contains the details for
     *         displaying a user-friendly message to end user.
     *  
     */
    ExceptionDTO handleException(String context, String userId, Throwable th);
}