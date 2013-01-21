package com.education.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.expframework.exceptions.BaseAppException;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.services.UserRegistrationService;
import com.education.transferobj.RegistrationTo;
import com.education.transferobj.UserTO;
import com.education.util.EducationConstant;

public class PartialLoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		String strUsername = (req.getParameter("username") != null ? req
				.getParameter("username") : "");
		String strPassword1 = (req.getParameter("password1") != null ? req
				.getParameter("password1") : "");
		String strPassword2 = (req.getParameter("password2") != null ? req
				.getParameter("password2") : "");

		UserSessionInfo objUserInfo = new UserSessionInfo();
		// for teacher
		if (strPassword1.equals(strPassword2)) {
			RegistrationTo rto = new RegistrationTo();
			rto.setEmailID(strUsername);
			rto.setPassword(strPassword1);
			rto.setRoleId(1);
			rto.setIsApproved(1);
			rto.setIsFullregistration(0);
			UserRegistrationService userRegistrationService = new UserRegistrationService();
			UserTO userTo;
			try {
				userTo = userRegistrationService.registerUser(rto);
			
			
			HttpSession session = req.getSession();
			objUserInfo.setRoleId(String.valueOf(userTo.getRoleId()));
			//objUserInfo.setUserID("123");
			objUserInfo.setUserloginName(userTo.getEmailId());
			session.setAttribute(SessionConstants.user_info, objUserInfo);
			} catch (BaseAppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			out.print("INVALID");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
