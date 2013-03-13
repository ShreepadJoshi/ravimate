package com.bean;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "node")
@XmlAccessorType(XmlAccessType.FIELD)
public class Node {

	@XmlAttribute
	private String id = "";

	@XmlAttribute
	private String name = "";

	@XmlAttribute
	private String ro = "";

	@Override
	public String toString() {
		return "Node [id=" + id + ", name=" + name + ", ro=" + ro + "]";
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

	public static void main(String[] args) {

		try {

			File file = new File("playlist.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Node.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Node node = (Node) jaxbUnmarshaller.unmarshal(file);
			System.out.println(node);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
