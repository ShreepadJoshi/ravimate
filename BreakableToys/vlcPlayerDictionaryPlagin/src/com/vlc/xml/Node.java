package com.vlc.xml;

import javax.xml.bind.annotation.XmlAccessType;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import static com.util.Constants.NEW_LINE;

@XmlRootElement(name = "Node")
@XmlAccessorType(XmlAccessType.FIELD)
public class Node {

	@XmlAttribute
	private String id = "";

	@XmlAttribute
	private String name = "";

	@XmlAttribute
	private String ro = "";

	@XmlElement
	private Leaf leaf;

	@Override
	public String toString() {
		return NEW_LINE + "Node [id=" + id + ", name=" + name + ", ro=" + ro
				+ ", leaf=" + leaf + "]";
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

	public String getRo() {
		return ro;
	}

	public void setRo(String ro) {
		this.ro = ro;
	}

	public Leaf getLeaf() {
		return leaf;
	}

	public void setLeaf(Leaf leaf) {
		this.leaf = leaf;
	}

}
