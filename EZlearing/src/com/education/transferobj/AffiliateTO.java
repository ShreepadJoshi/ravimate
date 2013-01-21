/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.education.transferobj;

import java.io.Serializable;

public class AffiliateTO extends RegistrationTo implements Serializable
{
	/** 
	 * This attribute maps to the column affiliate_id in the t_affiliate table.
	 */
	protected String affiliateId;

	/** 
	 * This attribute maps to the column affiliate_org_name in the t_affiliate table.
	 */
	protected String affiliateOrgName;

	/** 
	 * This attribute maps to the column affiliatee_url in the t_affiliate table.
	 */
	protected String affiliateeUrl;

	/** 
	 * This attribute maps to the column affiliate_type in the t_affiliate table.
	 */
	protected String affiliateType;

	/** 
	 * This attribute maps to the column aff_contact_person in the t_affiliate table.
	 */
	protected String affContactPerson;

	/** 
	 * This attribute maps to the column affiliate_addr in the t_affiliate table.
	 */
	protected String affiliateAddr;

	/** 
	 * This attribute maps to the column aff_acct_dtls in the t_affiliate table.
	 */
	protected String affAcctDtls;

	/** 
	 * This attribute maps to the column aff_subs_type in the t_affiliate table.
	 */
	protected String affSubsType;

	/** 
	 * This attribute maps to the column aff_base_fee in the t_affiliate table.
	 */
	protected double affBaseFee;

	/** 
	 * This attribute represents whether the primitive attribute affBaseFee is null.
	 */
	protected boolean affBaseFeeNull = true;

	/**
	 * Method 'TAffiliate'
	 * 
	 */
	public AffiliateTO()
	{
	}

	/**
	 * Method 'getAffiliateId'
	 * 
	 * @return String
	 */
	public String getAffiliateId()
	{
		return affiliateId;
	}

	/**
	 * Method 'setAffiliateId'
	 * 
	 * @param affiliateId
	 */
	public void setAffiliateId(String affiliateId)
	{
		this.affiliateId = affiliateId;
	}

	/**
	 * Method 'getAffiliateOrgName'
	 * 
	 * @return String
	 */
	public String getAffiliateOrgName()
	{
		return affiliateOrgName;
	}

	/**
	 * Method 'setAffiliateOrgName'
	 * 
	 * @param affiliateOrgName
	 */
	public void setAffiliateOrgName(String affiliateOrgName)
	{
		this.affiliateOrgName = affiliateOrgName;
	}

	/**
	 * Method 'getAffiliateeUrl'
	 * 
	 * @return String
	 */
	public String getAffiliateeUrl()
	{
		return affiliateeUrl;
	}

	/**
	 * Method 'setAffiliateeUrl'
	 * 
	 * @param affiliateeUrl
	 */
	public void setAffiliateeUrl(String affiliateeUrl)
	{
		this.affiliateeUrl = affiliateeUrl;
	}

	/**
	 * Method 'getAffiliateType'
	 * 
	 * @return String
	 */
	public String getAffiliateType()
	{
		return affiliateType;
	}

	/**
	 * Method 'setAffiliateType'
	 * 
	 * @param affiliateType
	 */
	public void setAffiliateType(String affiliateType)
	{
		this.affiliateType = affiliateType;
	}

	/**
	 * Method 'getAffContactPerson'
	 * 
	 * @return String
	 */
	public String getAffContactPerson()
	{
		return affContactPerson;
	}

	/**
	 * Method 'setAffContactPerson'
	 * 
	 * @param affContactPerson
	 */
	public void setAffContactPerson(String affContactPerson)
	{
		this.affContactPerson = affContactPerson;
	}

	/**
	 * Method 'getAffiliateAddr'
	 * 
	 * @return String
	 */
	public String getAffiliateAddr()
	{
		return affiliateAddr;
	}

	/**
	 * Method 'setAffiliateAddr'
	 * 
	 * @param affiliateAddr
	 */
	public void setAffiliateAddr(String affiliateAddr)
	{
		this.affiliateAddr = affiliateAddr;
	}

	/**
	 * Method 'getAffAcctDtls'
	 * 
	 * @return String
	 */
	public String getAffAcctDtls()
	{
		return affAcctDtls;
	}

	/**
	 * Method 'setAffAcctDtls'
	 * 
	 * @param affAcctDtls
	 */
	public void setAffAcctDtls(String affAcctDtls)
	{
		this.affAcctDtls = affAcctDtls;
	}

	/**
	 * Method 'getAffSubsType'
	 * 
	 * @return String
	 */
	public String getAffSubsType()
	{
		return affSubsType;
	}

	/**
	 * Method 'setAffSubsType'
	 * 
	 * @param affSubsType
	 */
	public void setAffSubsType(String affSubsType)
	{
		this.affSubsType = affSubsType;
	}

	/**
	 * Method 'getAffBaseFee'
	 * 
	 * @return double
	 */
	public double getAffBaseFee()
	{
		return affBaseFee;
	}

	/**
	 * Method 'setAffBaseFee'
	 * 
	 * @param affBaseFee
	 */
	public void setAffBaseFee(double affBaseFee)
	{
		this.affBaseFee = affBaseFee;
		this.affBaseFeeNull = false;
	}

	/**
	 * Method 'setAffBaseFeeNull'
	 * 
	 * @param value
	 */
	public void setAffBaseFeeNull(boolean value)
	{
		this.affBaseFeeNull = value;
	}

	/**
	 * Method 'isAffBaseFeeNull'
	 * 
	 * @return boolean
	 */
	public boolean isAffBaseFeeNull()
	{
		return affBaseFeeNull;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof AffiliateTO)) {
			return false;
		}
		
		final AffiliateTO _cast = (AffiliateTO) _other;
		if (affiliateId == null ? _cast.affiliateId != affiliateId : !affiliateId.equals( _cast.affiliateId )) {
			return false;
		}
		
		if (affiliateOrgName == null ? _cast.affiliateOrgName != affiliateOrgName : !affiliateOrgName.equals( _cast.affiliateOrgName )) {
			return false;
		}
		
		if (affiliateeUrl == null ? _cast.affiliateeUrl != affiliateeUrl : !affiliateeUrl.equals( _cast.affiliateeUrl )) {
			return false;
		}
		
		if (affiliateType == null ? _cast.affiliateType != affiliateType : !affiliateType.equals( _cast.affiliateType )) {
			return false;
		}
		
		if (affContactPerson == null ? _cast.affContactPerson != affContactPerson : !affContactPerson.equals( _cast.affContactPerson )) {
			return false;
		}
		
		if (affiliateAddr == null ? _cast.affiliateAddr != affiliateAddr : !affiliateAddr.equals( _cast.affiliateAddr )) {
			return false;
		}
		
		if (affAcctDtls == null ? _cast.affAcctDtls != affAcctDtls : !affAcctDtls.equals( _cast.affAcctDtls )) {
			return false;
		}
		
		if (affSubsType == null ? _cast.affSubsType != affSubsType : !affSubsType.equals( _cast.affSubsType )) {
			return false;
		}
		
		if (affBaseFee != _cast.affBaseFee) {
			return false;
		}
		
		if (affBaseFeeNull != _cast.affBaseFeeNull) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		if (affiliateId != null) {
			_hashCode = 29 * _hashCode + affiliateId.hashCode();
		}
		
		if (affiliateOrgName != null) {
			_hashCode = 29 * _hashCode + affiliateOrgName.hashCode();
		}
		
		if (affiliateeUrl != null) {
			_hashCode = 29 * _hashCode + affiliateeUrl.hashCode();
		}
		
		if (affiliateType != null) {
			_hashCode = 29 * _hashCode + affiliateType.hashCode();
		}
		
		if (affContactPerson != null) {
			_hashCode = 29 * _hashCode + affContactPerson.hashCode();
		}
		
		if (affiliateAddr != null) {
			_hashCode = 29 * _hashCode + affiliateAddr.hashCode();
		}
		
		if (affAcctDtls != null) {
			_hashCode = 29 * _hashCode + affAcctDtls.hashCode();
		}
		
		if (affSubsType != null) {
			_hashCode = 29 * _hashCode + affSubsType.hashCode();
		}
		
		long temp_affBaseFee = Double.doubleToLongBits(affBaseFee);
		_hashCode = 29 * _hashCode + (int) (temp_affBaseFee ^ (temp_affBaseFee >>> 32));
		_hashCode = 29 * _hashCode + (affBaseFeeNull ? 1 : 0);
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return TAffiliatePk
	 */
	public TAffiliatePk createPk()
	{
		return new TAffiliatePk(affiliateId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.mycompany.myapp.dto.TAffiliate: " );
		ret.append( "affiliateId=" + affiliateId );
		ret.append( ", affiliateOrgName=" + affiliateOrgName );
		ret.append( ", affiliateeUrl=" + affiliateeUrl );
		ret.append( ", affiliateType=" + affiliateType );
		ret.append( ", affContactPerson=" + affContactPerson );
		ret.append( ", affiliateAddr=" + affiliateAddr );
		ret.append( ", affAcctDtls=" + affAcctDtls );
		ret.append( ", affSubsType=" + affSubsType );
		ret.append( ", affBaseFee=" + affBaseFee );
		return ret.toString();
	}

}
