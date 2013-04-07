package com.reader.vlc.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.logger.UILogger;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Status {

	@XmlElement
	private String time;

	public static void main(String[] args) {

		try {
			File file = new File("status.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Status.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Status statusBean = (Status) jaxbUnmarshaller
					.unmarshal(file);
			UILogger.log(statusBean);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "StatusBean [time=" + time + "]";
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
