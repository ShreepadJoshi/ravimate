package com.education.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.expframework.exceptions.BaseAppException;

import com.education.transferobj.ClassCertTO;
import com.education.transferobj.DropDownOption;
import com.education.transferobj.LinkQTOClassTO;
import com.education.transferobj.TopicSubTopicTO;
import com.education.util.GetConnection;
import com.education.util.Utilities;

public class OracleEZDAO extends AbstractDAO{
	
	// method to get all class type
	public ArrayList getClassORCertList() throws BaseAppException{
		String sql = "select * From t_class_cert order by certificatename";
		Connection con;
		PreparedStatement psmt;
		ResultSet rs;
		ArrayList classOrCertList = new ArrayList();		 

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
		}
		return classOrCertList;
	}
	
	// method to get all class type
	public ArrayList getRegistration_Status_List() throws BaseAppException {
		String sql = " select regStatus_Value,regStatus_Description "+
					 " from t_registration_status order by regStatus_Description";
		Connection con;
		PreparedStatement psmt;
		ResultSet rs;
		ArrayList regStatusList = new ArrayList();		 

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				DropDownOption option  = new DropDownOption();
				option.setLabel(rs.getString("regStatus_Description"));
				option.setValue(rs.getString("regStatus_Value"));
				regStatusList.add(option);
			}

		}  catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return regStatusList;
	}
	
	// method to get all Content type list
	public ArrayList getContent_TypeList() throws BaseAppException {
		String sql = " select contentType_value,contentType_description "+
					 " from t_contenttype ";
		Connection con;
		PreparedStatement psmt;
		ResultSet rs;
		ArrayList contentTypeList = new ArrayList();		 

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				DropDownOption option  = new DropDownOption();
				option.setLabel(rs.getString("contentType_description"));
				option.setValue(rs.getString("contentType_value"));
				contentTypeList.add(option);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return contentTypeList;
	}

	
	// method to get all Question Complexity
	public ArrayList getQuestionComplexity() throws BaseAppException {
		String sql = "Select "+ 
					 "complexity_Id,complexityValue "+
					 "From t_question_complexity order by complexityValue ";
		Connection con;
		PreparedStatement psmt;
		ResultSet rs;
		ArrayList questionComplexityList = new ArrayList();		 

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				LinkQTOClassTO objTo = new LinkQTOClassTO();
				objTo.setComplexityId(rs.getString("complexity_Id"));
				objTo.setComplexityValue(rs.getString("complexityValue"));
				questionComplexityList.add(objTo);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return questionComplexityList;
	}

	// method to get all Question Complexity
	public ArrayList getSubjects() throws BaseAppException{
		String sql = "Select DISTINCT "+ 
					 "subject_Id,subjectValue "+
					 "From t_subjects order by subjectValue ";
		Connection con;
		PreparedStatement psmt;
		ResultSet rs;
		ArrayList subjectList = new ArrayList();		 

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
		}
		return subjectList;
	}
	
	public ArrayList getSubjecListByClassId(String classId) throws BaseAppException{
		String sql = " SELECT DISTINCT subjectMasterTable.subject_Id, subjectMasterTable.subjectValue "+
					 " FROM t_class_subject_topic_subTopic_mapping mappingTable "+
					 " INNER JOIN t_subjects subjectMasterTable " +
					 " 		ON mappingTable.subjectId = subjectMasterTable.subject_Id ";		
		String where = " WHERE mappingTable.classId = ? ";
		
		if( !Utilities.isNullOrBlank(classId)){
			sql +=where;
		}
		sql +=" order by subjectMasterTable.subjectValue ";
		
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ArrayList subjectList = new ArrayList();		 

		try {
			con = GetConnection.getSimpleConnection();
			psmt = con.prepareStatement(sql);
			if( !Utilities.isNullOrBlank(classId)){
				psmt.setString(1,classId);
			}
			rs = psmt.executeQuery();
			while (rs.next()) {
				TopicSubTopicTO objTo = new TopicSubTopicTO();
				objTo.setSubjectId(rs.getInt("subject_Id"));
				objTo.setSubjectValue((rs.getString("subjectValue")));
				subjectList.add(objTo);
			}

		}  catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return subjectList;
	}
	
	public ArrayList getTopicListBYSubjectId(String classId,String subjectId) throws BaseAppException{
		String sql = " SELECT DISTINCT topicTable.topicId, topicTable.topicValue "+
					 " FROM t_class_subject_topic_subTopic_mapping mapping_Table "+
					 " INNER JOIN t_topics topicTable "+
					 "   ON mapping_Table.topicId = topicTable.topicId ";
		
		if((!Utilities.isNullOrBlank(subjectId)) && (!Utilities.isNullOrBlank(classId))){
			
			sql+=" where mapping_Table.classId ="+classId+" and mapping_Table.subjectId="+subjectId;
		}
		sql += " order by topicTable.topicValue ";
		
		Connection con;
		PreparedStatement psmt;
		ResultSet rs;
		ArrayList topicList = new ArrayList();		 
		
		try {
		con = GetConnection.getSimpleConnection();
		psmt = con.prepareStatement(sql);
		
		rs = psmt.executeQuery();
		while (rs.next()) {
			TopicSubTopicTO objTo = new TopicSubTopicTO();
			objTo.setTopicId(rs.getInt("topicId"));
			objTo.setTopicvalue((rs.getString("topicValue")));
			topicList.add(objTo);
		}
		
		}  catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return topicList;
	}
	
	public ArrayList getTopicList() throws BaseAppException{
		String sql = "Select DISTINCT "+ 
		 			 "topicId,topicValue "+
		 			 "From t_topics order by topicValue  ";
		Connection con;
		PreparedStatement psmt;
		ResultSet rs;
		ArrayList topicList = new ArrayList();		 
		
		try {
		con = GetConnection.getSimpleConnection();
		psmt = con.prepareStatement(sql);
		
		rs = psmt.executeQuery();
		while (rs.next()) {
			TopicSubTopicTO objTo = new TopicSubTopicTO();			
			objTo.setTopicId(rs.getInt("topicId"));
			objTo.setTopicvalue((rs.getString("topicValue")));
			topicList.add(objTo);
		}
		psmt.close();
		
		}  catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return topicList;
	}
	
	public ArrayList getSubTopicListBYSubjectId(String subjectId,String strTopicValue) throws BaseAppException{
		String sql = " SELECT DISTINCT subTopicTable.subTopicId,subTopicTable.subTopicValue "+
					 " FROM t_class_subject_topic_subTopic_mapping mapping_Table "+
					 " INNER JOIN t_subtopics subTopicTable "+
					 "   ON mapping_Table.subTopicId = subTopicTable.subTopicId "+
					 " INNER JOIN t_topics TopicTable "+
					 "	   ON mapping_Table.topicId = TopicTable.topicId ";

		String where = ""; 
		
		where += ( !Utilities.isNullOrBlank(subjectId) ? 
					where.equals("") ? " where mapping_Table.subjectId="+subjectId : " AND mapping_Table.subjectId="+subjectId : "" );
		
		where += ( !Utilities.isNullOrBlank(strTopicValue) ? 
				where.equals("") ? " where mapping_Table.topicId='"+strTopicValue+"'" : " AND mapping_Table.topicId='"+strTopicValue+"'" : "" );

		sql +=where;
		sql +=" order by subTopicTable.subTopicValue ";
		
		Connection con;
		PreparedStatement psmt;
		ResultSet rs;
		ArrayList subTopicList = new ArrayList();		 
		
		try {
		con = GetConnection.getSimpleConnection();
		psmt = con.prepareStatement(sql);
		
		rs = psmt.executeQuery();
		while (rs.next()) {
			TopicSubTopicTO objTo = new TopicSubTopicTO();
			objTo.setSubTopicId(rs.getInt("subTopicId"));
			objTo.setSubTopicValue((rs.getString("subtopicValue")));
			subTopicList.add(objTo);
		}
		
		}  catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return subTopicList;
	}
	
	public ArrayList getSubTopicList() throws BaseAppException{
		String sql = "Select DISTINCT "+ 
		 			 "subTopicId,subTopicValue "+
		 			 "From t_subtopics order by subTopicValue ";
		Connection con;
		PreparedStatement psmt;
		ResultSet rs;
		ArrayList subTopicList = new ArrayList();		 
		
		try {
		con = GetConnection.getSimpleConnection();
		psmt = con.prepareStatement(sql);
		
		rs = psmt.executeQuery();
		while (rs.next()) {
			TopicSubTopicTO objTo = new TopicSubTopicTO();
			objTo.setSubjectTopicId(rs.getInt("subTopicId"));
			objTo.setSubTopicValue((rs.getString("subTopicValue")));
			subTopicList.add(objTo);
		}
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return subTopicList;
	}

	public static void main(String[] args)throws BaseAppException {
		new OracleEZDAO().getSubTopicListBYSubjectId("1","1");
	}
}
