/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.education.actions;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Administrator
 */
public class ShowRegistrationMessageContentAction extends EducationBaseAction{

    @Override
    public ActionForward displayAction(ActionMapping mapping, ActionForm aForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("registrationMessage");    
       }

    @Override
    public ActionForward performAction(ActionMapping mapping, ActionForm aForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
       String strActionForward =null;
    	
        return mapping.findForward(strActionForward);
    }
    /** (non-Javadoc)
	 * return false as login is needed for this action.
	 * 
	 * --Shripad
	 * 
	 * @see com.education.actions.EducationBaseAction#byPassSessionTracking_BeforeLogin()
	 */
	protected boolean byPassSessionTracking_BeforeLogin() {		
		return true;
	}
	


	
   }















