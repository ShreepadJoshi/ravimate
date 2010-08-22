package com.education.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.expframework.exceptionhandler.ExceptionUtil;
import org.expframework.exceptions.BaseAppException;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.formbeans.LoginActionForm;
import com.education.services.LoginService;
import com.education.services.SessionService;
import com.education.services.UserRegistrationService;
import com.education.transferobj.RegistrationTo;
import com.education.transferobj.UserTO;
import com.education.util.EducationConstant;
import com.education.util.Utilities;




public class SessionFaliureAction extends Action{

	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			{
		ActionForward fwd = null;	
		//out.print("INVALID");
		ActionMessages actionErrors;
		actionErrors = getErrors(request);
			
		try{
		
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			UserTO userTo = null;
			UserSessionInfo objUserInfo = new UserSessionInfo();
			LoginActionForm loginBean = (LoginActionForm)form;
					
			
			
			//Handle Action only through Post Method 
			if(request.getMethod().equalsIgnoreCase("post")){			
				
				userTo =new UserRegistrationService().getloginDetails(
							loginBean.getUsername(),loginBean.getPassword());
				//On successful login set details to session
				if(userTo != null){
					UserSessionInfo objUserSessionInfo =   Utilities.populateUserDetails_ToSession(userTo,loginBean.getLoginType());
					request.getSession().setAttribute(SessionConstants.user_info, objUserSessionInfo);			
					//Add sessionID to DB for session tracking:
					userTo.setSessionID( request.getSession(false).getId());
					new SessionService().insertUserSessionID(userTo);
					fwd =  mapping.findForward("loginSuccess");
				}else{					
					actionErrors.add("",new ActionMessage("sessionFailurePage.InvalidUserNameORPassword"));
					saveErrors(request, actionErrors);
					fwd = mapping.findForward("displayPage");
				}
			}else{
				fwd = mapping.findForward("displayPage");
			}
			//SET Values in application scope for futher use
			getServlet().getServletContext().setAttribute("DOMAIN_NAME",request.getContextPath()+"/");
			
		}catch(RuntimeException ex){
			ExceptionUtil.logException(this.getClass(), ex,"");
			fwd = mapping.findForward("displayPage");
			actionErrors.add("",new ActionMessage("login_exception_message"));
			saveErrors(request, actionErrors);
		}catch(BaseAppException ex){
			ExceptionUtil.logException(this.getClass(), ex,"");
			fwd = mapping.findForward("displayPage");
			actionErrors.add("",new ActionMessage("login_exception_message"));
			saveErrors(request, actionErrors);
		}catch(IOException ex){
			ExceptionUtil.logException(this.getClass(), ex,"");
			fwd = mapping.findForward("displayPage");
			actionErrors.add("",new ActionMessage("login_exception_message"));
			saveErrors(request, actionErrors);
		}
		return fwd;	
	}
}
