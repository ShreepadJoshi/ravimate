package com.education.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.education.util.EducationConstant;

public class AdminAction extends EducationBaseAction {

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return   performAction(mapping, form, request, response);    
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("success");
	}
	
	@Override 
    /**
     * Return true for user as Teacher
     */
    protected boolean hasValidPermission(HttpServletRequest request) {    	
    	String strRole = getUserRoleFromSession(request);    	
    	if(strRole==null || !strRole.equals(EducationConstant.ADMIN_USER_ROLE))
    		return false;
    	else
    		return true;
    }

}
