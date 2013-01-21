/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.education.transferobj;

import java.io.InputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;

import org.apache.struts.upload.FormFile;

/**
 *
 * @author Administrator
 */
public class QuestionBankTO implements Serializable {

    private int questionId;
    private String question = "";
    private String topic = "";
    private String subTopic = "";
    private String createdOn = "";
    private String answer = "";
    private int questionStatusId;
    private int isVerified ;
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
    private String cert = "";
	private String complexity = "";
    private FormFile questionImage = null;
	private FormFile ansOneImage = null;
	private FormFile ansTwoImage = null;
	private FormFile ansThreeImage = null;
	private FormFile ansFourImage = null;
	private FormFile ansFiveImage = null;
	private FormFile ansExplanation = null;
	private LinkedHashMap blobList= new LinkedHashMap();
	private String topicId = "";
	private String subTopicId = "";
	
	/** Question Image Details **/
	private InputStream questionFileStream = null;
	private InputStream Option1FileStream = null;
	private InputStream Option2FileStream = null;
	private InputStream Option3FileStream = null;
	private InputStream Option4FileStream = null;
	private InputStream Option5FileStream = null;
	private InputStream ansExplanationFileStream = null;
	
	private int questionFileStreamLength ;
	private int option1FileStreamLength ;
	private int option2FileStreamLength ;
	private int option3FileStreamLength ;
	private int option4FileStreamLength ;
	private int option5FileStreamLength ;
	private int ansExplanationFileStreamLength ;
	
	private String questionImgFileName = "";
	private String option1ImgFileName = "";
	private String option2ImgFileName = "";
	private String option3ImgFileName = "";
	private String option4ImgFileName = "";
	private String option5ImgFileName = "";
	private String ansExpImgFileName = "";
	
	/** For Edit mode **/
	private String questionImg_editAction = "";
	private String option1Img_editAction = "";	
	private String option2Img_editAction = "";
	private String option3Img_editAction = "";
	private String option4Img_editAction = "";
	private String option5Img_editAction = "";
	private String ansExpImg_editAction = "";
	
	/** For Test Module **/
	private int hasOption1Img;
	private int hasOption2Img;
	private int hasOption3Img;
	private int hasOption4Img;
	private int hasOption5Img;


    
    
    public InputStream getQuestionFileStream() {
		return questionFileStream;
	}

	public void setQuestionFileStream(InputStream questionFileStream) {
		this.questionFileStream = questionFileStream;
	}

	public InputStream getOption1FileStream() {
		return Option1FileStream;
	}

	public void setOption1FileStream(InputStream option1FileStream) {
		Option1FileStream = option1FileStream;
	}

	public InputStream getOption2FileStream() {
		return Option2FileStream;
	}

	public void setOption2FileStream(InputStream option2FileStream) {
		Option2FileStream = option2FileStream;
	}

	public InputStream getOption3FileStream() {
		return Option3FileStream;
	}

	public void setOption3FileStream(InputStream option3FileStream) {
		Option3FileStream = option3FileStream;
	}

	public InputStream getOption4FileStream() {
		return Option4FileStream;
	}

	public void setOption4FileStream(InputStream option4FileStream) {
		Option4FileStream = option4FileStream;
	}

	public InputStream getOption5FileStream() {
		return Option5FileStream;
	}

	public void setOption5FileStream(InputStream option5FileStream) {
		Option5FileStream = option5FileStream;
	}

	public int getQuestionFileStreamLength() {
		return questionFileStreamLength;
	}

	public void setQuestionFileStreamLength(int questionFileStreamLength) {
		this.questionFileStreamLength = questionFileStreamLength;
	}

	public int getOption1FileStreamLength() {
		return option1FileStreamLength;
	}

	public void setOption1FileStreamLength(int option1FileStreamLength) {
		this.option1FileStreamLength = option1FileStreamLength;
	}

	public int getOption2FileStreamLength() {
		return option2FileStreamLength;
	}

	public void setOption2FileStreamLength(int option2FileStreamLength) {
		this.option2FileStreamLength = option2FileStreamLength;
	}

	public int getOption3FileStreamLength() {
		return option3FileStreamLength;
	}

	public void setOption3FileStreamLength(int option3FileStreamLength) {
		this.option3FileStreamLength = option3FileStreamLength;
	}

	public int getOption4FileStreamLength() {
		return option4FileStreamLength;
	}

	public void setOption4FileStreamLength(int option4FileStreamLength) {
		this.option4FileStreamLength = option4FileStreamLength;
	}

	public int getOption5FileStreamLength() {
		return option5FileStreamLength;
	}

	public void setOption5FileStreamLength(int option5FileStreamLength) {
		this.option5FileStreamLength = option5FileStreamLength;
	}

	public int getAnsExplanationFileStreamLength() {
		return ansExplanationFileStreamLength;
	}

	public void setAnsExplanationFileStreamLength(int ansExplanationFileStreamLength) {
		this.ansExplanationFileStreamLength = ansExplanationFileStreamLength;
	}

	public InputStream getAnsExplanationFileStream() {
		return ansExplanationFileStream;
	}

	public void setAnsExplanationFileStream(InputStream ansExplanationFileStream) {
		this.ansExplanationFileStream = ansExplanationFileStream;
	}

	public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerDiscription() {
        return answerDiscription;
    }

    public void setAnswerDiscription(String answerDiscription) {
        this.answerDiscription = answerDiscription;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public int getQuestionStatusId() {
        return questionStatusId;
    }

    public void setQuestionStatusId(int questionStatusId) {
        this.questionStatusId = questionStatusId;
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
    private int isGraphics ;

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
    

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
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

	public LinkedHashMap getBlobList() {
		return blobList;
	}

	public void setBlobList(LinkedHashMap blobList) {
		this.blobList = blobList;
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

	public String getQuestionImgFileName() {
		return questionImgFileName;
	}

	public void setQuestionImgFileName(String questionImgFileName) {
		this.questionImgFileName = questionImgFileName;
	}

	public String getOption1ImgFileName() {
		return option1ImgFileName;
	}

	public void setOption1ImgFileName(String option1ImgFileName) {
		this.option1ImgFileName = option1ImgFileName;
	}

	public String getOption2ImgFileName() {
		return option2ImgFileName;
	}

	public void setOption2ImgFileName(String option2ImgFileName) {
		this.option2ImgFileName = option2ImgFileName;
	}

	public String getOption3ImgFileName() {
		return option3ImgFileName;
	}

	public void setOption3ImgFileName(String option3ImgFileName) {
		this.option3ImgFileName = option3ImgFileName;
	}

	public String getOption4ImgFileName() {
		return option4ImgFileName;
	}

	public void setOption4ImgFileName(String option4ImgFileName) {
		this.option4ImgFileName = option4ImgFileName;
	}

	public String getOption5ImgFileName() {
		return option5ImgFileName;
	}

	public void setOption5ImgFileName(String option5ImgFileName) {
		this.option5ImgFileName = option5ImgFileName;
	}

	public String getAnsExpImgFileName() {
		return ansExpImgFileName;
	}

	public void setAnsExpImgFileName(String ansExpImgFileName) {
		this.ansExpImgFileName = ansExpImgFileName;
	}

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

	public String getComplexity() {
		return complexity;
	}

	public void setComplexity(String complexity) {
		this.complexity = complexity;
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

	
}
