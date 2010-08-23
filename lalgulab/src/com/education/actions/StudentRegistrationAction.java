package com.education.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.formbeans.CertificateActionForm;
import com.education.formbeans.StudentRegistrationBeanActionForm;
import com.education.services.StudentRegistrationService;
import com.education.transferobj.ClassCertTO;
import com.education.util.EducationConstant;

public class StudentRegistrationAction extends EducationBaseAction {

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String mode = request.getParameter("mode");
		if (mode != null
				&& mode.equalsIgnoreCase(EducationConstant.PAGE_CREATE_MODE)) {

			StudentRegistrationBeanActionForm studentBean = (StudentRegistrationBeanActionForm) form;
			HttpSession session = request.getSession(false);
			if (session != null) {
				UserSessionInfo objUserSessionInfo = (UserSessionInfo) session
						.getAttribute(SessionConstants.user_info);

				populateTOFromForm(studentBean, objUserSessionInfo);
				StudentRegistrationService studentRegistrationService = new StudentRegistrationService();
				List<ClassCertTO> certificateList = studentRegistrationService
						.getClassCertificateList();
				List<CertificateActionForm> certificateActionFormList = new ArrayList<CertificateActionForm>();
				populateFormListFromToList(certificateActionFormList,
						certificateList);
				studentBean.setCertificateList(certificateActionFormList);

				return mapping.findForward("createStudentReg");
			}
		}
		return mapping.findForward("success");
	}

	private void populateFormListFromToList(
			
			List<CertificateActionForm> certificateActionFormList,
			List<ClassCertTO> certificateList) {
		
		if (certificateList != null) {
		
			for (ClassCertTO classCertTO : certificateList) {
				CertificateActionForm certificateActionForm = new CertificateActionForm();
				certificateActionForm.setCertId(classCertTO.getClassCertId());
				certificateActionForm.setCertName(classCertTO
						.getClassCertName());
				certificateActionFormList.add(certificateActionForm);
			}
		}
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

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
				SessionConstants.SCH_RESULTS_STUDENT_LIST);
		req.setAttribute("roleType", EducationConstant.GUEST_USER_ROLE);
		return records;
	}
}
