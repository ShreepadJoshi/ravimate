package com.education.formbeans;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.education.transferobj.CourseCatalogueTO;

public class CourseCatalogueForm extends ActionForm {

	
	List<CourseCatalogueTO> _toList;

	
	public List<CourseCatalogueTO> getCourseCatalogueTOList() {
		return _toList;
	}

	public void setCourseCatalogueTOList(List<CourseCatalogueTO> toList) {
		_toList = toList;
	}

}
