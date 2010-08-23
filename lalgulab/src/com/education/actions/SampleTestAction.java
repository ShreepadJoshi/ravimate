package com.education.actions;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.expframework.exceptions.BaseAppException;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.formbeans.TestMainPageData;
import com.education.formbeans.TestSelectionCriteriaActionForm;
import com.education.util.EducationConstant;
import com.education.util.Utilities;

public class SampleTestAction  extends EducationBaseAction {

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseAppException,ServletException,IOException {
		String actionFwdName = "displayPage";
		UserSessionInfo objUserInfo =  (UserSessionInfo)request.getSession().getAttribute(SessionConstants.user_info);
		TestSelectionCriteriaActionForm bean = (TestSelectionCriteriaActionForm)form; 
		/**set drop down values */
        Utilities util =   new Utilities();
        System.out.println("---------------------------"+objUserInfo);
        if(objUserInfo!= null) {
        	bean.setClassTypeOptions(util.getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE,objUserInfo.getUserId()));
        	bean.setSubjectTypeOptions(util.getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE,objUserInfo.getUserId()));
        	System.out.println("-----------in if--------------"+objUserInfo.getUserId());
        } else {
        	bean.setClassTypeOptions(util.getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE));
			bean.setSubjectTypeOptions(util.getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE));
			System.out.println("-----------else---EducationConstant.CLASS_DROPDOWN_VALUE------------"+EducationConstant.CLASS_DROPDOWN_VALUE);
        }
		return mapping.findForward(actionFwdName);
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseAppException {		
		String actionFwdName = "takeTestPage";
		String actionPerformed = getAction(request);
		TestSelectionCriteriaActionForm bean = (TestSelectionCriteriaActionForm)form;
		
		request.setAttribute("classID", bean.getSch_classID());
		request.setAttribute("subjectID", bean.getSubjectID());
		request.setAttribute("action","display");
		return mapping.findForward(actionFwdName);
	}
	
	@Override
	protected boolean byPassSessionTracking_BeforeLogin() {
		return true;
	}

}
