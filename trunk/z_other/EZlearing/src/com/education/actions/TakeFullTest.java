package com.education.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.education.formbeans.FullTestForm;
import com.education.util.EducationConstant;
import com.education.util.Utilities;

public class TakeFullTest extends EducationBaseAction {

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fwdMapping = "displayInputPage";
		Utilities utilities =   new Utilities();
		FullTestForm fullTestForm = (FullTestForm) form;
		
		fullTestForm.setClassTypeOptions(utilities.getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE));
		fullTestForm.setSubjectTypeOptions(utilities.getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE));
		fullTestForm.setTopicTypeOptions(utilities.getDropdownValue(EducationConstant.TOPICS_DROPDOWN_VALUE));
        
		return mapping.findForward(fwdMapping);
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
