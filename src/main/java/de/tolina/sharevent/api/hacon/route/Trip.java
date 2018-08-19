package de.tolina.sharevent.api.hacon.route;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a Trip (possible route).
 */
@XmlRootElement(name = "Trip")
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("javadoc")
public class Trip {

	@XmlAttribute
	private int idx;

	@XmlAttribute
	private String duration;

	@XmlElement(name = "LegList")
	private LegList legList;

	/** Required for JAX-B */
	public Trip() {
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public LegList getLegList() {
		return legList;
	}

	public void setLegList(LegList legList) {
		this.legList = legList;
	}
}
