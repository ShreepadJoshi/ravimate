package com.education.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.expframework.exceptions.BaseAppException;

import com.education.exceptions.DBDataOutOfRangeException;
import com.education.exceptions.DBDataSourceException;
import com.education.exceptions.DBIntegrityViolationException;
import com.education.exceptions.DBInvalidDataInsertionException;
import com.education.formbeans.AgentFullRegActionForm;
import com.education.transferobj.AgentFullRegTO;
import com.education.util.GetConnection;
import com.education.util.Utilities;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class AgentRegistrationDAO extends AbstractDAO{

	public void registerAgent(AgentFullRegTO agentFullRegTO)
			throws BaseAppException {

		Connection con = null;
		String fullReg_Q = " insert into t_agent(AgentId,BankName,"
				+ "AccountNumber,FixedMonthlyFee,PercentageCut,"
				+ "MinSaleForBonus,BonusPercentage,TransactionCode,"
				+ "Qualification) " + " values(?,?,?,?,?,?,?,?,?) ";

		PreparedStatement psmt = null;
		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(fullReg_Q);
			psmt.setInt(1, agentFullRegTO.getUserId());
			psmt.setString(2, agentFullRegTO.getBankName());
			psmt.setString(3, agentFullRegTO.getAccountNo());
			psmt.setInt(4, agentFullRegTO.getFixedMonthlyFee());
			psmt.setInt(5, agentFullRegTO.getPercentageOnSale());
			psmt.setInt(6, agentFullRegTO.getMinSaleForBonus());
			psmt.setInt(7, agentFullRegTO.getPercentageOnBonus());
			psmt.setString(8, agentFullRegTO.getTransactionCode());
			psmt.setString(9, agentFullRegTO.getQualification());
			psmt.executeUpdate();

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

	}

	public AgentFullRegActionForm getAgentDetails(
			AgentFullRegActionForm agentRegistrationBean) throws BaseAppException {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs;
		String sql1 = "SELECT * FROM t_agent WHERE AgentId ="+agentRegistrationBean.getUserId();
		String sql2 = "SELECT REGISTRATIONDATE FROM T_USER WHERE USERID ="+agentRegistrationBean.getUserId();
		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql1);
			rs = psmt.executeQuery();
			if (rs.next()) {
				agentRegistrationBean.setAgentId(rs.getInt("AgentId"));
				agentRegistrationBean.setBankName(rs.getString("BankName"));
				agentRegistrationBean.setAccountNo(rs.getString("AccountNumber"));
				agentRegistrationBean.setFixedMonthlyFee(rs.getInt("FixedMonthlyFee"));
				agentRegistrationBean.setPercentageOnSale(rs.getInt("PercentageCut"));
				agentRegistrationBean.setMinSaleForBonus(rs.getInt("MinSaleForBonus"));
				agentRegistrationBean.setPercentageOnBonus(rs.getInt("BonusPercentage"));
				agentRegistrationBean.setTransactionCode(rs.getString("TransactionCode"));
				agentRegistrationBean.setQualification(rs.getString("Qualification"));
			}
			psmt = con.prepareStatement(sql2);
			rs = psmt.executeQuery();
			if (rs.next()) {
				agentRegistrationBean.setRegistrationDate(rs.getDate("RegistrationDate"));
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
		return agentRegistrationBean;
	}

	public int modifyAgent(AgentFullRegTO agentFullRegTO) throws BaseAppException {
		int modifyCount = 0;
		String sql = "update t_agent set " +
				"BankName = '"+agentFullRegTO.getBankName()+"'," +
				"AccountNumber='"+agentFullRegTO.getAccountNo()+"'," +
				"FixedMonthlyFee='"+agentFullRegTO.getFixedMonthlyFee()+"'," +
				"PercentageCut='"+agentFullRegTO.getPercentageOnSale()+"'," +
				"MinSaleForBonus='"+agentFullRegTO.getMinSaleForBonus()+"'," +
				"BonusPercentage='"+agentFullRegTO.getPercentageOnBonus()+"'," +
				"TransactionCode='"+agentFullRegTO.getTransactionCode()+"'," +
				"Qualification='"+agentFullRegTO.getQualification()+"'" +
				"where AgentId="+agentFullRegTO.getUserId();
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
}