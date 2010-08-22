package com.education.formbeans;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;


import com.education.displaytag.IExtendedPaginatedList;
import com.education.util.Utilities.Option;

public class ManageTopicsActionForm extends ActionForm{
	
	private String sch_subject = "";
	private String sch_classType = "";
	
	/** Store hidden values **/
	private String[] topicIds;
	private String[] topicList;
	
	private String[] subTopicIds;
	private String[] subTopicList;
	
	private String[] recordStatus;
	private IExtendedPaginatedList pgSearchResults = null;
	
	private int newRecordIndex;
	private String[] SubjectTopicIdList;
	private String[] classList;

    
	/* drop down values */
    private ArrayList<Option> subjectOptions = null;
   private ArrayList<Option> classOptions = null;

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

	public ArrayList<Option> getSubjectOptions() {
		return subjectOptions;
	}

	public void setSubjectOptions(ArrayList<Option> subjectOptions) {
		this.subjectOptions = subjectOptions;
	}
	

	public String[] getTopicIds() {
		return topicIds;
	}

	public void setTopicIds(String[] topicIds) {
		this.topicIds = topicIds;
	}

	public String[] getTopicList() {
		return topicList;
	}

	public void setTopicList(String[] topicList) {
		this.topicList = topicList;
	}

	public String[] getSubTopicIds() {
		return subTopicIds;
	}

	public void setSubTopicIds(String[] subTopicIds) {
		this.subTopicIds = subTopicIds;
	}

	public String[] getSubTopicList() {
		return subTopicList;
	}

	public void setSubTopicList(String[] subTopicList) {
		this.subTopicList = subTopicList;
	}

	public IExtendedPaginatedList getPgSearchResults() {
		return pgSearchResults;
	}

	public void setPgSearchResults(IExtendedPaginatedList pgSearchResults) {
		this.pgSearchResults = pgSearchResults;
	}

	public String[] getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String[] recordStatus) {
		this.recordStatus = recordStatus;
	}

	public int getNewRecordIndex() {
		return newRecordIndex;
	}

	public void setNewRecordIndex(int newRecordIndex) {
		this.newRecordIndex = newRecordIndex;
	}

	public String[] getSubjectTopicIdList() {
		return SubjectTopicIdList;
	}

	public void setSubjectTopicIdList(String[] subjectTopicIdList) {
		SubjectTopicIdList = subjectTopicIdList;
	}

	public ArrayList<Option> getClassOptions() {
		return classOptions;
	}

	public void setClassOptions(ArrayList<Option> classOptions) {
		this.classOptions = classOptions;
	}

	public String[] getClassList() {
		return classList;
	}

	public void setClassList(String[] classList) {
		this.classList = classList;
	}	
}