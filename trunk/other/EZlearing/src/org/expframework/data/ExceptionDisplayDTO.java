package org.expframework.data;

/**
 * Contains the details of ActionForward name and context detail (for context
 * sensitive exceptions).
 * 
 * @author ShriKant
 * @version 1.0
 */
public class ExceptionDisplayDTO {
	String context;

	String actionForwardName;

	String columnName; //Add by Arun
	

	/**
	 * Creates a ExceptionDisplayDTO with name and context params
	 * 
	 * @param name
	 *            ActionForward name to forward for exception cases
	 * @param context
	 *            used for context-sensitive exceptions
	 * 
	 */
	public ExceptionDisplayDTO(String name, String context) {
		super();
		actionForwardName = name;
		this.context = context;
	}

	/**
	 * Returns the actionForwardName
	 * 
	 * @return String
	 */
	public String getActionForwardName() {
		return actionForwardName;
	}

	/**
	 * Sets the actionForwardName
	 * 
	 * @param actionForwardName
	 *            The actionForwardName to set.
	 */
	public void setActionForwardName(String actionForwardName) {
		this.actionForwardName = actionForwardName;
	}

	/**
	 * Returns the context
	 * 
	 * @return String
	 */
	public String getContext() {
		return context;
	}

	/**
	 * Sets the context
	 * 
	 * @param context
	 *            The context to set.
	 */
	public void setContext(String context) {
		this.context = context;
	}
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
}