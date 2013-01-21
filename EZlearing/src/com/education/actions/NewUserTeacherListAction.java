package com.education.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.expframework.data.ExceptionDisplayDTO;
import org.expframework.exceptions.BaseAppException;

import com.education.Session.SessionConstants;
import com.education.displaytag.IExtendedPaginatedList;
import com.education.displaytag.PaginatedListImpl;
import com.education.formbeans.NewUserActionForm;
import com.education.services.UserRegistrationService;
import com.education.util.EducationConstant;
import com.education.util.NewUserListSearchCriteria;
import com.education.util.Utilities;


public class NewUserTeacherListAction extends EducationBaseAction{

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws BaseAppException{		
		NewUserActionForm newUserBean = (NewUserActionForm)form;
		
		//condition to not display search results by default
		if(isNavigationDone(request)){
			getPaginatedSearchResults(request,newUserBean,null);
		}	
		
		//For back navigation
		if(isBackNavigation(request)){
			IExtendedPaginatedList cachedPage =  getPaginatedListFromSession(request,
					SessionConstants.SCH_RESULTS_NEWUSER_TEACHER_LIST);
			restoreSearchCriteria(request, newUserBean, cachedPage);
		}
		newUserBean.setRegStatusOptions( new Utilities().
				getDropdownValue(EducationConstant.USER_REGISTRATION_STATUS_DROPDOWN_VALUE));
		return mapping.findForward("displayPage");
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewUserActionForm newUserBean = (NewUserActionForm)form;
		UserRegistrationService service = new UserRegistrationService();
		String strActionFwd = "actionSuccess";
		
		//Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("actionSuccess","");
		expDisplayDetails.set(expDTO);
		
		//Check if User Performed Approve or Reject Action
		String strPerformedAction = getAction(request); 
		if( strPerformedAction != null ){			
			IExtendedPaginatedList cachedPage =  getPaginatedListFromSession(request,
					SessionConstants.SCH_RESULTS_NEWUSER_TEACHER_LIST);
			restoreSearchCriteria(request, newUserBean, cachedPage);			
			String[] strIds = getCheckedIds(request);
			
			//check records are selected before performing approve/disableLogin/MarkAsReviewer action
			if(strIds == null || strIds.length == 0){
				dispRecordSelection_ErrorMsg(request,strPerformedAction);
				request.setAttribute("action","display");
				newUserBean.setPgSearchResults(cachedPage);
				return mapping.findForward(strActionFwd);
			}
			
			if( strPerformedAction.equalsIgnoreCase("approvelogin") && strIds != null ){
				//call DAO method to Approve Login action on strIds
				int approveCount = service.approveUser_Logins(strIds);
				// Get new updated search results for current page 
				getPaginatedSearchResults(request,newUserBean,cachedPage);
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
			}
		}else{
			// Get Paginated partial list 
			getPaginatedSearchResults(request,newUserBean,null);			
		}
		request.setAttribute("action","display");
		return mapping.findForward(strActionFwd);
	}
	
    /** Gets partial list of records for current page
	 * and sets the partial search results to FormBean
	 * Store current search results in session.
	 * @param request - request containing details of currently navigated page
	 * @param questionBean - FormBean to which search results are set
	 */
	private void getPaginatedSearchResults(HttpServletRequest request, NewUserActionForm newUserBean,
				IExtendedPaginatedList thePage)throws BaseAppException{
		UserRegistrationService service = new UserRegistrationService();
		thePage = thePage == null ? getPaginatedListFromRequest(request) : thePage ;
		
		String strEmailId = newUserBean.getSch_EmailID();
		String strFirstName = newUserBean.getSch_FirstName();
		String strlastName = newUserBean.getSch_LastName();
		Integer regStatus = newUserBean.getSch_RegStatus().equals("") ? null : new Integer(Integer.parseInt(newUserBean.getSch_RegStatus()));
		int roleId = Integer.parseInt(EducationConstant.TEACHER_USER_ROLE);
		String frmDate = newUserBean.getSch_fromDate();
		String toDate = newUserBean.getSch_toDate();

		int totalNoOfRecords = service.getUsersCountByRoleId(roleId,strEmailId,strFirstName,
				strlastName,regStatus,frmDate,toDate);
		
		int frmRecNo = thePage.getFirstRecordIndex();
		int noOfRecords = thePage.getPageSize();
		thePage.setTotalNumberOfRows(totalNoOfRecords);

		ArrayList searchResults = service.getUsersByRoleId(roleId,strEmailId,strFirstName,strlastName,
				regStatus,frmDate,toDate,frmRecNo,noOfRecords);
		thePage.setList(searchResults);
		newUserBean.setPgSearchResults(thePage);
		//Take backup of Search criteria 
		 NewUserListSearchCriteria searchCriteria = new NewUserListSearchCriteria();
		 Utilities.copyProperties(searchCriteria,newUserBean);
		//store searchCriteria 
		((PaginatedListImpl)thePage).setSearchCriteria(searchCriteria);

		//cache search results in session
		cacheSearchResult(request,SessionConstants.SCH_RESULTS_NEWUSER_TEACHER_LIST, thePage);		
	}
	
	@Override 
    /**
     * Return true for user as Admin Role
     */
    protected boolean hasValidPermission(HttpServletRequest request) {    	
    	String strRole = getUserRoleFromSession(request);
    	if(strRole != null && strRole.equals(EducationConstant.TEACHER_USER_ROLE))
    		return true;
    	if(strRole==null || !strRole.equals(EducationConstant.ADMIN_USER_ROLE) )
    		return false;
    	else
    		return true;
    }

	private void restoreSearchCriteria(HttpServletRequest request,NewUserActionForm newUserBean, IExtendedPaginatedList cachedPage){
		//Restore search criteria
		NewUserListSearchCriteria searchCriteria = (NewUserListSearchCriteria)
					getCahedSearchCriteria(request,SessionConstants.SCH_RESULTS_NEWUSER_TEACHER_LIST);		
		if(searchCriteria != null){
			Utilities.copyProperties(newUserBean,searchCriteria);
			newUserBean.setPgSearchResults(cachedPage);
		}
	}
}
