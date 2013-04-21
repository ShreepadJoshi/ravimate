package com.bean;

import static com.util.Constants.NEW_LINE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
@Table(name = "t_user")
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
	
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "first_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
