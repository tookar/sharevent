package de.tolina.sharevent.api.hacon.route;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a list of trips.
 */
@XmlRootElement(name = "TripList")
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("javadoc")
public class TripList {

	@XmlElement(name = "Trip")
	private List<Trip> trips;

	/** Required for JAX-B */
	public TripList() {
	}

	public List<Trip> getTrip() {
		return trips;
	}

	public void setTrip(List<Trip> trips) {
		this.trips = trips;
	}
}
