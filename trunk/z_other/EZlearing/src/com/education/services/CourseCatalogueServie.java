package com.education.services;

import java.util.List;

import org.expframework.exceptions.BaseAppException;

import com.education.dao.CourseCatalogueDAO;
import com.education.transferobj.CourseCatalogueTO;

public class CourseCatalogueServie {

	private CourseCatalogueDAO courseCatalogueDAO = new CourseCatalogueDAO();;
	
	public List<CourseCatalogueTO> getDefaultValues() throws BaseAppException {

		return courseCatalogueDAO.getDefaultValues();
	}

	public void setCourseCatalogueDAO(CourseCatalogueDAO courseCatalogueDAO) {
		this.courseCatalogueDAO = courseCatalogueDAO;
	}

	public void addCourseCatalogue(List<CourseCatalogueTO> selectedToList) throws BaseAppException {
		courseCatalogueDAO.addCourseCatalogue(selectedToList);		
	}

	
}
