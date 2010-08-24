/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.education.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.displaytag.IExtendedPaginatedList;
import com.education.displaytag.QueDescColDecorator;
import com.education.formbeans.AddQuestionForm;
import com.education.services.QuestionBankService;
import com.education.transferobj.QuestionBankTO;
import com.education.util.EducationConstant;
import com.education.util.Utilities;

import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;
import org.expframework.data.ExceptionDisplayDTO;

/**
 *
 * @author Administrator
 */
public class ShowParentsSectionStaticContentAction extends EducationBaseAction{

    @Override
    public ActionForward displayAction(ActionMapping mapping, ActionForm aForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	return mapping.findForward("parents_section");    
       }

    @Override
    public ActionForward performAction(ActionMapping mapping, ActionForm aForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AddQuestionForm addQuestionForm = (AddQuestionForm)aForm;
       String strActionForward =null;
    	
        return mapping.findForward(strActionForward);
    }

	
   }















