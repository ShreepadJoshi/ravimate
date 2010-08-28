package com.education.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.expframework.exceptions.BaseAppException;

import com.education.exceptions.DBDataSourceException;
import com.education.transferobj.QuestionBankTO;
import com.education.util.EducationConstant;
import com.education.util.ExcelUtil;
import com.education.util.GetConnection;

public class OracleTestDAO extends AbstractDAO{

	/**
	 * @return
	 * @author Amit
	 * @see This method will return all question count.
	 */
	public QuestionBankTO getSampleTestQuestionDetails(String questionID) throws BaseAppException{
		int count = 0;
		String sql = "select isgraphics from t_sampletest_questions WHERE questionId="+questionID;
		Connection con = null;
		PreparedStatement psmt;
		QuestionBankTO questionBankTO = null;
		ResultSet rs= null;
		boolean isGraphics = false;
		try {
			
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("isgraphics");
				isGraphics = count >= 1 ? true : false;
			}
			psmt.close();
			
			if (isGraphics) {				
				sql = " SELECT * FROM t_picture_question " +
					  " WHERE questionId="+questionID;
				
				psmt = con.prepareStatement(sql);
				rs = psmt.executeQuery();
				if (rs.next()) {
					questionBankTO = new QuestionBankTO();					
					questionBankTO.setOption1(rs.getString("option_1"));
					questionBankTO.setOption2(rs.getString("option_2"));
					questionBankTO.setOption3(rs.getString("option_3"));
					questionBankTO.setOption4(rs.getString("option_4"));
					questionBankTO.setOption5(rs.getString("option_5"));
					questionBankTO.setQuestion(rs.getString("Description"));
					
					//Call QuestionBank Service to get Image Options for options
					OracleQuestionbankDAO questionBankService = new OracleQuestionbankDAO();
					InputStream option1Stream =  questionBankService.getPictureQuestion_ImagesByID(
								Integer.parseInt(questionID),EducationConstant.IMAGE_FOR_OPTION1);
					InputStream option2Stream =  questionBankService.getPictureQuestion_ImagesByID(
							Integer.parseInt(questionID),EducationConstant.IMAGE_FOR_OPTION2);
					InputStream option3Stream =  questionBankService.getPictureQuestion_ImagesByID(
							Integer.parseInt(questionID),EducationConstant.IMAGE_FOR_OPTION3);
					InputStream option4Stream =  questionBankService.getPictureQuestion_ImagesByID(
							Integer.parseInt(questionID),EducationConstant.IMAGE_FOR_OPTION4);
					InputStream option5Stream =  questionBankService.getPictureQuestion_ImagesByID(
							Integer.parseInt(questionID),EducationConstant.IMAGE_FOR_OPTION5);
					if(option1Stream != null)
						questionBankTO.setHasOption1Img(1);
					else
						questionBankTO.setHasOption1Img(0);
					if(option2Stream != null)
						questionBankTO.setHasOption2Img(1);
					else
						questionBankTO.setHasOption2Img(0);
					if(option3Stream != null)
						questionBankTO.setHasOption3Img(1);
					else
						questionBankTO.setHasOption3Img(0);
					if(option4Stream != null)
						questionBankTO.setHasOption4Img(1);
					else
						questionBankTO.setHasOption4Img(0);
					if(option5Stream != null)
						questionBankTO.setHasOption5Img(1);
					else
						questionBankTO.setHasOption5Img(0);
				}
			} else {				
				sql = " SELECT * FROM t_text_question " +
					  " WHERE questionId="+questionID;
				
				psmt = con.prepareStatement(sql);
				rs = psmt.executeQuery();
				if (rs.next()) {
					questionBankTO = new QuestionBankTO();
					questionBankTO.setOption1(rs.getString("option_1"));
					questionBankTO.setOption2(rs.getString("option_2"));
					questionBankTO.setOption3(rs.getString("option_3"));
					questionBankTO.setOption4(rs.getString("option_4"));
					questionBankTO.setOption5(rs.getString("option_5"));
					questionBankTO.setQuestion(rs.getString("Description"));
					//set image details
					questionBankTO.setHasOption1Img(0);
					questionBankTO.setHasOption2Img(0);
					questionBankTO.setHasOption3Img(0);
					questionBankTO.setHasOption4Img(0);
					questionBankTO.setHasOption5Img(0);
				}
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

		return questionBankTO;
	}
	
	public ArrayList getSampleTestQuestion_ByclassID(int classID,String questionID_delimitedString)throws BaseAppException{
		String sql = " SELECT a.QuestionId,a.Answer,b.Description, "+
					 " b.Option_1,b.Option_2,b.Option_3,b.Option_4,b.Option_5 "+
					 " FROM t_question_bank a ,t_text_question b ,t_sampletest_questions c"+
					 " WHERE a.questionid=b.questionid and a.isgraphics='0' and a.questionid=c.questionid"+
					 " AND a.questionId IN ("+ questionID_delimitedString +") "+
					 " UNION ALL "+
					 " SELECT a.QuestionId,a.Answer,d.Description, "+
					 " d.Option_1,d.Option_2,d.Option_3,d.Option_4,d.Option_5 "+
					 " FROM t_question_bank a , t_sampletest_questions c,t_picture_question d "+
					 " WHERE a.questionid=d.questionid and a.questionid=c.questionid  and a.isgraphics='1'"+
					 " AND a.questionId IN ("+ questionID_delimitedString +")";
		
		
		
		//String sql = "SELECT a.QuestionId,a.Answer,b.Description,  b.Option_1,b.Option_2,b.Option_3,b.Option_4,b.Option_5 FROM t_sampletest_questions a ,t_text_question b  WHERE a.questionid=b.questionid  AND a.questionId IN ("
			//	+ questionID_delimitedString + ")"; 
		
		Connection con = null;
		PreparedStatement psmt =null;
		ResultSet rs;
		ArrayList questionList = new ArrayList();
		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while (rs.next()) {				
				QuestionBankTO questionBankTO = new QuestionBankTO();
				questionBankTO.setQuestionId(rs.getInt("questionId"));
				//questionBankTO.setIsGraphics(rs.getInt("IsGraphics"));
				//questionBankTO.setAnswer(rs.getString("Answer"));
//				questionBankTO.setQuestion(rs.getString("Description"));
//				questionBankTO.setOption1(rs.getString("option_1"));
//				questionBankTO.setOption2(rs.getString("option_2"));
//				questionBankTO.setOption3(rs.getString("option_3"));
//				questionBankTO.setOption4(rs.getString("option_4"));
//				questionBankTO.setOption5(rs.getString("option_5"));
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
	
	public ArrayList getSampleTestQuestionID_byClassID(int classID,int subjectId, int size) throws BaseAppException{
		
		int count = 0;
		String sql = "select a.questionid from t_sampletest_questions a , t_class_question_link b,t_question_bank c"
				+ " where  a.questionid=b.classquestionid and a.questionid=c.questionid and a.classcertid='"
				+ classID + "' and a.subject = '" + subjectId + "' ORDER BY RAND() ";
		
		Connection con = null;
		ResultSet rs;
		
		PreparedStatement psmt;
		ArrayList idlist = new ArrayList();
		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
				while (rs.next()) {
					if(count < size) {
						idlist.add(rs.getString("questionid"));
						count++;
					}
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
		return idlist;
	}
	
	
	public int getSampleTestQuestionCount_ByClassID(int classID, int subjectId)throws BaseAppException{
		int count = 0;
		String sql = " SELECT count(*) AS questionCount "+
					 " FROM t_sampletest_questions a , t_class_question_link b ,t_question_bank c "+
					 " where a.questionid=b.classquestionid and a.questionid=c.questionid and a.ClassCertID = "+classID + " and a.subject = " + subjectId;
				
		Connection con = null;
		PreparedStatement psmt =null;
		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			//psmt.setInt(1,classID);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("questionCount");
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

	

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
//		OracleTestDAO or = new OracleTestDAO();
//		int count = or.getSampleTestQuestionCount_ByClassID(1);
//		System.out.println("Sample Test QuestionCount: "+ count);
		
//		ArrayList questionIDList = or.getSampleTestQuestionID_byClassID(1,3);
//		System.out.println("Samplet Test QuestionID fetched: "+ questionIDList.size());
		
//		ArrayList questions = or.getSampleTestQuestion_ByclassID(1,"1");
//		System.out.println("Samplet Test Questions: "+ questions.size());
		
		//System.err.println(or.getAllQuestionIdsforClassId(1,3).size());
		
		ExcelUtil util = new ExcelUtil();
		String sql = "SELECT * FROM t_session_tracking where userid = ?";
		ArrayList<Object> lst = new ArrayList<Object>(1);
		lst.add(1);
		util.exportToExcel(sql, null);
	}

	public InputStream getPictureQuestion_ImagesByID(int QuestionId, int imageOf) {

		String selectQuery = " SELECT Description_Img,Option_1_Img,Option_2_Img,Option_3_Img,Option_4_Img, "
				+ " Option_5_Img,AnswerExplanation_Img "
				+ " FROM t_picture_question pictureQuesTable "
				+ " INNER JOIN t_question_bank questionBankTable"
				+ " ON questionBankTable.questionid = pictureQuesTable.questionid "
				+ " WHERE questionBankTable.questionid=? ";

		Connection con;
		PreparedStatement psmt;
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
			if (stream != null) {
				stream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stream;
	}
	
	
}
