/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.education.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;
import org.expframework.data.ExceptionDisplayDTO;
import org.expframework.exceptions.BaseAppException;

/**
 *
 * @author Administrator
 */
public class ManageQuestionAction extends EducationBaseAction{

    @Override
    public ActionForward displayAction(ActionMapping mapping, ActionForm aForm, 
    		HttpServletRequest request, HttpServletResponse response) throws BaseAppException, IllegalAccessException, InvocationTargetException {
        
        AddQuestionForm addQuestionForm= (AddQuestionForm)aForm;
        QuestionBankService qbs=new QuestionBankService();
        String mode = null;
        String strActionForward = null;
        String cachedpage_for = null;
        
        if(request.getParameter("mode")!=null || request.getAttribute("mode")!= null){
        	 mode = request.getParameter("mode")== null ? request.getAttribute("mode").toString(): request.getParameter("mode");
        }
        QuestionBankTO qToDisplay = null;       
              
        //If Page is opened in View mode
        if( mode != null && (mode.equals(EducationConstant.PAGE_VIEW_MODE) ||
        		mode.equals(EducationConstant.PAGE_EDIT_MODE)) ){        
        	
        	int id = request.getParameter("id")== null ? -88 : Integer.parseInt(request.getParameter("id"));
        	cachedpage_for = request.getParameter("cachedpage_for")== null ? "" :(String)request.getParameter("cachedpage_for");
        	String domainName = getServlet().getServletContext().getAttribute("DOMAIN_NAME").toString();
        	ArrayList searchRewsults = new ArrayList();
        	
        	//Get cached page from session
        	if(cachedpage_for.equalsIgnoreCase(SessionConstants.SCH_RESULTS_TEACHER_QUESTION_LIST)){
        		searchRewsults = getCahedSearchResults(request,
        				SessionConstants.SCH_RESULTS_TEACHER_QUESTION_LIST);
        		addQuestionForm.setNavigationFromGrid(SessionConstants.SCH_RESULTS_TEACHER_QUESTION_LIST);
        		
        	}else if(cachedpage_for.equalsIgnoreCase(SessionConstants.SCH_RESULTS_ADMIN_QUESTION_LIST)){
        		searchRewsults = getCahedSearchResults(request,
        				SessionConstants.SCH_RESULTS_ADMIN_QUESTION_LIST);        		
        		addQuestionForm.setNavigationFromGrid(SessionConstants.SCH_RESULTS_ADMIN_QUESTION_LIST);
        		
        	}else if(cachedpage_for.equalsIgnoreCase(SessionConstants.SCH_RESULTS_REVIEWER_QUESTION_LIST)){
        		searchRewsults = getCahedSearchResults(request,
        				SessionConstants.SCH_RESULTS_REVIEWER_QUESTION_LIST);        		
        		addQuestionForm.setNavigationFromGrid(SessionConstants.SCH_RESULTS_REVIEWER_QUESTION_LIST);
        		
        	}else if(request.getSession(false).getAttribute(SessionConstants.LINKPAGE_QUESTION_DETAIL)!=null){
        		qToDisplay = (QuestionBankTO)request.getSession(false).getAttribute(SessionConstants.LINKPAGE_QUESTION_DETAIL); 
        		addQuestionForm.setNavigationFromGrid(SessionConstants.LINKPAGE_QUESTION_DETAIL);        		
        	}        	
        	//Get records from cached records
        	Iterator itr = searchRewsults.iterator();
        	while(itr.hasNext()){
        		qToDisplay =  (QuestionBankTO)itr.next();
        		if( qToDisplay.getQuestionId() == id )
        			break;
        	}
        	BeanUtils.copyProperties(addQuestionForm, qToDisplay);
        }
        
        //Depending on Page opened Mode return action forward 
        if(mode!=null && mode.equals(EducationConstant.PAGE_VIEW_MODE)){
    		addQuestionForm.setMode(EducationConstant.PAGE_VIEW_MODE);
    		strActionForward ="viewMode";
    		addQuestionForm.setPageOpen_Mode(EducationConstant.PAGE_VIEW_MODE);
    		
    	}else if(mode!=null && mode.equals(EducationConstant.PAGE_EDIT_MODE)){
    		addQuestionForm.setMode(EducationConstant.PAGE_EDIT_MODE);
    		if(cachedpage_for.equalsIgnoreCase(SessionConstants.SCH_RESULTS_ADMIN_QUESTION_LIST))
    			strActionForward = "AdminPage";
    		else
    			strActionForward = "editMode";
    		addQuestionForm.setPageOpen_Mode(EducationConstant.PAGE_EDIT_MODE);
    	}
        //Set Page drop down values
        setPageDropDownValues(addQuestionForm);
        
        request.setAttribute("qId",addQuestionForm.getQuestionId());
        return mapping.findForward(strActionForward);
    }

    @Override
    public ActionForward performAction(ActionMapping mapping, ActionForm aForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AddQuestionForm addQuestionForm = (AddQuestionForm)aForm;
       
        QuestionBankTO qTo=new QuestionBankTO();        
        QuestionBankService qbs=new QuestionBankService();
        String strActionForward = null;
        //String isPictureQ = (String) request.getParameter("isPictureQ");
        
      //Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO(
				handleNavigationFormGrids(request,addQuestionForm),"");
		expDisplayDetails.set(expDTO);
        
        //copy details to TO object first
        BeanUtils.copyProperties(qTo, addQuestionForm); //COPY details to TO object
        
        //Perform Action Based of mode in which is opened        
        if(addQuestionForm.getMode().equals(EducationConstant.PAGE_EDIT_MODE )){        	
        	String strActionPerformed = getAction(request);
        	int updateCount = 0;
        	if(!Utilities.isNullOrBlank(strActionPerformed) && strActionPerformed.equalsIgnoreCase("save")){
        		
        		if( isInvalidData(request,addQuestionForm)){
        			request.setAttribute("qId", addQuestionForm.getQuestionId());
        			request.setAttribute("action","display");        			
        	        setPageDropDownValues(addQuestionForm);//Set Page drop down values
        	        if(getUserInfoObject_FromSession(request).getRoleId().
        	        		equals(EducationConstant.ADMIN_USER_ROLE))        	        
        	        	return mapping.findForward("AdminPage");
        	        else
        	        	return mapping.findForward("editMode");
        		}
        		//call DAO method to update question details        	
            	if ( addQuestionForm.getIsGraphics() == 1 ) {
            		setPictureQuestionDetails(qTo,addQuestionForm);            		
            		updateCount = qbs.updatePictureQuestion(qTo); //update picture question				
    			} else {
    				updateCount =  qbs.updateQuestion(qTo); // update text question
    			}
            	//display Message
            	dispRecordUpdationMessage(request,updateCount);
        	}
        	
        }else if( addQuestionForm.getMode().equals(EducationConstant.PAGE_VIEW_MODE ) ){
        	//view mode 
        	
        }
        
        //Depending on mode in which page in Opened handle navigations
        if(addQuestionForm.getMode().equals(EducationConstant.PAGE_EDIT_MODE)){
        	strActionForward = handleNavigationFormGrids(request,addQuestionForm);        	
        	
        }else if(addQuestionForm.getMode().equals(EducationConstant.PAGE_VIEW_MODE)){
        	strActionForward = handleNavigationFormGrids(request,addQuestionForm);
        	
        }
//        expDTO.setActionForwardName(strActionForward);
//        expDisplayDetails.set(expDTO);
        request.setAttribute("qId", addQuestionForm.getQuestionId());
        return mapping.findForward(strActionForward);
    }
    
    /**
     * For Edit and view mode handles navigation done from grid - It checks navigation was done from which grid.
     * If any action is performed then prepares the required data and actionForward string,
     * sends back actionForwad string. 
     * @return ActionForward string - Action to which data should be passed for processing
     * Action 
     */
    private String handleNavigationFormGrids(HttpServletRequest request, AddQuestionForm addQuestionForm){    	
    	String fwd = null;
    	
    	//Get Action Performed
    	String strActionPerformed = getAction(request);    	
    	//Prepare Data for Next Action
    	String[] id = {String.valueOf(addQuestionForm.getQuestionId())};    	
    	
    	//Set Action Details to Request Scope variables
    	request.setAttribute(SessionConstants.FORM_ACTION_PERFORMED,strActionPerformed);
    	request.setAttribute(SessionConstants.GRID_CHECKED_IDS,id);
    	
    	//For Back or Close Action - Just redirect to back page
    	if(strActionPerformed.equals("back") || strActionPerformed.equals("close") || strActionPerformed.equals("save")){
    		request.setAttribute("action", "display");
    		request.setAttribute("back","123");
    	}
    	
    	//Handle Navigation to Parent page
    	if(addQuestionForm.getNavigationFromGrid().equals(SessionConstants.SCH_RESULTS_ADMIN_QUESTION_LIST)){
    		fwd = "grd_nav_admin_QuestionList";
    	}else if(addQuestionForm.getNavigationFromGrid().equals(SessionConstants.SCH_RESULTS_TEACHER_QUESTION_LIST)){
    		fwd = "grd_nav_teacher_QuestionList";
    	}else if(addQuestionForm.getNavigationFromGrid().equals(SessionConstants.SCH_RESULTS_REVIEWER_QUESTION_LIST)){
    		fwd = "grd_nav_reviewer_QuestionList";
    	}else if(addQuestionForm.getNavigationFromGrid().equals(SessionConstants.LINKPAGE_QUESTION_DETAIL)){
    		fwd = "nav_linkQtoClass_page";
    	}else{
    		fwd = "saveClose";
    	}
    	return fwd;
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

    private boolean isInvalidData(HttpServletRequest request, AddQuestionForm addQuestionForm){
    	
		boolean isInvalidData = false;
		boolean isGraphics = ( addQuestionForm.getIsGraphics() == 1 ? true : false);
		boolean question_Img = false;
		boolean option1_Img = false;
		boolean option2_Img = false;
		boolean option3_Img = false;
		boolean option4_Img = false;
		boolean option5_Img = false;
		boolean ans_Exp_Img = false;
		if(isGraphics){
			question_Img = addQuestionForm.getQuestionImage().getFileSize() == 0 ? false:true;
			option1_Img = addQuestionForm.getAnsOneImage().getFileSize() == 0 ? false:true;
			option2_Img = addQuestionForm.getAnsTwoImage().getFileSize() == 0 ? false:true;
			option3_Img = addQuestionForm.getAnsThreeImage().getFileSize() == 0 ? false:true;
			option4_Img = addQuestionForm.getAnsFourImage().getFileSize() == 0 ? false:true;
			option5_Img = addQuestionForm.getAnsFiveImage().getFileSize() == 0 ? false:true;
			ans_Exp_Img = addQuestionForm.getAnsExplanation().getFileSize() == 0 ? false:true;
		}
		boolean question_text = Utilities.isNullOrBlank(addQuestionForm.getQuestion()) ? false:true ;
		boolean option1_text = Utilities.isNullOrBlank(addQuestionForm.getOption1()) ? false:true ;
		boolean option2_text = Utilities.isNullOrBlank(addQuestionForm.getOption2()) ? false:true ;
		boolean option3_text = Utilities.isNullOrBlank(addQuestionForm.getOption3()) ? false:true ;
		boolean option4_text = Utilities.isNullOrBlank(addQuestionForm.getOption4()) ? false:true ;
		boolean option5_text = Utilities.isNullOrBlank(addQuestionForm.getOption5()) ? false:true ;
		boolean subject_text = Utilities.isNullOrBlank(addQuestionForm.getSubject()) ? false:true ;
		boolean topic_text = Utilities.isNullOrBlank(addQuestionForm.getTopicId()) ? false:true ;
		boolean subTopic_text = Utilities.isNullOrBlank(addQuestionForm.getSubTopicId()) ? false:true ;
		boolean ans_Exp_text = Utilities.isNullOrBlank(addQuestionForm.getAnswerDiscription()) ? false:true ;		
		boolean ans_text = Utilities.isNullOrBlank(addQuestionForm.getAnswer()) ? false:true ;
		

		//Do the Check for Mandatory Fields Based on the Mode is which page is opened
		if(addQuestionForm.getPageOpen_Mode().equalsIgnoreCase(EducationConstant.PAGE_EDIT_MODE)){
			
			//Validation Common for both Text/Picture Question
			if(!topic_text){
				isInvalidData = true;
				dispMessage(request,"",new ActionMessage("Topic is Required",false));
			}
			if(!subTopic_text){
				isInvalidData = true;
				dispMessage(request,"",new ActionMessage("SubTopic is Required",false));
			}
			if(!subject_text){
				isInvalidData = true;
				dispMessage(request,"",new ActionMessage("Subject is Required",false));
			}
			//For Answer
			if(!ans_text){
				isInvalidData = true;
				dispMessage(request,"",new ActionMessage("Answer is Required",false));
			}
			//validation specific to Picture Question
			if(isGraphics){
				//For Question
				//either there should be question text or question Img
				if( !question_text && 
						editAction(addQuestionForm.getQuestionImg_editAction())==
							EducationConstant.EDIT_ACTION_REMOVE ){
					//add question is required message
					isInvalidData = true;
					dispMessage(request,"",new ActionMessage("Either Question Text/Image is Required",false));
				}
				if(editAction(addQuestionForm.getQuestionImg_editAction())
							== EducationConstant.EDIT_ACTION_UPDATE && !question_Img){
					isInvalidData = true;
					dispMessage(request,"",new ActionMessage("Question Image is Missing",false));
				}
				//For Option1
				//either there should be option1 text or option1 Img
				if(!option1_text &&
						 editAction(addQuestionForm.getOption1Img_editAction())==
								EducationConstant.EDIT_ACTION_REMOVE ){
					isInvalidData = true;
					dispMessage(request,"",new ActionMessage("Either Option1 Text/Image is Required",false));
				}
				
				if(editAction(addQuestionForm.getOption1Img_editAction())
						== EducationConstant.EDIT_ACTION_UPDATE && !option1_Img){
					isInvalidData = true;
					dispMessage(request,"",new ActionMessage("Option1 Image is Missing",false));
				}
				
				//For Option2
				//either there should be option1 text or option1 Img
				if(!option2_text && 
						editAction(addQuestionForm.getOption2Img_editAction())==
								EducationConstant.EDIT_ACTION_REMOVE ){				
					isInvalidData = true;
					dispMessage(request,"",new ActionMessage("Either Option2 Text/Image is Required",false));
				}
				if(editAction(addQuestionForm.getOption2Img_editAction())
						== EducationConstant.EDIT_ACTION_UPDATE && !option2_Img){
					isInvalidData = true;
					dispMessage(request,"",new ActionMessage("Option2 Image is Missing",false));
				}
				
				//For Option3
				if(editAction(addQuestionForm.getOption3Img_editAction())
						== EducationConstant.EDIT_ACTION_UPDATE && !option3_Img){
					isInvalidData = true;
					dispMessage(request,"",new ActionMessage("Option3 Image is Missing",false));
				}
				//For Option4
				if(editAction(addQuestionForm.getOption4Img_editAction())
						== EducationConstant.EDIT_ACTION_UPDATE && !option4_Img){
					isInvalidData = true;
					dispMessage(request,"",new ActionMessage("Option4 Image is Missing",false));
				}
				//For Option5
				if(editAction(addQuestionForm.getOption5Img_editAction())
						== EducationConstant.EDIT_ACTION_UPDATE && !option5_Img){
					isInvalidData = true;
					dispMessage(request,"",new ActionMessage("Option5 Image is Missing",false));
				}				
				//For Ans Explanation
				if(editAction(addQuestionForm.getAnsExpImg_editAction())
						== EducationConstant.EDIT_ACTION_UPDATE && !ans_Exp_Img){
					isInvalidData = true;
					dispMessage(request,"",new ActionMessage("Ans Explantion Image is Missing",false));
				}			
			}else{
				//validation specific to Text Question
				if(!question_text){
					isInvalidData = true;
					dispMessage(request,"",new ActionMessage("Question Text is Missing",false));
				}
				if(!option1_text){
					isInvalidData = true;
					dispMessage(request,"",new ActionMessage("Option1 Text is Missing",false));
				}
				if(!option2_text){
					isInvalidData = true;
					dispMessage(request,"",new ActionMessage("Option2 Text is Missing",false));
				}
			}//End Is Not IsGraphics
		}//End Edit Mode 
	return isInvalidData;
    }
    
    private String editAction(String action){
		String actionPerformed = null;
		if(action.equalsIgnoreCase("update"))
			actionPerformed = EducationConstant.EDIT_ACTION_UPDATE;
		else if(action.equalsIgnoreCase("remove"))
			actionPerformed = EducationConstant.EDIT_ACTION_REMOVE;
		else if(action.equalsIgnoreCase("none"))
			actionPerformed = EducationConstant.EDIT_ACTION_DONOTINHG;
		return actionPerformed;
	}
    
    private void setPageDropDownValues(AddQuestionForm addQuestionForm) throws BaseAppException{
    	 //set drop down values
        Utilities util =   new Utilities();
        addQuestionForm.setSubjectOptions(util.getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE));
        addQuestionForm.setTopicOptions(util.getDropdownValue(EducationConstant.TOPICS_DROPDOWN_VALUE));
        addQuestionForm.setSubTopicOptions(util.getDropdownValue(EducationConstant.SUBTOPICS_DROPDOWN_VALUE));
        addQuestionForm.setClassOptions(util.getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE));
        addQuestionForm.setComplexityOptions(util.getDropdownValue(EducationConstant.QUESTION_COMPLEXITY_DROPDOWN_VALUE));
        util = null;
    }
}