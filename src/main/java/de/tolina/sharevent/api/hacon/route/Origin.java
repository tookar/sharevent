package de.tolina.sharevent.api.hacon.route;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlType(name = "Origin")
@XmlAccessorType(XmlAccessType.FIELD)
public class Origin {

	@XmlAttribute
	private String extId;

	@XmlAttribute
	private String name;

	@XmlAttribute
	private String lon;

	@XmlAttribute
	private String lat;

	@XmlAttribute
	private String date;

	@XmlAttribute
	private String time;
}
