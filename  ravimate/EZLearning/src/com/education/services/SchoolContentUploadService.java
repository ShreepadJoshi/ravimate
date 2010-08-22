package com.education.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.education.dao.SchoolContentUploadDao;
import com.education.transferobj.TSchool;

public class SchoolContentUploadService {
	
	private SchoolContentUploadDao _daoMst;
	
	public void setSchoolContentUploadDao(SchoolContentUploadDao dao)
	{
		_daoMst = dao;
	}
	
	
	/**
	 * Method to Batch Insert data from the Excel file.
	 * @param schoolList
	 * @return no of inserts in the table.
	 * @throws DataAccessException
	 */
	public int insertIntoSchool(ArrayList<TSchool> schoolList) throws DataAccessException
	{
		return _daoMst.insertIntoSchool(schoolList);
	}
	
	/**
	 * Find all schools depending upon the criteria.
	 * @param school
	 * @return List of all schools.
	 */
	public List<TSchool> findAllSchools(TSchool school)
	{
		Assert.notNull(school,"School Object Cannot be null");
		return _daoMst.findAllSchools(school);
	}

}
