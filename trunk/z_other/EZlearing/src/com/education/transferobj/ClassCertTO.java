package com.education.transferobj;

import java.io.Serializable;

public class ClassCertTO implements Serializable {

	public int classCertId = 0;
	public String classCertName = "";

	public int getClassCertId() {
		return classCertId;
	}

	public void setClassCertId(int classCertId) {
		this.classCertId = classCertId;
	}

	public String getClassCertName() {
		return classCertName;
	}

	public void setClassCertName(String classCertName) {
		this.classCertName = classCertName;
	}

}
