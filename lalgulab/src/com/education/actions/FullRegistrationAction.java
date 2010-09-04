package com.education.actions;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.expframework.data.ExceptionDisplayDTO;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.formbeans.FullRegActionForm;
import com.education.services.UserRegistrationService;
import com.education.transferobj.RegistrationTo;
import com.education.transferobj.UserTO;
import com.education.util.EducationConstant;
import com.education.util.Utilities;

public class FullRegistrationAction extends EducationBaseAction{

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FullRegActionForm registrationBean = (FullRegActionForm)form;
		UserSessionInfo objUserInfo = (UserSessionInfo) request.getSession()
		.getAttribute(SessionConstants.user_info);
		if(objUserInfo != null) {
			registrationBean.setEmailID(objUserInfo.getEmailId());
			registrationBean.setPassword("********");
			registrationBean.setRepassword("********");
		}
		String mode = request.getParameter("mode");
		RegistrationTo regTOData = null;
		String actionPage = null;
		//If Page is opened in View mode
        if(mode != null ||  mode == "view"){
        	String id = (String)request.getParameter("id");
        	String roleType = (String)request.getParameter("roleType");
        	ArrayList searchResults =  getCachedRecordsForRole(request,roleType);
        	
        	Iterator itr = searchResults.iterator();
        	while(itr.hasNext()){
        		regTOData =  (RegistrationTo)itr.next();
        		if( regTOData.getUserId().equalsIgnoreCase(id) )
        			break;
        	}
        	populateRegBeanFrmTO(registrationBean,regTOData);
        	//registrationBean.setRoleType("Agent_DUMMYDATA");
        	registrationBean.setRoleName(Utilities.getRoleNameByID(roleType));        	
        	registrationBean.setNavFromPage(roleType);
        	registrationBean.setUserId(id);
        	return mapping.findForward("viewMode");
        }else if(mode != null && mode == "edit"){
        	String id = (String)request.getParameter("id");
        	String roleType = (String)request.getParameter("roleType");
        	ArrayList searchResults =  getCachedRecordsForRole(request,roleType);
        	
        	Iterator itr = searchResults.iterator();
        	while(itr.hasNext()){
        		regTOData =  (RegistrationTo)itr.next();
        		if( regTOData.getUserId().equalsIgnoreCase(id) )
        			break;
        	}
        	populateRegBeanFrmTO(registrationBean,regTOData);
        	//registrationBean.setRoleType("Agent_DUMMYDATA");
        	registrationBean.setRoleName(Utilities.getRoleNameByID(roleType));        	
        	registrationBean.setNavFromPage(roleType);
        	registrationBean.setUserId(id);
        	return mapping.findForward("editMode");
        }else{
        	registrationBean.setClassTypeOptions(new Utilities().getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE));
    		
    		//Get Registration Is for - default id Guest User
    		String strRegroleType  = (String)(request.getParameter("registrationFor") != null ?
    									 request.getParameter("registrationFor") : EducationConstant.GUEST_USER_ROLE);
    		registrationBean.setRoleType(strRegroleType);
    		registrationBean.setRoleName(Utilities.getRoleNameByID(strRegroleType));
    		
    		String registrationForm = request.getParameter("registrationFor");
    		
  		
    	/*	if (registrationForm!=null && registrationForm.equals(EducationConstant.AGENT_REG_FORM))
    			return mapping.findForward("agentRegFormCreateMode");*/
    		if(registrationForm != null && registrationForm.length() > 0) {
	    		switch(Integer.parseInt(registrationForm))
	    		{
	    		case 2: actionPage = "createMode";break;  					//teacherUserListPage
	    		case 3: actionPage = "createMode";break; 					///affiliateUserListPage
	    		case 4: actionPage = "createMode";break;					//agentRegFormCreateMode
	//    		case 5: actionPage = "";break;
	    		case 6: actionPage = "createMode";break;					//reviewerUserListPage
	    		default : actionPage = "createMode"; break;
	    		}
    		} 
    		
        }
        return mapping.findForward(actionPage);
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		
		//Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("onError","");
		expDisplayDetails.set(expDTO);
		
		FullRegActionForm registrationBean = (FullRegActionForm)form;		
		String strActionForward = null;
		
		String strActionPerformed = getAction(request);
		if(strActionPerformed!= null){			
			strActionForward = handleNavigationFormGrids(request,registrationBean);
		}else{
			RegistrationTo rto=new RegistrationTo();
			populateRegTOFrmBean(registrationBean,rto);
			UserRegistrationService userRegistrationService= new UserRegistrationService();
			//UserTO userTo =  userRegistrationService.registerUser(rto);
			
			UserSessionInfo objUserInfo = (UserSessionInfo) request.getSession()
			.getAttribute(SessionConstants.user_info);

						int userId;
						if(objUserInfo != null) {
							userId = objUserInfo.getUserId();
							rto.setUserId(String.valueOf(userId));
						}
			UserTO userTo = userRegistrationService.updateUser(rto);
			strActionForward = "success";
		}
		
		return mapping.findForward(strActionForward);
	}
	
	private void populateRegTOFrmBean(FullRegActionForm registrationBean,RegistrationTo rto){
		rto.setEmailID(registrationBean.getEmailID());
		rto.setPassword(registrationBean.getPassword());
		rto.setFirstName(registrationBean.getFirstName());
		rto.setLastName(registrationBean.getLastName());
		rto.setAddress1(registrationBean.getAddress());
		rto.setAlternateEmailID(registrationBean.getAlternateEmailID());
		rto.setCity(registrationBean.getCity());
		rto.setCountry(registrationBean.getCountry());
		rto.setHobbies(registrationBean.getHobbies());
		rto.setIsFullregistration(1); //Set Full Registration flag to true
		rto.setIsApproved(EducationConstant.REG_STATUS_WAIT_FOR_ADMIN_APPROVAL); // Waits for approval from Admin
		rto.setLandlineNo( registrationBean.getLandlineNo() );
		rto.setStdCode(registrationBean.getStdCode());
		rto.setIsdCode(registrationBean.getIsdCode());
		rto.setMobileNo( registrationBean.getMobileNo() );
		rto.setPin(registrationBean.getPin());
		rto.setSex(registrationBean.getSex());		
		rto.setRoleId(Integer.parseInt(registrationBean.getRoleType()));
		rto.setState(registrationBean.getState());
		
	}
	
	private void populateRegBeanFrmTO(FullRegActionForm registrationBean,RegistrationTo rto){
		registrationBean.setAddress(rto.getAddress1()+" "+rto.getAddress2());		
		registrationBean.setAlternateEmailID(rto.getAlternateEmailID());
		registrationBean.setCity(rto.getCity());
		registrationBean.setClassType(rto.getClasstype());
		registrationBean.setCountry(rto.getCountry());
		registrationBean.setEmailID(rto.getEmailID());
		registrationBean.setFirstName(rto.getFirstName());
		registrationBean.setHobbies(rto.getHobbies());
		registrationBean.setLandlineNo(rto.getLandlineNo());		
		registrationBean.setStdCode(rto.getStdCode());
		registrationBean.setIsdCode(rto.getIsdCode());
		registrationBean.setPassword(rto.getPassword());
		registrationBean.setPin(rto.getPin());
		registrationBean.setMobileNo(rto.getMobileNo());
		registrationBean.setMobNoPart1(rto.getMobNoPart1());
		registrationBean.setMobNoPart2(rto.getMobNoPart2());
		registrationBean.setRegistrationDate(rto.getRegistrationDate());
		//egistrationBean.setRoleType(rto.getr)
		registrationBean.setSex(rto.getSex());
		registrationBean.setLastName(rto.getLastName());
		registrationBean.setState(rto.getState());
	}
		
	private ArrayList  getCachedRecordsForRole(HttpServletRequest req, String strRoleType){	
		ArrayList records = null;
		if(EducationConstant.AGENT_USER_ROLE.equalsIgnoreCase(strRoleType)){
			 records = getCahedSearchResults(req,SessionConstants.SCH_RESULTS_NEWUSER_AGENT_LIST);
			 req.setAttribute("roleType",EducationConstant.AGENT_USER_ROLE);
		}else if(EducationConstant.AFFILIATE_USER_ROLE.equalsIgnoreCase(strRoleType)){
			records = getCahedSearchResults(req,SessionConstants.SCH_RESULTS_NEWUSER_AFFILIATE_LIST);
			req.setAttribute("roleType",EducationConstant.AFFILIATE_USER_ROLE);
		}else if(EducationConstant.TEACHER_USER_ROLE.equalsIgnoreCase(strRoleType)){
			records = getCahedSearchResults(req,SessionConstants.SCH_RESULTS_NEWUSER_TEACHER_LIST);
			req.setAttribute("roleType",EducationConstant.TEACHER_USER_ROLE);
		}else if(EducationConstant.REVIEWER_USER_ROLE.equalsIgnoreCase(strRoleType)){
			records = getCahedSearchResults(req,SessionConstants.SCH_RESULTS_NEWUSER_REVIEWER_LIST);
			req.setAttribute("roleType",EducationConstant.REVIEWER_USER_ROLE);
		}
		return records;
	}

	@Override
	protected boolean byPassSessionTracking_BeforeLogin() {
		return false;
	}

	 /**
     * For Edit and view mode handles navigation done from grid - It checks navigation was done from which grid.
     * If any action is performed then prepares the required data and actionForward string,
     * sends back actionForwad string. 
     * @return ActionForward string - Action to which data should be passed for processing
     * Action 
     */
    private String handleNavigationFormGrids(HttpServletRequest request, FullRegActionForm registrationBean){    	
    	String strActionForward = null;
    	
    	//Get Action Performed
    	String strActionPerformed = getAction(request);    	
    	//Prepare Data for Next Action
    	String[] id = {registrationBean.getUserId()};    
    	
    	//Set Action Details to Request Scope variables
    	request.setAttribute(SessionConstants.FORM_ACTION_PERFORMED,strActionPerformed);
    	request.setAttribute(SessionConstants.GRID_CHECKED_IDS,id);
    	
    	//For Back or Close Action - Just redirect to back page
    	if(strActionPerformed.equals("back") || strActionPerformed.equals("close")){
    		request.setAttribute("action", "display");
    		request.setAttribute("back","123");
    	}
    	
    	//Handle Navigation to Parent page
    	if(registrationBean.getNavFromPage().equals(EducationConstant.AFFILIATE_USER_ROLE))
			strActionForward = "affiliateUserListPage";
		else if(registrationBean.getNavFromPage().equals(EducationConstant.AGENT_USER_ROLE))
			strActionForward = "agentUserListPage";
		else if(registrationBean.getNavFromPage().equals(EducationConstant.TEACHER_USER_ROLE))
			strActionForward = "teacherUserListPage";
		else if(registrationBean.getNavFromPage().equals(EducationConstant.REVIEWER_USER_ROLE))
			strActionForward = "reviewerUserListPage";	
    	return strActionForward;
    }   
}
