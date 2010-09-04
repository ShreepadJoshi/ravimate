package com.education.actions;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.expframework.data.ExceptionDisplayDTO;
import org.expframework.exceptions.BaseAppException;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.exceptions.BussTestCannotGeneratedException;
import com.education.formbeans.TestQuestionBeanActionForm;
import com.education.services.TestGenerationService;
import com.education.transferobj.QuestionBankTO;
import com.education.transferobj.TestQuestion;
import com.education.util.EducationConstant;
import com.education.util.Utilities;

public class MakePaymentAction  extends EducationBaseAction {

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseAppException,ServletException,IOException {
		String actionFwdName = "makePaymentPage";
		return mapping.findForward(actionFwdName);
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseAppException {
		String actionFwdName = "makePaymentPage";
		return mapping.findForward(actionFwdName);
	}
	
	@Override
	protected boolean byPassSessionTracking_BeforeLogin() {
		return true;
	}
	
	
}











