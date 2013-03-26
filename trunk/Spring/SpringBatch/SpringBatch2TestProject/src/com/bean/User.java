package com.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static com.util.Constants.NEW_LINE;;

@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	
	@XmlElement
	private String id;
	@XmlElement
	private String name;
	@XmlElement
	private String lastName;
	
	
	
	@Override
	public String toString() {
		return NEW_LINE + "User [id=" + id + ", name=" + name + ", lastName=" + lastName
				+ "]"  ;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
