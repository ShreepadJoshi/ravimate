package com.education.formbeans;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.education.util.Utilities.Option;

public class FullTestForm extends ActionForm {

	private String classID = "";
	private String subjectID = "";
	private String topicID = "";
	private int noOfQuestions = 0;
	private int simpleQuestionPercent = 0;
	private int mediumQuestionPercent = 0;
	private int complexQuestionPercent = 0;
	
	/* drop down values */
    private ArrayList<Option> classTypeOptions = null;
    private ArrayList<Option> subjectTypeOptions = null;
    private ArrayList<Option> topicTypeOptions = null;

	public ArrayList<Option> getClassTypeOptions() {
		return classTypeOptions;
	}

	public void setClassTypeOptions(ArrayList<Option> classTypeOptions) {
		this.classTypeOptions = classTypeOptions;
	}

	public String getClassID() {
		return classID;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	}

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	public String getTopicID() {
		return topicID;
	}

	public void setTopicID(String topicID) {
		this.topicID = topicID;
	}

	public ArrayList<Option> getSubjectTypeOptions() {
		return subjectTypeOptions;
	}

	public void setSubjectTypeOptions(ArrayList<Option> subjectTypeOptions) {
		this.subjectTypeOptions = subjectTypeOptions;
	}

	public ArrayList<Option> getTopicTypeOptions() {
		return topicTypeOptions;
	}

	public void setTopicTypeOptions(ArrayList<Option> topicTypeOptions) {
		this.topicTypeOptions = topicTypeOptions;
	}

	public int getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(int noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public int getSimpleQuestionPercent() {
		return simpleQuestionPercent;
	}

	public void setSimpleQuestionPercent(int simpleQuestionPercent) {
		this.simpleQuestionPercent = simpleQuestionPercent;
	}

	public int getMediumQuestionPercent() {
		return mediumQuestionPercent;
	}

	public void setMediumQuestionPercent(int mediumQuestionPercent) {
		this.mediumQuestionPercent = mediumQuestionPercent;
	}

	public int getComplexQuestionPercent() {
		return complexQuestionPercent;
	}

	public void setComplexQuestionPercent(int complexQuestionPercent) {
		this.complexQuestionPercent = complexQuestionPercent;
	}
}
