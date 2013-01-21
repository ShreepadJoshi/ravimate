package com.education.actions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.nt.NTEventLogAppender;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.expframework.data.ExceptionDisplayDTO;
import org.expframework.exceptions.BaseAppException;

import com.education.Session.SessionConstants;
import com.education.dao.OracleTopicSubTopicDAO;
import com.education.displaytag.IExtendedPaginatedList;
import com.education.displaytag.PaginatedListImpl;
import com.education.formbeans.ManageTopicsActionForm;
import com.education.formbeans.NewUserActionForm;
import com.education.services.QuestionBankService;
import com.education.services.TopicSubTopicService;
import com.education.services.UserRegistrationService;
import com.education.transferobj.TopicSubTopicTO;
import com.education.util.EducationConstant;
import com.education.util.NewUserListSearchCriteria;
import com.education.util.Utilities;

public class ManageTopicsAction extends EducationBaseAction {

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws BaseAppException {		
		
		ManageTopicsActionForm manageTopicBean = (ManageTopicsActionForm) form;
		
		//condition to not display search results by default
		if(isNavigationDone(request)){
			getPaginatedSearchResults(request,manageTopicBean,null);
		}
		
		//get Subject drop down value
		manageTopicBean.setSubjectOptions(new Utilities().getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE));
		manageTopicBean.setClassOptions(new Utilities().getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE));
		
		return mapping.findForward("displayPage");
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("actionSuccess","");
		expDisplayDetails.set(expDTO);
		
		ManageTopicsActionForm manageTopicBean = (ManageTopicsActionForm) form;
		TopicSubTopicService service = new TopicSubTopicService();
		String strActionForward = "actionSuccess";
		
		String strPerformedAction = getAction(request);//Check if User Performed Action 
		if( strPerformedAction != null ){			
			IExtendedPaginatedList cachedPage =  getPaginatedListFromSession(request,
					SessionConstants.SCH_RESULTS_MANAGE_TOPICS_LIST);
			String[] strIds = getCheckedIds(request);
			
			//If Add button is clicked first
			if(cachedPage == null){
				cachedPage = getPaginatedListFromRequest(request);
			}else{
				restoreSearchCriteria(request,cachedPage);
			}
			
			//For add/update and delete action records needs to be selected check
			if( (strPerformedAction.equalsIgnoreCase("addorupdate") ||
					strPerformedAction.equalsIgnoreCase("delete")) 
					&& (strIds == null || strIds.length == 0) ){
				dispRecordSelection_ErrorMsg(request, strPerformedAction);
				request.setAttribute("action","display");
				manageTopicBean.setPgSearchResults(cachedPage);
				return mapping.findForward(strActionForward);
			}
			
			if( strPerformedAction.equalsIgnoreCase("add")){
				//User should Complusory select class and subject to get search result 
				if(!isSearchCriteria_Selection_done(request, manageTopicBean)){
					dispMessage(request, "", new ActionMessage("error.select.classSubject.searchCriteria"));
					manageTopicBean.setPgSearchResults(cachedPage);
					//get Subject drop down value
					manageTopicBean.setSubjectOptions(new Utilities().getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE));
					manageTopicBean.setClassOptions(new Utilities().getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE));
					return mapping.findForward("displayPage");
				}
				addNewRecord(request,manageTopicBean,cachedPage);
				strActionForward = "displayPage";
				//disp message
				dispMessage(request, "", new ActionMessage("Added 1 New Row",false));
				
			}else if( strPerformedAction.equalsIgnoreCase("addorupdate")){
				saveRecords(request,manageTopicBean,strIds);
				//get New paginated results
				getPaginatedSearchResults(request, manageTopicBean, cachedPage);
				
			}else if( strPerformedAction.equalsIgnoreCase("delete")){				
				boolean recordsDeleted = deleteRecords(request,cachedPage, manageTopicBean, strIds);
				if(recordsDeleted){
					//get new total no.of record count after delete
					int nnewTotalRecordCount = service.getManageTopicSubTopic_RecordCount(manageTopicBean.getSch_subject(),manageTopicBean.getSch_classType());				
					//get new paginated page after delete
					getNewPage_AfterDelete(nnewTotalRecordCount, cachedPage);
				}
				//fetch new records from Db
				getPaginatedSearchResults(request, manageTopicBean, cachedPage);
//					//fetch new records from Db
//					getPaginatedSearchResults(request, manageTopicBean, cachedPage);
//				}else{					
//					getPaginatedSearchResults(request, manageTopicBean, cachedPage);
//				}
			}else if( strPerformedAction.equalsIgnoreCase("close")){
				strActionForward="close";
				session_RemoveAttribute(request,SessionConstants.SCH_RESULTS_MANAGE_TOPICS_LIST);
			}
		}else{
//			if( !Utilities.isNullOrBlank(manageTopicBean.getSch_subject()) &&
//					!Utilities.isNullOrBlank(manageTopicBean.getSch_classType()) ){
//				getPaginatedSearchResults(request,manageTopicBean,null);
//			}
			getPaginatedSearchResults(request,manageTopicBean,null);
		}
		
		request.setAttribute("action","display");
		return mapping.findForward(strActionForward);
	}
	
	/**
	 * Gets partial list of records for current page
	 * and sets the partial search results to FormBean
	 * Store current search results in session.
	 * @param request - request containing details of currently navigated page
	 * @param questionBean - FormBean to which search results are set
	 */
	private void getPaginatedSearchResults(HttpServletRequest request, ManageTopicsActionForm manageTopicBean,
				IExtendedPaginatedList thePage)throws BaseAppException{
		
		//User should Complusory select class and subject to get search result 
		if(!isSearchCriteria_Selection_done(request, manageTopicBean)){
			dispMessage(request,"",new ActionMessage("error.select.classSubject.searchCriteria"));
			return;
		}
		
		thePage = thePage == null ? getPaginatedListFromRequest(request) : thePage ;
		TopicSubTopicService service = new TopicSubTopicService();
			
		int noOfRecords = service.getManageTopicSubTopic_RecordCount(
				manageTopicBean.getSch_subject(),manageTopicBean.getSch_classType());
		
		int frmRecNo = thePage.getFirstRecordIndex();
		int toRecNo = frmRecNo + thePage.getPageSize();
		
		thePage.setTotalNumberOfRows(noOfRecords);
		ArrayList searchResults = service.getManageTopicSubTopic_List(manageTopicBean.getSch_subject(),manageTopicBean.getSch_classType(),frmRecNo,toRecNo);
		
		thePage.setList(searchResults);
		manageTopicBean.setPgSearchResults(thePage);
		//Backup search Criteria
		ManageTopicsSearchCriteria searchCriteria = new ManageTopicsSearchCriteria();
		Utilities.copyProperties(searchCriteria ,manageTopicBean);
		
		//set Search Criteria to page results
		((PaginatedListImpl)thePage).setSearchCriteria(searchCriteria);
		
		//cache search results in session
		cacheSearchResult(request,SessionConstants.SCH_RESULTS_MANAGE_TOPICS_LIST, thePage);
	}
	
	/**
	 * checks weather class/subject search criteria is secleted or not 
	 * if not queues error message to display
	 * @param request - HttpServletRequest
	 * @param manageTopicBean - Bean having class and subject details
	 */
	private boolean isSearchCriteria_Selection_done(HttpServletRequest request, ManageTopicsActionForm manageTopicBean){
		//User should Complusory select class and subject to get search result 
		if( Utilities.isNullOrBlank(manageTopicBean.getSch_subject()) &&
				Utilities.isNullOrBlank(manageTopicBean.getSch_classType()) ){			
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Prepares new data for Add/update operation by construting new ArrayList of TO's 
	 * 
	 * @param idList - checked Id list
	 * @param topicList - list of topics
	 * @param subTopicList - list of subtopics
	 * @param subjectId - subjectId to which topic and subtopic are to be associated
	 */
	private ArrayList prepareDataForSaveOrDelete(String[] fullIdList,String[] selectIdList,
			String[] topicList, String[] subTopicList,String[] topicIdList,String[] subTopicIdList){
		ArrayList listData = new ArrayList();
		boolean flag = false;
		for(int i=0;i<fullIdList.length;i++){			
			for(int j=0;j<selectIdList.length;j++){
				if(fullIdList[i].equals(selectIdList[j]))
					flag = true;
			}
			if(flag){
				TopicSubTopicTO objTo = new TopicSubTopicTO();
				//make sure both topic and subtopic values are entered
				if(!"".equals( topicList[i] ) && !"".equals( subTopicList[i] )){
					objTo.setSubTopicValue(subTopicList[i]);
					objTo.setTopicvalue(topicList[i]);
					objTo.setSubjectTopicId(Integer.parseInt(fullIdList[i]));
					objTo.setTopicId( Integer.parseInt(topicIdList[i]) );
					objTo.setSubTopicId( Integer.parseInt(subTopicIdList[i]) );
					listData.add(objTo);
				}
			}
			flag = false;
		}
		return listData;
	}

		
	/**
	 * Divides the data to be updated and inserted 
	 * @param listData - list of data containing add/update data
	 * @param updateOrDbDelteListRef - Reference of list to be updated with update/DBdelete data
	 * @param insertOrsimpleDelteListRef - Reference of list to be updated with insert/simpleDelete data
	 */
	private void figureAddUpdateDeleteData(ArrayList listData, ArrayList updateOrDbDelteListRef,ArrayList insertOrsimpleDelteListRef,int subjectId,int classId){		
		
		if(listData == null)
			return;
		Iterator itr = listData.iterator();		
		while(itr.hasNext()){
			TopicSubTopicTO objTo = (TopicSubTopicTO)itr.next();
			//New Row has negative subjectId
			if(objTo.getSubjectTopicId() > 0 ){
				updateOrDbDelteListRef.add(objTo);
			}else{
				objTo.setSubjectId(subjectId);
				objTo.setClassid(classId);
				insertOrsimpleDelteListRef.add(objTo);
			}
		}
	}

	/**
	 * Called to add new record
	 */
	private void addNewRecord(HttpServletRequest request,
			ManageTopicsActionForm manageTopicBean,
			IExtendedPaginatedList cachedPage) throws BaseAppException{
		
		//get new Record index
		int index = manageTopicBean.getNewRecordIndex()==0  ? -1 :( manageTopicBean.getNewRecordIndex() + -1);
		//Get records ArrayList
		ArrayList list = cachedPage.getList() == null ? new ArrayList() : (ArrayList)cachedPage.getList() ;
		//Prepare new data
		TopicSubTopicTO objTo = new TopicSubTopicTO();
		objTo.setSubjectTopicId(index); //negavite Ids for new records
		objTo.setTopicId(index);
		objTo.setTopicvalue("");
		objTo.setSubTopicId(index);		
		objTo.setSubTopicValue("");
		list.add(objTo);
		//updated cached page
		cachedPage.setList(list);
		//updated bean with new search results
		manageTopicBean.setPgSearchResults(cachedPage);
		manageTopicBean.setNewRecordIndex(index);
		//cache new set of records list
		cacheSearchResult(request,SessionConstants.SCH_RESULTS_MANAGE_TOPICS_LIST,cachedPage);				
		//get Subject drop down value
		manageTopicBean.setSubjectOptions(new Utilities().getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE));
		manageTopicBean.setClassOptions(new Utilities().getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE));
	}

	/**
	 * Called to save records
	 * @param request
	 * @param manageTopicBean
	 * @param strIds
	 */
	private void saveRecords(HttpServletRequest request,
			ManageTopicsActionForm manageTopicBean,String[] strIds )throws BaseAppException{		
		//prepare data to save
		int subjectId = Integer.parseInt(manageTopicBean.getSch_subject());
		int classId = Integer.parseInt(manageTopicBean.getSch_classType());
		String[] topiclist = manageTopicBean.getTopicList();
		String[] subTopicList = manageTopicBean.getSubTopicList();
		String[] topicIdList = manageTopicBean.getTopicIds();
		String[] subTopicIdList = manageTopicBean.getSubTopicIds();
		
		ArrayList insertList = new ArrayList();
		ArrayList updateList = new ArrayList();
		TopicSubTopicService service = new TopicSubTopicService();
		
		ArrayList data_SavingList =  prepareDataForSaveOrDelete(request.getParameterValues("SubjectTopicIdList"),
				strIds,topiclist,subTopicList,topicIdList,subTopicIdList);
		
		//figure data to update/Insert				
		figureAddUpdateDeleteData(data_SavingList, updateList, insertList,subjectId,classId);
		
		//Do add/update
		if(insertList.size()>0){
			//do insert data
			int insertCount = service.insertTopicSubTopic(insertList);
			//disp Message
			dispRecordAdditionMessage(request,insertCount);
		}
		if(updateList.size()>0){
			//do update data
			int recUpdateCount =  service.updateTopicSubTopic_BySubjectTopicId(updateList);
			//disp message
			dispRecordUpdationMessage(request,recUpdateCount);
			
		}
	}

	/**
	 * called to delete Records
	 * @param request
	 * @param manageTopicBean
	 * @param strIds
	 */
	private boolean deleteRecords(HttpServletRequest request,IExtendedPaginatedList cachedPage,
			ManageTopicsActionForm manageTopicBean,String[] strIds )throws BaseAppException{
		boolean needsDbDelete = false;
		String[] dbDeleteArray = new String[strIds.length];
		String[] cacheDeleteArray = new String[strIds.length];
		int j=0;
		TopicSubTopicService service = new TopicSubTopicService();
		for(int i=0;i<strIds.length;i++){
			if(Integer.parseInt(strIds[i])>0 ){
				needsDbDelete = true;
				dbDeleteArray[j++] = strIds[i];						
			}else{
				cacheDeleteArray[j++] = strIds[i];
			}
		}
		if(needsDbDelete){				
			//int nSubjectId = Integer.parseInt(manageTopicBean.getSch_subject());
			//Delete the selected records from DB
			int recDeleteCount = service.deleteTopicSubTopic_BySubjectTopicId(dbDeleteArray);
			//disp Message
			dispRecordDeletionMessage(request, recDeleteCount);
		}
		if(needsDbDelete)
			return true;
		else
			return false;
	}
	
	private void restoreSearchCriteria(HttpServletRequest request, IExtendedPaginatedList cachedPage){
		if(cachedPage== null)
			return;
		//set Search Criteria
		ManageTopicsSearchCriteria searchCriteria =(ManageTopicsSearchCriteria)getCahedSearchCriteria(request,
				SessionConstants.SCH_RESULTS_MANAGE_TOPICS_LIST);
		if(searchCriteria != null)
			((PaginatedListImpl)cachedPage).setSearchCriteria(searchCriteria);
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
	
	class ManageTopicsSearchCriteria{
		private String sch_subject = "";
		private String sch_classType = "";
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
	}
	
}











