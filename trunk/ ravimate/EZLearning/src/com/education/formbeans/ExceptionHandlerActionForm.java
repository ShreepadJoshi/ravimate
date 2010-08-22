package com.education.formbeans;

import org.apache.struts.action.ActionForm;

public class ExceptionHandlerActionForm extends ActionForm {

	private String errorId = "";
	private String userNotes = "";
	private String stackTrace = "";
	
	
	public String getUserNotes() {
		return userNotes;
	}
	public void setUserNotes(String userNotes) {
		this.userNotes = userNotes;
	}
	public String getStackTrace() {
		return stackTrace;
	}
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	public String getErrorId() {
		return errorId;
	}
	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}
}
