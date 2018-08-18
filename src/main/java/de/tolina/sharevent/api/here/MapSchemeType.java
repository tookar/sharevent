package de.tolina.sharevent.api.here;

/**
 * All available schemes are defined by Map view server of the Map Image API. Currently this API supports the following set of schemes.
 */
public enum MapSchemeType {

	/** Normal map view in day light mode. */
	NORMAL_DAY(0),

	/** Satellite map view in day light mode. */
	SATELLITE_DAY(1),

	/** Terrain map view in day light mode. */
	TERRAIN_DAY(2),

	/** Satellite map view with streets in day light mode. */
	HYBRID_DAY(3),

	/** Normal grey map view with public transit in day light mode. */
	NORMAL_DAY_TRANSIT(4),

	/** Normal grey map view in day light mode (used for background maps). */
	NORMAL_DAY_GREY(5),

	/** Normal map view for small screen devices in day light mode. */
	NORMAL_DAY_MOBILE(6),

	/** Normal map view for small screen devices in night mode. */
	NORMAL_NIGHT_MOBILE(7),

	/** Terrain map view for small screen devices in day light mode. */
	TERRAIN_DAY_MOBILE(8),

	/** Satellite map view with streets for small screen devices in day light mode. */
	HYBRID_DAY_MOBILE(9),

	/** Normal grey map view with public transit for small screen devices in day light mode. */
	NORMAL_DAY_TRANSIT_MOBILE(10),

	/** n.a. */
	NORMAL_DAY_GREY_MOBILE(11),

	/** Map view designed for navigation devices. */
	CARNAV_DAY_GREY(12),

	/** Map view designed for pedestrians walking by day. */
	PEDESTRIAN_DAY(13),

	/**
	 * Map view designed for pedestrians walking by night.
	 * 
	 * Normal grey map view for small screen devices in day light mode (used for background maps).
	 */
	PEDESTRIAN_NIGHT(14);

	private final int value;

	private MapSchemeType(int value) {
		this.value = value;
	}

	/**
	 * @return map scheme type value
	 */
	public int getValue() {
		return value;
	}
}
