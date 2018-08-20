package de.tolina.sharevent.api.here;

import java.util.List;

import lombok.NonNull;

public interface IRouting {

	/**
	 * @return a map image
	 */
	byte[] getRouteMapWithIcons(@NonNull List<Waypoint> waypoints, @NonNull Integer widthInPixel, @NonNull Integer heightInPixel);
}
