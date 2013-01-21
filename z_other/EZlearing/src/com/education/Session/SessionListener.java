package com.education.Session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.expframework.exceptionhandler.ExceptionUtil;
import org.expframework.exceptions.BaseAppException;

import com.education.dao.SessionDAO;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession sess = se.getSession();
		UserSessionInfo sessInfo = null;
		SessionDAO sessDao = null;
		if (sess != null) 
		{
			sessInfo = (UserSessionInfo)sess.getAttribute(SessionConstants.user_info);
			if (sessInfo != null)
			{
				try {
					sessDao = new SessionDAO();
					sessDao.insertUserSessionID(sessInfo.getUserTo());
				} catch (BaseAppException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ExceptionUtil.logException(this.getClass(), e, "");
				}
			}
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.out.println(""+se.getSession());
	}

}
