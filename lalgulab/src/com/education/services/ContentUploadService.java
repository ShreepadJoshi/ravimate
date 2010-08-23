package com.education.services;

import java.util.ArrayList;

import org.expframework.exceptions.BaseAppException;

import com.education.dao.OracleContentUploadDAO;
import com.education.transferobj.ContentUploadTO;

public class ContentUploadService {

	public int deleteContentUploadDetails(int contentId) throws BaseAppException{		
		return new OracleContentUploadDAO().deleteContentUploadDetails(contentId);
	}
	
	public ContentUploadTO getContent_Blob_ById(int contentID)throws BaseAppException {
		return new OracleContentUploadDAO().getContent_Blob_ById(contentID);
	}
	
	public int insertContentUploadDetails(ContentUploadTO contentTo)throws BaseAppException{
		return new OracleContentUploadDAO().insertContentUploadDetails(contentTo);
	}
	
	public int updateContentUploadDetails(ContentUploadTO contentTo)throws BaseAppException{
		return new OracleContentUploadDAO().updateContentUploadDetails(contentTo);
	}

	public int getContentUploadCount(String classId,String subjectId)throws BaseAppException {
		return new OracleContentUploadDAO().getContentUploadCount(classId, subjectId);
	}
	
	public ArrayList getContentUploadDetails(ContentUploadTO contentTO,
				int frmRecord, int noOfRecords)throws BaseAppException {
		return new OracleContentUploadDAO().getContentUploadDetails(contentTO, frmRecord, noOfRecords);
	}
}
