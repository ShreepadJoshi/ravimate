package com.education.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.expframework.exceptions.BaseAppException;

import com.education.exceptions.DBDataOutOfRangeException;
import com.education.exceptions.DBDataSourceException;
import com.education.exceptions.DBIntegrityViolationException;
import com.education.exceptions.DBInvalidDataInsertionException;
import com.education.transferobj.TopicSubTopicTO;
import com.education.util.GetConnection;
import com.education.util.Utilities;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class OracleTopicSubTopicDAO extends AbstractDAO{

	public int getManageTopicSubTopic_RecordCount(String subjectId,
			String classId)throws BaseAppException {
		String sql = "select count(*) as rowcount from t_class_subject_topic_subTopic_mapping ";
		String where = "";

		where += (!Utilities.isNullOrBlank(subjectId) ? where.equals("") ? " where subjectId="
				+ subjectId
				: " AND subjectId=" + subjectId
				: "");

		where += (!Utilities.isNullOrBlank(classId) ? where.equals("") ? " where classId="
				+ classId
				: " AND classId=" + classId
				: "");

		sql += where;

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
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return rowcount;
	}

	public ArrayList getManageTopicSubTopic_List(String subjectId,
			String classId, int min, int max)throws BaseAppException {
		String sql = " SELECT subjectTopicId,"
				+ " topic_subTopic_mappingTable.topicId,topicTable.topicValue,"
				+ " topic_subTopic_mappingTable.subTopicId,subTopicTable.subTopicValue,"
				+ " topic_subTopic_mappingTable.classId "
				+ " FROM t_class_subject_topic_subTopic_mapping topic_subTopic_mappingTable "
				+ " INNER JOIN t_topics topicTable "
				+ "  ON topic_subTopic_mappingTable.topicId = topicTable.topicId "
				+ " INNER JOIN t_subTopics subTopicTable "
				+ "  ON topic_subTopic_mappingTable.subTopicId = subTopicTable.subTopicId ";

		String where = "";
		String rowLimit = " limit " + (min) + "," + max;

		where += (!Utilities.isNullOrBlank(subjectId) ? where.equals("") ? " WHERE topic_subTopic_mappingTable.subjectId="
				+ subjectId
				: " AND topic_subTopic_mappingTable.subjectId=" + subjectId
				: "");

		where += (!Utilities.isNullOrBlank(classId) ? where.equals("") ? " where topic_subTopic_mappingTable.classId="
				+ classId
				: " AND topic_subTopic_mappingTable.classId=" + classId
				: "");

		sql += where + rowLimit;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs;
		ArrayList list = new ArrayList();

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);

			rs = psmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				TopicSubTopicTO objTo = new TopicSubTopicTO();
				objTo.setSubjectTopicId(rs.getInt("subjectTopicId"));
				objTo.setTopicvalue(rs.getString("topicValue"));
				objTo.setSubTopicValue(rs.getString("subtopicValue"));
				objTo.setClassid(rs.getInt("classId"));
				list.add(objTo);
			}

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
		return list;
	}

	public int insertTopicSubTopic(ArrayList topicSubtopicTO) throws BaseAppException{
		int insertCount = 0;
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = GetConnection.getSimpleConnection();
			Iterator itr = topicSubtopicTO.iterator();
			while (itr.hasNext()) {
				TopicSubTopicTO objTo = (TopicSubTopicTO) itr.next();
				int newtopicID = insertNewTopic_getTopicId(objTo
						.getTopicvalue());
				int newSubTopicID = insertNewSubTopic_getSubTopicId(objTo
						.getSubTopicValue());
				String mappingTbale_sql = " INSERT INTO t_class_subject_topic_subTopic_mapping "
						+ " (subTopicId,subjectId,classId,topicId) "
						+ " VALUES(?,?,?,?)";

				psmt = con.prepareStatement(mappingTbale_sql);
				psmt.setInt(1, newSubTopicID);
				psmt.setInt(2, objTo.getSubjectId());
				psmt.setInt(3, objTo.getClassid());				
				psmt.setInt(4, newtopicID);
				insertCount += psmt.executeUpdate();
				psmt.close();
			}
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
		return insertCount;
	}
	
	public int insertNewTopic_getTopicId(String topicValue)throws BaseAppException {
		int newRecordID = -1;
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = GetConnection.getSimpleConnection();
			String sql = " INSERT INTO t_topics(topicValue) " + " VALUES(?)";
			String newTopicID_sql = "SELECT MAX(topicid) AS topicid  FROM t_topics";

			psmt = con.prepareStatement(sql);
			psmt.setString(1, topicValue);
			int insertCount = psmt.executeUpdate();
			psmt.close();
			if (insertCount > 0) {
				psmt = con.prepareStatement(newTopicID_sql);
				ResultSet rs = psmt.executeQuery();
				if (rs.next()) {
					newRecordID = rs.getInt("topicid");
				}
				psmt.close();
			}
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
		return newRecordID;
	}

	public int insertNewSubTopic_getSubTopicId(String subTopicValue) throws BaseAppException{
		int newRecordID = -1;
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = GetConnection.getSimpleConnection();
			String sql = " INSERT INTO t_subtopics(subTopicValue) "
					+ " VALUES(?)";
			String newTopicID_sql = "SELECT MAX(subTopicId) AS subTopicId  FROM t_subtopics";

			psmt = con.prepareStatement(sql);
			psmt.setString(1, subTopicValue);
			int insertCount = psmt.executeUpdate();
			psmt.close();
			if (insertCount > 0) {
				psmt = con.prepareStatement(newTopicID_sql);
				ResultSet rs = psmt.executeQuery();
				if (rs.next()) {
					newRecordID = rs.getInt("subTopicId");
				}
				psmt.close();
			}
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
		return newRecordID;
	}

	public int updateTopicBYId(int topicId, String topicValue)throws BaseAppException {
		int updateCount = -1;
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = GetConnection.getSimpleConnection();
			String sql = " UPDATE t_topics " + " SET topicValue=? "
					+ " WHERE topicId=?";

			psmt = con.prepareStatement(sql);
			psmt.setString(1, topicValue);
			psmt.setInt(2, topicId);
			updateCount = psmt.executeUpdate();
			psmt.close();
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
		return updateCount;
	}

	public int deleteTopicBYId(int topicId)throws BaseAppException {
		int deleteCount = -1;
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = GetConnection.getSimpleConnection();
			String sql = " DELETE  " + " FROM t_topics " + " WHERE topicId=?";

			psmt = con.prepareStatement(sql);
			psmt.setInt(1, topicId);
			deleteCount = psmt.executeUpdate();
			psmt.close();
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
		return deleteCount;
	}

	public int deleteSubTopicBYId(int subTopicId)throws BaseAppException {
		int deleteCount = -1;
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = GetConnection.getSimpleConnection();
			String sql = " DELETE  " + " FROM t_subtopics "
					+ " WHERE subTopicId=?";

			psmt = con.prepareStatement(sql);
			psmt.setInt(1, subTopicId);
			deleteCount = psmt.executeUpdate();
			psmt.close();
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
		return deleteCount;
	}

	public int updateSubTopicBYId(int subTopicId, String subTopicValue)throws BaseAppException {
		int updateCount = -1;
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = GetConnection.getSimpleConnection();
			String sql = " UPDATE t_subtopics " + " SET subTopicValue=? "
					+ " WHERE subTopicId=?";

			psmt = con.prepareStatement(sql);
			psmt.setString(1, subTopicValue);
			psmt.setInt(2, subTopicId);
			updateCount = psmt.executeUpdate();
			psmt.close();
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
		return updateCount;
	}

	public int updateTopicSubTopic_BySubjectTopicId(ArrayList topicSubtopicTO) throws BaseAppException{
		int recUpdateCount = 0;
		Connection con = null;
		PreparedStatement psmt = null;

		
		con = GetConnection.getSimpleConnection();
		Iterator itr = topicSubtopicTO.iterator();
		while (itr.hasNext()) {
			TopicSubTopicTO objTo = (TopicSubTopicTO) itr.next();
			int topicUpdateCount = updateTopicBYId(objTo.getTopicId(),
					objTo.getTopicvalue());
			int subTopicupdateCount = updateSubTopicBYId(objTo
						.getSubjectTopicId(), objTo.getSubTopicValue());
			if (topicUpdateCount > 0 && subTopicupdateCount > 0)
					recUpdateCount += 1;
		}		
		return recUpdateCount;
	}

	public int deleteTopicSubTopic_BySubjectTopicId(String[] idList)throws BaseAppException {
		int recDeleteCount = 0;
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = GetConnection.getSimpleConnection();
			for (int i = 0; i < idList.length; i++) {
				int topicId = 0;
				int subTopicId = 0;
				String sql = " SELECT subTopicId,topicId "
						+ " FROM t_class_subject_topic_subTopic_mapping "
						+ " WHERE subjectTopicId = " + idList[i];
				psmt = con.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				if (rs.next()) {
					topicId = rs.getInt("topicId");
					subTopicId = rs.getInt("subTopicId");
				}
				psmt.close();

				int delTopicCount = deleteTopicBYId(topicId);
				int delSubTopicCount = deleteSubTopicBYId(subTopicId);
				if (delTopicCount > 0 && delSubTopicCount > 0)
					recDeleteCount += 1;

			}
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
		return recDeleteCount;
	}
}
