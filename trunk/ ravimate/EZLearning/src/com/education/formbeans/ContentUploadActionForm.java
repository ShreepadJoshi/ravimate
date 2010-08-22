package com.education.formbeans;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.education.displaytag.IExtendedPaginatedList;
import com.education.util.Utilities.Option;

public class ContentUploadActionForm extends ActionForm {

	/** search related attributes **/
	private String sch_subject = "";
	private String sch_classType = "";
	
	private IExtendedPaginatedList pgSearchResults = null;
	private int newRecordIndex;
	private String[] topicList;
	private String[] subTopicList;
	private FormFile[] contentFileList;
	private FormFile[] suppFileList;
	private int contentTypeID ;
	
	/** Add/update action performed **/
	private String actionPerformed = "";
	
		    
	/* drop down values */
    private ArrayList<Option> classTypeOptions = null;
    private ArrayList<Option> subjectOptions = null;
    private ArrayList<Option> topicOptions = null;
    private ArrayList<Option> subTopicOptions = null;
    private ArrayList<Option> contentTypeOptions = null;
    
    /** Add/update values **/
    private String uptcontentFileName = "";
    private String uptsuppFileName = "";
    private String uptTopicId = "";
    private String uptsubjectId = "";
    private String uptsubTopicId = "";
    private String classId = "";
    private String subjectId = "";
    private int uptContentTypeID ;
    private String uptContentTypeName = "";
    private int uptHasExistingSuppFile;
    private String uptContentid;
    private int uptContentStreamLength;
    private int uptSupportStreamLength;
    private String uptClassId = "";
    
    /** Add/update content stream **/
    private FormFile contentFileStream = null;
    private FormFile suppFileStream = null;
    
	
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
	public IExtendedPaginatedList getPgSearchResults() {
		return pgSearchResults;
	}
	public void setPgSearchResults(IExtendedPaginatedList pgSearchResults) {
		this.pgSearchResults = pgSearchResults;
	}
	public int getNewRecordIndex() {
		return newRecordIndex;
	}
	public void setNewRecordIndex(int newRecordIndex) {
		this.newRecordIndex = newRecordIndex;
	}
	public String[] getTopicList() {
		return topicList;
	}
	public void setTopicList(String[] topicList) {
		this.topicList = topicList;
	}
	public String[] getSubTopicList() {
		return subTopicList;
	}
	public void setSubTopicList(String[] subTopicList) {
		this.subTopicList = subTopicList;
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
	public FormFile[] getContentFileList() {
		return contentFileList;
	}
	public void setContentFileList(FormFile[] contentFileList) {
		this.contentFileList = contentFileList;
	}
	public FormFile[] getSuppFileList() {
		return suppFileList;
	}
	public void setSuppFileList(FormFile[] suppFileList) {
		this.suppFileList = suppFileList;
	}
	public String getActionPerformed() {
		return actionPerformed;
	}
	public void setActionPerformed(String actionPerformed) {
		this.actionPerformed = actionPerformed;
	}
	public String getUptcontentFileName() {
		return uptcontentFileName;
	}
	public void setUptcontentFileName(String uptcontentFileName) {
		this.uptcontentFileName = uptcontentFileName;
	}
	public String getUptsuppFileName() {
		return uptsuppFileName;
	}
	public void setUptsuppFileName(String uptsuppFileName) {
		this.uptsuppFileName = uptsuppFileName;
	}
	public FormFile getContentFileStream() {
		return contentFileStream;
	}
	public void setContentFileStream(FormFile contentFileStream) {
		this.contentFileStream = contentFileStream;
	}
	public FormFile getSuppFileStream() {
		return suppFileStream;
	}
	public void setSuppFileStream(FormFile suppFileStream) {
		this.suppFileStream = suppFileStream;
	}
	public String getUptTopicId() {
		return uptTopicId;
	}
	public void setUptTopicId(String uptTopic) {
		this.uptTopicId = uptTopic;
	}
	public String getUptsubTopicId() {
		return uptsubTopicId;
	}
	public void setUptsubTopicId(String uptsubTopic) {
		this.uptsubTopicId = uptsubTopic;
	}
	public String getUptsubjectId() {
		return uptsubjectId;
	}
	public void setUptsubjectId(String uptsubjectId) {
		this.uptsubjectId = uptsubjectId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public int getUptHasExistingSuppFile() {
		return uptHasExistingSuppFile;
	}
	public void setUptHasExistingSuppFile(int uptHasExistingSuppFile) {
		this.uptHasExistingSuppFile = uptHasExistingSuppFile;
	}
	public String getUptContentid() {
		return uptContentid;
	}
	public void setUptContentid(String uptContentid) {
		this.uptContentid = uptContentid;
	}
	public int getUptContentStreamLength() {
		return uptContentStreamLength;
	}
	public void setUptContentStreamLength(int uptContentStreamLength) {
		this.uptContentStreamLength = uptContentStreamLength;
	}
	public int getUptSupportStreamLength() {
		return uptSupportStreamLength;
	}
	public void setUptSupportStreamLength(int uptSupportStreamLength) {
		this.uptSupportStreamLength = uptSupportStreamLength;
	}
	public String getUptClassId() {
		return uptClassId;
	}
	public void setUptClassId(String uptClassId) {
		this.uptClassId = uptClassId;
	}
	
	public ArrayList<Option> getContentTypeOptions() {
		return contentTypeOptions;
	}
	public void setContentTypeOptions(ArrayList<Option> contentTypeOptions) {
		this.contentTypeOptions = contentTypeOptions;
	}
	public int getUptContentTypeID() {
		return uptContentTypeID;
	}
	public void setUptContentTypeID(int uptContentTypeID) {
		this.uptContentTypeID = uptContentTypeID;
	}
	public String getUptContentTypeName() {
		return uptContentTypeName;
	}
	public void setUptContentTypeName(String uptContentTypeName) {
		this.uptContentTypeName = uptContentTypeName;
	}
	public int getContentTypeID() {
		return contentTypeID;
	}
	public void setContentTypeID(int contentTypeID) {
		this.contentTypeID = contentTypeID;
	}
		
}
