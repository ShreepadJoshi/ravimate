package com.education.transferobj;

import java.io.Serializable;

public class TestQuestion implements Serializable{
	
	private int questionID;
	private int testpaperQuestionNumber;
	private String answer;
	private String questionStatus;
	
	private int isGraphics;
	private String option1Text;
	private String option2Text;
	private String option3Text;
	private String option4Text;
	private String option5Text;
	private int hasOption1Img;
	private int hasOption2Img;
	private int hasOption3Img;
	private int hasOption4Img;
	private int hasOption5Img;
	
	
	public int getIsGraphics() {
		return isGraphics;
	}

	public void setIsGraphics(int isGraphics) {
		this.isGraphics = isGraphics;
	}

	public String getOption1Text() {
		return option1Text;
	}

	public void setOption1Text(String option1Text) {
		this.option1Text = option1Text;
	}

	public String getOption2Text() {
		return option2Text;
	}

	public void setOption2Text(String option2Text) {
		this.option2Text = option2Text;
	}

	public String getOption3Text() {
		return option3Text;
	}

	public void setOption3Text(String option3Text) {
		this.option3Text = option3Text;
	}

	public String getOption4Text() {
		return option4Text;
	}

	public void setOption4Text(String option4Text) {
		this.option4Text = option4Text;
	}

	public String getOption5Text() {
		return option5Text;
	}

	public void setOption5Text(String option5Text) {
		this.option5Text = option5Text;
	}

	public int getHasOption1Img() {
		return hasOption1Img;
	}

	public void setHasOption1Img(int hasOption1Img) {
		this.hasOption1Img = hasOption1Img;
	}

	public int getHasOption2Img() {
		return hasOption2Img;
	}

	public void setHasOption2Img(int hasOption2Img) {
		this.hasOption2Img = hasOption2Img;
	}

	public int getHasOption3Img() {
		return hasOption3Img;
	}

	public void setHasOption3Img(int hasOption3Img) {
		this.hasOption3Img = hasOption3Img;
	}

	public int getHasOption4Img() {
		return hasOption4Img;
	}

	public void setHasOption4Img(int hasOption4Img) {
		this.hasOption4Img = hasOption4Img;
	}

	public int getHasOption5Img() {
		return hasOption5Img;
	}

	public void setHasOption5Img(int hasOption5Img) {
		this.hasOption5Img = hasOption5Img;
	}

	public TestQuestion(int questionID,int testPaperQNumber,String answer,String questionStatus) {
		this.questionID = questionID;
		this.testpaperQuestionNumber = testPaperQNumber;
		this.answer = answer;
		this.questionStatus = questionStatus;
	}
	
	public TestQuestion() {}
	
	public int getQuestionID() {
		return questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	public int getTestpaperQuestionNumber() {
		return testpaperQuestionNumber;
	}
	public void setTestpaperQuestionNumber(int testpaperQuestionNumber) {
		this.testpaperQuestionNumber = testpaperQuestionNumber;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getQuestionStatus() {
		return questionStatus;
	}
	public void setQuestionStatus(String questionStatus) {
		this.questionStatus = questionStatus;
	}
}