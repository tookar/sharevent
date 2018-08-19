package de.tolina.sharevent;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import de.tolina.sharevent.api.hacon.route.Destination;
import de.tolina.sharevent.api.hacon.route.Leg;
import de.tolina.sharevent.api.hacon.route.LegList;
import de.tolina.sharevent.api.hacon.route.Origin;
import de.tolina.sharevent.api.hacon.route.RouteFinder;
import de.tolina.sharevent.api.hacon.route.Trip;
import de.tolina.sharevent.api.here.MapSchemeType;
import de.tolina.sharevent.api.here.Resolution;
import de.tolina.sharevent.api.here.Routing;
import de.tolina.sharevent.api.here.Waypoint;

public class ShareventApplicationRoutingTest extends ShareventApplication {

	@Bean
	public CommandLineRunner test(RestTemplateBuilder builder) {
		return args -> {
			RouteFinder routeFinder = new RouteFinder();
			List<Trip> trips = routeFinder.findRoute("900029302", "900023201", new Date());

			Routing routing = new Routing(builder, "1652B4", Integer.valueOf(6), MapSchemeType.NORMAL_DAY, Resolution.DEFAULT);

			List<Waypoint> waypoints = new ArrayList<>();

			LegList legList = trips.get(0).getLegList();

			for (Leg leg : legList.getLeg()) {
				Origin origin = leg.getOrigin();
				Destination destination = leg.getDestination();

				if (origin != null) {
					Waypoint waypoint = new Waypoint();
					waypoint.setLatitude(Double.valueOf(origin.getLat()).doubleValue());
					waypoint.setLongitude(Double.valueOf(origin.getLon()).doubleValue());
					waypoints.add(waypoint);
				}

				if (destination != null) {
					Waypoint waypoint = new Waypoint();
					waypoint.setLatitude(Double.valueOf(destination.getLat()).doubleValue());
					waypoint.setLongitude(Double.valueOf(destination.getLon()).doubleValue());
					waypoints.add(waypoint);
				}
			}

			byte[] routeMapWithIcons = routing.getRouteMapWithIcons(waypoints, Integer.valueOf(400), Integer.valueOf(600));
			Path path = Paths.get("image.jpg");
			Files.write(path, routeMapWithIcons);
			System.out.println(path.toAbsolutePath().toString());
		};
	}
}
