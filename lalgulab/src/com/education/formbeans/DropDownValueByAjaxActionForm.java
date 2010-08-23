package com.education.formbeans;

import org.apache.struts.action.ActionForm;

public class DropDownValueByAjaxActionForm extends ActionForm{

	private String subjectId;
	private String classId;
	private String topicValue = "";
	private String subTopicValue = "";
	private String subjectValue = "";
	private String elementName = "";
	
	
	private String pickDropDownvalueFor = "";

	
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

	public String getPickDropDownvalueFor() {
		return pickDropDownvalueFor;
	}

	public void setPickDropDownvalueFor(String pickDropDownvalueFor) {
		this.pickDropDownvalueFor = pickDropDownvalueFor;
	}

	public String getSubjectValue() {
		return subjectValue;
	}

	public void setSubjectValue(String subjectValue) {
		this.subjectValue = subjectValue;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
}
