package com.education.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.expframework.data.ExceptionDisplayDTO;

import com.education.Session.SessionConstants;
import com.education.formbeans.AgentFullRegActionForm;
import com.education.services.AgentRegistrationService;
import com.education.services.UserRegistrationService;
import com.education.transferobj.AgentFullRegTO;
import com.education.transferobj.RegistrationTo;
import com.education.transferobj.UserTO;
import com.education.util.EducationConstant;
import com.education.util.Utilities;

public class AgentFullRegistrationAction extends EducationBaseAction {

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		AgentFullRegActionForm agentRegistrationBean = (AgentFullRegActionForm) form;
		String mode = request.getParameter("mode");
		RegistrationTo regTOData = null;

		// If Page is opened in View mode
		if (mode != null
				&& mode.equalsIgnoreCase(EducationConstant.PAGE_VIEW_MODE)) {
			String id = (String) request.getParameter("id");
			String roleType = (String) request.getParameter("roleType");
			ArrayList searchResults = getCachedRecordsForRole(request, roleType);

			Iterator itr = searchResults.iterator();
			while (itr.hasNext()) {
				regTOData = (RegistrationTo) itr.next();
				if (regTOData.getUserId().equalsIgnoreCase(id))
					break;
			}
			populateTOFromForm(agentRegistrationBean, regTOData);
			// registrationBean.setRoleType("Agent_DUMMYDATA");
			agentRegistrationBean.setRoleName(Utilities
					.getRoleNameByID(roleType));
			agentRegistrationBean.setNavFromPage(roleType);
			agentRegistrationBean.setUserId(Integer.parseInt(id));
			agentRegistrationBean = new AgentRegistrationService()
					.getAgentDetails(agentRegistrationBean);
			return mapping.findForward("agentViewModifyMode");
		} else {
		}
		return mapping.findForward("success");
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// Set the Exception Handling details ActionForward, Context Senstive
		// Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("onError", "");
		expDisplayDetails.set(expDTO);

		AgentFullRegActionForm agentFullRegActionForm = (AgentFullRegActionForm) form;
		RegistrationTo rto = new RegistrationTo();
		populateTOFromForm(rto, agentFullRegActionForm);
		rto.setMobileNo(agentFullRegActionForm.getMobNoPart1()
				+ agentFullRegActionForm.getMobNoPart2());
		UserRegistrationService userRegistrationService = new UserRegistrationService();
		AgentFullRegTO agentFullRegTO = new AgentFullRegTO();
		populateTOFromForm(agentFullRegTO, agentFullRegActionForm);
		AgentRegistrationService agentRegistrationService = new AgentRegistrationService();

		String action = request.getParameter("action");
		if (action != null
				&& action.equalsIgnoreCase(EducationConstant.PAGE_EDIT_MODE)) {

			int userModifyCount = userRegistrationService.modifyUser(rto);
			int agentModifyCount = agentRegistrationService
					.modifyAgent(agentFullRegTO);
			dispRecordUpdationMessage(request, userModifyCount);
			return mapping.findForward("success");
		} else {
			UserTO userTo = userRegistrationService.registerUser(rto);
			agentFullRegTO.setUserId(userTo.getUserId());
			agentRegistrationService.registerAgent(agentFullRegTO);
		}
		return mapping.findForward("success");
	}

	private void populateTOFromForm(Object dest, Object orig)
			throws IllegalAccessException, InvocationTargetException {

		BeanUtils.copyProperties(dest, orig);
	}

	@Override
	protected boolean byPassSessionTracking_BeforeLogin() {
		return true;
	}

	private ArrayList getCachedRecordsForRole(HttpServletRequest req,
			String strRoleType) {

		ArrayList records = getCahedSearchResults(req,
				SessionConstants.SCH_RESULTS_NEWUSER_AGENT_LIST);
		req.setAttribute("roleType", EducationConstant.AGENT_USER_ROLE);
		return records;
	}
}
