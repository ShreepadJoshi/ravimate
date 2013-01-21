package org.expframework.data;

/**
 * Contains the information of subclasses of <code>BaseAppException</code> for
 * exception handling purposes.
 * <p>
 * <b>Overview: </b>
 * <p>
 * The information against each exception derived from
 * <code>BaseAppException</code> is collected from
 * <code>exceptioninfo.xml</code> and encapsulated in
 * <code>ExceptionInfoDTO</code> objects.
 * 
 * @author ShriKant
 * 
 * @version 1.0
 */
public class ExceptionInfoDTO extends BaseDTO {
    static final long serialVersionUID = 5853337547949414866L;

    /**
     * an error code which will be used in ResourceBundle for getting the
     * corresponding user message.
     *  
     */
    private String messageCode = null;

    /**
     * an indicator saying whether context key passed by Action class to be used
     * or not.
     *  
     */
    private boolean contextSensitive = false;

    /**
     * If the exception is of confirmationInd type or not.
     */
    private boolean confirmationInd = false;

    /**
     * The logging type to be used for exception logging.
     */
    private String loggingType = null;

    /**
     * A public constructor containing errorId and contextSensitive,
     * confirmationInd and loggingType as parameters.
     * 
     * @param messageCode
     * @param contextSensitive
     *            boolean parameter.
     * @param confirmationInd
     *            boolean parameter.
     * @param loggingType
     *            String object.
     *  
     */
    public ExceptionInfoDTO(String messageCode, boolean contextSensitive,
            boolean confirmation, String loggingType) {
        this.messageCode = messageCode;
        this.contextSensitive = contextSensitive;
        this.confirmationInd = confirmation;
        this.loggingType = loggingType;
    }

    /**
     * Returns the confirmationInd.
     * 
     * @return <code>true</code> if exception is of confirmationInd type;
     *         otherwise return <code>false</code>
     */
    public boolean isConfirmationInd() {
        return confirmationInd;
    }

    /**
     * Returns the contextSensitive.
     * 
     * @return <code>true</code> if message is context sensitive; otherwise
     *         returns <code>false</code>.
     */
    public boolean isContextSensitive() {
        return contextSensitive;
    }

    /**
     * Returns the logging type. The valid logging types are as follows.
     * 
     * <ul>
     * <li>error
     * <li>warning
     * <li>info
     * <li>nologging
     * </ul>
     * 
     * @return loggingType
     */
    public String getLoggingType() {
        return loggingType;
    }

    /**
     * Returns the message code which will be used in ResourceBundle for getting
     * the corresponding user message.
     * 
     * @return messageCode
     */
    public String getMessageCode() {
        return messageCode;
    }
}