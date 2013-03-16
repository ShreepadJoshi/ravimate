package com.bean;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "node")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootNode {

	@XmlAttribute
	private String id = "";
	@XmlAttribute
	private String name = "";
	@XmlAttribute
	private String ro = "";

	// XmLElementWrapper generates a wrapper element around XML representation
	// @XmlElementWrapper(name = "node")
	// XmlElement sets the name of the entities
	@XmlElement(name = "node")
	private ArrayList<Node> nodeList;

	public static void main(String[] args) {

		RootNode rootNode = new RootNode();
		rootNode.setId("1");
		rootNode.setName("test1Name");
		rootNode.setRo("ro1ro");
		ArrayList<Node> nodeListLocal = new ArrayList<Node>();
		Node node = new Node();
		node.setId("1");
		node.setRo("ro");
		node.setName("name1 dx");
		nodeListLocal.add(node);
		rootNode.setNodeList(nodeListLocal);

		try {

			File file = new File("playlist.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(RootNode.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			RootNode rootNodetemp = (RootNode) jaxbUnmarshaller.unmarshal(file);
			System.out.println(rootNodetemp);

			// create JAXB context and instantiate marshaller
			// JAXBContext context = JAXBContext.newInstance(RootNode.class);
			// Marshaller m = context.createMarshaller();
			// m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Write to System.out
			// m.marshal(rootNode, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return "RootNode [id=" + id + ", name=" + name + ", ro=" + ro
				+ ", nodeList=" + nodeList + "]";
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

	public ArrayList<Node> getNodeList() {
		return nodeList;
	}

	public void setNodeList(ArrayList<Node> nodeList) {
		this.nodeList = nodeList;
	}

}
