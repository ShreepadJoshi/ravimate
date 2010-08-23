package com.education.actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.expframework.data.ExceptionDisplayDTO;
import org.expframework.exceptions.BaseAppException;

import com.education.Session.SessionConstants;
import com.education.formbeans.AdminQuestionListActionForm;
import com.education.services.QuestionBankService;
import com.education.transferobj.QuestionBankTO;
import com.education.util.EducationConstant;
import com.education.displaytag.IExtendedPaginatedList;
import com.education.displaytag.PaginatedListImpl;
import com.education.util.Utilities;


public class AdminQuestionListAction extends EducationBaseAction {

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseAppException {
		//Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("actionSuccess","");
		expDisplayDetails.set(expDTO);
		
		AdminQuestionListActionForm adminQBean = (AdminQuestionListActionForm)form;
        
		//condition to not display search results by default
		if(isNavigationDone(request)){
			getPaginatedSearchResults(request,adminQBean,null);
		}	
		
		//For back navigation
		if(isBackNavigation(request)){
			IExtendedPaginatedList cachedPage =  getPaginatedListFromSession(request,
					SessionConstants.SCH_RESULTS_ADMIN_QUESTION_LIST);
			restoreSearchCriteria(request, adminQBean);
			getPaginatedSearchResults(request,adminQBean,cachedPage);
		}		
		
		//get drop down values
		adminQBean.setClassTypeOptions( new Utilities().
				getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE));
		adminQBean.setSubjectOptions( new Utilities().
				getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE));
		adminQBean.setQuestionStatusOptions( new Utilities().
				getDropdownValue(EducationConstant.QUESTION_STATUS_DROPDOWN_VALUE));
		adminQBean.setTopicOptions(new Utilities().getDropdownValue(EducationConstant.TOPICS_DROPDOWN_VALUE));
				
		return mapping.findForward("displayPage");
	}
	
	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws BaseAppException{
		AdminQuestionListActionForm adminQBean = (AdminQuestionListActionForm)form;		
		QuestionBankService service = new QuestionBankService();
		String strfwdAction ="actionSuccess";
		//Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("actionSuccess","");
		expDisplayDetails.set(expDTO);
		//Check if User Performed Approve or Reject Action
		String strPerformedAction = getAction(request);
		
		if( strPerformedAction != null ){			
			IExtendedPaginatedList cachedPage =  getPaginatedListFromSession(request,
					SessionConstants.SCH_RESULTS_ADMIN_QUESTION_LIST);
			restoreSearchCriteria(request, adminQBean);
			
			String[] strIds = getCheckedIds(request);
			
			//display error message if no record is selected
			if(Utilities.isNullOrBlank(strIds) || strIds.length == 0){
				dispRecordSelection_ErrorMsg(request,strPerformedAction);				
				adminQBean.setPgSearchResults(cachedPage);
				request.setAttribute("action","display");
				return mapping.findForward(strfwdAction);
			}
			
			if( strPerformedAction.equals("approve")){
				//call DAO method to perform Approve action on strIds					
				int approveCount = service.approveQuestionbyIds(strIds);					
				// Get new updated search results for current page 
				getPaginatedSearchResults(request,adminQBean,cachedPage);
				//disp message
				dispMessage(request,"",new ActionMessage("message.question.action",
									new String[]{""+approveCount,"Approved"}));
			}else if( strPerformedAction.equals("reject")){
				//call DAO method to perform Reject action on strIds
				int rejectCount = service.rejectQuestionbyIds(strIds);
				// Get new updated search results for current page 
				getPaginatedSearchResults(request,adminQBean,cachedPage);
				//disp message
				dispMessage(request,"",new ActionMessage("message.question.action",
									new String[]{""+rejectCount,"Rejected"}));
				
			}else if( strPerformedAction.equals("delete")){
				//call DAO method to perform Reject action on strIds
				int deleteCount = service.deleteQuestionbyId(strIds);					
				//get new total records count after record deletion
				int newTotalRecords = service.getQuestionsCount(adminQBean.getSch_classType(),
						adminQBean.getSch_subject(),adminQBean.getSch_questionStatus(),
						adminQBean.getSch_topic(),adminQBean.getSch_fromDate(),
						adminQBean.getSch_toDate(),adminQBean.getSch_isGraphics());					
				//get new paginated page after records are deleted
				cachedPage =  getNewPage_AfterDelete(newTotalRecords, cachedPage);					
				// Get new updated search results for current page 
				getPaginatedSearchResults(request,adminQBean,cachedPage);
				//disp message
				dispRecordDeletionMessage(request,deleteCount);
			}else if( strPerformedAction.equals("linktoclass")){
				if(strIds.length > 1){
					dispMessage(request,"",new ActionMessage("message.linkQClass.count"));
					adminQBean.setPgSearchResults(cachedPage);
					request.setAttribute("action","display");
					return mapping.findForward(strfwdAction);
				}
				//Get records from cached records
		       	Iterator itr = cachedPage.getList().iterator();
		       	QuestionBankTO  questionTO = new QuestionBankTO();
		       	while(itr.hasNext()){
		       		questionTO =  (QuestionBankTO)itr.next();
		       		if( questionTO.getQuestionId() == Integer.parseInt(strIds[0]) )
		        		break;
		        }
		        request.setAttribute(SessionConstants.LINKPAGE_QUESTION_DETAIL,questionTO);
				strfwdAction="linkQtoClassAction";
			}else if( strPerformedAction.equals("markasinactive")){					
				//call DAO method to mark Question as Inactive on strIds
				int qInactiveCount = service.markQuestionASInactivebyIds(strIds);
				// Get new updated search results for current page 
				getPaginatedSearchResults(request,adminQBean,cachedPage);
				//disp message
				dispMessage(request, "",
						new ActionMessage("message.question.action",
								new String[]{""+qInactiveCount,"Mark as Inactive"}));
			}	
		}else{
			// Get Paginated partial list 
			getPaginatedSearchResults(request,adminQBean,null);			
		}
		request.setAttribute("action","display"); //complusory request must
												  //go to displayAction get required data on form and then display
		return mapping.findForward(strfwdAction);
	}
	
	/**
	 * Gets partial list of records for current page
	 * and sets the partial search results to FormBean
	 * Store current search results in session.
	 * @param request - request containing details of currently navigated page
	 * @param questionBean - FormBean to which search results are set
	 */
	private void getPaginatedSearchResults(HttpServletRequest request, AdminQuestionListActionForm questionBean,
				IExtendedPaginatedList thePage) throws BaseAppException{
		thePage = thePage == null ? getPaginatedListFromRequest(request) : thePage ;
		
		//call DAO service
		QuestionBankService service = new QuestionBankService();
		int totalNoOfRecords = service.getQuestionsCount(questionBean.getSch_classType(),questionBean.getSch_subject(),
				questionBean.getSch_questionStatus(),questionBean.getSch_topic(),
				questionBean.getSch_fromDate(),questionBean.getSch_toDate(),questionBean.getSch_isGraphics());		
		int frmRecord = thePage.getFirstRecordIndex();
		int noOfRecords = thePage.getPageSize();		
		thePage.setTotalNumberOfRows(totalNoOfRecords);
		
		ArrayList searchResults = service.getQuestions(questionBean.getSch_classType(),
				questionBean.getSch_subject(),questionBean.getSch_questionStatus(),
				questionBean.getSch_topic(),questionBean.getSch_fromDate(),questionBean.getSch_toDate(),
				questionBean.getSch_isGraphics(), frmRecord,noOfRecords);
		thePage.setList(searchResults);
		questionBean.setPgSearchResults(thePage);
		
		//Take backup of search Criteria
		AdminQuestionListSearchCriteria searchCriteria = new AdminQuestionListSearchCriteria();
		Utilities.copyProperties(searchCriteria, questionBean);
		//store search criteria
		((PaginatedListImpl)thePage).setSearchCriteria(searchCriteria);
		//cache search results in session
		cacheSearchResult(request,SessionConstants.SCH_RESULTS_ADMIN_QUESTION_LIST, thePage);		
	}	
	
	@Override 
    /**
     * Return true for user as Admin Role
     */
    protected boolean hasValidPermission(HttpServletRequest request) {    	
    	String strRole = getUserRoleFromSession(request);    	
    	if(strRole==null || !strRole.equals(EducationConstant.ADMIN_USER_ROLE))
    		return false;
    	else
    		return true;
    }

	private void restoreSearchCriteria(HttpServletRequest request, AdminQuestionListActionForm adminQBean){
		//Restore search criteria
		AdminQuestionListSearchCriteria searchCriteria =(AdminQuestionListSearchCriteria)getCahedSearchCriteria(request,SessionConstants.SCH_RESULTS_ADMIN_QUESTION_LIST);
		if(searchCriteria != null)
			Utilities.copyProperties(adminQBean, searchCriteria);
	}
	
	public class AdminQuestionListSearchCriteria{
	
		private String sch_topic = "";
	    private String sch_questionStatus = "";
	    private String sch_subject = "";
	    private String sch_classType = "";
	    private String sch_fromDate = new SimpleDateFormat("dd-MMM-yyyy").format( new Date()).toString();
	    private String sch_toDate = new SimpleDateFormat("dd-MMM-yyyy").format( new Date()).toString();
		public String getSch_topic() {
			return sch_topic;
		}
		public void setSch_topic(String sch_topic) {
			this.sch_topic = sch_topic;
		}
		public String getSch_questionStatus() {
			return sch_questionStatus;
		}
		public void setSch_questionStatus(String sch_questionStatus) {
			this.sch_questionStatus = sch_questionStatus;
		}
		public String getSch_subject() {
			return sch_subject;
		}
		public void setSch_subject(String sch_subject) {
			this.sch_subject = sch_subject;
		}
		public String getSch_classType() {
			return sch_classType;
		}
		public void setSch_classType(String sch_classType) {
			this.sch_classType = sch_classType;
		}
		public String getSch_fromDate() {
			return sch_fromDate;
		}
		public void setSch_fromDate(String sch_fromDate) {
			this.sch_fromDate = sch_fromDate;
		}
		public String getSch_toDate() {
			return sch_toDate;
		}
		public void setSch_toDate(String sch_toDate) {
			this.sch_toDate = sch_toDate;
		}
	    
	}

}

















