package com.education.transferobj;

import java.io.InputStream;

public class ContentUploadTO {

	private String classId ;
	private String contentId;
	private String subjectId = "";
	private String topicId = "";
	private String subTopicId = "";
	private InputStream contentFileStream = null;	
	private InputStream supportingFileStream = null;
	private int contentStreamLength ;
	private int supportingStreamLength ;
	private String contentFileName = "";
	private String supportingtFileName = "";
	private int hasSupportingFiles;
	private int contentTypeID ;
	private String topicValue = "";
	private String subTopicValue = "";
		
		
	public int getContentTypeID() {
		return contentTypeID;
	}
	public void setContentTypeID(int contentTypeID) {
		this.contentTypeID = contentTypeID;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topic) {
		this.topicId = topic;
	}
	public String getSubTopicId() {
		return subTopicId;
	}
	public void setSubTopicId(String subTopic) {
		this.subTopicId = subTopic;
	}
	public InputStream getContentFileStream() {
		return contentFileStream;
	}
	public void setContentFileStream(InputStream contentFileStream) {
		this.contentFileStream = contentFileStream;
	}
	public InputStream getSupportingFileStream() {
		return supportingFileStream;
	}
	public void setSupportingFileStream(InputStream supportingFileStream) {
		this.supportingFileStream = supportingFileStream;
	}
	public int getContentStreamLength() {
		return contentStreamLength;
	}
	public void setContentStreamLength(int contentStreamLength) {
		this.contentStreamLength = contentStreamLength;
	}
	public int getSupportingStreamLength() {
		return supportingStreamLength;
	}
	public void setSupportingStreamLength(int supportingStreamLength) {
		this.supportingStreamLength = supportingStreamLength;
	}
	public String getContentFileName() {
		return contentFileName;
	}
	public void setContentFileName(String contentFileName) {
		this.contentFileName = contentFileName;
	}
	public String getSupportingtFileName() {
		return supportingtFileName;
	}
	public void setSupportingtFileName(String supportingtFileName) {
		this.supportingtFileName = supportingtFileName;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public int getHasSupportingFiles() {
		return hasSupportingFiles;
	}
	public void setHasSupportingFiles(int hasSupportingFiles) {
		this.hasSupportingFiles = hasSupportingFiles;
	}
	public String getTopicValue() {
		return topicValue;
	}
	public void setTopicValue(String topicValue) {
		this.topicValue = topicValue;
	}
	public String getSubTopicValue() {
		return subTopicValue;
	}
	public void setSubTopicValue(String subTopicValue) {
		this.subTopicValue = subTopicValue;
	}
	
}
