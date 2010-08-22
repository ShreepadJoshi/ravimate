package org.expframework.exceptions;

/**
 * A base class for all business related exceptions.
 * 
 * <p>
 * <b>Overview: </b>
 * <p>
 * All business exceptions should inherit from <code>BusinessException</code>.
 * Exceptions can be defined based on business scenarios.
 * 
 * @author ShriKant
 * 
 * @version 1.0
 */
public class BusinessException extends BaseAppException {

    static final long serialVersionUID = 4157262600607325995L;

    /**
     * a public constructor for <code>BusinessException</code>.
     * 
     * @param msg
     *            exception message.
     *  
     */
    public BusinessException(String msg) {
        super(msg);
    }
}