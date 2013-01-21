package org.expframework.exceptionhandler;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.expframework.data.ExceptionDTO;
import org.expframework.data.ExceptionInfoDTO;
import org.expframework.exceptions.BaseAppException;

/**
 * A helper class which is used to for various utility methods in exception
 * handling. All these utility methods are related to exceptions.
 * 
 * @author ShriKant
 * 
 * @version 1.0
 */
public class ExceptionUtil {

    /**
     * lgging type for error messages.
     */
    public static final String LOGTYPE_ERROR = "error";

    /**
     * logging type for warning messages.
     */
    public static final String LOGTYPE_WARN = "warning";

    /**
     * logging type for info messages.
     */
    public static final String LOGTYPE_INFO = "info";

    /**
     * logging type for no logging.
     */
    public static final String LOGTYPE_NOLOG = "nologging";

    /**
     * The length of exception trace beyond which it will be truncated
     */
    public static final int EXCEPTION_TRACE_LENGTH = 4000;

    static final String DOT = ".";

    private static final int INIT_BUFFER_SIZE = 1024;

    /**
     * Gets the stack trace of a <code>BaseAppException</code> in String form.
     * 
     * @param exception
     *            BaseAppException object
     * @return <code>String</code> Returns the detailed message.
     *  
     */
    public static String getDetailedMessage(BaseAppException exception) {
        StringBuffer msg = new StringBuffer(INIT_BUFFER_SIZE);

        if (exception.getMessage() != null) {
            msg.append("Message : ");
            msg.append(exception.getMessage());
            msg.append("\n");
        }

        msg.append("Exception Stack Trace\n");
        try {
            StringWriter sw = new StringWriter(INIT_BUFFER_SIZE);
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            msg.append(sw.toString());
            sw.close();
        } catch (Exception e) {
            msg.append(exception.toString());
        }
        Throwable rootCause = exception.getCause();
        if (rootCause != null) {
            msg.append("\n Root Exception Stack Trace : ");
            msg.append(rootCause.toString());
            msg.append("\n");
            try {
                StringWriter sw = new StringWriter(INIT_BUFFER_SIZE);
                PrintWriter pw = new PrintWriter(sw);
                rootCause.printStackTrace(pw);
                msg.append(sw.toString());
                sw.close();
            } catch (Exception e) {
                msg.append(rootCause.toString());
            }
        }
        return msg.toString();
    }

    /**
     * Gets the stack trace of a <code>Throwable</code> in String form.
     * 
     * @param a
     *            Throwable object.
     * @return <code>String</code> Returns the message as String.
     *  
     */
    public static String getDetailedMessage(Throwable a) {
        StringBuffer msg = new StringBuffer();

        msg.append("Message : ");
        msg.append(a.getMessage());
        msg.append("\n");
        msg.append("Exception Stack Trace\n");
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            a.printStackTrace(pw);
            msg.append(sw.toString());
            sw.close();
        } catch (Exception e) {
            msg.append(a.toString());
        }
        String ret = msg.toString();
        msg = null;
        return ret;
    }

    /**
     * Gets the name of class based on passed <code>Throwable</code> instance.
     * 
     * @param e
     *            Throwable object for which class name needs to be find out.
     * @return the name of the class.
     *  
     */
    public static String getClassName(Throwable e) {
        String className = e.getClass().getName();
        String errId = className;

        if (e instanceof BaseAppException) {
            int i = className.lastIndexOf('.');
            errId = className.substring(i + 1);
        }
        return errId;
    }

    /**
     * Gets the detailed message of a <code>BaseAppException</code> including
     * stack trace, userId and errorId information in String form. This detailed
     * message is used as a stack trace for log file as well as database.
     * 
     * @param exp
     *            BaseAppException object
     * @param userId
     *            the user for which exception occured.
     * @return the detailed message.
     *  
     */
    public static String getExceptionLog(Throwable exp, String userId) {
        String errorId = getClassName(exp);
        String detailedMessage = null;
        if (exp instanceof BaseAppException) {
            BaseAppException ie = (BaseAppException) exp;
            detailedMessage = getDetailedMessage(ie);
        } else {
            detailedMessage = getDetailedMessage(exp);
        }

        StringBuffer lBuffer = new StringBuffer(INIT_BUFFER_SIZE);
        String msg = null;
        lBuffer.append("ERRORID :");
        lBuffer.append(errorId);
        lBuffer.append("\n");

        lBuffer.append("USERID : ");
        lBuffer.append(userId);
        lBuffer.append("\n");

        lBuffer.append("EXCEPTION MESSAGE :");
        lBuffer.append(detailedMessage);
        lBuffer.append("\n");
        lBuffer
                .append("--------------------------------------------------------------------------------------\n");

        msg = lBuffer.toString();
        return msg;
    }

    public static ExceptionDTO getExceptionDetails(String context, Throwable exp) {
        ExceptionDTO exDTO = new ExceptionDTO();
        ExceptionInfoCache ecache = ExceptionInfoCache.getInstance();
        ExceptionInfoDTO exInfo = ecache.getExceptionInfo(ExceptionUtil
                .getClassName(exp));
        if (exInfo != null) {
            exDTO.setLoggingType(exInfo.getLoggingType());
            exDTO.setConfirmation(exInfo.isConfirmationInd());
            String messageCode = exInfo.getMessageCode();
            if (exInfo.isContextSensitive())
                messageCode = messageCode + DOT + context;
            exDTO.setMessageCode(messageCode);
        }
        return exDTO;
    }

    /**
     * Logs the exception using Log4j logger.
     * 
     * @param th
     * @param userId
     * @param loggingType
     *  
     */
    public static void logException(Class<?> clazz, Throwable th, String userId,
            String loggingType) {
    	
    	Log _log = LogFactory.getLog(clazz);
    	
    	String exceptionTrace = ExceptionUtil.getExceptionLog(th, userId);
        if (_log.isDebugEnabled())
    		_log.debug(exceptionTrace);
        else if(_log.isErrorEnabled())
        	_log.error(exceptionTrace);
        else if (_log.isInfoEnabled())
        	_log.info(exceptionTrace);
        
        /*if (ExceptionUtil.LOGTYPE_NOLOG.equals(loggingType))
            return;
        else if (ExceptionUtil.LOGTYPE_ERROR.equals(loggingType))
            logger.error(exceptionTrace);
        else if (ExceptionUtil.LOGTYPE_INFO.equals(loggingType))
            logger.info(exceptionTrace);
        else if (ExceptionUtil.LOGTYPE_WARN.equals(loggingType))
            logger.warn(exceptionTrace);
        else {
            logger.error(exceptionTrace);
        }*/
    }

    /**
     * Logs the exception using Log4j Logger. Implicitely calls
     * logException(Throwable, String userId, String loggingType). The logging
     * type to be passed to this method is ExceptionUtil.LOGTYPE_ERROR.
     *  
     */
    public static void logException(Class<?> clazz, Throwable th, String userId) {
        logException(clazz, th, userId, LOGTYPE_ERROR);
    }
}