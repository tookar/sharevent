package de.tolina.sharevent.api.hacon.route;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LegList")
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("javadoc")
public class LegList {

	@XmlElement(name = "Leg")
	private List<Leg> legs;

	/** Required for JAX-B */
	public LegList() {
	}

	public List<Leg> getLeg() {
		return legs;
	}

	public void setLeg(List<Leg> trips) {
		this.legs = trips;
	}
}
