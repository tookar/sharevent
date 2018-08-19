package de.tolina.sharevent.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import de.tolina.sharevent.api.hacon.location.name.LocationList;
import de.tolina.sharevent.api.hacon.location.name.StopLocation;
import de.tolina.sharevent.api.hacon.route.Destination;
import de.tolina.sharevent.api.hacon.route.Leg;
import de.tolina.sharevent.api.hacon.route.LegList;
import de.tolina.sharevent.api.hacon.route.Origin;
import de.tolina.sharevent.api.hacon.route.RouteFinder;
import de.tolina.sharevent.api.hacon.route.Trip;
import de.tolina.sharevent.api.hacon.route.TripList;
import de.tolina.sharevent.api.here.MapSchemeType;
import de.tolina.sharevent.api.here.Resolution;
import de.tolina.sharevent.api.here.Routing;
import de.tolina.sharevent.api.here.Waypoint;
import de.tolina.sharevent.route.Routes;


@Service
public class AnfrageClient {

	private RestTemplate restTemplate;
	private String backendUrl;

	@Autowired
	public AnfrageClient(RestTemplateBuilder restTemplateBuilder, @Value("${backend}") String backendUrl) {
		this.restTemplate = restTemplateBuilder.build();
		this.backendUrl = backendUrl;
	}

	public List<StopLocation> lookupHaltestellen(String input) {
		URI uri = UriComponentsBuilder.fromUriString(backendUrl).path(Routes.ANFRAGE_SITE).queryParam(Routes.PARAM_ORT, input).build().encode().toUri();
		System.err.println(uri);
		ResponseEntity<LocationList> entity = restTemplate.getForEntity(uri, LocationList.class);
		return entity.getBody().getStopLocation();
	}

	public byte[] getMapImage(Trip trip) {
		RouteFinder routeFinder = new RouteFinder();
		List<Trip> trips = routeFinder.findRoute("900029302", "900023201", new Date());

		Routing routing = new Routing(restTemplate, "1652B4", Integer.valueOf(6), MapSchemeType.NORMAL_DAY, Resolution.DEFAULT);

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

		return routing.getRouteMapWithIcons(waypoints, Integer.valueOf(400), Integer.valueOf(600));

	}

	public List<Trip> lookupTrips(String from, String to, String traveldate, String traveltime) {
		URI uri = UriComponentsBuilder.fromUriString(backendUrl).path(Routes.ANFRAGE_FIND_ROUTE).queryParam(Routes.PARAM_FROM, from).queryParam(Routes.PARAM_TO, to)
				.queryParam(Routes.PARAM_TRAVELDATE, traveldate).queryParam(Routes.PARAM_TRAVELTIME, traveltime).build().encode().toUri();
		System.err.println(uri);
		ResponseEntity<TripList> entity = restTemplate.getForEntity(uri, TripList.class);
		return entity.getBody().getTrip();
	}


}
