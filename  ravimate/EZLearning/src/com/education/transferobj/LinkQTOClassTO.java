package com.education.transferobj;

public class LinkQTOClassTO {

	public String quid = "";
	public String classId = "";
	public String complexityId = "";
	public String LinkedBy = "";
	
	private String complexityValue ="";
	private String classValue="";
	private String status = "Old";
	
	
	public String getComplexityValue() {
		return complexityValue;
	}
	public void setComplexityValue(String complexityValue) {
		this.complexityValue = complexityValue;
	}
	public String getClassValue() {
		return classValue;
	}
	public void setClassValue(String classValue) {
		this.classValue = classValue;
	}
	public String getQuid() {
		return quid;
	}
	public void setQuid(String quid) {
		this.quid = quid;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getComplexityId() {
		return complexityId;
	}
	public void setComplexityId(String complexityId) {
		this.complexityId = complexityId;
	}
	public String getLinkedBy() {
		return LinkedBy;
	}
	public void setLinkedBy(String linkedBy) {
		LinkedBy = linkedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
