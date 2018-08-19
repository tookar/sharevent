package de.tolina.sharevent.api.journey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.tolina.sharevent.api.hacon.route.RouteFinder;
import de.tolina.sharevent.api.user.User;
import de.tolina.sharevent.api.user.UserFinder;

/**
 * Mock of the journey utility.
 */
public class JourneyFinder {

	private List<Journey> journeys = new ArrayList<>();

	public JourneyFinder() {
		RouteFinder routeFinder = new RouteFinder();
		UserFinder userFinder = new UserFinder();
		List<User> users = userFinder.findUser();

		Journey journey = new Journey();
		journey.setInitiator(users.get(1));
		journey.setLabel("TODO: Event name");
		journey.setTrip(routeFinder.findRoute("900029302", "900023201", new Date()).get(0));
		journeys.add(journey);

		journey = new Journey();
		journey.setInitiator(users.get(2));
		journey.setLabel("TODO: Event name");
		journey.setTrip(routeFinder.findRoute("900029302", "900023201", new Date()).get(0));
		journeys.add(journey);

		journey = new Journey();
		journey.setInitiator(users.get(3));
		journey.setLabel("TODO: Event name");
		journey.setTrip(routeFinder.findRoute("900029302", "900023201", new Date()).get(0));
		journeys.add(journey);
	}

	public List<Journey> findJourney() {
		return journeys;
	}
}
