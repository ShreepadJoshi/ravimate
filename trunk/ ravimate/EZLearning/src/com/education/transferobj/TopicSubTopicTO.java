package com.education.transferobj;

public class TopicSubTopicTO {

	private int subjectTopicId;
	private int topicId;
	private int classid;
	private String topicvalue = "";
	private int subTopicId;
	private String subTopicValue = "";
	private int subjectId;
	private String subjectValue = "";
	
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public String getTopicvalue() {
		return topicvalue;
	}
	public void setTopicvalue(String topicvalue) {
		this.topicvalue = topicvalue;
	}
	public int getSubTopicId() {
		return subTopicId;
	}
	public void setSubTopicId(int subTopicId) {
		this.subTopicId = subTopicId;
	}
	public String getSubTopicValue() {
		return subTopicValue;
	}
	public void setSubTopicValue(String subTopicValue) {
		this.subTopicValue = subTopicValue;
	}
	public int getSubjectTopicId() {
		return subjectTopicId;
	}
	public void setSubjectTopicId(int subjectTopicId) {
		this.subjectTopicId = subjectTopicId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectValue() {
		return subjectValue;
	}
	public void setSubjectValue(String subjectValue) {
		this.subjectValue = subjectValue;
	}
	public int getClassid() {
		return classid;
	}
	public void setClassid(int classid) {
		this.classid = classid;
	}

}
