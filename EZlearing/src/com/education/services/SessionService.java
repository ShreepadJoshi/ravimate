package com.education.services;

import org.expframework.exceptions.BaseAppException;

import com.education.dao.SessionDAO;
import com.education.transferobj.UserTO;
import com.education.util.Utilities;

public class SessionService {

	/**
	 * Fetches sessionID from DB for given user	  
	 * @return 
 	 * String - blank if no sessionId is found in DB given User 
 	 * else sessionID if found in DB	
	 */
	public boolean isValidSession(String userID,String sessionID) throws BaseAppException{
		boolean result  = new SessionDAO().isValidSession(userID,sessionID);
		return result;		
	}
	
	/**
	 * Insert logged In user sessionID in DB for session tracking 
	 * @param userTo needs following properties set
	 *  sessionID
	 *  roleId 
	 * @return boolean
	 * true - on successful insertion
	 * false - if no insertion is done
	 */
	public boolean insertUserSessionID(UserTO userTo) throws BaseAppException{		
		return new SessionDAO().insertUserSessionID(userTo);
	}
	
	public static void main(String[] args) throws BaseAppException {
		UserTO userto = new UserTO();
		
		userto.setSessionID("12312");
		userto.setRoleId(999);
		
		//boolean value = new SessionService().insertUserSessionID(userto);
		//System.out.println("insertion result: "+ value);
		
		boolean result = new SessionService().isValidSession("2","");
		
		
	}
}













