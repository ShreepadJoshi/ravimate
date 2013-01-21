package com.education.transferobj;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.education.util.Utilities.Option;


public class RegistrationTo extends AbstractTO implements Serializable {

    private String userId = "";
	private String firstName = "";
    private int roleId;
    private String lastName = "";
    private String emailID = "";
    private String password = "";
    private String sex = "";
    private String classtype = "";
    private String address1 = "";
    private String stdCode = "";
	private String isdCode = "";
	private String state = "";
	

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

	public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    private String address2 = "";
    private String city = "";
    private String pin = "";
    private String country = "";
    private String mobNoPart1 = "";
    private String mobNoPart2 = "";
   // private String landNoPart1 = "";
   // private String landNoPart2 = "";
    private String alternateEmailID = "";
    private String hobbies = "";
   
    private String registrationDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date()).toString();
    private int isFullregistration=0;
    private int isApproved=0;
    
    private String mobileNo;
    private String landlineNo;
    
    
    /**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return the landlineNo
	 */
	public String getLandlineNo() {
		return landlineNo;
	}

	/**
	 * @param landlineNo the landlineNo to set
	 */
	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}

	public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
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

   
    public String getClasstype() {
		return classtype;
	}

	public void setClasstype(String classtype) {
		this.classtype = classtype;
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
	
	public int getIsFullregistration() {
		return isFullregistration;
	}

	public void setIsFullregistration(int isFullregistration) {
		this.isFullregistration = isFullregistration;
	}

	public int getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
