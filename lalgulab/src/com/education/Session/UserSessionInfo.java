package com.education.Session;

import com.education.transferobj.UserTO;

public class UserSessionInfo {

	 private String emailId = "";
	 private String firstName = "";
	 private String lastName = "";
	 private String registrationDate = "";
	private boolean doneFullRegistration = false;
	private String password = "";
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String userloginName = "";
	private String roleId = "";
	 private int userId ;
	 private UserTO userTo;
	 private String loginType="";
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserloginName() {
		return userloginName;
	}
	public void setUserloginName(String userloginName) {
		this.userloginName = userloginName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public boolean isDoneFullRegistration() {
		return doneFullRegistration;
	}
	public void setDoneFullRegistration(boolean doneFullRegistration) {
		this.doneFullRegistration = doneFullRegistration;
	}
	
	public void setUserTo(UserTO usrTo)
	{
		userTo = usrTo;
	}
	
	public UserTO getUserTo()
	{
		return userTo;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
}
