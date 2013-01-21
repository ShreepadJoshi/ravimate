package com.education.formbeans;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.education.displaytag.IExtendedPaginatedList;
import com.education.util.Utilities.Option;

public class NewUserActionForm extends ActionForm {

	private String emailID = "";
	private String firstName = "";
	private String lastName = "";
	private IExtendedPaginatedList pgSearchResults = null;
	private String registrationDate = "";
	private String formDate = "";
	private String toDate = "";
	private String regStatus = "";
	private ArrayList<Option> regStatusOptions = null;
	
	private String sch_EmailID = "";
	private String sch_FirstName = "";
	private String sch_LastName = "";
	private String sch_RegStatus = "";
	private String sch_fromDate = "";
    private String sch_toDate = "";
	
	
	
	
	public String getSch_fromDate() {
		return sch_fromDate;
	}
	public void setSch_fromDate(String sch_fromDate) {
		this.sch_fromDate = sch_fromDate;
	}
	public String getSch_toDate() {
		return sch_toDate;
	}
	public void setSch_toDate(String sch_toDate) {
		this.sch_toDate = sch_toDate;
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
	public IExtendedPaginatedList getPgSearchResults() {
		return pgSearchResults;
	}
	public void setPgSearchResults(IExtendedPaginatedList pgSearchResults) {
		this.pgSearchResults = pgSearchResults;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getFormDate() {
		return formDate;
	}
	public void setFormDate(String formDate) {
		this.formDate = formDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getRegStatus() {
		return regStatus;
	}
	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}
	public ArrayList<Option> getRegStatusOptions() {
		return regStatusOptions;
	}
	public void setRegStatusOptions(ArrayList<Option> regStatusOptions) {
		this.regStatusOptions = regStatusOptions;
	}
	public String getSch_EmailID() {
		return sch_EmailID;
	}
	public void setSch_EmailID(String sch_EmailID) {
		this.sch_EmailID = sch_EmailID;
	}
	public String getSch_FirstName() {
		return sch_FirstName;
	}
	public void setSch_FirstName(String sch_FirstName) {
		this.sch_FirstName = sch_FirstName;
	}
	public String getSch_LastName() {
		return sch_LastName;
	}
	public void setSch_LastName(String sch_LastName) {
		this.sch_LastName = sch_LastName;
	}
	public String getSch_RegStatus() {
		return sch_RegStatus;
	}
	public void setSch_RegStatus(String sch_RegStatus) {
		this.sch_RegStatus = sch_RegStatus;
	}
}
