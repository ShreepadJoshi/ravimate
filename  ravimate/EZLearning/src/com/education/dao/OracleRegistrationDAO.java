/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.education.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.expframework.exceptions.BaseAppException;

import com.education.exceptions.DBDataOutOfRangeException;
import com.education.exceptions.DBDataSourceException;
import com.education.exceptions.DBIntegrityViolationException;
import com.education.exceptions.DBInvalidDataInsertionException;
import com.education.transferobj.RegistrationTo;
import com.education.transferobj.UserTO;
import com.education.util.EducationConstant;
import com.education.util.GetConnection;
import com.education.util.Utilities;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

/**
 * 
 * @author Administrator
 */
public class OracleRegistrationDAO extends AbstractDAO{

	public boolean existUsername(String emailID) throws BaseAppException {
		Connection con = null;
		PreparedStatement psmt = null;
		String sql = " select emailId " + " from t_user " + " where emailId=?";
		boolean result = false;

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, emailID);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return result;
	}

	public UserTO registerUser(RegistrationTo rto) throws BaseAppException {

		// TODO : Make it update query for full registration .
		UserTO userTo = new UserTO();
		Connection con = null;
		String fullReg_Q = " insert into t_user (EmailId, RoleId, Password, Address1,PIN,"
				+ " Country,PhoneSTD,PhoneISD,Landline,"
				+ " MobileNumber,AlternateEmailId, FirstName, "
				+ " LastName, Sex, RegistrationDate, "
				+ " Hobbies, IsFullReg,IsApproved,city,state) "
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

		String partialReg_Q = " insert into t_user (EmailId, RoleId, Password, RegistrationDate, "
				+ " IsFullReg,IsApproved) " + " values(?,?,?,?,?,?) ";

		PreparedStatement psmt = null;
		try {
			con = GetConnection.getSimpleConnection();
			//if (rto.getIsFullregistration() == 1) {
				psmt = con.prepareStatement(fullReg_Q);
				psmt.setString(1, rto.getEmailID());
				psmt.setInt(2, rto.getRoleId());
				psmt.setString(3, rto.getPassword());
				psmt.setString(4, rto.getAddress1());
				psmt.setString(5, rto.getPin());
				psmt.setString(6, rto.getCountry());
				psmt.setString(7, rto.getStdCode());
				psmt.setString(8, rto.getIsdCode());
				psmt.setString(9, rto.getLandlineNo());
				psmt.setString(10, rto.getMobileNo());
				psmt.setString(11, rto.getAlternateEmailID());
				psmt.setString(12, rto.getFirstName());
				psmt.setString(13, rto.getLastName());
				psmt.setString(14, rto.getSex());
				psmt.setDate(15, new java.sql.Date(Utilities
						.getCurrentDB_Date()));
				psmt.setString(16, rto.getHobbies());
				psmt.setInt(17, rto.getIsFullregistration());
				psmt.setInt(18, rto.getIsApproved());
				psmt.setString(19, rto.getCity());
				psmt.setString(20, rto.getState());
				psmt.executeUpdate();
			//} else if (rto.getIsFullregistration() == 0) {
//				psmt = con.prepareStatement(partialReg_Q);
//				psmt.setString(1, rto.getEmailID());
//				psmt.setInt(2, rto.getRoleId());
//				psmt.setString(3, rto.getPassword());
//				psmt.setDate(4,
//						new java.sql.Date(Utilities.getCurrentDB_Date()));
//				psmt.setInt(5, rto.getIsFullregistration());
//				psmt.setInt(6, rto.getIsApproved());
//				psmt.executeUpdate();
			//}
			
			//Get Logged In user Details For Application to use
			userTo = getloginDetails(rto.getEmailID(),rto.getPassword());			
			
		}catch (MySQLIntegrityConstraintViolationException e) {
			throw new DBIntegrityViolationException(e.getMessage());
		} catch (MysqlDataTruncation e) {
			throw new DBDataOutOfRangeException(e.getMessage(), Utilities
					.getDataTooLongExceptionColumnName(e.getMessage()));
		} catch (SQLException e) {
			if (e.getMessage().contains("Incorrect"))
				throw new DBInvalidDataInsertionException(e.getMessage(),
						Utilities.getDataMissingExceptionColumnName(e
								.getMessage()), Utilities
								.getDataMissingExceptionColumValue(e
										.getMessage()));
			else
				throw new RuntimeException(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return userTo;
	}

	/**
	 * Checks if the user with given emailId and password exists and accordingly
	 * prepares the UserTO object holding all the user information else returns
	 * null for userTO object
	 * 
	 * @param emailId
	 *            - String
	 * @param password
	 *            - String
	 * @return UserTO Object holding the details of logged in user/null if user
	 *         doest not exist
	 */
	public UserTO getloginDetails(String emailId, String password)
			throws BaseAppException {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs;
		UserTO userTo = null;
		String sql1 = " SELECT UserID,EmailId,IsFullReg, "
				+ " FirstName,LastName,RegistrationDate,RoleId,UnsuccessfulAttempt "
				+ " FROM t_user WHERE EmailId='" + emailId + "'"
				+ " AND Password ='" + password + "'";
		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql1);
			rs = psmt.executeQuery();
			userTo = new UserTO();
			if (rs.next()) {
				boolean hasDoneFullReg = (Integer.parseInt(rs
						.getString("IsFullReg")) == 1 ? true : false);
				userTo.setEmailId(rs.getString("EmailId"));
				userTo.setFirstName(rs.getString("FirstName"));
				userTo.setLastName(rs.getString("LastName"));
				userTo.setDoneFullRegistration(hasDoneFullReg);
				userTo.setRegistrationDate(rs.getString("RegistrationDate"));
				userTo.setRoleId(rs.getInt("RoleId"));
				userTo.setUserloginID(userTo.getEmailId());
				userTo.setUserId(rs.getInt("UserID"));
				int cnt = rs.getInt("UnsuccessfulAttempt");
				if (cnt > EducationConstant.MAX_UNSUCCESSFULL_ATTEMPTS) {
					userTo = null;
					throw new BaseAppException("UNSUCCESSFULL_EXCEPTION_MSG",
							true);
				}
			} else {
				updateUnsuccessfulAttempt(userTo.getUserId());
				userTo = null;
			}

		} catch (SQLException e) {
			userTo = null;
			throw new RuntimeException(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return userTo;
	}

	// method to get user count.
	public int getUsersCountByRoleId(int roleID, String email, String fname,
			String lname, Integer status, String frmDate, String toDate)
			throws BaseAppException {

		String sql = "select count(*) AS rowcount From t_user a ";

		String where = "where  roleid='" + roleID + "'";
		if (null != email && !email.equalsIgnoreCase("")) {
			where = where + " and a.emailID='" + email + "'";
		}
		if (null != status) {
			where = where + " and a.IsApproved=" + status.intValue();
		}
		if (null != fname && !fname.equalsIgnoreCase("")) {
			where = where + " and a.firstName='" + fname + "'";
		}
		if (null != lname && !lname.equalsIgnoreCase("")) {
			where = where + " and a.lastName='" + lname + "'";
		}
		if (null != frmDate && !frmDate.equalsIgnoreCase("")) {
			where = where
					+ " and a.RegistrationDate >= '"
					+ Utilities.getDate_DBFormat(frmDate,
							EducationConstant.DISPLAY_DATE_FORMAT) + "'";
		}
		if (null != toDate && !toDate.equalsIgnoreCase("")) {
			where = where
					+ " and a.RegistrationDate <= '"
					+ Utilities.getDate_DBFormat(toDate,
							EducationConstant.DISPLAY_DATE_FORMAT) + "'";
		}

		sql = sql + where;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs;
		int rowcount = 0;

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				rowcount = rs.getInt("rowcount");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return rowcount;
	}

	public ArrayList getUsersByRoleId(int roleID, String email, String fname,
			String lname, Integer status, String frmDate, String toDate,
			int frmRecordNo, int noOfRecords) throws BaseAppException {

		String sql = "select * From t_user a ";
		String rowLimit = " limit " + frmRecordNo + "," + noOfRecords;

		String where = "where  roleid='" + roleID + "'";
		if (null != email && !email.equalsIgnoreCase("")) {
			where = where + " and a.emailID='" + email + "'";
		}
		if (null != status) {
			where = where + " and a.IsApproved=" + status.intValue();
		}
		if (null != fname && !fname.equalsIgnoreCase("")) {
			where = where + " and a.firstName='" + fname + "'";
		}
		if (null != lname && !lname.equalsIgnoreCase("")) {
			where = where + " and a.lastName='" + lname + "'";
		}
		if (null != frmDate && !frmDate.equalsIgnoreCase("")) {
			where = where
					+ " and a.RegistrationDate >= '"
					+ Utilities.getDate_DBFormat(frmDate,
							EducationConstant.DISPLAY_DATE_FORMAT) + "'";
		}
		if (null != toDate && !toDate.equalsIgnoreCase("")) {
			where = where
					+ " and a.RegistrationDate <= '"
					+ Utilities.getDate_DBFormat(toDate,
							EducationConstant.DISPLAY_DATE_FORMAT) + "'";
		}

		sql = sql + where + rowLimit;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs;
		ArrayList registerUserList = new ArrayList();

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				// i++;
				// if (i >= frmRecordNo && i <= noOfRecords) {
				RegistrationTo registrationTo = new RegistrationTo();
				registrationTo.setAddress1(rs.getString("address1"));
				registrationTo.setCity(rs.getString("city"));
				registrationTo.setCountry(rs.getString("country"));
				registrationTo.setEmailID(rs.getString("emailID"));
				registrationTo.setFirstName(rs.getString("firstName"));
				registrationTo.setHobbies(rs.getString("hobbies"));
				registrationTo.setIsApproved(rs.getInt("IsApproved"));
				registrationTo.setIsFullregistration(rs.getInt("IsFullReg"));
				registrationTo.setLandlineNo(rs.getString("Landline"));
				registrationTo.setStdCode(rs.getString("PhoneSTD"));
				registrationTo.setIsdCode(rs.getString("PhoneISD"));
				registrationTo.setLastName(rs.getString("lastName"));
				registrationTo.setMobileNo(rs.getString("MobileNumber"));
				registrationTo.setPin(rs.getString("pin"));
				registrationTo.setRoleId(rs.getInt("roleId"));
				registrationTo.setSex(rs.getString("sex"));
				registrationTo.setUserId(rs.getString("UserID"));
				registrationTo.setPassword(rs.getString("Password"));
				registrationTo.setAlternateEmailID(rs
						.getString("AlternateEmailId"));
				registrationTo.setRegistrationDate(rs
						.getString("RegistrationDate"));
				registrationTo.setState(rs.getString("state"));

				registerUserList.add(registrationTo);
				// }
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return registerUserList;
	}

	public int approveUser_Logins(String[] qIds) throws BaseAppException {
		int approveCount = 0;
		boolean flag = false;
		StringBuffer in = new StringBuffer();
		in.append("(");
		for (int i = 0; i < qIds.length; i++) {
			if (i == 0) {
				in.append("'" + qIds[i] + "'");
			} else {
				in.append(",'" + qIds[i] + "'");
			}

		}
		in.append(")");
		String sql = "update t_user set isapproved='1' where userid in" + in;

		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			approveCount = psmt.executeUpdate();
			psmt.close();
			flag = true;

		} catch (MySQLIntegrityConstraintViolationException e) {
			throw new DBIntegrityViolationException(e.getMessage());
		} catch (MysqlDataTruncation e) {
			throw new DBDataOutOfRangeException(e.getMessage(), Utilities
					.getDataTooLongExceptionColumnName(e.getMessage()));
		} catch (SQLException e) {
			if (e.getMessage().contains("Incorrect"))
				throw new DBInvalidDataInsertionException(e.getMessage(),
						Utilities.getDataMissingExceptionColumnName(e
								.getMessage()), Utilities
								.getDataMissingExceptionColumValue(e
										.getMessage()));
			else
				throw new RuntimeException(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return approveCount;
	}

	public int disableUser_Logins(String[] qIds) throws BaseAppException {
		int disableCount = 0;
		StringBuffer in = new StringBuffer();
		in.append("(");

		for (int i = 0; i < qIds.length; i++) {
			if (i == 0) {
				in.append("'" + qIds[i] + "'");
			} else {
				in.append(",'" + qIds[i] + "'");
			}

		}
		in.append(")");
		String sql = "update t_user set isapproved='3' where userid in" + in;

		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			disableCount = psmt.executeUpdate();
			psmt.close();

		} catch (MySQLIntegrityConstraintViolationException e) {
			throw new DBIntegrityViolationException(e.getMessage());
		} catch (MysqlDataTruncation e) {
			throw new DBDataOutOfRangeException(e.getMessage(), Utilities
					.getDataTooLongExceptionColumnName(e.getMessage()));
		} catch (SQLException e) {
			if (e.getMessage().contains("Incorrect"))
				throw new DBInvalidDataInsertionException(e.getMessage(),
						Utilities.getDataMissingExceptionColumnName(e
								.getMessage()), Utilities
								.getDataMissingExceptionColumValue(e
										.getMessage()));
			else
				throw new RuntimeException(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return disableCount;
	}

	public int makeTeacherReviewer(String[] qIds) throws BaseAppException {
		int updateCount = 0;
		;
		StringBuffer in = new StringBuffer();
		in.append("(");
		for (int i = 0; i < qIds.length; i++) {
			if (i == 0) {
				in.append("'" + qIds[i] + "'");
			} else {
				in.append(",'" + qIds[i] + "'");
			}

		}
		in.append(")");
		String sql = "update t_user set isapproved='1' , roleid='6' where userid in "
				+ in;

		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			updateCount = psmt.executeUpdate();
			psmt.close();

		} catch (MySQLIntegrityConstraintViolationException e) {
			throw new DBIntegrityViolationException(e.getMessage());
		} catch (MysqlDataTruncation e) {
			throw new DBDataOutOfRangeException(e.getMessage(), Utilities
					.getDataTooLongExceptionColumnName(e.getMessage()));
		} catch (SQLException e) {
			if (e.getMessage().contains("Incorrect"))
				throw new DBInvalidDataInsertionException(e.getMessage(),
						Utilities.getDataMissingExceptionColumnName(e
								.getMessage()), Utilities
								.getDataMissingExceptionColumValue(e
										.getMessage()));
			else
				throw new RuntimeException(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return updateCount;
	}

	public int markReviewer_As_Teacher(String[] qIds) throws BaseAppException {
		int updateCount = 0;
		StringBuffer in = new StringBuffer();
		in.append("(");
		for (int i = 0; i < qIds.length; i++) {
			if (i == 0) {
				in.append("'" + qIds[i] + "'");
			} else {
				in.append(",'" + qIds[i] + "'");
			}

		}
		in.append(")");
		String sql = "update t_user set roleid='"
				+ EducationConstant.TEACHER_USER_ROLE + "' where userid in "
				+ in;

		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			updateCount = psmt.executeUpdate();
			psmt.close();

		} catch (MySQLIntegrityConstraintViolationException e) {
			throw new DBIntegrityViolationException(e.getMessage());
		} catch (MysqlDataTruncation e) {
			throw new DBDataOutOfRangeException(e.getMessage(), Utilities
					.getDataTooLongExceptionColumnName(e.getMessage()));
		} catch (SQLException e) {
			if (e.getMessage().contains("Incorrect"))
				throw new DBInvalidDataInsertionException(e.getMessage(),
						Utilities.getDataMissingExceptionColumnName(e
								.getMessage()), Utilities
								.getDataMissingExceptionColumValue(e
										.getMessage()));
			else
				throw new RuntimeException(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return updateCount;
	}

	/**
	 * Update Unsuccessfull Attemp by user.
	 */
	private void updateUnsuccessfulAttempt(int UserId) throws BaseAppException {
		Connection con;
		PreparedStatement psmt = null;
		int count = 0;

		String sql = "Update t_user set UnsuccessfulAttempt = UnsuccessfulAttempt + 1 where UserID ="
				+ UserId;

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			count = psmt.executeUpdate();
		} catch (SQLException sqld) {
			throw new RuntimeException(sqld);
		} finally {
			try {
				if (null != psmt) {
					psmt.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public int deleteUser_Logins(String[] strIds) throws BaseAppException {
		int deleteCount = 0;
		StringBuffer in = new StringBuffer();
		in.append("(");

		for (int i = 0; i < strIds.length; i++) {
			if (i == 0) {
				in.append("'" + strIds[i] + "'");
			} else {
				in.append(",'" + strIds[i] + "'");
			}

		}
		in.append(")");
		String sql = "delete from t_user where userid in " + in;

		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			deleteCount = psmt.executeUpdate();
			psmt.close();

		} catch (MySQLIntegrityConstraintViolationException e) {
			throw new DBIntegrityViolationException(e.getMessage());
		} catch (MysqlDataTruncation e) {
			throw new DBDataOutOfRangeException(e.getMessage(), Utilities
					.getDataTooLongExceptionColumnName(e.getMessage()));
		} catch (SQLException e) {
			if (e.getMessage().contains("Incorrect"))
				throw new DBInvalidDataInsertionException(e.getMessage(),
						Utilities.getDataMissingExceptionColumnName(e
								.getMessage()), Utilities
								.getDataMissingExceptionColumValue(e
										.getMessage()));
			else
				throw new RuntimeException(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return deleteCount;
	}

	public int modifyUser(RegistrationTo rto) throws BaseAppException {
		int modifyCount = 0;
		String sql = "update t_user set " +
				"FirstName = '"+rto.getFirstName()+"'," +
				"LastName='"+rto.getLastName()+"'," +
				"Sex='"+rto.getSex()+"'," +
				"Address1='"+rto.getAddress1()+"'," +
				"Country='"+rto.getCountry()+"'," +
				"state='"+rto.getState()+"'," +
				"city='"+rto.getCity()+"'," +
				"PIN='"+rto.getPin()+"'," +
				"MobileNumber='"+rto.getMobileNo()+"'," +
				"PhoneISD='"+rto.getIsdCode()+"'," +
				"PhoneSTD='"+rto.getStdCode()+"'," +
				"Landline='"+rto.getLandlineNo()+"'," +
				"AlternateEmailId='"+rto.getAlternateEmailID()+"' " +
				"where UserID="+rto.getUserId();
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			modifyCount = psmt.executeUpdate();
			psmt.close();

		} catch (MySQLIntegrityConstraintViolationException e) {
			throw new DBIntegrityViolationException(e.getMessage());
		} catch (MysqlDataTruncation e) {
			throw new DBDataOutOfRangeException(e.getMessage(), Utilities
					.getDataTooLongExceptionColumnName(e.getMessage()));
		} catch (SQLException e) {
			if (e.getMessage().contains("Incorrect"))
				throw new DBInvalidDataInsertionException(e.getMessage(),
						Utilities.getDataMissingExceptionColumnName(e
								.getMessage()), Utilities
								.getDataMissingExceptionColumValue(e
										.getMessage()));
			else
				throw new RuntimeException(e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return modifyCount;
	}
	
	
	public String getNewPassword(String emailId) throws BaseAppException {

		Connection con = null;
		PreparedStatement psmt = null;		
		String password = "";
        if(existUsername(emailId)==false){
        	throw new BaseAppException("EMAILID_NOT_EXIST_MSG",true);
        }
        password = Utilities.getNewPassword();
        String newPassword = Utilities.encryptData(password);
        String sql = "update t_user set Password='" + newPassword + "' where emailId ='"+ emailId +"'";
		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);			
			psmt.executeUpdate();
			psmt.close();
		} catch (SQLException e) {
			throw new BaseAppException(e.getMessage());			
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return password;
	}

	
}
