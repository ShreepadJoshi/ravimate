package com.bean;

public class StatementOfAccountBean {
	
	private String date;
	private String narration;
	private String chqRefNo;
	private String valueDt;
	private String withdrawalAmt;
	private String depositAmt;
	private String closingBalance;
	
	
	@Override
	public String toString() {
		return "StatementOfAccountBean [date=" + date + ", narration="
				+ narration + ", chqRefNo=" + chqRefNo + ", valueDt=" + valueDt
				+ ", withdrawalAmt=" + withdrawalAmt + ", depositAmt="
				+ depositAmt + ", closingBalance=" + closingBalance + "]";
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	public String getChqRefNo() {
		return chqRefNo;
	}
	public void setChqRefNo(String chqRefNo) {
		this.chqRefNo = chqRefNo;
	}
	public String getValueDt() {
		return valueDt;
	}
	public void setValueDt(String valueDt) {
		this.valueDt = valueDt;
	}
	public String getWithdrawalAmt() {
		return withdrawalAmt;
	}
	public void setWithdrawalAmt(String withdrawalAmt) {
		this.withdrawalAmt = withdrawalAmt;
	}
	public String getDepositAmt() {
		return depositAmt;
	}
	public void setDepositAmt(String depositAmt) {
		this.depositAmt = depositAmt;
	}
	public String getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(String closingBalance) {
		this.closingBalance = closingBalance;
	}
	
	
}
