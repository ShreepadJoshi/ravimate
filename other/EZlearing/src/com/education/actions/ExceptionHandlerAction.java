package com.education.actions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.education.formbeans.ExceptionHandlerActionForm;
import com.education.services.QuestionBankService;
import com.education.transferobj.ContentUploadTO;
import com.education.util.EducationConstant;
import com.education.util.Utilities;

public class ExceptionHandlerAction extends EducationBaseAction {

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
		ExceptionHandlerActionForm exceptionHandlingBean = (ExceptionHandlerActionForm)form;
		String fwd = null;
		if(sessionExists(request) &&
				getUserRoleFromSession(request).equals(EducationConstant.ADMIN_USER_ROLE))
			fwd = "AdminResponseExceptionPage";
		else if(sessionExists(request) &&
				!getUserRoleFromSession(request).equals(EducationConstant.ADMIN_USER_ROLE))
			fwd= "UserResponseExceptionPage";
		else
			fwd= "UserResponseExceptionPage";		
		return mapping.findForward(fwd);
	}
}
