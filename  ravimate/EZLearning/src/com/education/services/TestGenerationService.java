package com.education.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import org.expframework.exceptions.BaseAppException;

import com.education.dao.OracleTestDAO;
import com.education.transferobj.QuestionBankTO;
import com.education.transferobj.TestQuestion;
import com.education.util.EducationConstant;

public class TestGenerationService {

	/**
	 * Checks Question bank DB if it has Questions count required to sample Test
	 * @param classID - classId for which SampleTest needs to be generated
	 * @param sampleTestPaperQCount - Sample Test papaer question count
	 * @return boolean true /false
	 */
	public boolean isSampleTestValid_forClassId(int classID,int subjectId,int sampleTestPaperQCount)throws BaseAppException{
		boolean isQuestionBank_shortOfQeustions = false;
		OracleTestDAO dao = new OracleTestDAO();
		int questionCount = dao.getSampleTestQuestionCount_ByClassID(classID,subjectId);
		System.out.println("sampleTestPaperQCount----------------"+sampleTestPaperQCount);
		System.out.println("questionCount----------------"+questionCount);
		if(questionCount < sampleTestPaperQCount)
			isQuestionBank_shortOfQeustions = false;
		else
			isQuestionBank_shortOfQeustions = true;
		
		return isQuestionBank_shortOfQeustions;
		
	}
	
	public TreeMap getSampleTestQuestions_byClassId(int classID,int subjectId, int noOfQuestions)throws BaseAppException{
		TreeMap questionMap = new TreeMap();
		OracleTestDAO dao = new OracleTestDAO();		
		ArrayList questionIDList = dao.getSampleTestQuestionID_byClassID(classID,subjectId,noOfQuestions);
		String questionID_delimString = "";
		
		//Prepare Delimited string of questionID's
		Iterator itr = questionIDList.iterator();	
		while(itr.hasNext()){
			String questionID = (String)itr.next();
			questionID_delimString += questionID_delimString.equals("") ? questionID : ","+questionID;
		}
		
		
		
		//Get Sampletest Questions
		ArrayList sampleTestQuestionList =  dao.getSampleTestQuestion_ByclassID(classID, questionID_delimString);
		
		//Prepare Map form QuestionList for display purpose
		itr = sampleTestQuestionList.iterator();
		int questionNumber =1;
		while(itr.hasNext()){
			QuestionBankTO questionBanlTO = (QuestionBankTO)itr.next();
			
			questionMap.put(new Integer(questionNumber),
					new	TestQuestion(questionBanlTO.getQuestionId(),
									 questionNumber,
									 null ,
									 EducationConstant.TEST_QUESTION_STATUS_TOATTEMPT));
			questionNumber++;
		}
		return questionMap;
	}

	/*
	public int getSampleTestQuestionCount_ByClassID(int classID)throws BaseAppException{
		return new OracleTestDAO().getSampleTestQuestionCount_ByClassID(classID);
	}
	*/
	
	public QuestionBankTO getSampleTestQuestionDetails(String questionID)throws BaseAppException{
		return new OracleTestDAO().getSampleTestQuestionDetails(questionID);
	}
}
