package com.education.formbeans;
import org.apache.struts.action.ActionForm;


public class TestQuestionBeanActionForm extends ActionForm{

	private String option1 = "";
	private String option2 = "";
    private String option3 = "";
    private String option4 = "";
    private String option5 = "";
    private String questionNumber = "";
    private String questionID = "";
    private String answer = "";
    private String questionDescription = "";
    
    /** For Test Module **/
	private int hasOption1Img;
	private int hasOption2Img;
	private int hasOption3Img;
	private int hasOption4Img;
	private int hasOption5Img;
	
    private boolean answerDescriptionImgExist;
    private String noOfquestions;
    
    
	
	public String getNoOfquestions() {
		return noOfquestions;
	}
	public void setNoOfquestions(String noOfquestions) {
		this.noOfquestions = noOfquestions;
	}
	public String getQuestionDescription() {
		return questionDescription;
	}
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}
	public String getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(String questionNumber) {
		this.questionNumber = questionNumber;
	}
	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getOption5() {
		return option5;
	}
	public void setOption5(String option5) {
		this.option5 = option5;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
	public boolean isAnswerDescriptionImgExist() {
		return answerDescriptionImgExist;
	}
	public void setAnswerDescriptionImgExist(boolean answerDescriptionImgExist) {
		this.answerDescriptionImgExist = answerDescriptionImgExist;
	}
}
