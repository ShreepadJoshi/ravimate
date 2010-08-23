package com.education.services;

import org.expframework.exceptions.BaseAppException;

import com.education.dao.LoginDAO;
import com.education.transferobj.UserTO;


/**
 * This class handles all the functionality related to login and 
 * registrating new user to DB, Encrypting login passwords
 * @author arunc
 *
 */
public class LoginService {

	public UserTO isValidLogin(UserTO userTo) throws BaseAppException{		
		return new LoginDAO().loginValidation(userTo);
	}
        
        
	
}
