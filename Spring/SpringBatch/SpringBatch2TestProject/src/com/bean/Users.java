package com.bean;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name ="Users")
@XmlAccessorType(XmlAccessType.FIELD)
public class Users {

	@XmlElement(name = "User")
	private ArrayList<User> userList;
	
	@XmlAttribute(name = "node")
	private String node;

	
	@Override
	public String toString() {
		return "Users [userList=" + userList + ", node=" + node + "]";
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

}
