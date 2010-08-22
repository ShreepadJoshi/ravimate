package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.expframework.data.ExceptionDTO;
import org.expframework.exceptionhandler.ExceptionHandlerFactory;
import org.expframework.exceptionhandler.IExceptionHandler;
import org.expframework.exceptions.BaseAppException;

import com.education.exceptions.DBDataOutOfRangeException;
import com.education.exceptions.DBIntegrityViolationException;
import com.education.exceptions.DBInvalidDataInsertionException;
import com.education.transferobj.QuestionBankTO;
import com.education.util.EducationConstant;
import com.education.util.GetConnection;
import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.MySQLDataException;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import com.mysql.jdbc.exceptions.MySQLTimeoutException;



public class DBTest {

	public static void main(String[] args) {
		try {
			//GetConnection.getSimpleConnection();
			QuestionBankTO objTo = new QuestionBankTO();
			objTo.setQuestionId(1);
			objTo.setSubTopicId("1");
			objTo.setTopicId("1");
			objTo.setQuestionStatusId(1);
			objTo.setSubject("1");
			objTo.setIsGraphics(0);
			objTo.setAnswer("Answer");
			objTo.setCreatedBy("1");
			objTo.setCreatedOn("2010-04-11");
			
			new DBTest().doEntry_QuestionBank_Table(objTo);
			
		} catch (BaseAppException ex) {
			IExceptionHandler eh = ExceptionHandlerFactory.getInstance().create();
			ExceptionDTO exDTO = eh.handleException("Context", "System", ex);
			System.out.println(exDTO);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean doEntry_QuestionBank_Table(QuestionBankTO qTo) throws BaseAppException {
		int recUpdateCount = 0;
		int questionId = qTo.getQuestionId();
		String question = qTo.getQuestion();
		String topic = qTo.getTopicId();
		String subTopic = qTo.getSubTopicId();
		int isGraphics = qTo.getIsGraphics();
		String answer = qTo.getAnswer();
		int questionStatusId = qTo.getQuestionStatusId();
		int isVerified = qTo.getIsVerified();
		String createdBy = qTo.getCreatedBy();
		String verifiedBy = qTo.getVerifiedBy();
		String verificationRemark = qTo.getVerificationRemark();
		String subject = qTo.getSubject();
		
		String questionBank_Table_Q = "insert into t_question_bank "
				+ "(QuestionId,Subject,topicId,subTopicId,IsGraphics,Answer,"
				+ "QuestionStatusId,CreatedBy,CreatedOn)"
				+ "values(?,?,?,?,?,?,?,?,?)";
		Connection con = null;;
		PreparedStatement psmt = null;
		try {

			con = GetConnection.getSimpleConnection();
			con.setAutoCommit(false);
			psmt = con.prepareStatement(questionBank_Table_Q);
			psmt.setInt(1, questionId);
			psmt.setString(2, subject);
			psmt.setString(3, topic);
			psmt.setString(4, subTopic);
			psmt.setInt(5, isGraphics);
			psmt.setString(6, answer);
			psmt.setInt(7, questionStatusId);
			psmt.setString(8, createdBy);
			psmt.setDate(9, new java.sql.Date(getCurrentDB_Date()));
			recUpdateCount = psmt.executeUpdate();
			con.commit();
			psmt.close();
		
		}catch(MySQLIntegrityConstraintViolationException e){
			throw new DBIntegrityViolationException(e.getMessage());
		}catch(MysqlDataTruncation e){
			throw new DBDataOutOfRangeException(e.getMessage());
		}catch (SQLException sqld) {
			if(sqld.getMessage().contains("InCorrect"))
				throw new DBInvalidDataInsertionException(sqld.getMessage());
			else
				throw new RuntimeException();
		}
		//finally {
//			try {
//				
//			} catch (SQLException sqld) {
//				sqld.printStackTrace();
//			}
//		}
		return (recUpdateCount > 0 ? true : false);
	}
	
	public static String getDate_DBFormat(Object date,String inputDateFormat){
		java.util.Date disp_format_Date = null;
		String db_formatted_date = null;
		try {
			
			//check if instance of String 
			if (date instanceof java.lang.String) {
				disp_format_Date = new SimpleDateFormat(inputDateFormat).parse((String)date);
				db_formatted_date = new SimpleDateFormat(EducationConstant.DB_DATE_FORMAT).format(disp_format_Date);
				
			}else if(date instanceof java.util.Date) {
				db_formatted_date= new SimpleDateFormat(EducationConstant.DB_DATE_FORMAT).format((java.util.Date)date);
				
			}
			 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//System.out.println("display_format_date: "+ display_format_date);		
		return db_formatted_date;
		
	}
	
	public static long getCurrentDB_Date(){
		Long long_date = null;
		String curr_date = new SimpleDateFormat(EducationConstant.DB_DATE_FORMAT).format(new java.util.Date());
		
		try {
			long_date = new SimpleDateFormat(EducationConstant.DB_DATE_FORMAT).parse(curr_date).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return long_date;
	}
}
