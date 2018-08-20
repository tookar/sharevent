package de.tolina.sharevent.api.hacon.location;

import java.util.List;

import de.tolina.sharevent.api.hacon.location.name.StopLocation;
import lombok.NonNull;

/**
 * Utility to find locations.
 */
public interface ILocationFinder {

	/**
	 * @return {@link StopLocation} matching the given name.
	 */
	List<StopLocation> findLocationByName(@NonNull String name);
}
