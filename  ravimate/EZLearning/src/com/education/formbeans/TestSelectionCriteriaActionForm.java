package com.education.formbeans;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.education.util.Utilities.Option;

public class TestSelectionCriteriaActionForm extends ActionForm{

	private String sch_classID = "";
	private String subjectID = "";
	
	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	/* drop down values */
    private ArrayList<Option> classTypeOptions = null;
    private ArrayList<Option> subjectTypeOptions = null;

	public ArrayList<Option> getClassTypeOptions() {
		return classTypeOptions;
	}

	public void setClassTypeOptions(ArrayList<Option> classTypeOptions) {
		this.classTypeOptions = classTypeOptions;
	}
	
	public ArrayList<Option> getSubjectTypeOptions() {
		return subjectTypeOptions;
	}

	public void setSubjectTypeOptions(ArrayList<Option> subjectTypeOptions) {
		this.subjectTypeOptions = subjectTypeOptions;
	}

	public String getSch_classID() {
		return sch_classID;
	}

	public void setSch_classID(String sch_classID) {
		this.sch_classID = sch_classID;
	}
}
