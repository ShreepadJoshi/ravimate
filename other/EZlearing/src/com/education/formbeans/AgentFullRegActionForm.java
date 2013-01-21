package com.education.formbeans;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

public class AgentFullRegActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private Integer agentId = 0;
	private Integer userId = 0;
	private String emailID = "";
	private String password = "";
	private String repassword = "";
	private String sex = "";
	private String lastName = "";
	private String firstName = "";
	private String address1 = "";
	private String city = "";
	private Integer pin = 0;
	private String state = "";
	private String country = "";
	private String mobileNo = "";
	private String alternateEmailID = "";
	private String bankName = "";
	private String accountNo = "";
	private String qualification = "";
	private Integer fixedMonthlyFee = 0;
	private Integer percentageOnSale = 0;
	private Integer minSaleForBonus = 0;
	private Integer percentageOnBonus = 0;
	private String transactionCode = "";
	private int roleId;
	private int isApproved = 0;
	private int isFullregistration = 0;
	private String mobNoPart1 = "";
	private String mobNoPart2 = "";
	private String stdCode = "";
	private String isdCode = "";
	private String landlineNo;
	private String roleType = "";
	private String roleNameByID = "";
	private Date registrationDate;

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address) {
		this.address1 = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAlternateEmailID() {
		return alternateEmailID;
	}

	public void setAlternateEmailID(String alternateEmailID) {
		this.alternateEmailID = alternateEmailID;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Integer getFixedMonthlyFee() {
		return fixedMonthlyFee;
	}

	public void setFixedMonthlyFee(Integer fixedMonthlyFee) {
		this.fixedMonthlyFee = fixedMonthlyFee;
	}

	public Integer getPercentageOnSale() {
		return percentageOnSale;
	}

	public void setPercentageOnSale(Integer percentageOnSale) {
		this.percentageOnSale = percentageOnSale;
	}

	public Integer getMinSaleForBonus() {
		return minSaleForBonus;
	}

	public void setMinSaleForBonus(Integer minSaleForBonus) {
		this.minSaleForBonus = minSaleForBonus;
	}

	public Integer getPercentageOnBonus() {
		return percentageOnBonus;
	}

	public void setPercentageOnBonus(Integer percentageOnBonus) {
		this.percentageOnBonus = percentageOnBonus;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public int getIsFullregistration() {
		return isFullregistration;
	}

	public void setIsFullregistration(int isFullregistration) {
		this.isFullregistration = isFullregistration;
	}

	public String getLandlineNo() {
		return landlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}

	public String getStdCode() {
		return stdCode;
	}

	public void setStdCode(String stdCode) {
		this.stdCode = stdCode;
	}

	public String getIsdCode() {
		return isdCode;
	}

	public void setIsdCode(String isdCode) {
		this.isdCode = isdCode;
	}

	public String getMobNoPart1() {
		return mobNoPart1;
	}

	public void setMobNoPart1(String mobNoPart1) {
		this.mobNoPart1 = mobNoPart1;
	}

	public String getMobNoPart2() {
		return mobNoPart2;
	}

	public void setMobNoPart2(String mobNoPart2) {
		this.mobNoPart2 = mobNoPart2;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setNavFromPage(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleNameByID() {
		return roleNameByID;
	}

	public void setRoleName(String roleNameByID) {
		this.roleNameByID = roleNameByID;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}
}
