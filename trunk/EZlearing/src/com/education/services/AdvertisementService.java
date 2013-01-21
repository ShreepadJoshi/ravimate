/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.education.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import org.expframework.exceptions.BaseAppException;

import com.education.dao.AdvertisementDAO;
import com.education.dao.OracleQuestionbankDAO;
import com.education.formbeans.AdvertisementForm;
import com.education.transferobj.AdvertisementTO;
import com.education.transferobj.ContentUploadTO;
import com.education.transferobj.QuestionBankTO;

/**
 * 
 * @author Administrator
 */
public class AdvertisementService {

	public AdvertisementTO getAdvertisement() throws BaseAppException{
		return new AdvertisementDAO().getAdvertisement();
	}
	
	public AdvertisementForm getAdvertisementForm() throws BaseAppException{
		return new AdvertisementForm(getAdvertisement());
	}
}
