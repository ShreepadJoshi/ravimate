package com.education.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SignOutAction extends EducationBaseAction{
	
	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// Dummy Nothing to do
		return performAction(mapping, form, request, response);
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
        if(sessionExists(request) && isSessionValid(request) ){        	
        	request.getSession(false).invalidate();
        }
		return mapping.findForward("success");
	}
}
