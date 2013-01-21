package org.expframework.exceptions;

/**
 * This exception is thrown when some exception occurs while parsing XML file or
 * stream.
 * 
 * @author ShriKant
 * 
 * @version 1.0
 */
public class XmlParseException extends RuntimeException {

    static final long serialVersionUID = 5850243503337783048L;

    /**
     * Creates the <code>XmlParseException</code> instance using exception
     * message and root cause of XML parse exception.
     * 
     * @param msg
     *            message as String object.
     * @param t
     *            Throwable object.
     * 
     *  
     */
    public XmlParseException(String msg, Throwable t) {
        super(msg, t);
    }
}