package com.education.transferobj;

public class CourseCatalogueTO extends AbstractTO{

	private int classSubjectId;
	private int classId;
	private String className;
	private int subjectId;
	private String subjectName;
	private double subjectCost;
	private int userId;
	private String boardName;
	
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public int getClassSubjectId() {
		return classSubjectId;
	}
	public void setClassSubjectId(int classSubjectId) {
		this.classSubjectId = classSubjectId;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public double getSubjectCost() {
		return subjectCost;
	}
	public void setSubjectCost(double subjectCost) {
		this.subjectCost = subjectCost;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getUserId() {
		return this.userId;
	}
}
