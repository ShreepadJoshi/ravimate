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
import com.education.services.LoginService;
import com.education.transferobj.UserTO;
import com.education.util.EducationConstant;

public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		String strUsername = ( req.getParameter("username") != null ? req.getParameter("username") : "");
		String strPassword = ( req.getParameter("password") != null ? req.getParameter("password") : "");
		UserSessionInfo objUserInfo = new UserSessionInfo();
		UserTO userTo = new UserTO();
		userTo.setEmailId(strUsername);
		userTo.setPassword1(strPassword);
		
		LoginService loginService= new LoginService();
		try {
			userTo =loginService.isValidLogin(userTo);
		} catch (BaseAppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(userTo != null){
			objUserInfo.setRoleId( String.valueOf(userTo.getRoleId()) );
			//objUserInfo.setUserID("123");
			objUserInfo.setUserloginName(userTo.getEmailId());
			req.getSession().setAttribute(SessionConstants.user_info, objUserInfo);
		}else{
			out.print("INVALID");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
