package com.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_account")
public class Account {

	private int accountNumber = 0;
	private String firstName = null;
	private String lastName = null;
	private double amount = 0.0;

	
	@Id
	@GeneratedValue
	@Column(name = "account_number_id")
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", firstName="
				+ firstName + ", lastName=" + lastName + ", amount=" + amount
				+ "]";
	}


	public Account() {
		super();
	}

}
