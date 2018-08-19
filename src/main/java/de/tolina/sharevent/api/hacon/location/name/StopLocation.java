package de.tolina.sharevent.api.hacon.location.name;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

/**
 * Represents a stop location (e.g. bus station).
 */
@Data
@XmlType(name = "StopLocation")
@XmlAccessorType(XmlAccessType.FIELD)
public class StopLocation {

	@XmlAttribute
	private String id;

	@XmlAttribute
	private String extId;

	@XmlAttribute
	private String name;

	@XmlAttribute
	private String lon;

	@XmlAttribute
	private String lat;

	@XmlAttribute
	private String weight;

	@XmlAttribute
	private String products;

	/** Required for JAX-B */
	public StopLocation() {
	}
}
