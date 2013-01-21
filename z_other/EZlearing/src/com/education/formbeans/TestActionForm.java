package com.education.formbeans;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

public class TestActionForm extends ActionForm{

	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {	
		ActionErrors errors = new ActionErrors();
		
		if( username.equals("")){
			errors.add("", new ActionMessage("invalid.username"));
		}
		if( password.equals("")){
			errors.add("", new ActionMessage("invalid.password"));
		}
		return errors;
	}	
}
