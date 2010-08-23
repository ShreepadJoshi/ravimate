/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.education.formbeans;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import com.education.util.EducationConstant;
import com.education.util.Utilities;
import com.education.util.Utilities.Option;

/**
 *
 * @author Administrator
 */
public class AddQuestionForm extends ActionForm {

    private int questionId;
    private String question = "";
    private String topic = "";
    private String subTopic = "";
    private int isGraphics;
    private String answer = "";
    private int questionStatusId;
    private int isVerified;
    private String createdBy = "";
    private String createdOn = "";
    private String verifiedBy = "";
    private String verificationRemark = "";
    private String answerDiscription = "";
    private String option1 = "";
    private String option2 = "";
    private String option3 = "";
    private String option4 = "";
    private String option5 = "";
    private String subject = "";
    private String subjectId = "";
    private FormFile questionImage=null;
	private FormFile ansOneImage=null;
	private FormFile ansTwoImage=null;
	private FormFile ansThreeImage=null;
	private FormFile ansFourImage=null;
	private FormFile ansFiveImage=null;
	private FormFile ansExplanation=null;
	private String cert = "";
	private String complexity = "";
	private String topicId = "";
	private String subTopicId = "";
	
	/** For Edit mode **/
	private String questionImg_editAction = "";
	private String option1Img_editAction = "";	
	private String option2Img_editAction = "";
	private String option3Img_editAction = "";
	private String option4Img_editAction = "";
	private String option5Img_editAction = "";
	private String ansExpImg_editAction = "";
	

	/** display date format **/
	private String createdOn_dispFormat = "";
	
    
    private ArrayList<Option> subjectOptions = null;
    private ArrayList<Option> topicOptions = null;
    private ArrayList<Option> subTopicOptions = null;
    private ArrayList<Option> classOptions = null;
    private ArrayList<Option> complexityOptions = null;
    
    
    /** Try **/
    private String uptTopicId = "";
    private String uptsubjectId = "";
    private String uptsubTopicId = "";
    private String uptClassId ="";
    
    /** Page Open Mode **/
    String pageOpen_Mode = ""; 
    
    /** Used by hidden variable - to check if ManageQuestion page is open from Grid **/
    private String navigationFromGrid = "";
    
    /** Used by hidden variable - to check mode in which page is opened **/
    private String mode ="";
    
    /** Next operation  **/
    private String nextOperation= "close"; //default is close

    
    public int getIsGraphics() {
        return isGraphics;
    }

    public void setIsGraphics(int isGraphics) {
        this.isGraphics = isGraphics;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

   
    public int getQuestionStatusId() {
        return questionStatusId;
    }

    public void setQuestionStatusId(int questionStatus) {
        this.questionStatusId = questionStatus;
    }

    public String getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getVerificationRemark() {
        return verificationRemark;
    }

    public void setVerificationRemark(String verificationRemark) {
        this.verificationRemark = verificationRemark;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getAnswerDiscription() {
        return answerDiscription;
    }

    public void setAnswerDiscription(String answerDiscription) {
        this.answerDiscription = answerDiscription;
    }

	public String getNextOperation() {
		return nextOperation;
	}

	public void setNextOperation(String nextOperation) {
		this.nextOperation = nextOperation;
	}
	public String getNavigationFromGrid() {
		return navigationFromGrid;
	}
	public void setNavigationFromGrid(String navigationFromGrid) {
		this.navigationFromGrid = navigationFromGrid;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public ArrayList<Option> getSubjectOptions() {
		return subjectOptions;
	}

	public void setSubjectOptions(ArrayList<Option> subjectOptions) {
		this.subjectOptions = subjectOptions;
	}

	public FormFile getQuestionImage() {
		return questionImage;
	}

	public void setQuestionImage(FormFile questionImage) {
		this.questionImage = questionImage;
	}

	public FormFile getAnsOneImage() {
		return ansOneImage;
	}

	public void setAnsOneImage(FormFile ansOneImage) {
		this.ansOneImage = ansOneImage;
	}

	public FormFile getAnsTwoImage() {
		return ansTwoImage;
	}

	public void setAnsTwoImage(FormFile ansTwoImage) {
		this.ansTwoImage = ansTwoImage;
	}

	public FormFile getAnsThreeImage() {
		return ansThreeImage;
	}

	public void setAnsThreeImage(FormFile ansThreeImage) {
		this.ansThreeImage = ansThreeImage;
	}

	public FormFile getAnsFourImage() {
		return ansFourImage;
	}

	public void setAnsFourImage(FormFile ansFourImage) {
		this.ansFourImage = ansFourImage;
	}

	public FormFile getAnsFiveImage() {
		return ansFiveImage;
	}

	public void setAnsFiveImage(FormFile ansFiveImage) {
		this.ansFiveImage = ansFiveImage;
	}

	public FormFile getAnsExplanation() {
		return ansExplanation;
	}

	public void setAnsExplanation(FormFile ansExplanation) {
		this.ansExplanation = ansExplanation;
	}

	public String getQuestionImg_editAction() {
		return questionImg_editAction;
	}

	public void setQuestionImg_editAction(String questionImg_editAction) {
		this.questionImg_editAction = questionImg_editAction;
	}

	public String getOption1Img_editAction() {
		return option1Img_editAction;
	}

	public void setOption1Img_editAction(String option1Img_editAction) {
		this.option1Img_editAction = option1Img_editAction;
	}

	public String getOption2Img_editAction() {
		return option2Img_editAction;
	}

	public void setOption2Img_editAction(String option2Img_editAction) {
		this.option2Img_editAction = option2Img_editAction;
	}

	public String getOption3Img_editAction() {
		return option3Img_editAction;
	}

	public void setOption3Img_editAction(String option3Img_editAction) {
		this.option3Img_editAction = option3Img_editAction;
	}

	public String getOption4Img_editAction() {
		return option4Img_editAction;
	}

	public void setOption4Img_editAction(String option4Img_editAction) {
		this.option4Img_editAction = option4Img_editAction;
	}

	public String getOption5Img_editAction() {
		return option5Img_editAction;
	}

	public void setOption5Img_editAction(String option5Img_editAction) {
		this.option5Img_editAction = option5Img_editAction;
	}

	public String getAnsExpImg_editAction() {
		return ansExpImg_editAction;
	}

	public void setAnsExpImg_editAction(String ansExpImg_editAction) {
		this.ansExpImg_editAction = ansExpImg_editAction;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedOn_dispFormat() {
		return Utilities.getDate_displayFormat(createdOn);		
	}

	public ArrayList<Option> getTopicOptions() {
		return topicOptions;
	}

	public void setTopicOptions(ArrayList<Option> topicOptions) {
		this.topicOptions = topicOptions;
	}

	public ArrayList<Option> getSubTopicOptions() {
		return subTopicOptions;
	}

	public void setSubTopicOptions(ArrayList<Option> subTopicOptions) {
		this.subTopicOptions = subTopicOptions;
	}

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

	public ArrayList<Option> getClassOptions() {
		return classOptions;
	}

	public void setClassOptions(ArrayList<Option> classOptions) {
		this.classOptions = classOptions;
	}

	public ArrayList<Option> getComplexityOptions() {
		return complexityOptions;
	}

	public void setComplexityOptions(ArrayList<Option> complexityOptions) {
		this.complexityOptions = complexityOptions;
	}

	public String getComplexity() {
		return complexity;
	}

	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}
	
	public String getDefaultComplexity() {
		return "Medium";
	}
 


	
	public String getPageOpen_Mode() {
		return pageOpen_Mode;
	}

	public void setPageOpen_Mode(String pageOpen_Mode) {
		this.pageOpen_Mode = pageOpen_Mode;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getSubTopicId() {
		return subTopicId;
	}

	public void setSubTopicId(String subTopicId) {
		this.subTopicId = subTopicId;
	}

	public String getUptTopicId() {
		return uptTopicId;
	}

	public void setUptTopicId(String uptTopicId) {
		this.uptTopicId = uptTopicId;
	}

	public String getUptsubjectId() {
		return uptsubjectId;
	}

	public void setUptsubjectId(String uptsubjectId) {
		this.uptsubjectId = uptsubjectId;
	}

	public String getUptsubTopicId() {
		return uptsubTopicId;
	}

	public void setUptsubTopicId(String uptsubTopicId) {
		this.uptsubTopicId = uptsubTopicId;
	}

	public String getUptClassId() {
		return uptClassId;
	}

	public void setUptClassId(String uptClassId) {
		this.uptClassId = uptClassId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}		
}



