/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.education.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.struts.upload.FormFile;
import org.expframework.exceptions.BaseAppException;

import com.education.exceptions.DBDataOutOfRangeException;
import com.education.exceptions.DBDataSourceException;
import com.education.exceptions.DBIntegrityViolationException;
import com.education.exceptions.DBInvalidDataInsertionException;
import com.education.transferobj.ClassCertTO;
import com.education.transferobj.ContentUploadTO;
import com.education.transferobj.LinkQTOClassTO;
import com.education.transferobj.QuestionBankTO;
import com.education.transferobj.TopicSubTopicTO;
import com.education.util.EducationConstant;
import com.education.util.GetConnection;
import com.education.util.Utilities;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

/**
 * 
 * @author Administrator
 */
public class OracleQuestionbankDAO extends AbstractDAO{

	private boolean doEntry_QuestionBank_Table(QuestionBankTO qTo,Connection con) 
			throws BaseAppException,SQLException  {
		int recUpdateCount = 0;
		int questionId = qTo.getQuestionId();
		String topic = qTo.getTopic();
		String subTopic = qTo.getSubTopic() ;
		int isGraphics = qTo.getIsGraphics();
		String answer = qTo.getAnswer();
		int questionStatusId = qTo.getQuestionStatusId();		
		String createdBy = qTo.getCreatedBy();
		String subject = qTo.getSubject();

		String questionBank_Table_Q = "insert into t_question_bank "
				+ "(QuestionId,Subject,topicId,subTopicId,IsGraphics,Answer,"
				+ "QuestionStatusId,CreatedBy,CreatedOn)"
				+ "values(?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement psmt = null;
		//try {

			//con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(questionBank_Table_Q);
			psmt.setInt(1, questionId);
			psmt.setString(2, subject);
			psmt.setString(3, topic);
			psmt.setString(4, subTopic);
			psmt.setInt(5, isGraphics);
			psmt.setString(6, answer);
			psmt.setInt(7, questionStatusId);
			psmt.setString(8, createdBy);
			psmt.setDate(9, new java.sql.Date(Utilities.getCurrentDB_Date()));
			recUpdateCount = psmt.executeUpdate();
			psmt.close();
//		}catch (MySQLIntegrityConstraintViolationException e) {
//			throw new DBIntegrityViolationException(e.getMessage());
//		}catch (MysqlDataTruncation e) {
//			throw new DBDataOutOfRangeException(e.getMessage(),
//						Utilities.getDataTooLongExceptionColumnName(e.getMessage()));
//		}catch(SQLException e){
//			if(e.getMessage().contains("Incorrect"))
//				throw new DBInvalidDataInsertionException(e.getMessage(),
//						Utilities.getDataMissingExceptionColumnName(e.getMessage()),
//						Utilities.getDataMissingExceptionColumValue(e.getMessage()));
//			else
//				throw new RuntimeException(e);
//		}
		return (recUpdateCount > 0 ? true : false);
	}

	public int insertQuestion(QuestionBankTO qTo)
				throws BaseAppException {
		int recUpdateCount = 0;
		int questionId = qTo.getQuestionId();
		String question = qTo.getQuestion();
		String createdBy =  qTo.getCreatedBy();
		String answerDiscription = qTo.getAnswerDiscription();
		String option1 =  qTo.getOption1();
		String option2 = qTo.getOption2();
		String option3 = qTo.getOption3();
		String option4 =  qTo.getOption4();
		String option5 = qTo.getOption5();
		String subject =  qTo.getSubject();
		String certOrClass = qTo.getCert();
		String questionComplexity =  qTo.getComplexity();

		String textquestion_Table_Q = "insert into t_text_question"
				+ "(QuestionId,Description,Option_1,Option_2,Option_3,Option_4,Option_5,AnswerExplanation)"
				+ "values(?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			//check if the data is valid
			/* if(Utilities.isNullOrBlank(answerDiscription)){
				DBInvalidDataInsertionException ex = new DBInvalidDataInsertionException("Invalid data for Answer column");
				ex.setColumnName("Answer");
				ex.setColumnValue("");
				throw ex;
			}*/
			//check if the data is valid
			if(Utilities.isNullOrBlank(subject)){
				DBInvalidDataInsertionException ex = new DBInvalidDataInsertionException("Invalid data for Subject column");
				ex.setColumnName("Subject");
				ex.setColumnValue("");
				throw ex;
			}
			con = GetConnection.getSimpleConnection();
			doEntry_QuestionBank_Table(qTo,con);			
			psmt = con.prepareStatement(textquestion_Table_Q);
			psmt.setInt(1, questionId);
			psmt.setString(2, question);
			psmt.setString(3, option1);
			psmt.setString(4, option2);
			psmt.setString(5, option3);
			psmt.setString(6, option4);
			psmt.setString(7, option5);
			psmt.setString(8, answerDiscription);
			recUpdateCount = psmt.executeUpdate();

			// Add Question->class linkage data in DB
			int newQuestionID = getNextQuestionId();
			ArrayList list = new ArrayList();
			LinkQTOClassTO linkQclassTO = new LinkQTOClassTO();
			linkQclassTO.setClassId(certOrClass);
			linkQclassTO.setComplexityId(questionComplexity);
			linkQclassTO.setLinkedBy(createdBy);
			linkQclassTO.setQuid(String.valueOf(newQuestionID));
			list.add(linkQclassTO);
			linkQuestionToClass(list);

		}
		/*catch (MySQLIntegrityConstraintViolationException e) {
			throw new DBIntegrityViolationException(e.getMessage());
		}catch (MysqlDataTruncation e) {
			throw new DBDataOutOfRangeException(e.getMessage(),
						Utilities.getDataTooLongExceptionColumnName(e.getMessage()));
		}*/
		catch(SQLException e){
			if(e.getMessage().contains("Incorrect"))
				throw new DBInvalidDataInsertionException(e.getMessage(),
						Utilities.getDataMissingExceptionColumnName(e.getMessage()),
						Utilities.getDataMissingExceptionColumValue(e.getMessage()));
			else
				throw new RuntimeException(e);
		}finally {
			try {
				if(con !=null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return recUpdateCount;
	}

	public int updateQuestion(QuestionBankTO qTo) throws BaseAppException  {
		int updateCount = 0;
		int questionId = qTo.getQuestionId();
		String question = qTo.getQuestion();
		String topicId = qTo.getTopicId();		
		String subTopicId = qTo.getSubTopicId();
		int isGraphics = qTo.getIsGraphics();
		String answer = qTo.getAnswer();
		String answerDiscription = qTo.getAnswerDiscription();
		String option1 = qTo.getOption1();
		String option2 = qTo.getOption2();
		String option3 = qTo.getOption3();
		String option4 = qTo.getOption4();
		String option5 = qTo.getOption5();
		String subject = qTo.getSubject();

		String sql = " update t_question_bank "
				+ " set Subject= ?,TopicId= ?,SubTopicId=?, "
				+ " IsGraphics=?,Answer=? " + " where QuestionId=? ";

		String sql2 = " update t_text_question "
				+ " set Description= ?,Option_1=?, "
				+ " Option_2=?,Option_3=?,Option_4=?, "
				+ " Option_5=?,AnswerExplanation=? " + " where QuestionId=? ";

		Connection con = null;
		PreparedStatement psmt = null;
		try {

			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);

			psmt.setString(1, subject);
			psmt.setString(2, topicId);
			psmt.setString(3, subTopicId);
			psmt.setInt(4, isGraphics);
			psmt.setString(5, answer);
			psmt.setInt(6, questionId);

			updateCount = psmt.executeUpdate();
			psmt.close();
			psmt = con.prepareStatement(sql2);

			psmt.setString(1, question);
			psmt.setString(2, option1);
			psmt.setString(3, option2);
			psmt.setString(4, option3);
			psmt.setString(5, option4);
			psmt.setString(6, option5);
			psmt.setString(7, answerDiscription);
			psmt.setInt(8, questionId);
			psmt.executeUpdate();

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

	public int getNextQuestionId() throws BaseAppException {
		int count = 0;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs;
		String sql = "select max(questionid) as rowcount from t_question_bank";
		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("rowcount");
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
		return count;
	}

	// method to get question list
	public ArrayList getQuestions(String classID, String subject,
			String statusId, String topic, String frmDate, String toDate,
			Integer isGraphics, int frmRecord, int totalNoOfRecords) throws BaseAppException {
		
		 

		String where = "";
		String where1 = " where a.questionid=b.questionid "
				+ " and a.topicId=topic.topicId "
				+ " and a.subTopicId=subTopic.subTopicId ";
		String where2 = " where a.questionid=d.questionid "
				+ " and a.topicId=topic.topicId "
				+ " and a.subTopicId=subTopic.subTopicId ";

		String fromClause1 = " From t_question_bank a ,t_text_question b,"
				+ " t_topics topic,t_subtopics subTopic ";
		String fromClause2 = " From t_question_bank a ,t_picture_question d,"
				+ " t_topics topic,t_subtopics subTopic ";
		
		String limit = " Limit " + frmRecord + "," + totalNoOfRecords + " ";
		
		
		
		
		if (null != classID && !classID.equalsIgnoreCase("")) {

			fromClause1 += ",t_class_question_link c ";
			fromClause2 += ",t_class_question_link c ";
			where1 += " and c.questionid=a.questionid "
					+ " and c.classcertid='" + classID + "' ";
			where2 += " and c.questionid=a.questionid "
					+ " and c.classcertid='" + classID + "' ";
		}
		if (null != subject && !subject.equalsIgnoreCase("")) {
			where = where + " and a.subject='" + subject + "'";
		}
		if (null != statusId && !statusId.equalsIgnoreCase("")) {
			where = where + " and a.QuestionStatusId=" + statusId;
		}
		if (null != topic && !topic.equalsIgnoreCase("")) {
			where = where + " and a.topicId='" + topic + "'";
		}
		if (null != frmDate && !frmDate.equalsIgnoreCase("")) {
			where = where
					+ " and a.createdOn >= '"
					+ Utilities.getDate_DBFormat(frmDate,
							EducationConstant.DISPLAY_DATE_FORMAT) + "'";
		}
		if (null != toDate && !toDate.equalsIgnoreCase("")) {
			where = where
					+ " and a.createdOn <= '"
					+ Utilities.getDate_DBFormat(toDate,
							EducationConstant.DISPLAY_DATE_FORMAT) + "'";
		}
		where1 = where1 + where ;
		where2 = where2 + where ;
		if (null != isGraphics && isGraphics != 0) {
			where2 = where2 + " and a.IsGraphics = " + isGraphics.intValue();
			where1 = where1 + " and a.IsGraphics = '1' ";
			where1 = where1 + limit;
		} else if (isGraphics == 0 || isGraphics == null) {
			where1 = where1 + " and a.IsGraphics = '0' ";
			where2 = where2 + " and a.IsGraphics = '1' ";
			where2 = where2 + limit;
			
		}
		
		

		String sql = "select a.QuestionId,a.Subject,topic.topicId,topic.topicValue,subTopic.subTopicId,subTopic.subTopicValue,a.IsGraphics,"
				+ "a.Answer,a.QuestionStatusId,a.IsVerified,a.CreatedBy,a.CreatedOn,a.VerifiedBy,a.VerificationRemark,"
				+ "b.Description,b.Option_1,b.Option_2,b.Option_3,b.Option_4,b.Option_5,b.AnswerExplanation "
				+ fromClause1
				+ " "
				+ where1
				+ " UNION ALL "
				+ "select a.QuestionId,a.Subject,topic.topicId,topic.topicValue,subTopic.subTopicId,subTopic.subTopicValue,a.IsGraphics,"
				+ "a.Answer,a.QuestionStatusId,a.IsVerified,a.CreatedBy,a.CreatedOn,a.VerifiedBy,a.VerificationRemark,"
				+ "d.Description,d.Option_1,d.Option_2,d.Option_3,d.Option_4,d.Option_5,d.AnswerExplanation "
				+ fromClause2 + " " + where2;
		
		
		Connection con = null;
		PreparedStatement psmt;
		ResultSet rs;
		ArrayList questionList = new ArrayList();

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			int i = 0;
			while (rs.next()) {

				QuestionBankTO questionBankTO = new QuestionBankTO();
				questionBankTO.setAnswer(rs.getString("answer"));
				questionBankTO.setAnswerDiscription(rs
						.getString("AnswerExplanation"));
				questionBankTO.setCreatedBy(rs.getString("createdBy"));
				questionBankTO.setIsGraphics(rs.getInt("isGraphics"));
				questionBankTO.setIsVerified(rs.getInt("isVerified"));
				questionBankTO.setOption1(rs.getString("option_1"));
				questionBankTO.setOption2(rs.getString("option_2"));
				questionBankTO.setOption3(rs.getString("option_3"));
				questionBankTO.setOption4(rs.getString("option_4"));
				questionBankTO.setOption5(rs.getString("option_5"));
				questionBankTO.setQuestion(rs.getString("Description"));
				questionBankTO.setQuestionId(rs.getInt("questionId"));
				questionBankTO.setQuestionStatusId(rs
						.getInt("QuestionStatusId"));
				questionBankTO.setSubject(rs.getString("subject"));
				questionBankTO.setSubTopic(rs.getString("subTopicValue"));
				questionBankTO.setTopic(rs.getString("topicValue"));
				questionBankTO.setVerificationRemark(rs
						.getString("verificationRemark"));
				questionBankTO.setVerifiedBy(rs.getString("verifiedBy"));
				questionBankTO.setCreatedOn(rs.getString("CreatedOn"));
				questionBankTO.setTopicId(rs.getString("topicId"));
				questionBankTO.setSubTopicId(rs.getString("subTopicId"));
				questionList.add(questionBankTO);
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
		return questionList;
	}
	
	
	/** for TEACHER
	 * @param classID
	 * @param subject
	 * @param statusId
	 * @param topic
	 * @param frmDate
	 * @param toDate
	 * @param isGraphics
	 * @param frmRecord
	 * @param totalNoOfRecords
	 * @return
	 * @throws BaseAppException
	 */
	public ArrayList getQuestions(String classID, String subject,
			String statusId, String topic, String frmDate, String toDate,
			Integer isGraphics, int frmRecord, int totalNoOfRecords, int userID) throws BaseAppException {

		String where = "";
		String where1 = "where a.questionid=b.questionid "
				+ " and a.topicId=topic.topicId "
				+ " and a.subTopicId=subTopic.subTopicId ";
		String where2 = " where a.questionid=d.questionid "
				+ " and a.topicId=topic.topicId "
				+ " and a.subTopicId=subTopic.subTopicId ";

		String fromClause1 = " From t_question_bank a ,t_text_question b,"
				+ " t_topics topic,t_subtopics subTopic ";
		String fromClause2 = " From t_question_bank a ,t_picture_question d,"
				+ " t_topics topic,t_subtopics subTopic ";

		String limit = " Limit " + frmRecord + "," + totalNoOfRecords + " ";

		if (null != classID && !classID.equalsIgnoreCase("")) {

			fromClause1 += ",t_class_question_link c ";
			fromClause2 += ",t_class_question_link c ";
			where1 += " and c.questionid=a.questionid "
					+ " and c.classcertid='" + classID + "' ";
			where2 += " and c.questionid=a.questionid "
					+ " and c.classcertid='" + classID + "' ";
		}
		if (null != subject && !subject.equalsIgnoreCase("")) {
			where = where + " and a.subject='" + subject + "'";
		}
		if (null != statusId && !statusId.equalsIgnoreCase("")) {
			where = where + " and a.QuestionStatusId=" + statusId;
		}
		if (null != topic && !topic.equalsIgnoreCase("")) {
			where = where + " and a.topicId='" + topic + "'";
		}
		if (null != frmDate && !frmDate.equalsIgnoreCase("")) {
			where = where
					+ " and a.createdOn >= '"
					+ Utilities.getDate_DBFormat(frmDate,
							EducationConstant.DISPLAY_DATE_FORMAT) + "'";
		}
		if (null != toDate && !toDate.equalsIgnoreCase("")) {
			where = where
					+ " and a.createdOn <= '"
					+ Utilities.getDate_DBFormat(toDate,
							EducationConstant.DISPLAY_DATE_FORMAT) + "'";
		}
		where = where  + " and a.CreatedBy='" + userID + "' ";
		where1 = where1 + where ;
		where2 = where2 + where ;
		if (null != isGraphics && isGraphics != 0) {
			where2 = where2 + " and a.IsGraphics = " + isGraphics.intValue();
			where1 = where1 + " and a.IsGraphics = '1' ";
			where1 = where1 + limit;
		} else if (isGraphics == 0 || isGraphics == null) {
			where1 = where1 + " and a.IsGraphics = '0' ";
			where2 = where2 + " and a.IsGraphics = '1' ";
			where2 = where2 + limit;
		}

		String sql = "select a.QuestionId,a.Subject,topic.topicId,topic.topicValue,subTopic.subTopicId,subTopic.subTopicValue,a.IsGraphics,"
				+ "a.Answer,a.QuestionStatusId,a.IsVerified,a.CreatedBy,a.CreatedOn,a.VerifiedBy,a.VerificationRemark,"
				+ "b.Description,b.Option_1,b.Option_2,b.Option_3,b.Option_4,b.Option_5,b.AnswerExplanation "
				+ fromClause1
				+ " "
				+ where1
				+ " UNION ALL "
				+ "select a.QuestionId,a.Subject,topic.topicId,topic.topicValue,subTopic.subTopicId,subTopic.subTopicValue,a.IsGraphics,"
				+ "a.Answer,a.QuestionStatusId,a.IsVerified,a.CreatedBy,a.CreatedOn,a.VerifiedBy,a.VerificationRemark,"
				+ "d.Description,d.Option_1,d.Option_2,d.Option_3,d.Option_4,d.Option_5,d.AnswerExplanation "
				+ fromClause2 + " " + where2;
	
		Connection con = null;
		PreparedStatement psmt;
		ResultSet rs;
		ArrayList questionList = new ArrayList();

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			int i = 0;
			while (rs.next()) {

				QuestionBankTO questionBankTO = new QuestionBankTO();
				questionBankTO.setAnswer(rs.getString("answer"));
				questionBankTO.setAnswerDiscription(rs
						.getString("AnswerExplanation"));
				questionBankTO.setCreatedBy(rs.getString("createdBy"));
				questionBankTO.setIsGraphics(rs.getInt("isGraphics"));
				questionBankTO.setIsVerified(rs.getInt("isVerified"));
				questionBankTO.setOption1(rs.getString("option_1"));
				questionBankTO.setOption2(rs.getString("option_2"));
				questionBankTO.setOption3(rs.getString("option_3"));
				questionBankTO.setOption4(rs.getString("option_4"));
				questionBankTO.setOption5(rs.getString("option_5"));
				questionBankTO.setQuestion(rs.getString("Description"));
				questionBankTO.setQuestionId(rs.getInt("questionId"));
				questionBankTO.setQuestionStatusId(rs
						.getInt("QuestionStatusId"));
				questionBankTO.setSubject(rs.getString("subject"));
				questionBankTO.setSubTopic(rs.getString("subTopicValue"));
				questionBankTO.setTopic(rs.getString("topicValue"));
				questionBankTO.setVerificationRemark(rs
						.getString("verificationRemark"));
				questionBankTO.setVerifiedBy(rs.getString("verifiedBy"));
				questionBankTO.setCreatedOn(rs.getString("CreatedOn"));
				questionBankTO.setTopicId(rs.getString("topicId"));
				questionBankTO.setSubTopicId(rs.getString("subTopicId"));
				questionList.add(questionBankTO);
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
		return questionList;
	}

	// method to get question list
	public int getQuestionsCount(String classID, String subject,
			String statusId, String topic, String frmDate, String toDate,
			Integer isGraphics) throws BaseAppException {

		String where = "";
		String where1 = " where a.questionid=b.questionid ";
		String where2 = " where a.questionid=d.questionid ";
		if (null != classID && !classID.equalsIgnoreCase("")) {

			where1 = ",t_class_question_link c where c.questionid=a.questionid"
					+ " and c.questionid=b.questionid "
					+ " and c.classcertid='" + classID + "'";
			where2 = ",t_class_question_link c where c.questionid=a.questionid"
					+ " and c.questionid=d.questionid "
					+ " and c.classcertid='" + classID + "'";
		}
		if (null != subject && !subject.equalsIgnoreCase("")) {
			where = where + " and a.subject='" + subject + "'";
		}
		if (null != statusId && !statusId.equalsIgnoreCase("")) {
			where = where + " and a.QuestionStatusId=" + statusId;
		}
		if (null != topic && !topic.equalsIgnoreCase("")) {
			where = where + " and a.topicId='" + topic + "'";
		}
		if (null != frmDate && !frmDate.equalsIgnoreCase("")) {
			where = where
					+ " and a.createdOn >= '"
					+ Utilities.getDate_DBFormat(frmDate,
							EducationConstant.DISPLAY_DATE_FORMAT) + "'";
		}
		if (null != toDate && !toDate.equalsIgnoreCase("")) {
			where = where
					+ " and a.createdOn <= '"
					+ Utilities.getDate_DBFormat(toDate,
							EducationConstant.DISPLAY_DATE_FORMAT) + "'";
		}
		if (null != isGraphics && isGraphics != 0) {
			where = where +  "  and a.IsGraphics = "  + isGraphics.intValue();
		}
		where1 = where1  +  where;
		where2 = where2  +  where;
		String sql = "select count(temp.questionid) as rowcount from "
				+ "( select a.questionid from t_question_bank a , t_text_question b "
				+ where1
				+ " union all "
				+ "select a.questionid from t_question_bank a ,t_picture_question d "
				+ where2 + " ) " + "as temp";

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
	
	
	/**
	 *  for TEACHER
	 * @param classID
	 * @param subject
	 * @param statusId
	 * @param topic
	 * @param frmDate
	 * @param toDate
	 * @param isGraphics
	 * @param userId
	 * @return
	 * @throws BaseAppException
	 */
	public int getQuestionsCount(String classID, String subject,
			String statusId, String topic, String frmDate, String toDate,
			Integer isGraphics, int userId) throws BaseAppException {

		String where = "";
		String where1 = " where a.questionid=b.questionid ";
		String where2 = " where a.questionid=d.questionid ";
		if (null != classID && !classID.equalsIgnoreCase("")) {

			where1 = ",t_class_question_link c where c.questionid=a.questionid"
					+ " and c.questionid=b.questionid "
					+ " and c.classcertid='" + classID + "'";
			where2 = ",t_class_question_link c where c.questionid=a.questionid"
					+ " and c.questionid=d.questionid "
					+ " and c.classcertid='" + classID + "'";
		}
		if (null != subject && !subject.equalsIgnoreCase("")) {
			where = where + " and a.subject='" + subject + "'";
		}
		if (null != statusId && !statusId.equalsIgnoreCase("")) {
			where = where + " and a.QuestionStatusId=" + statusId;
		}
		if (null != topic && !topic.equalsIgnoreCase("")) {
			where = where + " and a.topicId='" + topic + "'";
		}
		if (null != frmDate && !frmDate.equalsIgnoreCase("")) {
			where = where
					+ " and a.createdOn >= '"
					+ Utilities.getDate_DBFormat(frmDate,
							EducationConstant.DISPLAY_DATE_FORMAT) + "'";
		}
		if (null != toDate && !toDate.equalsIgnoreCase("")) {
			where = where
					+ " and a.createdOn <= '"
					+ Utilities.getDate_DBFormat(toDate,
							EducationConstant.DISPLAY_DATE_FORMAT) + "'";
		}
		if (null != isGraphics && isGraphics != 0) {
			where = where + " and a.IsGraphics = " + isGraphics.intValue();
		}
		where = where + " and a.CreatedBy='" + userId + "' ";
		where1 = where1 + where;
		where2 = where2 + where;
		String sql = "select count(temp.questionid) as rowcount from "
				+ "( select a.questionid from t_question_bank a , t_text_question b "
				+ where1
				+ " union all "
				+ "select a.questionid from t_question_bank a ,t_picture_question d "
				+ where2 + " ) " + "as temp";

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

	public int deleteQuestionbyId(String[] qIds)throws BaseAppException {
		int deleteCount = 0;
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
		String questionBank_Table_Q = "delete from t_question_bank  where questionid in"
				+ in;
		// String sql2 = "delete from t_text_question where questionid in" + in;
		// String sql3 = "delete from t_class_question_link where questionid
		// in"+ in;
		// String sql4 = "delete from t_picture_question where questionid in"+
		// in;
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = GetConnection.getSimpleConnection();
			// psmt = con.prepareStatement(sql4);
			// psmt.executeUpdate();
			// psmt.close();
			//			
			// psmt = con.prepareStatement(sql3);
			// psmt.executeUpdate();
			// psmt.close();
			//			
			// psmt = con.prepareStatement(sql2);
			// psmt.executeUpdate();
			// psmt.close();

			psmt = con.prepareStatement(questionBank_Table_Q);
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

	// Method for getting Question list for specific user
	public ArrayList getQuestionsByUserID(String userID, String classID,
			String subject, String statusId, String topic, String frmDate,
			String toDate, Integer isGraphics, int fromRecIndex,
			int totalNoOfRecords)throws BaseAppException {

		String where = "";
		String where1 = " where a.questionid=b.questionid "
				+ " and a.topicId=topic.topicId "
				+ " and a.subTopicId=subTopic.subTopicId "
				+ " and a.createdBY='" + userID + "' ";
		String where2 = " where a.questionid=d.questionid "
				+ " and a.topicId=topic.topicId "
				+ " and a.subTopicId=subTopic.subTopicId "
				+ " and a.createdBY='" + userID + "' ";

		String fromClause1 = " From t_question_bank a ,t_text_question b,"
				+ " t_topics topic,t_subtopics subTopic ";
		String fromClause2 = " From t_question_bank a ,t_picture_question d,"
				+ " t_topics topic,t_subtopics subTopic ";

		String limit = " Limit " + fromRecIndex + "," + totalNoOfRecords + " ";

		if (null != classID && !classID.equalsIgnoreCase("")) {

			fromClause1 += ",t_class_question_link c ";
			fromClause2 += ",t_class_question_link c ";
			where1 += " and c.questionid=a.questionid "
					+ " and c.classcertid='" + classID + "' ";
			where2 += " and c.questionid=a.questionid "
					+ " and c.classcertid='" + classID + "' ";
		}
		if (null != subject && !subject.equalsIgnoreCase("")) {
			where = where + " and a.subject='" + subject + "'";
		}
		if (null != statusId && !statusId.equalsIgnoreCase("")) {
			where = where + " and a.QuestionStatusId=" + statusId;
		}
		if (null != topic && !topic.equalsIgnoreCase("")) {
			where = where + " and a.topicId='" + topic + "'";
		}
		if (null != frmDate && !frmDate.equalsIgnoreCase("")) {
			where = where
					+ " and a.createdOn >= '"
					+ Utilities.getDate_DBFormat(frmDate,
							EducationConstant.DISPLAY_DATE_FORMAT) + "'";
		}
		if (null != toDate && !toDate.equalsIgnoreCase("")) {
			where = where
					+ " and a.createdOn <= '"
					+ Utilities.getDate_DBFormat(toDate,
							EducationConstant.DISPLAY_DATE_FORMAT) + "'";
		}
		if (null != isGraphics && isGraphics != 0) {
			where = where + " and a.IsGraphics = " + isGraphics.intValue();
		}
		where1 = where1 + where + limit;
		where2 = where2 + where + limit;

		String sql = "select a.QuestionId,a.Subject,topic.topicId,topic.topicValue,subTopic.subTopicId,subTopic.subTopicValue,a.IsGraphics,"
				+ "a.Answer,a.QuestionStatusId,a.IsVerified,a.CreatedBy,a.CreatedOn,a.VerifiedBy,a.VerificationRemark,"
				+ "b.Description,b.Option_1,b.Option_2,b.Option_3,b.Option_4,b.Option_5,b.AnswerExplanation "
				+ fromClause1
				+ " "
				+ where1
				+ " UNION ALL "
				+ "select a.QuestionId,a.Subject,topic.topicId,topic.topicValue,subTopic.subTopicId,subTopic.subTopicValue,a.IsGraphics,"
				+ "a.Answer,a.QuestionStatusId,a.IsVerified,a.CreatedBy,a.CreatedOn,a.VerifiedBy,a.VerificationRemark,"
				+ "d.Description,d.Option_1,d.Option_2,d.Option_3,d.Option_4,d.Option_5,d.AnswerExplanation "
				+ fromClause2 + " " + where2;

		Connection con = null;
		PreparedStatement psmt;
		ResultSet rs;
		ArrayList questionList = new ArrayList();

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				// i++;
				// if (i >= fromRecIndex && i <= totalNoOfRecords) {
				QuestionBankTO questionBankTO = new QuestionBankTO();
				questionBankTO.setAnswer(rs.getString("answer"));
				questionBankTO.setAnswerDiscription(rs
						.getString("AnswerExplanation"));
				questionBankTO.setCreatedBy(rs.getString("createdBy"));
				questionBankTO.setIsGraphics(rs.getInt("isGraphics"));
				questionBankTO.setIsVerified(rs.getInt("isVerified"));
				questionBankTO.setOption1(rs.getString("option_1"));
				questionBankTO.setOption2(rs.getString("option_2"));
				questionBankTO.setOption3(rs.getString("option_3"));
				questionBankTO.setOption4(rs.getString("option_4"));
				questionBankTO.setOption5(rs.getString("option_5"));
				questionBankTO.setQuestion(rs.getString("Description"));
				questionBankTO.setQuestionId(rs.getInt("questionId"));
				questionBankTO.setQuestionStatusId(rs
						.getInt("QuestionStatusId"));
				questionBankTO.setSubject(rs.getString("subject"));
				questionBankTO.setSubTopic(rs.getString("subTopicValue"));
				questionBankTO.setTopic(rs.getString("topicValue"));
				questionBankTO.setVerificationRemark(rs
						.getString("verificationRemark"));
				questionBankTO.setVerifiedBy(rs.getString("verifiedBy"));
				questionBankTO.setCreatedOn(rs.getString("CreatedOn"));
				questionBankTO.setTopicId(rs.getString("topicId"));
				questionBankTO.setSubTopicId(rs.getString("subTopicId"));
				questionList.add(questionBankTO);
				// }

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
		return questionList;
	}

	// Method for getting Question count for specific user
	public int getQuestionsCountByUserID(String userID, String classID,
			String subject, String statusId, String topic, String frmDate,
			String toDate, Integer isGraphics)throws BaseAppException {

		String where = "";
		String where1 = " where a.questionid=b.questionid and a.createdBY='"
				+ userID + "'";
		String where2 = " where a.questionid=d.questionid and a.createdBY='"
				+ userID + "'";
		if (null != classID && !classID.equalsIgnoreCase("")) {

			where1 = ",t_class_question_link c where c.questionid=a.questionid"
					+ " and c.questionid=b.questionid "
					+ " and c.classcertid='" + classID + "'  and a.createdBY='"
					+ userID + "'";
			where2 = ",t_class_question_link c where c.questionid=a.questionid"
					+ " and c.questionid=d.questionid "
					+ " and c.classcertid='" + classID + "'  and a.createdBY='"
					+ userID + "'";
		}
		if (null != subject && !subject.equalsIgnoreCase("")) {
			where = where + " and a.subject='" + subject + "'";
		}
		if (null != statusId && !statusId.equalsIgnoreCase("")) {
			where = where + " and a.QuestionStatusId=" + statusId;
		}
		if (null != topic && !topic.equalsIgnoreCase("")) {
			where = where + " and a.topicId='" + topic + "'";
		}
		if (null != frmDate && !frmDate.equalsIgnoreCase("")) {
			where = where
					+ " and a.createdOn >= '"
					+ Utilities.getDate_DBFormat(frmDate,
							EducationConstant.DISPLAY_DATE_FORMAT) + "'";
		}
		if (null != toDate && !toDate.equalsIgnoreCase("")) {
			where = where
					+ " and a.createdOn <= '"
					+ Utilities.getDate_DBFormat(toDate,
							EducationConstant.DISPLAY_DATE_FORMAT) + "'";
		}
		if (null != isGraphics && isGraphics != 0) {
			where = where + " and a.IsGraphics = " + isGraphics.intValue();
		}
		where1 = where1 + where;
		where2 = where2 + where;
		String sql = "select count(temp.questionid) as rowcount from "
				+ "( select a.questionid from t_question_bank a , t_text_question b "
				+ where1
				+ " union all "
				+ "select a.questionid from t_question_bank a ,t_picture_question d "
				+ where2 + " ) " + "as temp";

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

	public int approveQuestionbyIds(String[] qIds) throws BaseAppException{
		int approveCount = 0;
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
		String sql = "update t_question_bank set QuestionStatusId=2, isVerified='1' where questionid in"
				+ in;
		

		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			approveCount = psmt.executeUpdate();
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
		return approveCount;
	}

	public int rejectQuestionbyIds(String[] qIds) throws BaseAppException{
		int rejectCount = 0;
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
		String sql = "update t_question_bank set QuestionStatusId=4, isVerified='1' where questionid in"
				+ in;

		Connection con= null;
		PreparedStatement psmt= null;

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rejectCount = psmt.executeUpdate();
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
		return rejectCount;
	}

	public int markQuestionASInactivebyIds(String[] qIds) throws BaseAppException{
		int questionInactiveCount = 0;
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
		String sql = "update t_question_bank set QuestionStatusId=3 where questionid in"
				+ in;

		Connection con=null;
		PreparedStatement psmt=null;

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			questionInactiveCount = psmt.executeUpdate();
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
		return questionInactiveCount;
	}

	public int linkQuestionToClass(ArrayList linkQToClassTOList)throws BaseAppException {
		int updateCount = 0;
		Connection con=null;
		PreparedStatement psmt=null;

		try {
			con = GetConnection.getSimpleConnection();

			Iterator itr = linkQToClassTOList.iterator();
			while (itr.hasNext()) {
				LinkQTOClassTO linkQ = (LinkQTOClassTO) itr.next();
				String sql = "insert into t_class_question_link "
						+ "(ClassCertID,QuestionId,Complexity,LinkedBy,LinkedDate)"
						+ "values(?,?,?,?,?)";
				psmt = con.prepareStatement(sql);
				psmt.setString(1, linkQ.getClassId());
				psmt.setString(2, linkQ.getQuid());
				psmt.setString(3, linkQ.getComplexityId());
				psmt.setString(4, linkQ.getLinkedBy());
				psmt
						.setString(5, Utilities.getDate_DBFormat(
								new java.util.Date(),
								EducationConstant.DB_DATE_FORMAT));
				updateCount = psmt.executeUpdate();
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
		return updateCount;
	}

	public int updateQuestionToClassLink(ArrayList linkQToClassTOList) throws BaseAppException{
		int updateCount = 0;
		Connection con=null;
		PreparedStatement psmt=null;

		try {
			con = GetConnection.getSimpleConnection();
			Iterator itr = linkQToClassTOList.iterator();
			while (itr.hasNext()) {
				LinkQTOClassTO objTo = (LinkQTOClassTO) itr.next();
				String sql = "UPDATE t_class_question_link "
						+ "SET Complexity =" + objTo.getComplexityId() + ""
						+ ",LinkedBy = '" + objTo.getLinkedBy() + "' "
						+ "WHERE ClassCertID = " + objTo.getClassId() + " "
						+ "AND QuestionId = " + objTo.getQuid();

				psmt = con.prepareStatement(sql);
				updateCount = psmt.executeUpdate();
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
		return updateCount;
	}

	public int getQuestionToClass_LinkCount(int QuestionId)throws BaseAppException {

		String sql = "Select count(*) as rowcount from t_class_question_link where QuestionId="
				+ QuestionId;
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

	public ArrayList getQuestionToClass_LinkedData(int QuestionId)throws BaseAppException {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs;
		ArrayList linkData = new ArrayList();
		String sql = "Select ClassCertID,QuestionId, "
				+ "Complexity,LinkedBy, " + "LinkedDate "
				+ "From t_class_question_link " + "Where QuestionId="
				+ QuestionId;
		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);

			rs = psmt.executeQuery();
			while (rs.next()) {
				LinkQTOClassTO objTo = new LinkQTOClassTO();
				objTo.setClassId(rs.getString("ClassCertID"));
				objTo.setQuid(rs.getString("QuestionId"));
				objTo.setComplexityId(rs.getString("Complexity"));
				objTo.setLinkedBy(rs.getString("LinkedBy"));
				objTo.setStatus("Old");
				linkData.add(objTo);
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
		return linkData;
	}
	
	public int insertPictureQuestion(QuestionBankTO qTo)throws BaseAppException {
		int recUpdateCount = 0;
		Connection con = null;
		PreparedStatement psmt = null;
		int questionId = qTo.getQuestionId();
		String createdBy = qTo.getCreatedBy();
		String questiondesctext = qTo.getQuestion();
		String option1text = qTo.getOption1();
		String option2text = qTo.getOption2();
		String option3text = qTo.getOption3();
		String option4text = qTo.getOption4();
		String option5text = qTo.getOption5();
		String answerdesctext = qTo.getAnswerDiscription();
		String certOrClass = qTo.getCert();
		String questionComplexity = qTo.getComplexity();

		try {
			con = GetConnection.getSimpleConnection();
			con.setAutoCommit(false); //Transaction support Start
			doEntry_QuestionBank_Table(qTo,con);
			String picture_questionTable_Q = "insert into t_picture_question"
					+ "(QuestionId,Description,Description_Img,Option_1,Option_1_Img,Option_2,Option_2_Img,"
					+ "Option_3,Option_3_Img,Option_4,Option_4_Img,"
					+ "Option_5,Option_5_Img,AnswerExplanation,AnswerExplanation_Img)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			
			psmt = con.prepareStatement(picture_questionTable_Q);
			psmt.setInt(1, questionId);
			psmt.setString(2, questiondesctext);

			psmt.setBinaryStream(3, qTo.getQuestionFileStream(), qTo
					.getQuestionFileStreamLength());
			psmt.setString(4, option1text);
			psmt.setBinaryStream(5, qTo.getOption1FileStream(), qTo
					.getOption1FileStreamLength());
			psmt.setString(6, option2text);
			psmt.setBinaryStream(7, qTo.getOption2FileStream(), qTo
					.getOption2FileStreamLength());
			psmt.setString(8, option3text);
			psmt.setBinaryStream(9, qTo.getOption3FileStream(), qTo
					.getOption3FileStreamLength());
			psmt.setString(10, option4text);
			psmt.setBinaryStream(11, qTo.getOption4FileStream(), qTo
					.getOption4FileStreamLength());
			psmt.setString(12, option5text);
			psmt.setBinaryStream(13, qTo.getOption5FileStream(), qTo
					.getOption5FileStreamLength());
			psmt.setString(14, answerdesctext);
			psmt.setBinaryStream(15, qTo.getAnsExplanationFileStream(), qTo
					.getAnsExplanationFileStreamLength());
			recUpdateCount = psmt.executeUpdate();

			// Add Question->class linkage data in DB
			int newQuestionID = getNextQuestionId();
			ArrayList list = new ArrayList();
			LinkQTOClassTO linkQclassTO = new LinkQTOClassTO();
			linkQclassTO.setClassId(certOrClass);
			linkQclassTO.setComplexityId(questionComplexity);
			linkQclassTO.setLinkedBy(createdBy);
			linkQclassTO.setQuid(String.valueOf(newQuestionID));
			list.add(linkQclassTO);
			linkQuestionToClass(list);
			con.commit(); //Transaction support End
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
		return recUpdateCount;
	}

	public FileInputStream getFileInputStream(FormFile myFile) {
		File temp = new File("temp");
		FileInputStream fis = null;
		try {

			// If file does not exists create file

			FileOutputStream fileOutStream = new FileOutputStream(temp);
			fileOutStream.write(myFile.getFileData());
			fis = new FileInputStream(temp);
			fileOutStream.close();
			temp.delete();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return fis;
	}

	// dummy method used for testing only
	public int getPictureQuestionsCount(String classID, String subject,
			String statusId, String topic) throws BaseAppException{

		String sql = "Select count(*) as rowcount from t_question_bank a , t_picture_question d";

		String where = " where a.questionid = d.questionid ";

		if (null != classID && !classID.equalsIgnoreCase("")) {
			where = ",t_class_question_link c where c.questionid=a.questionid"
					+ " and c.questionid = d.questionid  "
					+ " and c.classcertid='" + classID + "'";
		}
		if (null != subject && !subject.equalsIgnoreCase("")) {
			where = where + " and a.subject='" + subject + "'";
		}
		if (null != statusId && !statusId.equalsIgnoreCase("")) {
			where = where + " and a.QuestionStatusId=" + statusId;
		}
		if (null != topic && !topic.equalsIgnoreCase("")) {
			where = where + " and a.Topic='" + topic + "'";
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
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return rowcount;

	}

	// dummy method used for testing only
	public ArrayList getPictureQuestions(String classID, String subject,
			String statusId, String topic) throws BaseAppException{

		String sql = "select a.QuestionId,a.Subject,a.Topic,a.SubTopic,a.IsGraphics,"
				+ "a.Answer,a.QuestionStatusId,a.IsVerified,a.CreatedBy,a.VerifiedBy,a.VerificationRemark,"
				+ "d.QuestionId,d.Description,d.Description_Img,d.Option_1,d.Option_1_Img,d.Option_2,d.Option_2_Img,"
				+ "d.Option_3,d.Option_3_Img,d.Option_4,d.Option_4_Img,"
				+ "d.Option_5,d.Option_5_Img,d.AnswerExplanation,d.AnswerExplanation_Img"
				+ " from t_question_bank a , t_picture_question d";

		String where = " where a.questionid = d.questionid ";

		if (null != classID && !classID.equalsIgnoreCase("")) {
			where = ",t_class_question_link c where c.questionid=a.questionid"
					+ " and c.questionid = d.questionid   "
					+ " and c.classcertid='" + classID + "'";
		}
		if (null != subject && !subject.equalsIgnoreCase("")) {
			where = where + " and a.subject='" + subject + "'";
		}
		if (null != statusId && !statusId.equalsIgnoreCase("")) {
			where = where + " and a.QuestionStatusId=" + statusId;
		}
		if (null != topic && !topic.equalsIgnoreCase("")) {
			where = where + " and a.Topic='" + topic + "'";
		}

		sql = sql + where;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs;
		ArrayList questionList = new ArrayList();

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				i++;

				QuestionBankTO questionBankTO = new QuestionBankTO();
				questionBankTO.setAnswer(rs.getString("answer"));
				questionBankTO.setAnswerDiscription(rs
						.getString("AnswerExplanation"));
				questionBankTO.setCreatedBy(rs.getString("createdBy"));
				questionBankTO.setIsGraphics(rs.getInt("isGraphics"));
				questionBankTO.setIsVerified(rs.getInt("isVerified"));
				questionBankTO.setOption1(rs.getString("Option_1"));
				questionBankTO.setOption2(rs.getString("Option_2"));
				questionBankTO.setOption3(rs.getString("Option_3"));
				questionBankTO.setOption4(rs.getString("Option_4"));
				questionBankTO.setOption5(rs.getString("Option_5"));
				questionBankTO.setQuestion(rs.getString("Description"));
				questionBankTO.setQuestionId(rs.getInt("questionId"));
				questionBankTO.setQuestionStatusId(rs
						.getInt("questionStatusId"));
				questionBankTO.setSubject(rs.getString("subject"));
				questionBankTO.setSubTopic(rs.getString("subTopic"));
				questionBankTO.setTopic(rs.getString("Topic"));
				questionBankTO.setVerificationRemark(rs
						.getString("verificationRemark"));
				questionBankTO.setVerifiedBy(rs.getString("verifiedBy"));
				questionList.add(questionBankTO);
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
		return questionList;
	}

	public int updatePictureQuestion(QuestionBankTO qTo) throws BaseAppException{
		int updateCount = 0;
		Connection con = null;
		PreparedStatement psmt = null;
		int questionId = qTo.getQuestionId();

		String topicId = qTo.getTopicId();
		String subTopicId = qTo.getSubTopicId();
		int isGraphics = qTo.getIsGraphics();
		String answer = qTo.getAnswer();
		String questiondesctext = qTo.getQuestion();
		String option1text = qTo.getOption1();
		String option2text = qTo.getOption2();
		String option3text = qTo.getOption3();
		String option4text = qTo.getOption4();
		String option5text = qTo.getOption5();
		String answerdesctext = qTo.getAnswerDiscription();

		try {
			String subject = qTo.getSubject();
			String sql1 = "update t_question_bank " + " set Subject='"
					+ subject + "'," + "topicId='" + topicId + "',"
					+ "subTopicId='" + subTopicId + "'," + "IsGraphics="
					+ isGraphics + "," + "Answer='" + answer + "'"
					+ " where QuestionId=" + questionId;

			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql1);
			updateCount = psmt.executeUpdate();
			psmt.close();

			// FormFile question = qTo.getQuestionImage();
			InputStream questionStrem = null;
			InputStream answerExpStream = null;
			InputStream option1Stream = null;
			InputStream option2Stream = null;
			InputStream option3Stream = null;
			InputStream option4Stream = null;
			InputStream option5Stream = null;

			boolean updateOption1Img = false;
			boolean updateOption2Img = false;
			boolean updateOption3Img = false;
			boolean updateOption4Img = false;
			boolean updateOption5Img = false;
			boolean updateAnsExplantionImg = false;
			boolean updateQuestionImg = false;

			if (qTo.getQuestionImg_editAction().equals("update")
					&& qTo.getQuestionImage() != null) {
				questionStrem = qTo.getQuestionImage().getInputStream();
				updateQuestionImg = true;
			} else if (qTo.getQuestionImg_editAction().equals("remove")) {
				updateQuestionImg = true;
				questionStrem = null;
			}
			if (qTo.getAnsExpImg_editAction().equals("update")
					&& qTo.getAnsExplanation() != null) {
				answerExpStream = qTo.getAnsExplanation().getInputStream();
				updateAnsExplantionImg = true;
			} else if (qTo.getAnsExpImg_editAction().equals("remove")) {
				answerExpStream = null;
				updateAnsExplantionImg = true;
			}
			if (qTo.getOption1Img_editAction().equals("update")
					&& qTo.getAnsOneImage() != null) {
				option1Stream = qTo.getAnsOneImage().getInputStream();
				updateOption1Img = true;
			} else if (qTo.getOption1Img_editAction().equals("remove")) {
				option1Stream = null;
				updateOption1Img = true;
			}
			if (qTo.getOption2Img_editAction().equals("update")
					&& qTo.getAnsTwoImage() != null) {
				option2Stream = qTo.getAnsTwoImage().getInputStream();
				updateOption2Img = true;
			} else if (qTo.getOption2Img_editAction().equals("remove")) {
				option2Stream = null;
				updateOption2Img = true;
			}
			if (qTo.getOption3Img_editAction().equals("update")
					&& qTo.getAnsThreeImage() != null) {
				option3Stream = qTo.getAnsThreeImage().getInputStream();
				updateOption3Img = true;
			} else if (qTo.getOption3Img_editAction().equals("remove")) {
				option3Stream = null;
				updateOption3Img = true;
			}
			if (qTo.getOption4Img_editAction().equals("update")
					&& qTo.getAnsFourImage() != null) {
				option4Stream = qTo.getAnsFourImage().getInputStream();
				updateOption4Img = true;
			} else if (qTo.getOption4Img_editAction().equals("remove")) {
				option4Stream = null;
				updateOption4Img = true;
			}
			if (qTo.getOption5Img_editAction().equals("update")
					&& qTo.getAnsFiveImage() != null) {
				option5Stream = qTo.getAnsFiveImage().getInputStream();
				updateOption5Img = true;
			} else if (qTo.getOption5Img_editAction().equals("remove")) {
				option5Stream = null;
				updateOption5Img = true;
			}
			String sql22 = " UPDATE t_picture_question "
					+ " SET Description=?,Option_1=?, "
					+ " Option_2=?,Option_3=?, " + " Option_4=?,Option_5=?, "
					+ " AnswerExplanation=? ";

			String whereCndt = " where questionId=? ";
			int indexCount = 8;

			if (updateQuestionImg)
				sql22 += ",Description_Img=? ";
			if (updateOption1Img)
				sql22 += ",Option_1_Img=? ";
			if (updateOption2Img)
				sql22 += ",Option_2_Img=? ";
			if (updateOption3Img)
				sql22 += ",Option_3_Img=? ";
			if (updateOption4Img)
				sql22 += ",Option_4_Img=? ";
			if (updateOption5Img)
				sql22 += ",Option_5_Img=? ";
			if (updateAnsExplantionImg)
				sql22 += ",AnswerExplanation_Img=?";

			sql22 += whereCndt;
			psmt = con.prepareStatement(sql22);

			// question Text
			psmt.setString(1, questiondesctext);
			// option1 Text
			psmt.setString(2, option1text);
			// option2 Text
			psmt.setString(3, option2text);
			// option3 Text
			psmt.setString(4, option3text);
			// option4 Text
			psmt.setString(5, option4text);
			// option5 Text
			psmt.setString(6, option5text);
			// Answer Explanation Text
			psmt.setString(7, answerdesctext);

			if (updateQuestionImg)
				// Question Img
				psmt.setBinaryStream(indexCount++, questionStrem,
						(questionStrem == null ? 0 : qTo.getQuestionImage()
								.getFileSize()));

			if (updateOption1Img)
				// option1 Img
				psmt.setBinaryStream(indexCount++, option1Stream,
						(option1Stream == null ? 0 : qTo.getAnsOneImage()
								.getFileSize()));

			if (updateOption2Img)
				// option2 Img
				psmt.setBinaryStream(indexCount++, option2Stream,
						(option2Stream == null ? 0 : qTo.getAnsTwoImage()
								.getFileSize()));
			if (updateOption3Img)
				// option3 Img
				psmt.setBinaryStream(indexCount++, option3Stream,
						(option3Stream == null ? 0 : qTo.getAnsThreeImage()
								.getFileSize()));
			if (updateOption4Img)
				// option4 Img
				psmt.setBinaryStream(indexCount++, option4Stream,
						(option4Stream == null ? 0 : qTo.getAnsFourImage()
								.getFileSize()));
			if (updateOption5Img)
				// option5 Img
				psmt.setBinaryStream(indexCount++, option5Stream,
						(option5Stream == null ? 0 : qTo.getAnsFiveImage()
								.getFileSize()));
			if (updateAnsExplantionImg)
				// Answer Explanation Img
				psmt.setBinaryStream(indexCount++, answerExpStream,
						(answerExpStream == null ? 0 : qTo.getAnsExplanation()
								.getFileSize()));

			// QuestionId
			psmt.setInt(indexCount++, questionId);

			updateCount = psmt.executeUpdate();

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
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
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

	public InputStream getPictureQuestion_ImagesByID(int QuestionId, int imageOf)throws BaseAppException {

		String selectQuery = " SELECT Description_Img,Option_1_Img,Option_2_Img,Option_3_Img,Option_4_Img, "
				+ " Option_5_Img,AnswerExplanation_Img "
				+ " FROM t_picture_question pictureQuesTable "
				+ " INNER JOIN t_sampletest_questions questionBankTable"
				+ " ON questionBankTable.questionid = pictureQuesTable.questionid "
				+ " WHERE questionBankTable.questionid=? ";

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs;
		InputStream stream = null;

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(selectQuery);
			psmt.setInt(1, QuestionId);
			rs = psmt.executeQuery();

			if (rs.next()) {
				switch (imageOf) {
				case EducationConstant.IMAGE_FOR_QUESTION:
					stream = rs.getBinaryStream("Description_Img");
					break;
				case EducationConstant.IMAGE_FOR_OPTION1:
					stream = rs.getBinaryStream("Option_1_Img");
					break;
				case EducationConstant.IMAGE_FOR_OPTION2:
					stream = rs.getBinaryStream("Option_2_Img");
					break;
				case EducationConstant.IMAGE_FOR_OPTION3:
					stream = rs.getBinaryStream("Option_3_Img");
					break;
				case EducationConstant.IMAGE_FOR_OPTION4:
					stream = rs.getBinaryStream("Option_4_Img");
					break;
				case EducationConstant.IMAGE_FOR_OPTION5:
					stream = rs.getBinaryStream("Option_5_Img");
					break;
				case EducationConstant.IMAGE_FOR_ANSWER_EXPLANATION:
					stream = rs.getBinaryStream("AnswerExplanation_Img");
					break;
				}
			}
//			if (stream != null) {
//				stream.close();
//			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DBDataSourceException(e.getMessage());
			}
		}
		return stream;
	}

	/**
	 * get list of subjects
	 * @param userId
	 * @return
	 * @throws BaseAppException
	 */
	public ArrayList getSelectedSubjectOfStudent(int userId)throws BaseAppException {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ArrayList<TopicSubTopicTO> subjectList = new ArrayList<TopicSubTopicTO>();
		String sql = "select * from t_subjects where subject_id in (select subjectId from t_class_subject_cost where classSubId in (select classSubId from t_student_selected_classsub where userId = "
				+ userId + "))";
				
		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);

			rs = psmt.executeQuery();
			while (rs.next()) {				
				TopicSubTopicTO objTo = new TopicSubTopicTO();
				objTo.setSubjectId(rs.getInt("subject_Id"));
				objTo.setSubjectValue((rs.getString("subjectValue")));
				subjectList.add(objTo);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeQuietly(con, psmt,rs);
		}
		return subjectList;
	}
	
	
	/**
	 * get list of class
	 * @param userId
	 * @return
	 * @throws BaseAppException
	 */
	public ArrayList getSelectedClassOfStudent(int userId)throws BaseAppException {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ArrayList<ClassCertTO> classOrCertList = new ArrayList<ClassCertTO>();
		String sql = "select * from t_class_cert where ClassCertId in (select classId from t_class_subject_cost where classSubId in (select classSubId from t_student_selected_classsub where userId = "
					 + userId + "))";
		
				
		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);

			rs = psmt.executeQuery();
			while (rs.next()) {
				ClassCertTO classCertTO=new ClassCertTO();
				classCertTO.setClassCertId(rs.getInt("classcertid"));
				classCertTO.setClassCertName(rs.getString("CertificateName"));
				classOrCertList.add(classCertTO);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeQuietly(con, psmt,rs);
		}
		return classOrCertList;
	}

	

	

	
	
	public static void main(String[] args) throws Exception {
		OracleQuestionbankDAO dao = new OracleQuestionbankDAO();
		// FileInputStream fileStream = new FileInputStream("C:/inputs.txt");

		SimpleDateFormat formatter = new SimpleDateFormat(
				EducationConstant.DISPLAY_DATE_FORMAT);
		
		InputStream stream =  new OracleQuestionbankDAO().getPictureQuestion_ImagesByID(6,EducationConstant.IMAGE_FOR_OPTION1);
		if(stream != null){
			System.out.println("STREAM HAS IMAGE:" );
		}else{
			System.out.println("STREAM IS NULL:" );
		}
		

		// ContentUploadTO objTo = new ContentUploadTO();
		// objTo.setClassId("1");
		// objTo.setSubjectId("1");
		// objTo.setTopic("Alg1");
		// objTo.setSubTopic("equations1");
		// objTo.setContentFileStream(fileStream);
		// objTo.setContentFileName("contentFile");
		// objTo.setContentStreamLength( fileStream.toString().length());
		// objTo.setSupportingFileStream(fileStream);
		// objTo.setSupportingtFileName("suppFile");
		// objTo.setSupportingStreamLength(fileStream.toString().length());
		// dao.insertContentUploadDetails(objTo);

		// ContentUploadTO objTo = new ContentUploadTO();
		// objTo.setContentId("2");
		// //objTo.setSubjectId("1");
		// objTo.setTopic("Alg1");
		// objTo.setSubTopic("equations1");
		// objTo.setContentFileStream(fileStream);
		// objTo.setContentFileName("updateFile");
		// objTo.setContentStreamLength( fileStream.toString().length());
		// objTo.setHasSupportingFiles(1);
		// objTo.setSupportingFileStream(fileStream);
		// objTo.setSupportingtFileName("UpdateSupFile");
		// objTo.setSupportingStreamLength(fileStream.toString().length());
		//		
		// dao.updateContentUploadDetails(objTo);

		// int min = 1;
		// int max = 3;
		// int count = dao.getContentUploadCount("", "");
		// System.out.println("Row count: "+ count);
		// ArrayList list = dao.getContentUploadDetails(objTo,min,max);
		// System.out.println("\nList count: "+ list.size());
	}
}
