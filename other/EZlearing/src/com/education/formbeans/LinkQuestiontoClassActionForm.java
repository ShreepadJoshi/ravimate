/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.education.formbeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.education.transferobj.QuestionBankTO;
import com.education.util.EducationConstant;
import com.education.displaytag.IExtendedPaginatedList;
import com.education.util.Utilities.Option;

/**
 *
 * @author Administrator
 */
public class LinkQuestiontoClassActionForm extends ActionForm {
    
		
	private String link_questionID = "";
	private String link_subject = "";
	private String link_subtopic = "";
	private String link_question = "";
	private String link_rowCount = "0";
	private ArrayList link_rowData = null;
	private String[] classList = null;
	private String[] complexityList = null;
	private String[] linkStatus = null;
	
	
	/**To Handle Navigation done to this page**/
	private String navFromPage = "";
	
	/** complexity, classType options **/
	private ArrayList<Option> classTypeOptions = null;
	private ArrayList<Option> complexityOptions = null;
	private ArrayList<Option> subjectOptions = null;
	
	/** backup properties **/
	 private int questionId;
	    private String question = "";
	    private String topic = "";
	    private String subTopic = "";
	    private int isGraphics;
	    private String answer = "";
	    private String questionStatus = "";
	    private int isVerified;
	    private String createdBy = "";
	    private String verifiedBy = "";
	    private String verificationRemark = "";
	    private String answerDiscription = "";
	    private String option1 = "";
	    private String option2 = "";
	    private String option3 = "";
	    private String option4 = "";
	    private String option5 = "";
	    private String subject = "";
	
	private QuestionBankTO questionTo = null;
	
	public QuestionBankTO getQuestionTo() {
		return questionTo;
	}
	public void setQuestionTo(QuestionBankTO questionTo) {
		this.questionTo = questionTo;
	}
	public String getLink_questionID() {
		return link_questionID;
	}
	public void setLink_questionID(String link_questionID) {
		this.link_questionID = link_questionID;
	}
	public String getLink_subject() {
		return link_subject;
	}
	public void setLink_subject(String link_subject) {
		this.link_subject = link_subject;
	}
	public String getLink_subtopic() {
		return link_subtopic;
	}
	public void setLink_subtopic(String link_subtopic) {
		this.link_subtopic = link_subtopic;
	}
	public String getLink_question() {
		return link_question;
	}
	public void setLink_question(String link_question) {
		this.link_question = link_question;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getSubTopic() {
		return subTopic;
	}
	public void setSubTopic(String subTopic) {
		this.subTopic = subTopic;
	}
	public int getIsGraphics() {
		return isGraphics;
	}
	public void setIsGraphics(int isGraphics) {
		this.isGraphics = isGraphics;
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
	public int getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getVerifiedBy() {
		return verifiedBy;
	}
	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}
	public String getVerificationRemark() {
		return verificationRemark;
	}
	public void setVerificationRemark(String verificationRemark) {
		this.verificationRemark = verificationRemark;
	}
	public String getAnswerDiscription() {
		return answerDiscription;
	}
	public void setAnswerDiscription(String answerDiscription) {
		this.answerDiscription = answerDiscription;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getLink_rowCount() {
		return link_rowCount;
	}
	public void setLink_rowCount(String link_rowCount) {
		this.link_rowCount = link_rowCount;
	}
	public ArrayList getLink_rowData() {
		return link_rowData;
	}
	public void setLink_rowData(ArrayList link_rowData) {
		this.link_rowData = link_rowData;
	}
	public String[] getClassList() {
		return classList;
	}
	public void setClassList(String[] classList) {
		this.classList = classList;
	}
	public String[] getComplexityList() {
		return complexityList;
	}
	public void setComplexityList(String[] complexityList) {
		this.complexityList = complexityList;
	}
	public ArrayList<Option> getClassTypeOptions() {
		return classTypeOptions;
	}
	public void setClassTypeOptions(ArrayList<Option> classTypeOptions) {
		this.classTypeOptions = classTypeOptions;
	}
	public ArrayList<Option> getComplexityOptions() {
		return complexityOptions;
	}
	public void setComplexityOptions(ArrayList<Option> complexityOptions) {
		this.complexityOptions = complexityOptions;
	}
	public String[] getLinkStatus() {
		return linkStatus;
	}
	public void setLinkStatus(String[] linkStatus) {
		this.linkStatus = linkStatus;
	}
	public String getNavFromPage() {
		return navFromPage;
	}
	public void setNavFromPage(String navFromPage) {
		this.navFromPage = navFromPage;
	}
	public ArrayList<Option> getSubjectOptions() {
		return subjectOptions;
	}
	public void setSubjectOptions(ArrayList<Option> subjectOptions) {
		this.subjectOptions = subjectOptions;
	}
	  	
}
