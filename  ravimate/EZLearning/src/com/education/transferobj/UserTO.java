package com.education.transferobj;

public class UserTO {

    private String emailId = "";
    private String password1 = "";
    private String password2 = "";
    private int roleId;
    private String sessionID;
    private String firstName = "";
    private String lastName = "";
    private String registrationDate = "";
   private boolean doneFullRegistration = false;
    private String userloginID = "";
    private int userId ;
    

    public boolean isDoneFullRegistration() {
		return doneFullRegistration;
	}

	public void setDoneFullRegistration(boolean doneFullRegistration) {
		this.doneFullRegistration = doneFullRegistration;
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

	

	public String getUserloginID() {
		return userloginID;
	}

	public void setUserloginID(String userloginID) {
		this.userloginID = userloginID;
	}

	public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
