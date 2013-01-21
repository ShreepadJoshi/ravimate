package com.education.actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.expframework.data.ExceptionDisplayDTO;
import org.expframework.exceptions.BaseAppException;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.exceptions.BussMasterDataMissingException;
import com.education.formbeans.QuestionListActionForm;
import com.education.services.QuestionBankService;
import com.education.util.EducationConstant;
import com.education.displaytag.IExtendedPaginatedList;
import com.education.displaytag.PaginatedListImpl;
import com.education.util.Utilities;


public class TeacherAction extends EducationBaseAction {

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseAppException {
		//Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("displayPage","");
		expDisplayDetails.set(expDTO);
		
		QuestionListActionForm questionBean = (QuestionListActionForm)form;
		
		//condition to not display search results by default
		if(isNavigationDone(request)){
			getPaginatedSearchResults(request,questionBean,null);
		}	
		
		//For back navigation
		if(isBackNavigation(request)){
			IExtendedPaginatedList cachedPage =  getPaginatedListFromSession(request,
					SessionConstants.SCH_RESULTS_TEACHER_QUESTION_LIST);
			restoreSearchCriteria(request, questionBean);
			getPaginatedSearchResults(request, questionBean,cachedPage);			
		}
		
		//For Delete Actions load page from session		
		if(request.getAttribute(EducationConstant.TEACHER_PAGE_DELETE_OPERATION) != null){
			IExtendedPaginatedList cachedPage =  getPaginatedListFromSession(request,
					SessionConstants.SCH_RESULTS_TEACHER_QUESTION_LIST);
			getPaginatedSearchResults(request, questionBean,cachedPage);
		}
		//get drop down values
		questionBean.setClassTypeOptions( new Utilities().
				getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE));
		questionBean.setSubjectOptions( new Utilities().
				getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE));
		questionBean.setQuestionStatusOptions( new Utilities().
				getDropdownValue(EducationConstant.QUESTION_STATUS_DROPDOWN_VALUE));
		questionBean.setTopicOptions( new Utilities().
				getDropdownValue(EducationConstant.TOPICS_DROPDOWN_VALUE));
			
		return mapping.findForward("displayPage");
	}
	
	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws BaseAppException {
		
		//Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("actionSuccess","");
		expDisplayDetails.set(expDTO);
		
		QuestionListActionForm questionBean = (QuestionListActionForm)form;
		QuestionBankService service = new QuestionBankService();
		String strActionFwd = "actionSuccess";
		String strUserEmailId = ((UserSessionInfo)request.getSession(false).getAttribute(SessionConstants.user_info)).getUserloginName();
		
		//Check if User Performed Approve or Reject Action
		String strPerformedAction = getAction(request); 
		if( strPerformedAction != null ){
			
			IExtendedPaginatedList cachedPage =  getPaginatedListFromSession(request,
					SessionConstants.SCH_RESULTS_TEACHER_QUESTION_LIST);
			restoreSearchCriteria(request, questionBean);
			String[] strIds = getCheckedIds(request);
			
			//check records are selected before performing Delete action
			if(strIds == null || strIds.length == 0){
				dispRecordSelection_ErrorMsg(request,strPerformedAction);
				request.setAttribute("action","display");
				questionBean.setPgSearchResults(cachedPage);
				return mapping.findForward(strActionFwd);
			}
			
			 if( strPerformedAction.equals("delete") ){
					//call DAO method to perform Reject action on strIds
					int deleteCount = service.deleteQuestionbyId(strIds);
					
					//get new total records count after record deletion
					int newTotalRecords = service.getQuestionsCountByUserID(strUserEmailId,
							questionBean.getSch_classType(),questionBean.getSch_subject(),
							questionBean.getSch_questionStatus(),questionBean.getSch_topic(),
							questionBean.getSch_fromDate(),questionBean.getSch_toDate(),questionBean.getSch_isGraphics());
					
					//get new paginated page after records are deleted
					cachedPage =  getNewPage_AfterDelete(newTotalRecords, cachedPage);
					
					// Get new updated search results for current page 
					getPaginatedSearchResults(request,questionBean,cachedPage);
					//display Message
					dispRecordDeletionMessage(request,deleteCount);
			 }else{
				 questionBean.setPgSearchResults(cachedPage);
			 }
		
		}else{
			// Get Paginated partial list 
			getPaginatedSearchResults(request,questionBean,null);
		}		
		request.setAttribute("action","display");		
		return mapping.findForward(strActionFwd);
	}
	
	/**
	 * Gets partial list of records for current page
	 * and sets the partial search results to FormBean
	 * Store current search results in session.
	 * @param request - request containing details of currently navigated page
	 * @param questionBean - FormBean to which search results are set
	 */
	private void getPaginatedSearchResults(HttpServletRequest request, QuestionListActionForm questionBean,
				IExtendedPaginatedList thePage) throws BaseAppException{
		thePage = thePage == null ? getPaginatedListFromRequest(request) : thePage ;
			
		//call DAO service
		QuestionBankService service = new QuestionBankService();
		int strUserId = ((UserSessionInfo)request.getSession(false).getAttribute(SessionConstants.user_info)).getUserId();
		
		int noOfRecords = service.getQuestionsCountByUserID(String.valueOf(strUserId), questionBean.getSch_classType(),
				questionBean.getSch_subject(), questionBean.getSch_questionStatus(),
				questionBean.getSch_topic(),questionBean.getSch_fromDate(),questionBean.getSch_toDate(),questionBean.getSch_isGraphics());
		
		//int frmRecNo = thePage.getFirstRecordIndex()+1;
		//int toRecNo = frmRecNo + thePage.getPageSize()-1;
		
		int frmRecNo = thePage.getFirstRecordIndex();
		int totalRecordsCount = frmRecNo + thePage.getPageSize();
		
		thePage.setTotalNumberOfRows(noOfRecords);
		
		
		ArrayList searchResults = service.getQuestionsByUserID(String.valueOf(strUserId), questionBean.getSch_classType(),
				questionBean.getSch_subject(), questionBean.getSch_questionStatus(),
				questionBean.getSch_topic(),questionBean.getSch_fromDate(),questionBean.getSch_toDate(),
				questionBean.getSch_isGraphics(),frmRecNo,totalRecordsCount);
		
		thePage.setList(searchResults);
		questionBean.setPgSearchResults(thePage);
		
		//Take backup of Search Criteria 
		TeacherListPageSchCriteria searchCriteria = new TeacherListPageSchCriteria();  
		Utilities.copyProperties(searchCriteria , questionBean);
		
		//set Search Criteria to page results
		((PaginatedListImpl)thePage).setSearchCriteria(searchCriteria);
		
		//cache search results in session
		cacheSearchResult(request,SessionConstants.SCH_RESULTS_TEACHER_QUESTION_LIST, thePage);		
	}
	
	//@Override - Untill login Session tracking is suspended 
	protected boolean byPassSessionTracking_BeforeLogin() {		
		return true;
	}
	
	private void restoreSearchCriteria(HttpServletRequest request,QuestionListActionForm questionBean){
		//Reset Search Criteria stored in session first then get the paginated search results
		TeacherListPageSchCriteria searchCriteria = (TeacherListPageSchCriteria)getCahedSearchCriteria(request, SessionConstants.SCH_RESULTS_TEACHER_QUESTION_LIST);
		if(searchCriteria != null)
			Utilities.copyProperties(questionBean, searchCriteria);
	}
	
	public class TeacherListPageSchCriteria{
		
		private String sch_topic = "";
	    private String sch_questionStatus = "";
	    private String sch_subject = "";
	    private String sch_classType = "";
	    private String sch_fromDate = "";
	    private String sch_toDate = "";
	    private Integer sch_isGraphics = null;
	    
		
		public Integer getSch_isGraphics() {
			return sch_isGraphics;
		}
		public void setSch_isGraphics(Integer sch_isGraphics) {
			this.sch_isGraphics = sch_isGraphics;
		}
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















