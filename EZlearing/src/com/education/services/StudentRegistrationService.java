package com.education.services;

import java.util.List;

import org.expframework.exceptions.BaseAppException;

import com.education.dao.StudentRegistrationDAO;
import com.education.transferobj.ClassCertTO;

public class StudentRegistrationService {

	public List<ClassCertTO> getClassCertificateList() throws BaseAppException {
		StudentRegistrationDAO dao = new StudentRegistrationDAO();
		return dao.getClassCertificateList();
	}
}
