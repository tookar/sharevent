package de.tolina.sharevent.api.hacon.location.name;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a list of locations.
 */
@XmlRootElement(name = "LocationList")
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("javadoc")
public class LocationList {

	@XmlElement(name = "StopLocation")
	private List<StopLocation> stopLocation;

	/** Required for JAX-B */
	public LocationList() {
	}

	public List<StopLocation> getStopLocation() {
		return stopLocation;
	}

	public void setStopLocation(List<StopLocation> stopLocation) {
		this.stopLocation = stopLocation;
	}
}
