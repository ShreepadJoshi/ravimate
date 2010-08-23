package com.education.formbeans;

import org.apache.struts.action.ActionForm;

import com.education.displaytag.IExtendedPaginatedList;

public class FindSchool extends ActionForm {
	// Fields    

    private int schoolId;
    private String schoolName;
    private String schoolBoard;
    private String schoolState;
    private String schoolDistrict;
    private String schoolPostalAddr;
    private String schoolPinCode;
    private String schoolPhoneNo;
    private String schoolOffice;
    private String schoolEmail;
    private String schoolWebsite;
    private Integer yearoffoundation;
    private String principalName;
    private String schoolStatus;
    private String trustName;
    private String schoolCategory;
    private String mediumofInstruction;
    private String schoolType;
    private IExtendedPaginatedList pgSearchResults = null;
    
    
 // Property accessors

    public int getSchoolId() {
        return this.schoolId;
    }
    
    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return this.schoolName;
    }
    
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolBoard() {
        return this.schoolBoard;
    }
    
    public void setSchoolBoard(String schoolBoard) {
        this.schoolBoard = schoolBoard;
    }

    public String getSchoolState() {
        return this.schoolState;
    }
    
    public void setSchoolState(String schoolState) {
        this.schoolState = schoolState;
    }

    public String getSchoolDistrict() {
        return this.schoolDistrict;
    }
    
    public void setSchoolDistrict(String schoolDistrict) {
        this.schoolDistrict = schoolDistrict;
    }

    public String getSchoolPostalAddr() {
        return this.schoolPostalAddr;
    }
    
    public void setSchoolPostalAddr(String schoolPostalAddr) {
        this.schoolPostalAddr = schoolPostalAddr;
    }

    public String getSchoolPinCode() {
        return this.schoolPinCode;
    }
    
    public void setSchoolPinCode(String schoolPinCode) {
        this.schoolPinCode = schoolPinCode;
    }

    public String getSchoolPhoneNo() {
        return this.schoolPhoneNo;
    }
    
    public void setSchoolPhoneNo(String schoolPhoneNo) {
        this.schoolPhoneNo = schoolPhoneNo;
    }

    public String getSchoolOffice() {
        return this.schoolOffice;
    }
    
    public void setSchoolOffice(String schoolOffice) {
        this.schoolOffice = schoolOffice;
    }

    public String getSchoolEmail() {
        return this.schoolEmail;
    }
    
    public void setSchoolEmail(String schoolEmail) {
        this.schoolEmail = schoolEmail;
    }

    public String getSchoolWebsite() {
        return this.schoolWebsite;
    }
    
    public void setSchoolWebsite(String schoolWebsite) {
        this.schoolWebsite = schoolWebsite;
    }

    public Integer getYearoffoundation() {
        return this.yearoffoundation;
    }
    
    public void setYearoffoundation(Integer yearoffoundation) {
        this.yearoffoundation = yearoffoundation;
    }

    public String getPrincipalName() {
        return this.principalName;
    }
    
    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getSchoolStatus() {
        return this.schoolStatus;
    }
    
    public void setSchoolStatus(String schoolStatus) {
        this.schoolStatus = schoolStatus;
    }

    public String getTrustName() {
        return this.trustName;
    }
    
    public void setTrustName(String trustName) {
        this.trustName = trustName;
    }

    public String getSchoolCategory() {
        return this.schoolCategory;
    }
    
    public void setSchoolCategory(String schoolCategory) {
        this.schoolCategory = schoolCategory;
    }

    public String getMediumofInstruction() {
        return this.mediumofInstruction;
    }
    
    public void setMediumofInstruction(String mediumofInstruction) {
        this.mediumofInstruction = mediumofInstruction;
    }

    public String getSchoolType() {
        return this.schoolType;
    }
    
    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }
    
    public IExtendedPaginatedList getPgSearchResults() {
		return pgSearchResults;
	}

	public void setPgSearchResults(IExtendedPaginatedList pgSearchResults) {
		this.pgSearchResults = pgSearchResults;
	}
}
