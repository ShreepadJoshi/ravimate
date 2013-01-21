package com.education.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.expframework.exceptionhandler.ExceptionUtil;
import org.expframework.exceptions.BaseAppException;

import com.education.formbeans.LoginActionForm;
import com.education.services.UserRegistrationService;
import com.education.util.Utilities;

public class ForgottenPasswordAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		

		try {
			out = response.getWriter();
			LoginActionForm loginBean = (LoginActionForm) form;
			String to = loginBean.getUsername();
			String message = getPassword(to);
			String mailTo[] = new String[1];
			mailTo[0] = loginBean.getUsername();
			ResourceBundle rBundle = ResourceBundle.getBundle("mail");
			message = rBundle.getString("message") + message;			
			boolean result = Utilities.sendMail(mailTo, "Forgotten Password", message);
			if(result != true) throw new IOException("error sending mail");			
		} catch (BaseAppException ex) {
			ExceptionUtil.logException(this.getClass(), ex, "");
			if(ex.isShowException()){
				out.print(ex.getMessage());
			}else{			
				out.print("SENDMAIL_EXCEPTION_MSG");
			}
		} catch (IOException ex) {
			ExceptionUtil.logException(this.getClass(), ex, "");
			out.print("SENDMAIL_EXCEPTION_MSG");
		}
		return null;
	}

	/**
	 * Method will create new password for given user. 
	 * @param userMailId
	 * @return
	 * @throws BaseAppException
	 */
	private String getPassword(String userMailId) throws BaseAppException {
		UserRegistrationService userRegistrationService = new UserRegistrationService();		
		return userRegistrationService.getPassword(userMailId);
	}
}
