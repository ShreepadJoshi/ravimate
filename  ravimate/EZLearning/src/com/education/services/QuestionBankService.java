/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.education.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import org.expframework.exceptions.BaseAppException;

import com.education.dao.OracleQuestionbankDAO;
import com.education.transferobj.ContentUploadTO;
import com.education.transferobj.QuestionBankTO;

/**
 * 
 * @author Administrator
 */
public class QuestionBankService {

	public int addQuestion(QuestionBankTO qTo) throws BaseAppException{
		int updateCount = 0;		
		OracleQuestionbankDAO dao = new OracleQuestionbankDAO();
		updateCount =  dao.insertQuestion(qTo);		
		return updateCount;
	}

	public int addPictureQuestion(QuestionBankTO qTo) throws BaseAppException{
		int updateCount = 0;		
		OracleQuestionbankDAO dao = new OracleQuestionbankDAO();
		updateCount =  dao.insertPictureQuestion(qTo);		
		return updateCount;
	}
	
	public int updatePictureQuestion(QuestionBankTO qTo) throws BaseAppException{
		int updateCount = 0;
		OracleQuestionbankDAO dao = new OracleQuestionbankDAO();
		updateCount = dao.updatePictureQuestion(qTo);
		return updateCount;
	}

	public int updateQuestion(QuestionBankTO qTo) throws BaseAppException{
		int updateCount = 0;
		OracleQuestionbankDAO dao = new OracleQuestionbankDAO();
		updateCount =  dao.updateQuestion(qTo);
		return updateCount;
	}

	public int getNextQuestionId() throws BaseAppException{
		int count = 0;
		OracleQuestionbankDAO dao = new OracleQuestionbankDAO();
		count = dao.getNextQuestionId();
		// increment the count by 1;
		count = count + 1;
		return count;

	}

	public int getQuestionsCount(String classID, String subject, String status,
			String topic,String frmDate,String toDate,Integer isGraphics) throws BaseAppException{

		OracleQuestionbankDAO oracleQuestionbankDAO = new OracleQuestionbankDAO();
		return oracleQuestionbankDAO.getQuestionsCount(classID, subject,
				status, topic,frmDate,toDate,isGraphics);
	}

	public ArrayList getQuestions(String classID, String subject,
			String status, String topic,String frmDate,String toDate,
			Integer isGraphics,int frmRecord, int noOfRecords) throws BaseAppException{
		OracleQuestionbankDAO oracleQuestionbankDAO = new OracleQuestionbankDAO();
		return oracleQuestionbankDAO.getQuestions(classID, subject, status,
				topic,frmDate,toDate,isGraphics, frmRecord, noOfRecords);
	}

	public int deleteQuestionbyId(String[] qIds)throws BaseAppException {
		OracleQuestionbankDAO oracleQuestionbankDAO = new OracleQuestionbankDAO();
		return oracleQuestionbankDAO.deleteQuestionbyId(qIds);
	}

	public int getQuestionsCountByUserID(String userID, String classID,
			String subject, String status, String topic,
			String frmDate,String toDate,Integer isGraphics)throws BaseAppException {
		OracleQuestionbankDAO oracleQuestionbankDAO = new OracleQuestionbankDAO();
		return oracleQuestionbankDAO.getQuestionsCountByUserID(userID,
				classID, subject, status, topic,frmDate,toDate,isGraphics);
	}

	public ArrayList getQuestionsByUserID(String userID, String classID,
			String subject, String status, String topic,
			String frmDate,String toDate,Integer isGraphics,
			int min, int max)throws BaseAppException {
		OracleQuestionbankDAO oracleQuestionbankDAO = new OracleQuestionbankDAO();
		return oracleQuestionbankDAO.getQuestionsByUserID(userID, classID,
				subject, status, topic,frmDate,toDate,isGraphics, min, max);
	}

	public int approveQuestionbyIds(String[] qIds) throws BaseAppException{
		OracleQuestionbankDAO oracleQuestionbankDAO = new OracleQuestionbankDAO();
		return oracleQuestionbankDAO.approveQuestionbyIds(qIds);
	}

	public int rejectQuestionbyIds(String[] qIds)throws BaseAppException {
		OracleQuestionbankDAO oracleQuestionbankDAO = new OracleQuestionbankDAO();
		return oracleQuestionbankDAO.rejectQuestionbyIds(qIds);
	}

	public int markQuestionASInactivebyIds(String[] qIds)throws BaseAppException {
		OracleQuestionbankDAO oracleQuestionbankDAO = new OracleQuestionbankDAO();
		return oracleQuestionbankDAO.markQuestionASInactivebyIds(qIds);
	}

	public int linkQuestionToClass(ArrayList objTo) throws BaseAppException{
		OracleQuestionbankDAO oracleQuestionbankDAO = new OracleQuestionbankDAO();
		return oracleQuestionbankDAO.linkQuestionToClass(objTo);
	}

	public int updateQuestionToClassLink(ArrayList linkQToClassTOList) throws BaseAppException{
		OracleQuestionbankDAO oracleQuestionbankDAO = new OracleQuestionbankDAO();
		return oracleQuestionbankDAO.updateQuestionToClassLink(linkQToClassTOList);
	}

	public int getQuestionToClass_LinkCount(int QuestionId) throws BaseAppException{
		OracleQuestionbankDAO oracleQuestionbankDAO = new OracleQuestionbankDAO();
		return oracleQuestionbankDAO.getQuestionToClass_LinkCount(QuestionId);
	}

	public ArrayList getQuestionToClass_LinkedData(int QuestionId)throws BaseAppException {
		OracleQuestionbankDAO oracleQuestionbankDAO = new OracleQuestionbankDAO();
		return oracleQuestionbankDAO.getQuestionToClass_LinkedData(QuestionId);
	}	
	
	public InputStream getPictureQuestion_ImagesByID(int QuestionId,int imageFor)throws BaseAppException {
		OracleQuestionbankDAO oracleQuestionbankDAO = new OracleQuestionbankDAO();
		return oracleQuestionbankDAO.getPictureQuestion_ImagesByID(QuestionId,imageFor);
	}
	
	public ArrayList getSelectedClassOfStudent(int userId)throws BaseAppException {
		return new OracleQuestionbankDAO().getSelectedClassOfStudent(userId);
	}
	
	public ArrayList getSelectedSubjectOfStudent(int userId)throws BaseAppException {
		return new OracleQuestionbankDAO().getSelectedSubjectOfStudent(userId);
	}
}
