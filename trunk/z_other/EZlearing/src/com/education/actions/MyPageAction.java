package com.education.actions;

import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.services.QuestionBankService;
import com.education.util.EducationConstant;

public class MyPageAction extends EducationBaseAction {

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return performAction(mapping, form, request, response);
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String strActionFwd = ""; 
		
		//Get userInfo from session
		UserSessionInfo userInfo =  (UserSessionInfo)request.getSession(false).getAttribute(SessionConstants.user_info);
		
		//based upon the logged in user redirects the user to his home page
		if(userInfo.getRoleId().equals(EducationConstant.TEACHER_USER_ROLE)){
			strActionFwd = "teacherHomePage";
		}else if(userInfo.getRoleId().equals(EducationConstant.AGENT_USER_ROLE)){
			strActionFwd = "agentHomePage";
		}else if(userInfo.getRoleId().equals(EducationConstant.AFFILIATE_USER_ROLE)){
			strActionFwd = "affiliateHomePage";
		}else if(userInfo.getRoleId().equals(EducationConstant.REVIEWER_USER_ROLE)){
			strActionFwd = "reviewerHomePage";
		}else if(userInfo.getRoleId().equals(EducationConstant.ADMIN_USER_ROLE)){
			strActionFwd = "adminHomePage";
		}else if(userInfo.getRoleId().equals(EducationConstant.GUEST_USER_ROLE)){
			strActionFwd = "guestHomePage";
		}else{
			strActionFwd = "homePage"; 
		}
		return mapping.findForward(strActionFwd);
	}
}
