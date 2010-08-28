package com.education.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.expframework.data.ExceptionDisplayDTO;
import org.expframework.exceptions.BaseAppException;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.actions.ManageTopicsAction.ManageTopicsSearchCriteria;
import com.education.displaytag.IExtendedPaginatedList;
import com.education.displaytag.PaginatedListImpl;
import com.education.exceptions.TAffiliateDaoException;
import com.education.formbeans.AffiliateForm;
import com.education.services.AffiliateService;
import com.education.transferobj.AffiliateTO;
import com.education.util.EducationConstant;
import com.education.util.Utilities;

public class AffiliateAction extends EducationBaseAction {

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		AffiliateForm affiliateBean = (AffiliateForm) form;		
		UserSessionInfo objUserInfo = (UserSessionInfo) request.getSession()
				.getAttribute(SessionConstants.user_info);
		affiliateBean.setEmailID(objUserInfo.getEmailId());
		affiliateBean.setPassword("********");
		affiliateBean.setRepassword("********");
	
		if(isBackNavigation(request)){
			IExtendedPaginatedList cachedPage =  getPaginatedListFromSession(request,
					SessionConstants.SCH_RESULTS_NEWUSER_TEACHER_LIST);
			restoreSearchCriteria(request, cachedPage);
		}
		return mapping.findForward("displayPage");
	}
	
	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AffiliateForm bean = (AffiliateForm)form;
		String strActionFwd = "actionSuccess";
		AffiliateService service = null;
		AffiliateTO affTo = null;
		
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("actionSuccess","");
		expDisplayDetails.set(expDTO);
		
		//Check if User Performed Approve or Reject Action
		String strPerformedAction = getAction(request); 
		if( strPerformedAction != null ){			
			IExtendedPaginatedList cachedPage =  getPaginatedListFromSession(request,
					SessionConstants.SCH_RESULTS_NEWUSER_AFFILIATE_LIST);
			//restoreSearchCriteria(request, newUserBean, cachedPage);			
			String[] strIds = getCheckedIds(request);
			
			
			if(cachedPage == null){
				cachedPage = getPaginatedListFromRequest(request);
			}
			
			//check records are selected before performing approve/disableLogin/MarkAsReviewer action
		/*	if(strIds == null || strIds.length == 0){
				dispRecordSelection_ErrorMsg(request,strPerformedAction);
				request.setAttribute("action","display");
				bean.setPgSearchResults(cachedPage);
				return mapping.findForward(strActionFwd);
			}*/
			
			if( (strPerformedAction.equalsIgnoreCase("addorupdate") ||
					strPerformedAction.equalsIgnoreCase("delete")) 
					&& (strIds == null || strIds.length == 0) ){
				dispRecordSelection_ErrorMsg(request, strPerformedAction);
				request.setAttribute("action","display");
				bean.setPgSearchResults(cachedPage);
				return mapping.findForward("displayPage");
			}
			
			if("add".equals(strPerformedAction))
			{
				
					//User should Complusory select class and subject to get search result 
					if(!isSearchCriteria_Selection_done(bean)){
						dispMessage(request, "", new ActionMessage("error.select.classSubject.searchCriteria"));
						bean.setPgSearchResults(cachedPage);
						return mapping.findForward("displayPage");
					}
					
					service = new AffiliateService();
					affTo = new AffiliateTO();
					Utilities.copyProperties(affTo, bean);
					affTo.setFirstName(bean.getFirstName());
					affTo.setLastName(bean.getLastName());
					affTo.setEmailID(bean.getEmailID());
					affTo.setLastName(bean.getLastName());
					affTo.setPassword(bean.getPassword());
					affTo.setSex(bean.getSex());
					affTo.setRegistrationDate(bean.getRegistrationDate());
					affTo.setAffiliateAddr(bean.getAddress());
					affTo.setAffAcctDtls(bean.getAffAcctDtls());
					affTo.setCountry(bean.getCountry());
					affTo.setState(bean.getState());
					
					affTo.setRoleId(Integer.parseInt(EducationConstant.AFFILIATE_USER_ROLE));
					affTo.setIsApproved(Integer.parseInt(EducationConstant.REG_STATUS_NOT_APPROVED));
					service.addAffiliate(affTo);
					strActionFwd = "displayPage";
					dispMessage(request, "", new ActionMessage("Added 1 New Row",false));
					return mapping.findForward("searchPage");
			}
			else if("search".equals(strPerformedAction))
			{
				getPaginatedSearchResults(request, bean, cachedPage);
				return mapping.findForward("searchPage");
			}
		}
		request.setAttribute("action","display");
		return mapping.findForward("success");
		/*	else if( strPerformedAction.equalsIgnoreCase("addorupdate")){
//				saveRecords(request,manageTopicBean,strIds);
//				//get New paginated results
//				getPaginatedSearchResults(request, manageTopicBean, cachedPage);
				
			}*/
		/*	if( strPerformedAction.equalsIgnoreCase("approvelogin") && strIds != null ){
				//call DAO method to Approve Login action on strIds
				int approveCount = service.approveUser_Logins(strIds);
				// Get new updated search results for current page 
				getPaginatedSearchResults(request,bean,cachedPage);
				//display message
				dispMessage(request,"",
						new ActionMessage("message.user.login", new String[]{""+approveCount,"Approved"} ));
				
			}else if( strPerformedAction.equalsIgnoreCase("disablelogin") && strIds != null ){
				//call DAO method to Disable Login action on strIds
				int disableCount = service.disableUser_Logins(strIds);
				// Get new updated search results for current page 
				getPaginatedSearchResults(request,newUserBean,cachedPage);
				//display message
				dispMessage(request,"",
						new ActionMessage("message.user.login", new String[]{""+disableCount,"Disabled"} ));
				
			}else if( strPerformedAction.equalsIgnoreCase("MarkAsReviewer") && strIds != null ){				
				//call DAO method to Mark teacher as reviewer on strIds
				int updateCount = service.makeTeacherReviewer(strIds);
				// Get new updated search results for current page 
				getPaginatedSearchResults(request,newUserBean,cachedPage);
				//display message
				dispMessage(request,"",
						new ActionMessage("message.user.login", new String[]{""+updateCount,"Marked as Reviewer(s)"} ));
			}*/
			//request.setAttribute("action","display");
			
		
			/*}		else{
			// Get Paginated partial list 
			getPaginatedSearchResults(request,newUserBean,null);			
		}*/
		
		
	}
	
	/**
	 * Gets partial list of records for current page
	 * and sets the partial search results to FormBean
	 * Store current search results in session.
	 * @param request - request containing details of currently navigated page
	 * @param questionBean - FormBean to which search results are set
	 * @throws TAffiliateDaoException 
	 */
	private void getPaginatedSearchResults(HttpServletRequest request, AffiliateForm bean,
				IExtendedPaginatedList thePage)throws BaseAppException, TAffiliateDaoException{
		
		thePage = thePage == null ? getPaginatedListFromRequest(request) : thePage ;
		AffiliateService service = new AffiliateService();
		AffiliateTO dto = new AffiliateTO();
		
		Utilities.copyToTransferObject(bean, dto);
		
		int noOfRecords = service.searchAffiliate(dto).size();
		
		int frmRecNo = thePage.getFirstRecordIndex();
		int toRecNo = frmRecNo + thePage.getPageSize();
		
		thePage.setTotalNumberOfRows(noOfRecords);
		List<AffiliateTO> searchResults = service.searchAffiliate(dto);
		
		thePage.setList(searchResults);
		bean.setPgSearchResults(thePage);
		//Backup search Criteria
		ManageAffiliatesSearchCriteria searchCriteria = new ManageAffiliatesSearchCriteria();
		Utilities.copyProperties(searchCriteria ,bean);
		
		//set Search Criteria to page results
		((PaginatedListImpl)thePage).setSearchCriteria(searchCriteria);
		
		//cache search results in session
		cacheSearchResult(request,SessionConstants.SCH_RESULTS_NEWUSER_AFFILIATE_LIST, thePage);
	}

	
	
	private boolean isSearchCriteria_Selection_done(AffiliateForm bean)
	{
		if(Utilities.isNullOrBlank(bean.getFirstName()) || Utilities.isNullOrBlank(bean.getLastName()) 
				|| Utilities.isNullOrBlank(bean.getAffiliateOrgName()))
			return false;
		return true;
	}
	
	private void addNewRecord(HttpServletRequest req, AffiliateForm bean, IExtendedPaginatedList cachedPage) throws BaseAppException
	{
		//get new Record index
		int index = bean.getNewRecordIndex()==0  ? -1 :( bean.getNewRecordIndex() + -1);
		//Get records ArrayList
		ArrayList list = cachedPage.getList() == null ? new ArrayList() : (ArrayList)cachedPage.getList() ;
		AffiliateTO affTo = new AffiliateTO();
		Utilities.copyToTransferObject(bean, affTo);
		affTo.setAffiliateId(String.valueOf(index));
		list.add(affTo);
		//updated cached page
		cachedPage.setList(list);
		//updated bean with new search results
		bean.setPgSearchResults(cachedPage);
		bean.setNewRecordIndex(index);
		//cache new set of records list
		cacheSearchResult(req,SessionConstants.SCH_RESULTS_MANAGE_TOPICS_LIST,cachedPage);	
	}
	
	
	/** (non-Javadoc)
	 * return false as login is needed for this action.
	 * 
	 * --Shripad
	 * 
	 * @see com.education.actions.EducationBaseAction#byPassSessionTracking_BeforeLogin()
	 */
	protected boolean byPassSessionTracking_BeforeLogin() {		
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

	
	class ManageAffiliatesSearchCriteria{
		private String firstName = null;
		private String lastName = null;
		private String orgName = null;
		
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getOrgName() {
			return orgName;
		}
		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}
	}
}
