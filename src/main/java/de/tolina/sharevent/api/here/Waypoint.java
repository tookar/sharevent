package de.tolina.sharevent.api.here;

import lombok.Data;

/**
 * Waypoint coordinates. The parameter values represent coordinate pairs of latitude and longitude. These waypoints are used to calculate the route.
 */
@Data
public class Waypoint {

	private double latitude;
	private double longitude;
}
