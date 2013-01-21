package com.education.dao;

import java.io.BufferedInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.expframework.exceptions.BaseAppException;

import com.education.exceptions.DBDataOutOfRangeException;
import com.education.exceptions.DBDataSourceException;
import com.education.exceptions.DBIntegrityViolationException;
import com.education.exceptions.DBInvalidDataInsertionException;
import com.education.transferobj.ContentUploadTO;
import com.education.util.ExcelUtil;
import com.education.util.GetConnection;
import com.education.util.Utilities;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class OracleContentUploadDAO extends AbstractDAO{

	// method to get content upload record count
	public int getContentUploadCount(String classId, String subjectId)throws BaseAppException {

		String sql = "Select count(*) as rowcount ";
		String where = " from t_content_mapping a, t_content_mapping b"
				+ " where a.contentId=b.contentId ";

		if (null != classId && !"".equals(classId)) {
			where = where + " AND a.classid=" + classId;
		}
		if (null != subjectId && !"".equals(subjectId)) {
			where = where + " AND a.subjectId=" + subjectId;
		}
		sql = sql + where;
		Connection con = null;
		PreparedStatement psmt;
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

	public ArrayList getContentUploadDetails(ContentUploadTO contentTO,
			int frmRecord, int noOfRecords) throws BaseAppException {
		ArrayList list = new ArrayList();

		String sql = "SELECT contentTable.contentId, "
				+ " contentTable.contentFileName, "
				+ " contentTable.hasSupportingFiles,"
				+ " contentTable.contentType, "
				+ " suppFilesTable.SupportingFileName, "
				+ " mappingTable.classId, "
				+ " mappingTable.subjectId, "
				+ " mappingTable.topicId, "
				+ " mappingTable.subtopicId, "
				+ " topicTable.topicValue,subTopicTable.subTopicValue "
				+ " FROM "
				+ " t_contents contentTable "
				+ " LEFT JOIN t_content_supporting_files suppFilesTable  ON contentTable.contentId = suppFilesTable.contentId "
				+ " INNER JOIN t_content_mapping mappingTable ON contentTable.contentId = mappingTable.contentId "
				+ " INNER JOIN t_topics topicTable ON mappingTable.topicId = topicTable.topicId "
				+ " INNER JOIN t_subtopics subTopicTable ON mappingTable.subTopicId = subTopicTable.subTopicId ";

		String where = "";

		String rowLimit = " limit " + frmRecord + "," + noOfRecords;

		if (null != contentTO.getClassId()
				&& !"".equals(contentTO.getClassId())) {
			where = "".equals(where) ? " WHERE mappingTable.classId ="
					+ contentTO.getClassId() : where
					+ " mappingTable.classId =" + contentTO.getClassId();
		}
		if (null != contentTO.getSubjectId()
				&& !"".equals(contentTO.getSubjectId())) {
			where = "".equals(where) ? " WHERE mappingTable.subjectId ="
					+ contentTO.getSubjectId() : where
					+ " AND mappingTable.subjectId ="
					+ contentTO.getSubjectId();
		}
		sql = sql + where + rowLimit;

		Connection con = null;
		PreparedStatement psmt;
		ResultSet rs;

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ContentUploadTO objTo = new ContentUploadTO();
				objTo.setContentId(rs.getString("contentId"));
				objTo.setContentFileName(rs.getString("contentFileName"));
				objTo.setHasSupportingFiles(rs.getInt("hasSupportingFiles"));
				objTo.setClassId(rs.getString("classId"));
				objTo.setSubjectId(rs.getString("subjectId"));
				objTo.setTopicId(rs.getString("topicId"));
				objTo.setSubTopicId(rs.getString("subtopicId"));
				objTo
						.setSupportingtFileName(rs
								.getString("SupportingFileName"));
				objTo.setContentTypeID(rs.getInt("contentType"));
				objTo.setTopicValue(rs.getString("topicValue"));
				objTo.setSubTopicValue(rs.getString("subTopicValue"));
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

	public ContentUploadTO getContent_Blob_ById(int contentID) throws BaseAppException{
		ContentUploadTO contentTO = null;
		String sql = "SELECT contentTable.contentId, "
				+ " contentTable.contentStream, "
				+ " contentTable.contentFileName, "
				+ " contentTable.hasSupportingFiles,"
				+ " contentTable.contentType, "
				+ " suppFilesTable.SupportingFileName, "
				+ " suppFilesTable.supportingFileStream " + " FROM "
				+ " t_contents contentTable "
				+ " LEFT JOIN t_content_supporting_files suppFilesTable"
				+ " ON contentTable.contentId = suppFilesTable.contentId "
				+ " INNER JOIN t_content_mapping mappingTable "
				+ " ON contentTable.contentId = mappingTable.contentId ";

		String where = " AND contentTable.contentId = " + contentID;

		sql = sql + where;

		Connection con = null;
		PreparedStatement psmt;
		ResultSet rs;

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				contentTO = new ContentUploadTO();
				contentTO.setContentId(rs.getString("contentId"));
				contentTO.setContentFileName(rs.getString("contentFileName"));
				contentTO.setContentFileStream(new BufferedInputStream(rs
						.getBinaryStream("contentStream")));

				contentTO
						.setHasSupportingFiles(rs.getInt("hasSupportingFiles"));
				contentTO.setSupportingtFileName(rs
						.getString("SupportingFileName"));
				contentTO.setSupportingFileStream(rs
						.getBinaryStream("supportingFileStream"));

				contentTO.setContentTypeID(rs.getInt("contentType"));
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
		return contentTO;
	}

	public int insertContentUploadDetails(ContentUploadTO contentTo)throws BaseAppException {

		Connection con = null;
		PreparedStatement psmt;
		ResultSet rs = null;
		int hasSuppFile = (contentTo.getSupportingFileStream() == null ? 0 : 1);
		int contentTableQueryResult = 0;

		String contentTableQuery = "insert into t_contents "
				+ "(contentFileName,hasSupportingFiles,contentStream,contentType) "
				+ " values(?,?,?,?)";

		String newContentIdQuery = "SELECT MAX(contentId) as rowcount "
				+ " FROM t_contents";

		String suppTableQuery = "INSERT INTO t_content_supporting_files "
				+ "(SupportingFileName,contentId,supportingFileStream)"
				+ "VALUES(?,?,?)";

		String mappingTableQuery = "INSERT INTO t_content_mapping "
				+ "(contentId,classId, " + " subjectId,topicId, "
				+ " subTopicId) " + " VALUES " + " (?,?,?,?,?)";

		try {
			con = GetConnection.getSimpleConnection();
			String newContentId = null;

			// insert content table details
			psmt = con.prepareStatement(contentTableQuery);
			psmt.setString(1, contentTo.getContentFileName());
			psmt.setInt(2, hasSuppFile);
			psmt.setBinaryStream(3, contentTo.getContentFileStream(), contentTo
					.getContentStreamLength());
			psmt.setInt(4, contentTo.getContentTypeID());
			contentTableQueryResult = psmt.executeUpdate();

			if (contentTableQueryResult > 0) {

				// Get new ContentId
				psmt = con.prepareStatement(newContentIdQuery);
				rs = psmt.executeQuery();
				if (rs.next()) {
					newContentId = rs.getString("rowcount");
				}
				contentTo.setContentId(newContentId);

				// insert record into supporting table
				if (hasSuppFile == 1) {
					psmt = con.prepareStatement(suppTableQuery);
					psmt.setString(1, contentTo.getSupportingtFileName());
					psmt.setString(2, contentTo.getContentId());
					psmt.setBinaryStream(3,
							contentTo.getSupportingFileStream(), contentTo
									.getSupportingStreamLength());
					int suppTableQueryResult = psmt.executeUpdate();
				}

				// insert record into mapping table
				psmt = con.prepareStatement(mappingTableQuery);
				psmt.setString(1, contentTo.getContentId());
				psmt.setString(2, contentTo.getClassId());
				psmt.setString(3, contentTo.getSubjectId());
				psmt.setString(4, contentTo.getTopicId());
				psmt.setString(5, contentTo.getSubTopicId());
				psmt.executeUpdate();

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
		return contentTableQueryResult;
	}

	public int updateContentUploadDetails(ContentUploadTO contentTo) throws BaseAppException {

		Connection con = null;
		PreparedStatement psmt;
		int hasExistingSuppFile = 0;
		boolean update_suppTable = false;
		boolean insert_suppTable = false;
		int contentTableQueryResult = 0;

		// has existing supportin file
		hasExistingSuppFile = (contentTo.getHasSupportingFiles() == 1 ? 1 : 0);

		// check Insert/Update
		if (contentTo.getSupportingFileStream() != null) {

			if (hasExistingSuppFile == 1) {
				update_suppTable = true;
			} else {
				insert_suppTable = true;
			}
			hasExistingSuppFile = 1;
		}

		String contentTableQuery = " UPDATE t_contents "
				+ " SET contentFileName=?,hasSupportingFiles=?,contentStream=?,"
				+ " contentType=? " + " WHERE contentId=?";

		String contentTable_HasSuppFile = " UPDATE t_contents "
				+ " SET hasSupportingFiles=?" + " WHERE contentId=? ";

		String mappingTableQuery = " UPDATE t_content_mapping "
				+ " SET topicId=?,subTopicId=?,classId=?,subjectId=? "
				+ " WHERE contentId=?";

		String suppTableInsertQuery = "INSERT INTO t_content_supporting_files "
				+ "(SupportingFileName,contentId,supportingFileStream)"
				+ "VALUES(?,?,?)";

		String suppTableUpdateQuery = "UPDATE t_content_supporting_files "
				+ "SET SupportingFileName=?,supportingFileStream=? "
				+ " WHERE contentId=?";

		try {
			con = GetConnection.getSimpleConnection();
			String newContentId = null;

			if (!Utilities.isNullOrBlank(contentTo.getContentFileName())
					&& contentTo.getContentFileStream() != null) {
				// update content table details
				psmt = con.prepareStatement(contentTableQuery);
				psmt.setString(1, contentTo.getContentFileName());
				psmt.setInt(2, hasExistingSuppFile);
				psmt.setBinaryStream(3, contentTo.getContentFileStream(),
						contentTo.getContentStreamLength());
				psmt.setInt(4, contentTo.getContentTypeID());
				psmt.setString(5, contentTo.getContentId());
				contentTableQueryResult = psmt.executeUpdate();
			}
			// update mapping table
			psmt = con.prepareStatement(mappingTableQuery);
			psmt.setString(1, contentTo.getTopicId());
			psmt.setString(2, contentTo.getSubTopicId());
			psmt.setString(3, contentTo.getClassId());
			psmt.setString(4, contentTo.getSubjectId());
			psmt.setString(5, contentTo.getContentId());
			int mappingTableQueryResult = psmt.executeUpdate();

			if (insert_suppTable) {
				// insert new record into supporting table
				psmt = con.prepareStatement(suppTableInsertQuery);
				psmt.setString(1, contentTo.getSupportingtFileName());
				psmt.setString(2, contentTo.getContentId());
				psmt.setBinaryStream(3, contentTo.getSupportingFileStream(),
						contentTo.getSupportingStreamLength());
				psmt.executeUpdate();

				// update has supporting column of content table
				psmt = con.prepareStatement(contentTable_HasSuppFile);
				psmt.setInt(1, 1);
				psmt.setString(2, contentTo.getContentId());
				psmt.executeUpdate();

			} else if (update_suppTable) {
				// update supporting table record
				psmt = con.prepareStatement(suppTableUpdateQuery);
				psmt.setString(1, contentTo.getSupportingtFileName());
				psmt.setBinaryStream(2, contentTo.getSupportingFileStream(),
						contentTo.getSupportingStreamLength());
				psmt.setString(3, contentTo.getContentId());
				psmt.executeUpdate();

				// update has supporting column of content table
				psmt = con.prepareStatement(contentTable_HasSuppFile);
				psmt.setInt(1, 1);
				psmt.setString(2, contentTo.getContentId());
				psmt.executeUpdate();
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
		return contentTableQueryResult;
	}

	public int deleteContentUploadDetails(int contentId) throws BaseAppException{
		Connection con = null;
		PreparedStatement psmt = null;
		String contentTable_DeleteQ = "delete from t_contents where contentId =?";
		// String suppTable_DeleteQ = "delete from t_content_mapping where
		// contentId =?";
		// String contentMappingTable_DeleteQ = "delete from
		// t_content_supporting_files where contentId =?";
		int recDeleteCount = 0;

		try {
			con = GetConnection.getSimpleConnection();
			// psmt = con.prepareStatement(suppTable_DeleteQ);
			// psmt.setInt(1,contentId);
			// psmt.executeUpdate();
			//			
			// psmt = con.prepareStatement(contentMappingTable_DeleteQ);
			// psmt.setInt(1,contentId);
			// psmt.executeUpdate();

			psmt = con.prepareStatement(contentTable_DeleteQ);
			psmt.setInt(1, contentId);
			recDeleteCount = psmt.executeUpdate();

		
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
	
	public HSSFWorkbook downloadFile()throws BaseAppException{
		ExcelUtil excelUtil = new ExcelUtil();
		String sql = "Select * from t_session_tracking";		
		try {
			return excelUtil.exportExcelToStream(sql, new ArrayList());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BaseAppException(e.getMessage());
		}
	} 
}
