/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.education.formbeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.education.util.EducationConstant;
import com.education.displaytag.IExtendedPaginatedList;
import com.education.util.Utilities.Option;

/**
 *
 * @author Administrator
 */
public class AdminQuestionListActionForm extends ActionForm {
    
    private String sch_topic = "";
    private String sch_questionStatus = "";
    private String sch_subject = "";
    private String sch_classType = "";
    private String sch_fromDate = "";
    private String sch_toDate = "";
    private int sch_isGraphics = 0;
    
    /* for check box */
    private String checkedList = ""; 
    
    /* drop down values */
    private ArrayList<Option> classTypeOptions = null;
    private ArrayList<Option> subjectOptions = null;
    private ArrayList<Option> questionStatusOptions = null;
    private ArrayList<Option> topicOptions = null;
    
    private IExtendedPaginatedList pgSearchResults = null;
    
    
    private String label = "";
    private String value = "";
    
    
    public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}	

	public ArrayList<Option> getClassTypeOptions() {
		return classTypeOptions;
	}

	public void setClassTypeOptions(ArrayList<Option> classTypeOptions) {
		this.classTypeOptions = classTypeOptions;
	}

	public ArrayList<Option> getSubjectOptions() {
		return subjectOptions;
	}

	public void setSubjectOptions(ArrayList<Option> subjectOptions) {
		this.subjectOptions = subjectOptions;
	}

	public ArrayList<Option> getQuestionStatusOptions() {
		return questionStatusOptions;
	}

	public void setQuestionStatusOptions(ArrayList<Option> questionStatusOptions) {
		this.questionStatusOptions = questionStatusOptions;
	}	
   
	public IExtendedPaginatedList getPgSearchResults() {
		return pgSearchResults;
	}

	public void setPgSearchResults(IExtendedPaginatedList pgSearchResults) {
		this.pgSearchResults = pgSearchResults;
	}

	public String getCheckedList() {
		return checkedList;
	}

	public void setCheckedList(String checkedList) {
		this.checkedList = checkedList;
	}

	public String getSch_topic() {
		return sch_topic;
	}

	public void setSch_topic(String sch_topic) {
		this.sch_topic = sch_topic;
	}

	public String getSch_questionStatus() {
		return sch_questionStatus;
	}

	public void setSch_questionStatus(String sch_questionStatus) {
		this.sch_questionStatus = sch_questionStatus;
	}

	public String getSch_subject() {
		return sch_subject;
	}

	public void setSch_subject(String sch_subject) {
		this.sch_subject = sch_subject;
	}

	public String getSch_classType() {
		return sch_classType;
	}

	public void setSch_classType(String sch_classType) {
		this.sch_classType = sch_classType;
	}

	public String getSch_fromDate() {
		return sch_fromDate;
	}

	public void setSch_fromDate(String sch_fromDate) {
		this.sch_fromDate = sch_fromDate;
	}

	public String getSch_toDate() {
		return sch_toDate;
	}

	public void setSch_toDate(String sch_toDate) {
		this.sch_toDate = sch_toDate;
	}

	public ArrayList<Option> getTopicOptions() {
		return topicOptions;
	}

	public void setTopicOptions(ArrayList<Option> topicOptions) {
		this.topicOptions = topicOptions;
	}

	public int getSch_isGraphics() {
		return sch_isGraphics;
	}

	public void setSch_isGraphics(int sch_isGraphics) {
		this.sch_isGraphics = sch_isGraphics;
	}	
}
