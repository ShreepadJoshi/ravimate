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
public class AddQuestionAction extends EducationBaseAction{

    /** 
     * 
     * @Updated on 24 Aug.
     * now setting "Medium" as default complexity.
     * @author Shree
     */
    @Override
    public ActionForward displayAction(ActionMapping mapping, ActionForm aForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
    	//Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("createMode","");
		expDisplayDetails.set(expDTO);
		
        AddQuestionForm addQuestionForm= (AddQuestionForm)aForm;
        QuestionBankService qbs=new QuestionBankService();
        String strActionForward = "createMode";
        //Create Mode Only for new record
    	addQuestionForm.setPageOpen_Mode(EducationConstant.PAGE_CREATE_MODE);
        int count = qbs.getNextQuestionId();
        addQuestionForm.setQuestionId(count);            
        UserSessionInfo objUserInfo =  (UserSessionInfo)request.getSession().getAttribute(SessionConstants.user_info);
    	addQuestionForm.setCreatedBy(objUserInfo.getUserloginName());
    	if(addQuestionForm.getComplexity().equals(""))
    		addQuestionForm.setComplexity("2");//setting default :D.
    	
        //set drop down values
        Utilities util =   new Utilities();
        addQuestionForm.setSubjectOptions(util.getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE));
        addQuestionForm.setTopicOptions(util.getDropdownValue(EducationConstant.TOPICS_DROPDOWN_VALUE));
        addQuestionForm.setSubTopicOptions(util.getDropdownValue(EducationConstant.SUBTOPICS_DROPDOWN_VALUE));
        addQuestionForm.setClassOptions(util.getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE));
        addQuestionForm.setComplexityOptions(util.getDropdownValue(EducationConstant.QUESTION_COMPLEXITY_DROPDOWN_VALUE));
        
        request.setAttribute("qId",addQuestionForm.getQuestionId());
        return mapping.findForward(strActionForward);
    }

    @Override
    public ActionForward performAction(ActionMapping mapping, ActionForm aForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AddQuestionForm addQuestionForm = (AddQuestionForm)aForm;
       
    	//Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("saveInsertQ","");
		expDisplayDetails.set(expDTO);
        
        QuestionBankTO qTo=new QuestionBankTO();        
        QuestionBankService qbs=new QuestionBankService();
        String strActionForward = null;
        
        //copy details to TO object first
        BeanUtils.copyProperties(qTo, addQuestionForm); //COPY details to TO object
           
        
        String strActionPerformed = getAction(request); 
        if(!Utilities.isNullOrBlank(strActionPerformed) &&
            		( strActionPerformed.equalsIgnoreCase("save & next question")||
            		  strActionPerformed.equalsIgnoreCase("save & close")) ){
        	
        	//By Default Set Question status=PENDING, isVerfied= Not verified for new Question        
	        qTo.setQuestionStatusId(EducationConstant.QUESTION_STATUS_PENDING);
	        //qTo.setIsVerified(EducationConstant.QUESTION_NOT_VERIFIED);
	        int createdBy  = ((UserSessionInfo)request.getSession().
    				getAttribute(SessionConstants.user_info)).getUserId();
	        qTo.setCreatedBy(String.valueOf(createdBy));
	        int InsertionCount = 0; 
	        if (addQuestionForm.getIsGraphics() == 0 ) {
	        	qTo.setIsGraphics(0); 
	            InsertionCount =  qbs.addQuestion(qTo); //insert text Question
			} else if(addQuestionForm.getIsGraphics() == 1 ){				
				qTo.setIsGraphics(1);
				setPictureQuestionDetails(qTo,addQuestionForm);
				InsertionCount = qbs.addPictureQuestion(qTo); //insert picture question
			}  
	        //display Message
	        dispRecordAdditionMessage(request, InsertionCount);
        }
            
        if(!Utilities.isNullOrBlank(strActionPerformed) &&
        		strActionPerformed.equalsIgnoreCase("save & next question") ){
        	String domainName = this.getServlet().getServletContext().getAttribute("DOMAIN_NAME").toString();        	
        	response.sendRedirect(domainName + "/addQuestion.do");
        	strActionForward=null;
        }else if(!Utilities.isNullOrBlank(strActionPerformed) && 
        		strActionPerformed.equalsIgnoreCase("save & close") ){
        	strActionForward =  "saveClose";
        	request.setAttribute("action","display"); 
        }else if(!Utilities.isNullOrBlank(strActionPerformed) && 
        		strActionPerformed.equalsIgnoreCase("close")){
        	strActionForward =  "saveClose";
        	request.setAttribute("action","display"); 
      	} 
        request.setAttribute("qId", addQuestionForm.getQuestionId());
        return mapping.findForward(strActionForward);
    }
    
    private void setPictureQuestionDetails( QuestionBankTO questionTO,AddQuestionForm addQuestionForm) throws Exception{
    	
    	if(addQuestionForm.getQuestionImage().getFileSize() >0 ){
    		questionTO.setQuestionFileStream(addQuestionForm.getQuestionImage().getInputStream());
    		questionTO.setQuestionFileStreamLength(addQuestionForm.getQuestionImage().getFileSize());    		
    		questionTO.setQuestionImgFileName(addQuestionForm.getQuestionImage().getFileName());
    	}    	
    	if(addQuestionForm.getAnsOneImage().getFileSize() > 0 ){
    		questionTO.setOption1FileStream(addQuestionForm.getAnsOneImage().getInputStream());
    		questionTO.setOption1FileStreamLength(addQuestionForm.getAnsOneImage().getFileSize());    		
    		questionTO.setOption1ImgFileName(addQuestionForm.getAnsOneImage().getFileName());
    	}    	
    	if(addQuestionForm.getAnsTwoImage().getFileSize() > 0 ){
    		questionTO.setOption2FileStream(addQuestionForm.getAnsTwoImage().getInputStream());
    		questionTO.setOption2FileStreamLength(addQuestionForm.getAnsTwoImage().getFileSize());    		
    		questionTO.setOption2ImgFileName(addQuestionForm.getAnsTwoImage().getFileName());
    	}    	
    	if(addQuestionForm.getAnsThreeImage().getFileSize() > 0 ){
    		questionTO.setOption3FileStream(addQuestionForm.getAnsThreeImage().getInputStream());
    		questionTO.setOption3FileStreamLength(addQuestionForm.getAnsThreeImage().getFileSize());    		
    		questionTO.setOption3ImgFileName(addQuestionForm.getAnsThreeImage().getFileName());
    	}
    	if(addQuestionForm.getAnsFourImage().getFileSize() > 0 ){
    		questionTO.setOption4FileStream(addQuestionForm.getAnsFourImage().getInputStream());
    		questionTO.setOption4FileStreamLength(addQuestionForm.getAnsFourImage().getFileSize());    		
    		questionTO.setOption4ImgFileName(addQuestionForm.getAnsFourImage().getFileName());
    	}
    	if(addQuestionForm.getAnsFiveImage().getFileSize() > 0 ){
    		questionTO.setOption5FileStream(addQuestionForm.getAnsFiveImage().getInputStream());
    		questionTO.setOption5FileStreamLength(addQuestionForm.getAnsFiveImage().getFileSize());    		
    		questionTO.setOption5ImgFileName(addQuestionForm.getAnsFiveImage().getFileName());
    	}
    	if(addQuestionForm.getAnsExplanation().getFileSize() > 0 ){
    		questionTO.setAnsExplanationFileStream(addQuestionForm.getAnsExplanation().getInputStream());
    		questionTO.setAnsExplanationFileStreamLength(addQuestionForm.getAnsExplanation().getFileSize());    		
    		questionTO.setAnsExpImgFileName(addQuestionForm.getAnsExplanation().getFileName());
    	}
    }

    @Override 
    /**
     * Return true for user as Teacher
     */
    protected boolean hasValidPermission(HttpServletRequest request) {    	
    	String strRole = getUserRoleFromSession(request);    	
    	if(strRole==null || !strRole.equals(EducationConstant.TEACHER_USER_ROLE))
    		return false;
    	else
    		return true;
    }
}















