package com.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import static com.util.Constants.NEW_LINE;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Leaf {

	@XmlAttribute
	private String ro;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String id;
	@XmlAttribute
	private String duration;
	@XmlAttribute
	private String uri;
	@XmlAttribute
	private String current;

	@Override
	public String toString() {
		return NEW_LINE + "Leaf [ro=" + ro + ", name=" + name + ", id=" + id
				+ ", duration=" + duration + ", uri=" + uri + ", current="
				+ current + "]";
	}

	public String getRo() {
		return ro;
	}

	public void setRo(String ro) {
		this.ro = ro;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

}
