package com.education.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.expframework.exceptions.BaseAppException;

import com.education.Session.SessionConstants;
import com.education.exceptions.DBDataOutOfRangeException;
import com.education.exceptions.DBDataSourceException;
import com.education.exceptions.DBIntegrityViolationException;
import com.education.exceptions.DBInvalidDataInsertionException;
import com.education.transferobj.UserTO;
import com.education.util.GetConnection;
import com.education.util.Utilities;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class SessionDAO extends AbstractDAO{

	/**
	 * Gets logged in User SessionId from t_session_tracking table	    
	 * @param userTo - looged in user details for getting sessionId
	 * @return UserTO
	 *  null - if Session does't exists in DB
	 *  UserTo - userTo object with SessionID property set 
	 */
	public boolean isValidSession(String userID,String sessionID) throws BaseAppException {		
		int rowCount = 0;
		boolean result = false;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs;
		String sql = " SELECT count(*) AS rowCount" +
					 " FROM t_session_tracking "+
					 " WHERE userId ="+ userID
					+" AND sessionID = '"+sessionID+"'";
		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				rowCount = (rs.getInt("rowCount"));
			}
			if(rowCount >0)
				result=true;
			else
				result= false;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return result;
	}

	/**
	 * Inserts sessionId for user after login in t_session_tracking table
	 * @param userTo - details of user who has presently logged in
	 */
	public boolean insertUserSessionID(UserTO userTo) throws BaseAppException{
		
		Connection con = null;
		int rowCont = 0;
		Calendar cal = Calendar.getInstance();
		String sql = "insert into t_session_tracking "+ 
						"(sessionID,userId,creationTime)"+
					 "values"+
						"('"+userTo.getSessionID()+"',"+ 
						 userTo.getUserId()+",'"+new Date(System.currentTimeMillis())+"')";
		try {
			con = GetConnection.getSimpleConnection();			
			rowCont = con.createStatement().executeUpdate(sql);						

		}catch (MySQLIntegrityConstraintViolationException e) {
			throw new DBIntegrityViolationException(e.getMessage());
		}catch (MysqlDataTruncation e) {
			throw new DBDataOutOfRangeException(e.getMessage(),
						Utilities.getDataTooLongExceptionColumnName(e.getMessage()));
		}catch(SQLException e){
			if(e.getMessage().contains("Incorrect"))
				throw new DBInvalidDataInsertionException(e.getMessage(),
						Utilities.getDataMissingExceptionColumnName(e.getMessage()),
						Utilities.getDataMissingExceptionColumValue(e.getMessage()));
			else
				throw new RuntimeException(e);
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return rowCont > 0 ? true : false;
	}
	
	
	
}
