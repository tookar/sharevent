package de.tolina.sharevent.api.hacon.route;

import java.util.Date;
import java.util.List;

import lombok.NonNull;

public interface IRouteFinder {

	/**
	 * @return A route from <code>stopLocationIdFrom</code> to <code>stopLocationIdTo</code> for start time <code>start</code>.
	 */
	List<Trip> findRoute(@NonNull String stopLocationExtIdFrom, @NonNull String stopLocationExtIdTo, @NonNull Date start);

	/**
	 * @return A route from <code>stopLocationIdFrom</code> to <code>stopLocationIdTo</code> for the start time.
	 */
	List<Trip> findRoute(@NonNull String stopLocationExtIdFrom, @NonNull String stopLocationExtIdTo, @NonNull String startDate, @NonNull String startTime);
}
