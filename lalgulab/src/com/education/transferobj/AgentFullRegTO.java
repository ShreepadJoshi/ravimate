package com.education.transferobj;

import java.io.Serializable;

public class AgentFullRegTO implements Serializable {
	
	private Integer agentId = 0;
	private Integer userId = 0;
	private String bankName = "";
	private String accountNo = "";
	private String qualification = "";
	private Integer fixedMonthlyFee = 0;
	private Integer percentageOnSale = 0;
	private Integer minSaleForBonus= 0;
	private Integer percentageOnBonus = 0;
	private String transactionCode = "";
	
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
}
