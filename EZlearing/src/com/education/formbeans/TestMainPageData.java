package com.education.formbeans;

import java.util.HashMap;

public class TestMainPageData {

	private HashMap questionAnswerMap = null;
	private HashMap questionStatusMap = null;
	
	
	
	private int noOfQuestions ;
	private int questionToAttemptCount;
	private int questionsCompletedCount;
	private int questionsToRevistCount;
	private String[] questionsIDArray;
	private String[] questionsStatusArray;
	
	public int getNoOfQuestions() {
		return noOfQuestions;
	}
	public void setNoOfQuestions(int noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}
	public int getQuestionToAttemptCount() {
		return questionToAttemptCount;
	}
	public void setQuestionToAttemptCount(int questionToAttemptCount) {
		this.questionToAttemptCount = questionToAttemptCount;
	}
	public int getQuestionsCompletedCount() {
		return questionsCompletedCount;
	}
	public void setQuestionsCompletedCount(int questionsCompletedCount) {
		this.questionsCompletedCount = questionsCompletedCount;
	}
	public int getQuestionsToRevistCount() {
		return questionsToRevistCount;
	}
	public void setQuestionsToRevistCount(int questionsToRevistCount) {
		this.questionsToRevistCount = questionsToRevistCount;
	}
	public String[] getQuestionsIDArray() {
		return questionsIDArray;
	}
	public void setQuestionsIDArray(String[] questionsIDArray) {
		this.questionsIDArray = questionsIDArray;
	}
	public String[] getQuestionsStatusArray() {
		return questionsStatusArray;
	}
	public void setQuestionsStatusArray(String[] questionsStatusArray) {
		this.questionsStatusArray = questionsStatusArray;
	}
	public HashMap getQuestionAnswerMap() {
		return questionAnswerMap;
	}
	public void setQuestionAnswerMap(HashMap questionAnswerMap) {
		this.questionAnswerMap = questionAnswerMap;
	}
	public HashMap getQuestionStatusMap() {
		return questionStatusMap;
	}
	public void setQuestionStatusMap(HashMap questionStatusMap) {
		this.questionStatusMap = questionStatusMap;
	}
	
	
}
