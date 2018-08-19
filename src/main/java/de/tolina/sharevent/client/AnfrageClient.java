package de.tolina.sharevent.client;

import java.net.URI;
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
import de.tolina.sharevent.api.hacon.route.Trip;
import de.tolina.sharevent.api.hacon.route.TripList;
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
		URI uri = UriComponentsBuilder.fromUriString(backendUrl)
				.path(Routes.ANFRAGE_SITE)
				.queryParam(Routes.PARAM_ORT, input)
				.build()
				.encode()
				.toUri();
		System.err.println(uri);
		ResponseEntity<LocationList> entity = restTemplate.getForEntity(uri, LocationList.class);
		return entity.getBody().getStopLocation();
	}

	public List<Trip> lookupTrips(String from, String to, String traveldate, String traveltime) {
		URI uri = UriComponentsBuilder.fromUriString(backendUrl)
				.path(Routes.ANFRAGE_FIND_ROUTE)
				.queryParam(Routes.PARAM_FROM, from)
				.queryParam(Routes.PARAM_TO, to)
				.queryParam(Routes.PARAM_TRAVELDATE, traveldate)
				.queryParam(Routes.PARAM_TRAVELTIME, traveltime)
				.build()
				.encode()
				.toUri();
		System.err.println(uri);
		ResponseEntity<TripList> entity = restTemplate.getForEntity(uri, TripList.class);
		return entity.getBody().getTrip();
	}


}
