package com.education.actions;

import java.io.IOException;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.displaytag.properties.SortOrderEnum;
import org.expframework.data.ExceptionDTO;
import org.expframework.data.ExceptionDisplayDTO;
import org.expframework.exceptionhandler.ExceptionHandlerFactory;
import org.expframework.exceptionhandler.ExceptionUtil;
import org.expframework.exceptionhandler.IExceptionHandler;
import org.expframework.exceptions.BaseAppException;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.transferobj.UserTO;
import com.education.displaytag.IExtendedPaginatedList;
import com.education.displaytag.PaginatedListImpl;
import com.education.exceptions.BussMasterDataMissingException;
import com.education.exceptions.DBDataOutOfRangeException;
import com.education.exceptions.DBInvalidDataInsertionException;
import com.education.util.EducationConstant;
import com.education.util.Utilities;
import com.education.services.SessionService;

public abstract class EducationBaseAction extends Action {

	protected final String SESSION_FAILURE_ACTION = "/sessionFaliure.do";
	protected final String AUTHORIZATION_FAILURE_PAGE = "/pages/authorizationFaliure.jsp";
	protected final String USER_RUNTIME_EXCEPTION_PAGE = "/pages/UserRuntimeExceptionPage.jsp";
	protected final String ADMIN_RUNTIME_EXCEPTION_PAGE = "/pages/adminRuntimeExceptionPage.jsp";
	protected final int SEARCH_RESULT_PAGE_SIZE = Integer.valueOf(Utilities.getApplicationProperty("pagination"));
	protected static ThreadLocal expDisplayDetails = new ThreadLocal();
		
		
	protected void resetErrorPage(String errorPage,ExceptionDisplayDTO expDTO){
		expDTO.setActionForwardName("addContent");
		expDisplayDetails.set(expDTO);
	}
	
	
	protected UserSessionInfo getUserInfoObject_FromSession(HttpServletRequest request){
		return (UserSessionInfo)request.getSession(false).getAttribute(SessionConstants.user_info);
	}
	
	/**
	 * Removes the attribute from session
	 * @param key - Attribute Key used to store in session
	 */
	protected void session_RemoveAttribute(HttpServletRequest request,  String key){
		request.getSession(false).removeAttribute(key);
	}
	
	/**
	 * Method overridden by Action classes to check permission
	 * to invoke particular action, By default the premission is true
	 * all user can access particular action.
	 * @return - boolean value
	 * true - is has valid permission to access action else false
	 */
	protected boolean hasValidPermission(HttpServletRequest request){
		return true;
	}
	
	protected String getUserRoleFromSession(HttpServletRequest request){
	 
		String strUserRole = null;		
		strUserRole =  ((UserSessionInfo)request.getSession(false).
				getAttribute(SessionConstants.user_info)).getRoleId();		
		return strUserRole;
	}
	
	/**
	 * method set paginated page to be displayed after records is 
	 * deleted. Gets new total records count after delete. Calculates
	 * total number of pages after record is deleted. If current page
	 * is greater than new total number of pages then reduces the current
	 * page count.  
	 * @param nNewTotalRecCount - New total no.of records after delete operation
	 * @param cachedPage - cached page to be modified for pagination
	 * @return
	 */
	protected IExtendedPaginatedList getNewPage_AfterDelete(int nNewTotalRecCount, IExtendedPaginatedList cachedPage){
		
		if(nNewTotalRecCount == 0)
			return cachedPage;
		
		int currentPageNo = cachedPage.getPageNumber();
		int newPageNo = currentPageNo;
		cachedPage.setTotalNumberOfRows(nNewTotalRecCount); //set new record count
		int noOfPages = ((PaginatedListImpl)cachedPage).getTotalPages(); 
		
		//get new current page with refresh records
		while(currentPageNo > noOfPages){
			currentPageNo --;
			newPageNo = currentPageNo;
		}
		((PaginatedListImpl)cachedPage).setPageNumber(newPageNo);		
		return cachedPage;
	}
	
	/**
	 * Handle used to check weather validate th
	 * @return
	 */
	protected boolean byPassSessionTracking_BeforeLogin(){
		return false;
	}
	
	/**
	 * Using HttpRequest method check if User has clicked 
	 * on any action buttons like Approve or Reject on form and 
	 * accordingly returns string else null value
	 * @return - String or null value
	 */
	protected String getAction(HttpServletRequest request){
		String strActionClicked = null;
		if(request.getParameter("action") != null )
			strActionClicked = (String)request.getParameter("action").toLowerCase();
		else if(request.getAttribute(SessionConstants.FORM_ACTION_PERFORMED) != null )
			strActionClicked = ((String)request.getAttribute(SessionConstants.FORM_ACTION_PERFORMED)).toLowerCase();
		return strActionClicked;
	}
	
	/**
	 * Retrives checked check box values from request 
	 * @param request - HttpServeletRequest
	 * @return - String Array containing ID list else null
	 */
	protected String[] getCheckedIds(HttpServletRequest request){
		
		String checkedIds[] = null;
		if(request.getParameterValues("checkbox_id") != null )
			checkedIds = request.getParameterValues("checkbox_id");
		else if(request.getAttribute(SessionConstants.GRID_CHECKED_IDS) != null)
			checkedIds = (String[])request.getAttribute(SessionConstants.GRID_CHECKED_IDS);
		return checkedIds;
	}

	/**
	 *  methods checks if user has clicked on any navigation links on
	 *  page e.g: page1,page2,.. and returns boolean value accordingly
	 * @param request - HttpServletRequest
	 * @return - boolean value
	 * true: on click of paginations links
	 *  
	 */
	protected boolean isNavigationDone(HttpServletRequest request){
		String pageParam = request.getParameter(IExtendedPaginatedList .IRequestParameters.PAGE);
		if (pageParam != null) 
			return true;
		else
			return false;
	}
	
		
	protected boolean isBackNavigation(HttpServletRequest request){
		String backNavigation = request.getParameter("back");
		if(backNavigation != null || request.getAttribute("back")!= null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * method gets cached search list from session.Prepares new instance of current
	 * navigated page
	 * @param request - 
	 * @param forList - cached searchList key name
	 * @return - IExtendedPaginatedList instance
	 * 
	 */
	protected IExtendedPaginatedList getPaginatedListFromSession(
			HttpServletRequest request,String forList) {
		IExtendedPaginatedList paginatedList = new PaginatedListImpl();
		IExtendedPaginatedList thePage =  new PaginatedListImpl();
		HttpSession session =  request.getSession(false);
		
		//For Teacher Question List page 
		if(forList.equals(SessionConstants.SCH_RESULTS_TEACHER_QUESTION_LIST)){
			thePage = (IExtendedPaginatedList) session.getAttribute(SessionConstants.SCH_RESULTS_TEACHER_QUESTION_LIST);
		}
		//For Reviewer Question List page 
		if(forList.equals(SessionConstants.SCH_RESULTS_REVIEWER_QUESTION_LIST)){
			thePage = (IExtendedPaginatedList) session.getAttribute(SessionConstants.SCH_RESULTS_REVIEWER_QUESTION_LIST);
		}
		if(forList.equals(SessionConstants.SCH_RESULTS_NEWUSER_AGENT_LIST)){
			thePage = (IExtendedPaginatedList) session.getAttribute(SessionConstants.SCH_RESULTS_NEWUSER_AGENT_LIST);
		}
		if(forList.equals(SessionConstants.SCH_RESULTS_NEWUSER_TEACHER_LIST)){
			thePage = (IExtendedPaginatedList) session.getAttribute(SessionConstants.SCH_RESULTS_NEWUSER_TEACHER_LIST);
		}
		if(forList.equals(SessionConstants.SCH_RESULTS_NEWUSER_AFFILIATE_LIST)){
			thePage = (IExtendedPaginatedList) session.getAttribute(SessionConstants.SCH_RESULTS_NEWUSER_AFFILIATE_LIST);
		}
		if(forList.equals(SessionConstants.SCH_RESULTS_ADMIN_QUESTION_LIST)){
			thePage = (IExtendedPaginatedList) session.getAttribute(SessionConstants.SCH_RESULTS_ADMIN_QUESTION_LIST);
		}
		if(forList.equals(SessionConstants.SCH_RESULTS_NEWUSER_REVIEWER_LIST)){
			thePage = (IExtendedPaginatedList) session.getAttribute(SessionConstants.SCH_RESULTS_NEWUSER_REVIEWER_LIST);
		}
		if(forList.equals(SessionConstants.SCH_RESULTS_MANAGE_TOPICS_LIST)){
			thePage = (IExtendedPaginatedList) session.getAttribute(SessionConstants.SCH_RESULTS_MANAGE_TOPICS_LIST);
		}
		if(forList.equals(SessionConstants.SCH_RESULTS_CONTENTUPLOAD_LIST)){
			thePage = (IExtendedPaginatedList) session.getAttribute(SessionConstants.SCH_RESULTS_CONTENTUPLOAD_LIST);
		}
		return thePage;
	}
	
	/**
	 * gets cached search results from session for currently navigated page
	 * @param req - HttpServletRequest object
	 * @param forList - key name of search result to find from session
	 * @return ArrayList - cached search results from session
	 */
	protected ArrayList getCahedSearchResults(HttpServletRequest req, String forList){
		ArrayList cachedResults = null;
		IExtendedPaginatedList thePage = getPaginatedListFromSession(req, forList);
		cachedResults = (ArrayList) thePage.getList();
		return cachedResults;
	}
	
	/**
	 * Gets cached search criteria for set for page
	 * @param req - HttpServletRequest object
	 * @param forList - key name of search result to find from session
	 * @return Object - SearchCriteria object defined in Action class 
	 */
	protected Object getCahedSearchCriteria(HttpServletRequest req, String forList){		
		IExtendedPaginatedList thePage = getPaginatedListFromSession(req, forList);
		if(thePage != null){
			return ((PaginatedListImpl)thePage).getSearchCriteria();
		}else{
			return null;
		}
		
	}
	
	/**
	 * caches the search result to session
	 * @param request - HttpServletRequest object
	 * @param withKey - key with which search results will be stored in session
	 */
	protected void cacheSearchResult(HttpServletRequest request,String withKey,Object data){
		request.getSession(false).setAttribute(withKey,data);
	}
	
	/**
	 *  Methods creates new instance of paginated list object 
	 *  which will be used by displaytag on Jsp page 
	 * @param request - extracts details of pagination from request 
	 * @return new instance of PaginatedListImpl class
	 */
	protected IExtendedPaginatedList getPaginatedListFromRequest(
			HttpServletRequest request) {
		IExtendedPaginatedList paginatedList = new PaginatedListImpl();
		String sortCriterion = request
				.getParameter(IExtendedPaginatedList.IRequestParameters.SORT);
		paginatedList
				.setSortDirection(IExtendedPaginatedList.IRequestParameters.DESC
						.equals(request
								.getParameter(IExtendedPaginatedList.IRequestParameters.DIRECTION)) ? SortOrderEnum.DESCENDING
						: SortOrderEnum.ASCENDING);
		paginatedList.setSortCriterion(sortCriterion);
		int pageSize = SEARCH_RESULT_PAGE_SIZE; // Rows per page
		paginatedList.setPageSize(pageSize);
		String thePage = request
				.getParameter(IExtendedPaginatedList.IRequestParameters.PAGE);
		if (thePage != null) {
			int index = paginatedList == null ? 0
					: Integer.parseInt(thePage) - 1;
			paginatedList.setIndex(index);
		} else {
			paginatedList.setIndex(0);
		}
		return paginatedList;
	}
	
	/**
	 * Prepared new ActionForward at runtime
	 * redirecting to EducationBaseAction.SESSION_FAILURE_PAGE page
	 * @return new ActionForward object
	 */
	protected ActionForward getSession_Failure_ActionForward() {
		ActionForward fwd = new ActionForward();
		fwd.setName("SESSION_FAILURE");
		fwd.setPath(SESSION_FAILURE_ACTION);
		ActionMapping mapping = new ActionMapping();
		
		return fwd;
	}
	
	/**
	 * Prepared new ActionForward at runtime
	 * redirecting to EducationBaseAction.AUTHORIZATION_FAILURE_PAGE page
	 * @return new ActionForward object
	 */
	protected ActionForward getAuthorization_Failure_ActionForward() {
		ActionForward fwd = new ActionForward();
		fwd.setName("AUTHORIZATION_FAILURE");
		fwd.setPath(AUTHORIZATION_FAILURE_PAGE);
		return fwd;
	}
	
	/**
	 * Prepared new ActionForward at runtime
	 * redirects based on following conditions -
	 * If Session Exists LoggedIn user is Admin :- ADMIN_RUNTIME_EXCEPTION_PAGE
	 * If Session Exists LoggedIn other than Admin :- USER_RUNTIME_EXCEPTION_PAGE
	 * If Session does not Exists :- USER_RUNTIME_EXCEPTION_PAGE  
	 * @return new ActionForward object
	 */
	protected ActionForward getRuntimeException_ActionForward(HttpServletRequest request) {
		ActionForward fwd = new ActionForward();
		if(sessionExists(request) &&
				getUserRoleFromSession(request).equals(EducationConstant.ADMIN_USER_ROLE))
			fwd.setPath(ADMIN_RUNTIME_EXCEPTION_PAGE);
		else if(sessionExists(request) &&
				!getUserRoleFromSession(request).equals(EducationConstant.ADMIN_USER_ROLE))
			fwd.setPath(USER_RUNTIME_EXCEPTION_PAGE);
		else
			fwd.setPath(USER_RUNTIME_EXCEPTION_PAGE);
		return fwd;
	}
	
	
	/**
	 * Checks session Object is not NULL and UserObj exists in session
	 * @return: true - if valid, false- if invalid
	 */
	protected boolean sessionExists(HttpServletRequest request){
		HttpSession session =  request.getSession(false);		 
		boolean result = (session != null && 
						session.getAttribute(SessionConstants.user_info) != null ? true : false);
		return result;
	}
	
	/**
	 *  Checks:
	 *   1. For anonymous session - by firing query on db
	 *   2. Session idle time if so sets session time out	    
	 * @param request
	 * @return true - on satisfying above condition else false 
	 */
	protected boolean isSessionValid(HttpServletRequest request) throws BaseAppException{
		boolean result = false;
		HttpSession session = request.getSession(false);
		
		//Get UserSessionInfo
		UserSessionInfo userSessionObj = (UserSessionInfo)session.getAttribute(SessionConstants.user_info);
		if(userSessionObj == null){
			return false;
		}
		result = new SessionService().isValidSession(String.valueOf(userSessionObj.getUserId()),session.getId());
		return result;
	}
	/*
	 * This method will add an error without destroying the previous contents
	 */
	protected void addError(HttpServletRequest request, String messageType, ActionMessage actionMessage) {
		ActionMessages actionErrors;
		actionErrors = getErrors(request);
		actionErrors.add(messageType, actionMessage);
		saveErrors(request, actionErrors);
	}
	
	
	/**
	 * Stores record updation message to actionMessage property of bean 
	 * @param requet - HttpServlet Request
	 * @param nRecUpdateCount - No of record update count
	 */
	protected void dispRecordUpdationMessage(HttpServletRequest request,int nRecUpdateCount){
		addError(request, "", new ActionMessage("message.record.updation",nRecUpdateCount));
	}
	
	/**
	 * Stores record updation message to actionMessage property of bean 
	 * @param requet - HttpServlet Request
	 * @param nRecUpdateCount - No of record update count
	 */
	protected void dispRecordAdditionMessage(HttpServletRequest request,int nRecUpdateCount){
		addError(request, "", new ActionMessage("message.record.added",nRecUpdateCount));
	}
	/**
	 * 
	 * @param requet - HttpServlet Request
	 * @param totalNoOfRecords - No of record in List
	 */
	protected void dispRefineSearchMessage(HttpServletRequest request,int totalNoOfRecords){
		addError(request, "", new ActionMessage("message.refine.search",totalNoOfRecords));
	}
	
	/**
	 * Stores record updation message to actionMessage property of bean 
	 * @param requet - HttpServlet Request
	 * @param nRecUpdateCount - No of record update count
	 */
	protected void dispRecordDeletionMessage(HttpServletRequest request,int nRecUpdateCount){
		addError(request, "", new ActionMessage("message.record.deleted",nRecUpdateCount));
	}
	
	protected void dispRecordSelection_ErrorMsg(HttpServletRequest request,String operationType){
		addError(request,"", new ActionMessage("error.select.record",operationType) );
	}
	
	protected void dispMessage(HttpServletRequest request,String messageType, ActionMessage message){
//		addError(request,messageType, message );
	}
	
	/*
	 * This method will add an Message without destroying the previous contents
	 */
	protected void addMessage(HttpServletRequest request, String messageType, ActionMessage actionMessage) {
		ActionMessages actionMessages;
		actionMessages = getMessages(request);
		actionMessages.add(messageType, actionMessage);
		saveMessages(request, actionMessages);
	}

	protected ActionForward processReturnEvent(ActionMapping mapping, String eventName) {
		ActionForward retVal;
		retVal = mapping.findForward(eventName);
		if (retVal == null) {
			throw new IllegalStateException("Mapping not Found.  eventName:=" + eventName);
		} else {
			return retVal;
		}
	}

	protected ActionForward writeServletResponse(HttpServletResponse response, String responseString) throws IOException {
		ActionForward retVal = null;
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(responseString);
		return retVal;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm aForm, 
			HttpServletRequest request, HttpServletResponse response) {		

		ActionForward actionForward = null;
		String userLoginName = null;
		// **************************************************************
		// Now we navigate to the display or the perform part of the action
		// This is done wholly on the basis that a get is a read only activity
		// And a POST is used for submitting form data ( write activity )
		// **************************************************************
		
		
		
		try{
			
			
			userLoginName = getUserInfoObject_FromSession(request) != null ? getUserInfoObject_FromSession(request).getUserloginName() : ""; 
			//Session Tracking code - Session handling starts once user logs in
			//Else he will be able to access default pages
			
	       if(!sessionExists(request) && !isSessionValid(request) && !byPassSessionTracking_BeforeLogin() ){        	
	        	return getSession_Failure_ActionForward();
	       }
	        
	        //validate Permission to access particular action
	        if(!hasValidPermission(request)){
	        	// return to page Invalid permission
	        	return getAuthorization_Failure_ActionForward();
	        }
			
			if (request.getMethod().equals("GET")) {
				actionForward = displayAction(mapping, aForm, request, response);
			} else if (request.getMethod().equals("POST")) {
				if ((request.getAttribute("action") != null && request.getAttribute("action").equals("display"))
					|| (request.getParameter("action") != null && request.getParameter("action").equals("display"))) {
					actionForward = displayAction(mapping, aForm, request, response);
				} else {
					actionForward = performAction(mapping, aForm, request, response);
				}
			} else {
				throw new IllegalStateException("The only two HTTP methods supported are GET and POST");
			}
		
		}catch(RuntimeException ex){
			ExceptionUtil.logException(this.getClass(), ex, userLoginName);
			expDisplayDetails.set(null); 
			String msg =ExceptionUtil.getDetailedMessage(ex);
			request.setAttribute("stackTrace",msg);
			request.setAttribute("errorId",Utilities.getApplicationProperty("expError.runtimeException") );			
			return getRuntimeException_ActionForward(request);
			
		}catch (BaseAppException Ex) {
			//Exception Handler For Application
            ExceptionDisplayDTO expDTO = (ExceptionDisplayDTO) expDisplayDetails.get();
		    IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		    ExceptionDTO exDto = expHandler.handleException(expDTO.getContext(),userLoginName, Ex);		    
		    String msg = ExceptionUtil.getDetailedMessage(Ex);			
		    
		    String className = Ex.getClass().getName();
			className =  className.substring(className.lastIndexOf('.')+1);			
			if(className.equals("BussMasterDataMissingException") ||
					className.equals("DBDataSourceException") ||
					className.equals("DBInvalidDBDriverException") ||
					className.equals("BussTestCannotGeneratedException")){
				request.setAttribute("stackTrace",msg);
			    request.setAttribute("errorId",Utilities.getApplicationProperty(exDto.getMessageCode()));
				return getRuntimeException_ActionForward(request);
			}else{
				request.setAttribute("action","display");
				ActionErrors errors = getActionErrorForAppException(Ex,exDto);
				saveErrors(request,errors);
				return mapping.findForward(expDTO.getActionForwardName());
			}
		} catch (Exception ex) {
		    ExceptionUtil.logException(this.getClass(), ex,userLoginName);
		} finally {
		    expDisplayDetails.set(null);
		}
		return actionForward;
	}
	
	protected ActionErrors getActionErrorForAppException(BaseAppException ex,ExceptionDTO exDto){
		
		String className = ex.getClass().getName();
		ActionErrors errors = new ActionErrors();
		String columnName = null;
		String columnValue = null;
		className =  className.substring(className.lastIndexOf('.')+1);
		String errorId = "ErroID:"+Utilities.getApplicationProperty(exDto.getMessageCode());
		if(className.equals("DBInvalidDataInsertionException")){
			columnName = ((DBInvalidDataInsertionException)ex).getColumnName();
			columnValue = ((DBInvalidDataInsertionException)ex).getColumnValue();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError(exDto.getMessageCode(),
					new String[]{errorId,columnValue,columnName}));
		}else if(className.equals("DBDataOutOfRangeException")){
			columnName = ((DBDataOutOfRangeException)ex).getColumnName();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError(exDto.getMessageCode(),
					new String[]{errorId,columnName}));
		} else{
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError(exDto.getMessageCode()));
		}	    
//	    ActionErrors errors = new ActionErrors();
//	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError(exDto.getMessageCode()));
//	    saveErrors(request, errors);
	    return errors;
	}
	
	public abstract ActionForward displayAction(ActionMapping mapping, ActionForm aForm, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public abstract ActionForward performAction(ActionMapping mapping, ActionForm aForm, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
