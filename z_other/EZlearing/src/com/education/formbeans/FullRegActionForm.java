package com.education.formbeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;

import com.education.util.EducationConstant;
import com.education.util.Utilities;
import com.education.util.Utilities.Option;

public class FullRegActionForm extends ActionForm{

	private String userId="";
	private String firstName = "";
	private String lastName = "";
	private String emailID = "";
	private String password = "";
	private String repassword = "";
	private String sex = "";
	private ArrayList<Option> classTypeOptions = null;
	private String address = "";
	private String city = "";
	private String state = "";
	private String pin = "";
	private String country = "";
	private String mobNoPart1 = "";
	private String mobNoPart2 = "";
	private String stdCode = "";
	private String isdCode = "";
	private String landlineNo = "";
	private String alternateEmailID = "";
	private String hobbies = "";
	private String mobileNo = "";
	private String registrationDate = "";
	private String roleType = "1"; //default registration foe Guest
	private String classType = null ;
	
	/** Reg date for display format **/
	private String regDate_displayFormat = "";
	
	
	
	/** Handle navigation done from other page **/
	private String navFromPage = "";
	
//	//define roles constant
//	private String affiliateRole = EducationConstant.AFFILIATE_USER_ROLE;
//	private String agentRole = EducationConstant.AGENT_USER_ROLE;
//	private String guestRole = EducationConstant.GUEST_USER_ROLE;
//	private String teacherRole = EducationConstant.TEACHER_USER_ROLE;
//	private String adminRole = EducationConstant.ADMIN_USER_ROLE;
	private String roleName = "";
	
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	//	/**
//	 * @return the affiliateRole
//	 */
//	public String getAffiliateRole() {
//		return affiliateRole;
//	}
//	/**
//	 * @return the agentRole
//	 */
//	public String getAgentRole() {
//		return agentRole;
//	}
//	/**
//	 * @return the guestRole
//	 */
//	public String getGuestRole() {
//		return guestRole;
//	}
//	/**
//	 * @return the teacherRole
//	 */
//	public String getTeacherRole() {
//		return teacherRole;
//	}
//	/**
//	 * @return the adminRole
//	 */
//	public String getAdminRole() {
//		return adminRole;
//	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getMobileNo() {
		return this.mobNoPart1+this.mobNoPart2;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	
	public String getAlternateEmailID() {
		return alternateEmailID;
	}
	public void setAlternateEmailID(String alternateEmailID) {
		this.alternateEmailID = alternateEmailID;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public ArrayList<Option> getClassTypeOptions() {
		return classTypeOptions;
	}
	public void setClassTypeOptions(ArrayList<Option> classTypeOptions) {
		this.classTypeOptions = classTypeOptions;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	/**
	 * @return the roleType
	 */
	public String getRoleType() {
		return roleType;
	}
	/**
	 * @param roleType the roleType to set
	 */
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getNavFromPage() {
		return navFromPage;
	}
	public void setNavFromPage(String navFromPage) {
		this.navFromPage = navFromPage;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRegDate_displayFormat() {		
		return Utilities.getDate_displayFormat(registrationDate);
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getLandlineNo() {
		return landlineNo;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
}
