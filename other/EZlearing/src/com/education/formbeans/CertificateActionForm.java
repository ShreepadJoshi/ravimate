package com.education.formbeans;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

public class CertificateActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private int certId = 0;
	private String certName = "";
	
	public int getCertId() {
		return certId;
	}
	public void setCertId(int certId) {
		this.certId = certId;
	}
	public String getCertName() {
		return certName;
	}
	public void setCertName(String certName) {
		this.certName = certName;
	}
}
