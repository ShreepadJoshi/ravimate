package com.education.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.expframework.data.ExceptionDTO;
import org.expframework.exceptionhandler.ExceptionHandlerFactory;
import org.expframework.exceptionhandler.ExceptionUtil;
import org.expframework.exceptionhandler.IExceptionHandler;
import org.expframework.exceptions.BaseAppException;


import test.DBTest;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.formbeans.LoginActionForm;
import com.education.services.LoginService;
import com.education.services.SessionService;
import com.education.services.UserRegistrationService;
import com.education.transferobj.QuestionBankTO;
import com.education.transferobj.RegistrationTo;
import com.education.transferobj.UserTO;
import com.education.util.Utilities;




public class LoginAction extends Action {

	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			 {
		
		PrintWriter out = null;
		UserTO userTo = null;
		UserSessionInfo objUserInfo = new UserSessionInfo();
		LoginActionForm loginBean = (LoginActionForm)form;
		UserRegistrationService userRegistrationService = new UserRegistrationService();
		
		
		try{
			
			String[]names = request.getParameterValues("name");
			boolean login_Result = false;
			boolean signup_Result = false;
			
			String[]list = request.getParameterValues("list");
			String[]subjects = request.getParameterValues("subject");
			response.setContentType("text/html");			
			out = response.getWriter();
			
			
			
			//Condt for LoggedIN user Trying to login as different role
			//In this case remove his previous session details
			HttpSession session = request.getSession(false);
			if(session.getAttribute(SessionConstants.user_info) != null){
				request.getSession().invalidate();
			}
			
			if(loginBean.getLoginType().equalsIgnoreCase("login")){
				userTo =userRegistrationService.getloginDetails(loginBean.getUsername(),loginBean.getPassword());
				
				login_Result = (userTo != null ? true : false);
				
			}else if(loginBean.getLoginType().equalsIgnoreCase("signup")){	
				
				//check if username with same emailId exists in DB
				boolean exist_username = userRegistrationService.existUsername(loginBean.getUsername());
				
				if(!exist_username){
					if(loginBean.getPassword1().equals(loginBean.getPassword2())){
						RegistrationTo rto = new RegistrationTo();
						rto.setEmailID(loginBean.getUsername());
						rto.setPassword(loginBean.getPassword1());
						rto.setRoleId(1); //By default New user is SignIn as Guest
						rto.setIsApproved(1);
						rto.setIsFullregistration(0);
						userTo = userRegistrationService.registerUser(rto);
						signup_Result = ( userTo != null ? true : false);
						objUserInfo.setUserTo(userTo);
						session.setAttribute(SessionConstants.user_info, objUserInfo);
					}
				}else { 
					signup_Result = false;
					
				}
			}
			
			//On successful login/newUserSignup set details to session
			if(userTo != null){
				UserSessionInfo objUserSessionInfo =   Utilities.populateUserDetails_ToSession(userTo,loginBean.getLoginType());
				request.getSession(true).setAttribute(SessionConstants.user_info, objUserSessionInfo);			
				//Add sessionID to DB for session tracking:
				userTo.setSessionID( request.getSession(false).getId());
				new SessionService().insertUserSessionID(userTo);
			}
			
			// set the return value for Invalid login/Signup
			if( loginBean.getLoginType().equalsIgnoreCase("login") ){
				if(!login_Result)
					out.print("INVALID_LOGIN");			
			}else if( loginBean.getLoginType().equalsIgnoreCase("signup")){
				if(!signup_Result)
					out.print("SAME_EMAILID_EXIST");
			}
			
			//SET Values in application scope for further use
			getServlet().getServletContext().setAttribute("DOMAIN_NAME",request.getContextPath()+"/");		
		}catch(RuntimeException ex){
			ExceptionUtil.logException(this.getClass(), ex,"");
			out.print("LOGIN_EXCEPTION");	
		}catch(BaseAppException ex){
			ExceptionUtil.logException(this.getClass(), ex,"");
			if(ex.isShowException())
				out.print(ex.getMessage());
			else
				out.print("LOGIN_EXCEPTION");	
		}catch(IOException ex){
			ExceptionUtil.logException(this.getClass(), ex,"");
			out.print("LOGIN_EXCEPTION");	
		}
		return null;
		
	}

	private void dispMessage(HttpServletRequest request,String messageType, ActionMessage message){
		addError(request,messageType, message );
	}
	/*
	 * This method will add an error without destroying the previous contents
	 */
	protected void addError(HttpServletRequest request, String messageType, ActionMessage actionMessage) {
		ActionMessages actionErrors;
		actionErrors = getErrors(request);
		actionErrors.add(messageType, actionMessage);
		saveErrors(request, actionErrors);
	}
}
