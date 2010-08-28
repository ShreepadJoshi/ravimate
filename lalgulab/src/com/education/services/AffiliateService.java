package com.education.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.expframework.exceptions.BaseAppException;

import com.education.dao.AffiliateDao;
import com.education.dao.OracleRegistrationDAO;
import com.education.exceptions.TAffiliateDaoException;
import com.education.transferobj.AffiliateTO;
import com.education.transferobj.TAffiliatePk;
import com.education.transferobj.UserTO;

public class AffiliateService {
	
	/**
	 * Method to Add Affiliate to the <b>t_affiliate</b> table.
	 * @param affTo Transfer Object of Affiliate
	 * @return {@link TAffiliatePk} Affiliate Primary Key.
	 * @throws TAffiliateDaoException
	 * @throws BaseAppException 
	 */
	public TAffiliatePk addAffiliate(AffiliateTO affTo) throws TAffiliateDaoException, BaseAppException
	{
		AffiliateDao affDao = new AffiliateDao();
		UserRegistrationService service = new UserRegistrationService();
		UserTO userTo = service.registerUser(affTo);
		if(userTo == null) 
			throw new BaseAppException("Cannot Insert Data into User table."); 
		affTo.setAffiliateId(String.valueOf(userTo.getUserId()));
		return affDao.insert(affTo);
	}
	
	@SuppressWarnings("unchecked")
	public List<AffiliateTO> searchAffiliate(AffiliateTO dto) throws TAffiliateDaoException
	{
		AffiliateDao affDao = new AffiliateDao();
		ArrayList<String> param = new ArrayList<String>(3);
		StringBuilder sql = new StringBuilder(512);
		sql.append("SELECT t.FirstName, t.lastName, a.affiliate_org_name from t_user t, t_affiliate a ");
		sql.append("WHERE t.userid = a.affiliate_Id ");
		System.out.println("===========searchAffiliate========================"+dto.getFirstName());
		if (dto.getFirstName() != null && !"".equals(dto.getFirstName().trim()))
		{
			sql.append("AND t.firstName = ? ");
			param.add(dto.getFirstName());
		}
		 
		if(dto.getLastName() != null && !"".equals(dto.getLastName().trim()))
		{
			sql.append("AND t.lastName =? ");
			param.add(dto.getLastName());
		}
		
		if(dto.getAffiliateOrgName() != null && !"".equals(dto.getAffiliateOrgName().trim()))
		{
			sql.append("AND a.affiliate_org_name = ?");
			param.add(dto.getAffiliateOrgName());
		}
		
		AffiliateTO[] affTo = affDao.findByDynamicSelect(sql.toString(), param.toArray());
		
		return affTo != null ? Arrays.asList(affTo) : Collections.EMPTY_LIST;
	}

}
