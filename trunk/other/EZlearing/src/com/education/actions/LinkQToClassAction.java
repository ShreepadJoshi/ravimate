package com.education.actions;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.expframework.data.ExceptionDisplayDTO;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.displaytag.IExtendedPaginatedList;
import com.education.displaytag.RowData;
import com.education.formbeans.LinkQuestiontoClassActionForm;
import com.education.formbeans.QuestionListActionForm;
import com.education.services.QuestionBankService;
import com.education.transferobj.LinkQTOClassTO;
import com.education.transferobj.QuestionBankTO;
import com.education.util.EducationConstant;
import com.education.util.Utilities;


public class LinkQToClassAction extends EducationBaseAction {

		
	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		LinkQuestiontoClassActionForm questionBean = (LinkQuestiontoClassActionForm) form;
		QuestionBankTO questionTo = null;
		
		//Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("displayPage","");
		expDisplayDetails.set(expDTO);

		// For back navigation
		if (isBackNavigation(request)) {
			questionTo  = (QuestionBankTO)request.getSession(false).getAttribute(SessionConstants.LINKPAGE_QUESTION_DETAIL);		
			//copy details to Bean
			BeanUtils.copyProperties(questionBean,questionTo);
			copyDetailsFromTOtoBean(questionTo,questionBean);
		}else if( request.getAttribute(SessionConstants.LINKPAGE_QUESTION_DETAIL) != null){
			questionTo = (QuestionBankTO)request.getAttribute(SessionConstants.LINKPAGE_QUESTION_DETAIL);
			//copy details to Bean
			BeanUtils.copyProperties(questionBean,questionTo);
			copyDetailsFromTOtoBean(questionTo,questionBean);
		}
		
		//get link Question to class count
		int rowCount = new QuestionBankService().getQuestionToClass_LinkCount(questionBean.getQuestionId());		
		questionBean.setLink_rowCount(String.valueOf(rowCount));
		
		//get and set Question class link data
		ArrayList linkData =  new QuestionBankService().getQuestionToClass_LinkedData(
									questionBean.getQuestionId());		
		questionBean.setLink_rowData(linkData);
		
		//set linkStatus=Old according to linkData fetched
		if(rowCount>0){ 
			String[] linkstatus = null;
			linkstatus = new String[rowCount]; 
			for(int i=0;i<rowCount;i++){
				linkstatus[i] = "Old";
			}
			questionBean.setLinkStatus(linkstatus);
		}
		
		//set drop down value
		 Utilities util = new Utilities();
		questionBean.setClassTypeOptions(util.getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE));
		questionBean.setComplexityOptions(util.getDropdownValue(EducationConstant.QUESTION_COMPLEXITY_DROPDOWN_VALUE));
		questionBean.setSubjectOptions(util.getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE));
		util = null;
		//request.setAttribute("FormBean",questionBean);
		return mapping.findForward("displayPage");
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		//Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("actionSuccess","");
		expDisplayDetails.set(expDTO);
		
		LinkQuestiontoClassActionForm questionBean = (LinkQuestiontoClassActionForm) form;
		QuestionBankTO questionTO = new QuestionBankTO();
		BeanUtils.copyProperties(questionTO, questionBean);
		String fwd = null;
		String reviewerID = String.valueOf( ((UserSessionInfo)request.getSession(false).
				getAttribute(SessionConstants.user_info)).getUserId() );
		
		// Check if User Performed Action
		String strPerformedAction = getAction(request); 
		if( strPerformedAction != null ){				
			if( strPerformedAction.equals("close")){
				request.setAttribute("back","123");
				String RoleId = ((UserSessionInfo)request.getSession(false).getAttribute(SessionConstants.user_info)).getRoleId();
				//Based On Role Redirect to back page
				if(EducationConstant.REVIEWER_USER_ROLE.equals(RoleId))
					fwd="reviewer_page";
				else if(EducationConstant.ADMIN_USER_ROLE.equals(RoleId))
					fwd="adminQuestionList_page";
					
			}else if(strPerformedAction.equals("questiondetails")){				
				request.getSession(false).setAttribute(SessionConstants.LINKPAGE_QUESTION_DETAIL,questionTO);
				request.setAttribute("mode","view");
				fwd="manageQ_viewMode";
			}else if(strPerformedAction.equals("add")){
				int rowCount = Integer.parseInt(questionBean.getLink_rowCount());				
				ArrayList rowsData = new ArrayList();
				
				//set previous data
				for(int i=0; i< rowCount; i++){					
					LinkQTOClassTO objTO = new LinkQTOClassTO();
					objTO.setClassId(questionBean.getClassList()[i]);
					objTO.setComplexityId(questionBean.getComplexityList()[i]);
					objTO.setLinkedBy(reviewerID);
					objTO.setQuid(String.valueOf(questionBean.getQuestionId()));					
					rowsData.add(objTO);
				}				
				//Add new rowData
				LinkQTOClassTO objTO = new LinkQTOClassTO();
				objTO.setClassId("");
				objTO.setComplexityId("");
				objTO.setLinkedBy(reviewerID);
				objTO.setQuid(String.valueOf(questionBean.getQuestionId()));				
				rowsData.add(objTO);
				
				//setup new LinkStatus array
				String[]linkStatusArray = questionBean.getLinkStatus();
				String[]newlinkStatusArray = null;
				int i=0;
				if(linkStatusArray == null){
					newlinkStatusArray = new String[1];
				}
				else{
					newlinkStatusArray = new String[linkStatusArray.length+1];
					for(i=0; i<linkStatusArray.length;i++){
						newlinkStatusArray[i] = linkStatusArray[i];
					}
				}				
				newlinkStatusArray[i] = "New";
				questionBean.setLinkStatus(newlinkStatusArray);
				
				//Set new LinkData to Bean				
				questionBean.setLink_rowData(rowsData);
				//Set New Row Count
				questionBean.setLink_rowCount(String.valueOf(rowCount+1));
				//set drop down value
				 Utilities util = new Utilities();
				questionBean.setClassTypeOptions(util.getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE));
				questionBean.setComplexityOptions(util.getDropdownValue(EducationConstant.QUESTION_COMPLEXITY_DROPDOWN_VALUE));
				questionBean.setSubjectOptions(util.getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE));
				util = null;
				
				fwd="displayPage";
				//display Message
				dispMessage(request,"",new ActionMessage("Added 1 New Row",false));
				
			}else if(strPerformedAction.equals("save")){				
				ArrayList savingdataList = prepareDataForSaving(questionBean.getClassList(),questionBean.getLinkStatus(),  questionBean.getComplexityList(),
						String.valueOf(questionBean.getQuestionId()),reviewerID);
				ArrayList updateDataList = new ArrayList();
				ArrayList insertDataList = new ArrayList();
				int updateCount = 0;
				int insertCount = 0;
				figureDataUpdateOrInsert(savingdataList,updateDataList,insertDataList );
				
				if(updateDataList.size() > 0){
					updateCount =  new QuestionBankService().updateQuestionToClassLink(updateDataList); //updated details
					//display Message
					//dispRecordUpdationMessage(request, updateCount);
					dispMessage(request,"",new ActionMessage("message.linkQclass.updataion",updateCount));
				}
				if(insertDataList .size() > 0){
					insertCount =  new QuestionBankService().linkQuestionToClass(insertDataList); //updated details
					//display Message
					//dispRecordAdditionMessage(request,insertCount);
					dispMessage(request,"",new ActionMessage("message.linkQclass.insertion",updateCount));
				}
				fwd="actionSuccess";
			}
		}
		request.setAttribute("action","display");
		return mapping.findForward(fwd);
	}
			
	private void copyDetailsFromTOtoBean(QuestionBankTO objTO,LinkQuestiontoClassActionForm questionBean ){
		
		questionBean.setLink_questionID(String.valueOf(objTO.getQuestionId()));
		questionBean.setLink_subject(objTO.getSubject());
		questionBean.setLink_subtopic(objTO.getSubTopic());
		questionBean.setLink_question(objTO.getQuestion());
	}

	//Prepare the data for saving 
	private ArrayList prepareDataForSaving(String[] classList,String[] linkStatus,	String[] complexityList,String strQuestionId,String reviewerEmailID ){		
		ArrayList list = new ArrayList();
		for(int i=0; i< classList.length; i++){					
			LinkQTOClassTO objTo = new LinkQTOClassTO();					
			//save only data that is other then Please Specify
			if(!"".equals(classList[i])&& !"".equals(complexityList[i])){				
				objTo.setClassId(classList[i]);
				objTo.setComplexityId(complexityList[i]);
				objTo.setLinkedBy(reviewerEmailID);
				objTo.setQuid(strQuestionId);
				String status = linkStatus[i].equalsIgnoreCase("new") ? "New" : "Old";  
				objTo.setStatus(status);
				list.add(objTo);
			}					
		}
		return list;
	}

	/**
	 * Prepares the list of data to be saved and updated 
	 * @param savingdataList - full list 
	 * @param updateDataList - method updates list with records to be updated
	 * @param newDataList - method updates list with records to be inserted
	 */
	private void figureDataUpdateOrInsert(ArrayList savingdataList,ArrayList updateDataList,ArrayList insertDataList){
		LinkQTOClassTO objTo = null;
		for(int i=0; i< savingdataList.size(); i++){					
			objTo = (LinkQTOClassTO)savingdataList.get(i);
			if(objTo.getStatus().equalsIgnoreCase("new"))
				insertDataList.add(savingdataList.get(i));
			else if(objTo.getStatus().equalsIgnoreCase("old"))
				updateDataList.add(savingdataList.get(i));
		}		
	}
	
	@Override 
    /**
    * Return true for user as Admin Role
    */
    protected boolean hasValidPermission(HttpServletRequest request) {    	
    	String strRole = getUserRoleFromSession(request);    	
    	if(strRole==null || !strRole.equals(EducationConstant.REVIEWER_USER_ROLE))
    		return false;
    	else
    		return true;
    }
}










