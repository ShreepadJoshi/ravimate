package com.education.formbeans;

import java.sql.Date;
import java.util.List;

public class StudentRegistrationBeanActionForm extends FullRegActionForm {

	private static final long serialVersionUID = 1L;

	private List<CertificateActionForm> certificateList;
	private String schoolName = "";
	private String emailID = "";
	private String password = "";
	private String repassword = "";
	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	private Date dob;
	private int[] certIds;

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public List<CertificateActionForm> getCertificateList() {
		return certificateList;
	}

	public void setCertificateList(List<CertificateActionForm> certificateList) {
		this.certificateList = certificateList;
	}

	public int[] getCertIds() {
		return certIds;
	}

	public void setCertIds(int[] certIds) {
		this.certIds = certIds;
	}
}
