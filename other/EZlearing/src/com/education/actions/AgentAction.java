package com.education.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AgentAction extends EducationBaseAction {

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
			return mapping. findForward("success");
	}
	
	//@Override - Untill login Session tracking is suspended 
	protected boolean byPassSessionTracking_BeforeLogin() {		
		return true;
	}
}
