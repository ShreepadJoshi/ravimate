package com.education.services;

import java.util.ArrayList;

import org.expframework.exceptions.BaseAppException;

import com.education.dao.OracleEZDAO;

public class EZBusinessServices {

	public ArrayList getClassORCertList()throws BaseAppException {		
		OracleEZDAO oracleEZDAO = new OracleEZDAO();
		return oracleEZDAO.getClassORCertList();
	}
	
	public ArrayList getQuestionComplexity() throws BaseAppException{		
		OracleEZDAO oracleEZDAO = new OracleEZDAO();
		return oracleEZDAO.getQuestionComplexity();
	}
	
	public ArrayList getSubjects() throws BaseAppException{		
		OracleEZDAO oracleEZDAO = new OracleEZDAO();
		return oracleEZDAO.getSubjects();
	}
	
	public ArrayList getSubjecListByClassId(String classId)throws BaseAppException {		
		OracleEZDAO oracleEZDAO = new OracleEZDAO();
		return oracleEZDAO.getSubjecListByClassId(classId);
	}
	
	
	public ArrayList getTopicListBYSubjectId(String classId , String subjectId)throws BaseAppException{
		OracleEZDAO oracleEZDAO = new OracleEZDAO();
		return oracleEZDAO.getTopicListBYSubjectId(classId, subjectId);
	}
	
	public ArrayList getTopicList()throws BaseAppException{
		OracleEZDAO oracleEZDAO = new OracleEZDAO();
		return oracleEZDAO.getTopicList();
	}
	
	public ArrayList getSubTopicListBYSubjectId(String classId,String subjectId,String strTopicId)throws BaseAppException{
		OracleEZDAO oracleEZDAO = new OracleEZDAO();
		return oracleEZDAO.getSubTopicListBYSubjectId(classId,subjectId, strTopicId);
	}
	
	public ArrayList getSubTopicList()throws BaseAppException{
		OracleEZDAO oracleEZDAO = new OracleEZDAO();
		return oracleEZDAO.getSubTopicList();
	}
	
	public ArrayList getRegistration_Status_List() throws BaseAppException{
		OracleEZDAO oracleEZDAO = new OracleEZDAO();
		return oracleEZDAO.getRegistration_Status_List();
	}
	public ArrayList getContent_TypeList() throws BaseAppException{
		OracleEZDAO oracleEZDAO = new OracleEZDAO();
		return oracleEZDAO.getContent_TypeList();
	}
}
