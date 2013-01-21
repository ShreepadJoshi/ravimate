package com.education.actions;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.expframework.data.ExceptionDisplayDTO;
import org.expframework.exceptions.BaseAppException;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.displaytag.IExtendedPaginatedList;
import com.education.displaytag.PaginatedListImpl;
import com.education.formbeans.QuestionListActionForm;
import com.education.services.QuestionBankService;
import com.education.transferobj.QuestionBankTO;
import com.education.util.EducationConstant;
import com.education.util.Utilities;


public class ReviewerAction extends EducationBaseAction {

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws BaseAppException{
		
		QuestionListActionForm questionBean = (QuestionListActionForm) form;

		// condition to not display search results by default
		if (isNavigationDone(request)) {
			getPaginatedSearchResults(request, questionBean, null);
		}

		// For back navigation
		if (isBackNavigation(request)) {						
			restoreSearchCriteria(request,questionBean);
		}
		
		// get drop down values
		Utilities util = new Utilities();
		questionBean.setClassTypeOptions(util.getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE));
		questionBean.setSubjectOptions(util.getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE));
		questionBean.setQuestionStatusOptions(util.getDropdownValue(EducationConstant.QUESTION_STATUS_DROPDOWN_VALUE));
		questionBean.setTopicOptions(util.getDropdownValue(EducationConstant.TOPICS_DROPDOWN_VALUE));
		return mapping.findForward("displayPage");
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws BaseAppException {
		
		QuestionListActionForm questionBean = (QuestionListActionForm)form;
		QuestionBankService service = new QuestionBankService();
		String strUserEmailId = ((UserSessionInfo)request.getSession(false).getAttribute(SessionConstants.user_info)).getUserloginName();
		String strfwdAction = "actionSuccess"; 

		//Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("actionSuccess","");
		expDisplayDetails.set(expDTO);
		
		// Check if User Performed Approve or Reject Action
		String strPerformedAction = getAction(request); 
		if( strPerformedAction != null ){
			IExtendedPaginatedList cachedPage =  getPaginatedListFromSession(request,
				SessionConstants.SCH_RESULTS_REVIEWER_QUESTION_LIST);
			String[] strIds = getCheckedIds(request);		
			restoreSearchCriteria(request, questionBean);
			
			//check records are selected before performing approve/reject/LinkQclass action
			if(strIds == null || strIds.length == 0){
				dispRecordSelection_ErrorMsg(request,strPerformedAction);
				request.setAttribute("action","display");
				questionBean.setPgSearchResults(cachedPage);
				return mapping.findForward(strfwdAction);
			}
			
			if( strPerformedAction.equals("approve")){
				// call DAO method to perform Approve action on strIds
				int approveCount = service.approveQuestionbyIds(strIds);					
				// Get new updated search results for current page
				getPaginatedSearchResults(request,questionBean,cachedPage);
				//disp Message
				dispMessage(request,"",new ActionMessage("message.question.action",
									new String[]{""+approveCount,"Approved"}));
				
			}else if( strPerformedAction.equals("reject")){
				// call DAO method to perform Reject action on strIds
				int rejectCount = service.rejectQuestionbyIds(strIds);
				// Get new updated search results for current page
				getPaginatedSearchResults(request,questionBean,cachedPage);
				//disp Message
				dispMessage(request,"",new ActionMessage("message.question.action",
									new String[]{""+rejectCount,"Rejected"}));				
				
			}else if( strPerformedAction.equals("linktoclass")){
				if(strIds.length > 1){
					dispMessage(request,"",new ActionMessage("message.linkQClass.count"));
					request.setAttribute("action","display");
					questionBean.setPgSearchResults(cachedPage);
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
			}				
		}else{
			getPaginatedSearchResults(request,questionBean,null);
		}
		request.setAttribute("action","display");		
		return mapping.findForward(strfwdAction);
	}
	
	/**
	 * Gets partial list of records for current page and sets the partial search
	 * results to FormBean Store current search results in session.
	 * 
	 * @param request -
	 *            request containing details of currently navigated page
	 * @param questionBean -
	 *            FormBean to which search results are set
	 */
	private void getPaginatedSearchResults(HttpServletRequest request,
			QuestionListActionForm questionBean, IExtendedPaginatedList thePage) throws BaseAppException{
		thePage = thePage == null ? getPaginatedListFromRequest(request)
				: thePage;
		// Add Dummy search results
		/*
		 * ArrayList searchResults =
		 * DummyQuestionListData.getQuestionListData(thePage);
		 * thePage.setList(searchResults); thePage.setTotalNumberOfRows(20);
		 * questionBean.setPgSearchResults(thePage);
		 */
		QuestionBankService service = new QuestionBankService();				
		
		int totalNoOfRecords = service.getQuestionsCount( questionBean.getSch_classType(),
				questionBean.getSch_subject(),questionBean.getSch_questionStatus(),
				questionBean.getSch_topic(),questionBean.getSch_fromDate(),
				questionBean.getSch_toDate(),questionBean.getSch_isGraphics());
		
		int frmRecNo = thePage.getFirstRecordIndex();
		int noOfRecords = thePage.getPageSize()-1;
		thePage.setTotalNumberOfRows(totalNoOfRecords);
		
		//call DAO service
		ArrayList searchResults = service.getQuestions(questionBean.getSch_classType(),
				questionBean.getSch_subject(), questionBean.getSch_questionStatus(),
				questionBean.getSch_topic(),
				questionBean.getSch_fromDate(),questionBean.getSch_toDate(),
				questionBean.getSch_isGraphics(),frmRecNo,noOfRecords);
		
		thePage.setList(searchResults);
		questionBean.setPgSearchResults(thePage);
		
		//get backup of search criteria
		ReviewerListPageSchCriteria searchCriteria = new ReviewerListPageSchCriteria();
		Utilities.copyProperties(searchCriteria,questionBean);
		
		//store the search criteria details to page
		((PaginatedListImpl)thePage).setSearchCriteria(searchCriteria);
		
		//cache search results in session
		cacheSearchResult(request,SessionConstants.SCH_RESULTS_REVIEWER_QUESTION_LIST, thePage);	
	}
	
	/**
	 * By pass session tracking before login for Reviwer 
	 */
	@Override	
	protected boolean byPassSessionTracking_BeforeLogin() {
		return true;
	}
	
	private void restoreSearchCriteria(HttpServletRequest request, QuestionListActionForm questionBean){
		IExtendedPaginatedList cachedPage = getPaginatedListFromSession(
				request,SessionConstants.SCH_RESULTS_REVIEWER_QUESTION_LIST);
		//Restore search criteria
		ReviewerListPageSchCriteria searchCriteria = (ReviewerListPageSchCriteria)getCahedSearchCriteria(request,SessionConstants.SCH_RESULTS_REVIEWER_QUESTION_LIST);
		if(searchCriteria != null){
			Utilities.copyProperties(questionBean,searchCriteria);		
			questionBean.setPgSearchResults(cachedPage);
		}
	}
	
  public class ReviewerListPageSchCriteria{
		
		private String sch_topic = "";
	    private String sch_questionStatus = "";
	    private String sch_subject = "";
	    private String sch_classType = "";
	    private String sch_fromDate = "";
	    private String sch_toDate = "";
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
