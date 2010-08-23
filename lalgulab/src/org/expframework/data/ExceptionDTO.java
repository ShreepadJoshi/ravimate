package org.expframework.data;

/**
 * Contains the exception data to be used by <code>IExceptionHandler</code>
 * client classes.
 * 
 * @author ShriKant
 * 
 * @version 1.0
 * @see org.expframework.exceptionhandler.IExceptionHandler#handleException(String,
 *      String, Throwable)
 */
public class ExceptionDTO extends BaseDTO {
    static final long serialVersionUID = 8010208372246034996L;

    /**
     * Message code for displaying the user specific message for an exception
     */
    String messageCode;

    /**
     * An indicator to show if the exception is of confirmation type or not
     */
    boolean confirmation = false;

    String loggingType;

    /**
     * Creates a ExceptionDTO instance
     */
    public ExceptionDTO() {
    }

    /**
     * Creates the <code>ExceptionDTO</code> instance with
     * <code>messageCode</code> and <code>confirmationInd</code> params
     * 
     * @param messageCode
     *            Message code to be used by Action class for displaying the
     *            user friendly message for an exception
     * @param confirmationInd
     *            An indicator to show if the exception is of confirmation type
     *            or not
     * @param loggingType
     *            logging type to be used for logging the exception
     */
    public ExceptionDTO(String messageCode, boolean confirmationInd,
            String loggingType) {
        this.messageCode = messageCode;
        this.confirmation = confirmationInd;
        this.loggingType = loggingType;
    }

    /**
     * Returns the confirmation
     * 
     * @return <code>true</code> if exception is of confirmation type.
     *         Otherwise returns <code>false</code>
     */
    public boolean isConfirmation() {
        return confirmation;
    }

    /**
     * Sets the confirmation
     * 
     * @param confirmation
     *            The confirmationInd to set.
     */
    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    /**
     * Returns the messageCode
     * 
     * @return the message code
     */
    public String getMessageCode() {
        return messageCode;
    }

    /**
     * Sets the messageCode
     * 
     * @param messageCode
     *            The messageCode to set.
     */
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    /**
     * Returns the loggingType
     * 
     * @return String
     */
    public String getLoggingType() {
        return loggingType;
    }

    /**
     * Sets the loggingType
     * 
     * @param loggingType
     *            The loggingType to set.
     */
    public void setLoggingType(String loggingType) {
        this.loggingType = loggingType;
    }
}