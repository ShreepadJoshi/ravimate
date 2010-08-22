package com.education.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.DbUtils;
import org.expframework.exceptions.BaseAppException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.education.exceptions.DBDataSourceException;
import com.education.exceptions.DBInvalidDataInsertionException;
import com.education.transferobj.AbstractTO;
import com.education.util.GetConnection;
import com.education.util.Utilities;

public class AbstractDAO extends HibernateDaoSupport {
	
		
	/**
	 * @param query
	 * @return
	 * @throws BaseAppException
	 */
	public int insertQuery(String query) throws BaseAppException {
		Connection con = null;
		PreparedStatement psmt;
		ResultSet rs = null;
		int result = 0;

		try {
			con = GetConnection.getSimpleConnection();
			String newContentId = null;

			// insert content table details
			psmt = con.prepareStatement(query);
			result = psmt.executeUpdate();
			
						

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
		return result;
	}

	/**
	 * Close all
	 * @param conn
	 * @param stmt
	 * @param rs
	 * @author Chetan 
	 */
	public void closeQuietly(Connection conn, PreparedStatement stmt, ResultSet rs){
		DbUtils.closeQuietly(conn, stmt, rs);
	}
	
	/**
	 * Close all
	 * @param conn
	 * @param stmt
	 * @param rs
	 * @author Chetan 
	 */
	public void closeQuietly(Connection conn, Statement stmt, ResultSet rs){
		DbUtils.closeQuietly(conn, stmt, rs);
	}
	
	/**
	 * Close Connection, PreparedStatement
	 * @param conn
	 * @param stmt
	 * @author Chetan
	 */
	public void closeQuietly(Connection conn, PreparedStatement stmt){
		DbUtils.closeQuietly(conn);
		DbUtils.closeQuietly(stmt);
	}
	
	public void closeQuietly(Connection conn, Statement stmt){
		DbUtils.closeQuietly(conn);
		DbUtils.closeQuietly(stmt);
	}
	
	/**
	 * @param to
	 * @return
	 */
	protected String getInsertQuery(AbstractTO to) {
		
		//TODO write code...:D
		
		// INSERT INTO t_advertisement
		// (`id`,`picture`,`text`,`keyword`,`type`,`category`,`Target`,`Status`,`startDate`,`endDate`)

		// values

		// (2,NULL,'Shree','shree','Shree','shree','class7','A','2001-02-10','2020-01-10')""
		// +

		StringBuffer buffer = new StringBuffer("INSERT INTO");
		try {
			buffer.append(" " + to.getClass().getField("tableName"));

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return buffer.toString();
	}

}
