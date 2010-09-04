package com.education.services;

import java.util.ArrayList;
import java.util.Iterator;

import org.expframework.exceptions.BaseAppException;

import com.education.dao.OracleRegistrationDAO;
import com.education.transferobj.RegistrationTo;
import com.education.transferobj.UserTO;
import com.education.util.Utilities;

public class UserRegistrationService {

	public UserTO registerUser(RegistrationTo rto) throws BaseAppException {
		// encrypt password
		String encrypted_password = Utilities.encryptData(rto.getPassword());
		rto.setPassword(encrypted_password);
		OracleRegistrationDAO userDAO = new OracleRegistrationDAO();
		return userDAO.registerUser(rto);

	}
	
	public UserTO updateUser(RegistrationTo rto) throws BaseAppException {
		// encrypt password
		String encrypted_password = Utilities.encryptData(rto.getPassword());
		rto.setPassword(encrypted_password);
		OracleRegistrationDAO userDAO = new OracleRegistrationDAO();
		return userDAO.updateUser(rto);

	}
	
	
	
	
	public String getPassword(String emailId) throws BaseAppException{
		OracleRegistrationDAO oracleRegistrationDAO = new OracleRegistrationDAO();
		return oracleRegistrationDAO.getNewPassword(emailId);
	}


	public UserTO getloginDetails(String emailId, String password)
			throws BaseAppException {
		// encrypt password using MD5
		String encrypted_password = Utilities.encryptData(password);
		OracleRegistrationDAO userDAO = new OracleRegistrationDAO();
		return userDAO.getloginDetails(emailId, encrypted_password);
	}

	public boolean existUsername(String emailId) throws BaseAppException {
		OracleRegistrationDAO oracleRegistrationDAO = new OracleRegistrationDAO();
		return oracleRegistrationDAO.existUsername(emailId);
	}

	public int getUsersCountByRoleId(int roleID, String email, String fname,
			String lname, Integer status, String frmDate, String toDate)
			throws BaseAppException {
		OracleRegistrationDAO oracleRegistrationDAO = new OracleRegistrationDAO();
		return oracleRegistrationDAO.getUsersCountByRoleId(roleID, email,
				fname, lname, status, frmDate, toDate);
	}

	public ArrayList getUsersByRoleId(int roleID, String email, String fname,
			String lname, Integer status, String frmDate, String toDate,
			int frmRecNo, int noOfRecords) throws BaseAppException {
		OracleRegistrationDAO oracleRegistrationDAO = new OracleRegistrationDAO();

		ArrayList list = oracleRegistrationDAO.getUsersByRoleId(roleID, email,
				fname, lname, status, frmDate, toDate, frmRecNo, noOfRecords);
		// Format MobileNo
		Iterator itr = list.iterator();
		while (itr.hasNext()) {
			RegistrationTo regTo = (RegistrationTo) itr.next();
			if (!Utilities.isNullOrBlank(regTo.getMobileNo())) {
				String mobNo = regTo.getMobileNo();
				regTo.setMobNoPart2(getMobilePart2No(mobNo));
			}
		}

		return list;
	}

	public int approveUser_Logins(String[] qIds) throws BaseAppException {
		OracleRegistrationDAO oracleRegistrationDAO = new OracleRegistrationDAO();
		return oracleRegistrationDAO.approveUser_Logins(qIds);
	}

	public int disableUser_Logins(String[] qIds) throws BaseAppException {
		OracleRegistrationDAO oracleRegistrationDAO = new OracleRegistrationDAO();
		return oracleRegistrationDAO.disableUser_Logins(qIds);
	}

	public int markReviewer_As_Teacher(String[] qIds) throws BaseAppException {
		OracleRegistrationDAO oracleRegistrationDAO = new OracleRegistrationDAO();
		return oracleRegistrationDAO.markReviewer_As_Teacher(qIds);
	}

	public int makeTeacherReviewer(String[] userId) throws BaseAppException {
		OracleRegistrationDAO oracleRegistrationDAO = new OracleRegistrationDAO();
		return oracleRegistrationDAO.makeTeacherReviewer(userId);
	}

	private String getMobilePart2No(String mobileNo) {
		return mobileNo.substring(3);
	}

	public static void main(String[] args) {
		System.out.println(new UserRegistrationService()
				.getMobilePart2No("+919922484405"));
	}

	public int deleteUser_Logins(String[] strIds) throws BaseAppException {
		OracleRegistrationDAO oracleRegistrationDAO = new OracleRegistrationDAO();
		return oracleRegistrationDAO.deleteUser_Logins(strIds);
	}

	public int modifyUser(RegistrationTo rto) throws BaseAppException {
		return new OracleRegistrationDAO().modifyUser(rto);
	}
}
