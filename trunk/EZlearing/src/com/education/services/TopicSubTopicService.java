package com.education.services;

import java.util.ArrayList;

import org.expframework.exceptions.BaseAppException;

import com.education.dao.OracleQuestionbankDAO;
import com.education.dao.OracleTopicSubTopicDAO;

public class TopicSubTopicService {

	public int getManageTopicSubTopic_RecordCount(String subjectId,String classId)throws BaseAppException {		
		return new OracleTopicSubTopicDAO().getManageTopicSubTopic_RecordCount(subjectId,classId);
	}

	public ArrayList getManageTopicSubTopic_List(String subjectId,
			String classId, int min,int max) throws BaseAppException{
		return new OracleTopicSubTopicDAO().getManageTopicSubTopic_List(subjectId,classId,min,max);
	}

	public int insertTopicSubTopic(ArrayList topicSubtopicTO) throws BaseAppException{
		return new OracleTopicSubTopicDAO().insertTopicSubTopic(topicSubtopicTO);
	}

	public int updateTopicSubTopic_BySubjectTopicId(ArrayList topicSubtopicTO) throws BaseAppException{
		return new OracleTopicSubTopicDAO()
				.updateTopicSubTopic_BySubjectTopicId(topicSubtopicTO);
	}
	
	public int deleteTopicSubTopic_BySubjectTopicId(String[] idList)throws BaseAppException {
		return new OracleTopicSubTopicDAO().deleteTopicSubTopic_BySubjectTopicId(idList);
	}
}
