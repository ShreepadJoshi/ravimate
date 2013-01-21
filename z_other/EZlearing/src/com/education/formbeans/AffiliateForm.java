package com.education.formbeans;

import com.education.displaytag.IExtendedPaginatedList;

public class AffiliateForm extends FullRegActionForm {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private String _strAffiliateId;
	private String _strAffiliateOrgName;
	private String _strAffiliateUrl;
	private char _chrAffiliateType;
	private String _strAffiliateContactPerson;
	private String _strAffiliateAddr;
	private String _strAffiliateActDtls;
	private char _chrSubscriptionType;
	private double _dblAffiliateBaseFee = Double.valueOf(0.00d);
	private boolean affBaseFeeNull;
	private String[] recordStatus;
	private IExtendedPaginatedList pgSearchResults = null;
	
	private int newRecordIndex;
	
	
	public String[] getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String[] recordStatus) {
		this.recordStatus = recordStatus;
	}

	public int getNewRecordIndex() {
		return newRecordIndex;
	}

	public void setNewRecordIndex(int newRecordIndex) {
		this.newRecordIndex = newRecordIndex;
	}

	public IExtendedPaginatedList getPgSearchResults() {
		return pgSearchResults;
	}

	public void setPgSearchResults(IExtendedPaginatedList pgSearchResults) {
		this.pgSearchResults = pgSearchResults;
	}

	public String getAffiliateId()
	{
		return _strAffiliateId;
	}

	/**
	 * Method 'setAffiliateId'
	 * 
	 * @param affiliateId
	 */
	public void setAffiliateId(String affiliateId)
	{
		_strAffiliateId = affiliateId;
	}

	/**
	 * Method 'getAffiliateOrgName'
	 * 
	 * @return String
	 */
	public String getAffiliateOrgName()
	{
		return _strAffiliateOrgName;
	}

	/**
	 * Method 'setAffiliateOrgName'
	 * 
	 * @param affiliateOrgName
	 */
	public void setAffiliateOrgName(String affiliateOrgName)
	{
		_strAffiliateOrgName = affiliateOrgName;
	}

	/**
	 * Method 'getAffiliateeUrl'
	 * 
	 * @return String
	 */
	public String getAffiliateeUrl()
	{
		return _strAffiliateUrl;
	}

	/**
	 * Method 'setAffiliateeUrl'
	 * 
	 * @param affiliateeUrl
	 */
	public void setAffiliateeUrl(String affiliateeUrl)
	{
		_strAffiliateUrl = affiliateeUrl;
	}

	/**
	 * Method 'getAffiliateType'
	 * 
	 * @return String
	 */
	public String getAffiliateType()
	{
		return String.valueOf(_chrAffiliateType);
	}

	/**
	 * Method 'setAffiliateType'
	 * 
	 * @param affiliateType
	 */
	public void setAffiliateType(String affiliateType)
	{
		if(affiliateType != null)
		_chrAffiliateType = affiliateType.charAt(0);
	}

	/**
	 * Method 'getAffContactPerson'
	 * 
	 * @return String
	 */
	public String getAffContactPerson()
	{
		return _strAffiliateContactPerson;
	}

	/**
	 * Method 'setAffContactPerson'
	 * 
	 * @param affContactPerson
	 */
	public void setAffContactPerson(String affContactPerson)
	{
		this._strAffiliateContactPerson = affContactPerson;
	}

	/**
	 * Method 'getAffiliateAddr'
	 * 
	 * @return String
	 */
	public String getAffiliateAddr()
	{
		return _strAffiliateAddr;
	}

	/**
	 * Method 'setAffiliateAddr'
	 * 
	 * @param affiliateAddr
	 */
	public void setAffiliateAddr(String affiliateAddr)
	{
		this._strAffiliateAddr = affiliateAddr;
	}

	/**
	 * Method 'getAffAcctDtls'
	 * 
	 * @return String
	 */
	public String getAffAcctDtls()
	{
		return _strAffiliateActDtls;
	}

	/**
	 * Method 'setAffAcctDtls'
	 * 
	 * @param affAcctDtls
	 */
	public void setAffAcctDtls(String affAcctDtls)
	{
		this._strAffiliateActDtls = affAcctDtls;
	}

	/**
	 * Method 'getAffSubsType'
	 * 
	 * @return String
	 */
	public String getAffSubsType()
	{
		return String.valueOf(_chrSubscriptionType);
	}

	/**
	 * Method 'setAffSubsType'
	 * 
	 * @param affSubsType
	 */
	public void setAffSubsType(String affSubsType)
	{
		if (affSubsType != null)
			_chrSubscriptionType = affSubsType.charAt(0);
	}

	/**
	 * Method 'getAffBaseFee'
	 * 
	 * @return double
	 */
	public double getAffBaseFee()
	{
		return _dblAffiliateBaseFee;
	}

	/**
	 * Method 'setAffBaseFee'
	 * 
	 * @param affBaseFee
	 */
	public void setAffBaseFee(double affBaseFee)
	{
		this._dblAffiliateBaseFee = affBaseFee;
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
	

}
