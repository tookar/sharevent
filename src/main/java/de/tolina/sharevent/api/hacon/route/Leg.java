package de.tolina.sharevent.api.hacon.route;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlType(name = "Leg")
@XmlAccessorType(XmlAccessType.FIELD)
public class Leg {

	@XmlAttribute
	private int idx;

	@XmlAttribute
	private String name;

	@XmlAttribute
	private String category;

	@XmlAttribute
	private String direction;

	@XmlElement(name = "Origin")
	private Origin origin;

	@XmlElement(name = "Destination")
	private Destination destination;
}
