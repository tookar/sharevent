package de.tolina.sharevent.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.tolina.sharevent.api.StringListDto;
import de.tolina.sharevent.api.hacon.location.LocationFinder;
import de.tolina.sharevent.api.hacon.location.name.LocationList;
import de.tolina.sharevent.api.hacon.location.name.StopLocation;
import de.tolina.sharevent.api.hacon.route.RouteFinder;
import de.tolina.sharevent.api.hacon.route.Trip;
import de.tolina.sharevent.api.hacon.route.TripList;
import de.tolina.sharevent.route.Routes;

@RestController
@RequestMapping(produces = APPLICATION_JSON_UTF8_VALUE)
public class AnfrageController {

	private LocationFinder locationFinder;
	private RouteFinder routeFinder;

	@Autowired
	public AnfrageController(LocationFinder locationFinder, RouteFinder routeFinder) {
		this.locationFinder = locationFinder;
		this.routeFinder = routeFinder;
	}

	/**
	* Gibt für eine Teilanfrage mögliche Haltestellen etc zurück
	*/
	@RequestMapping(path = Routes.ANFRAGE_SITE, method = RequestMethod.GET)
	@ResponseStatus(OK)
	public LocationList lookupSites(@RequestParam(value = Routes.PARAM_ORT) String input) {
		List<StopLocation> locations = locationFinder.findLocationByName(input);
		LocationList locationList = new LocationList();
		locationList.setStopLocation(locations);
		return locationList;
	}

	@RequestMapping(path = Routes.ANFRAGE_FIND_ROUTE, method = RequestMethod.GET)
	@ResponseStatus(OK)
	public TripList findRoute(@RequestParam(value = Routes.PARAM_FROM) String stopLocationExtIdFrom,
			@RequestParam(value = Routes.PARAM_TO) String stopLocationExtIdTo,
			@RequestParam(value = Routes.PARAM_TRAVELDATE) String startDate,
			@RequestParam(value = Routes.PARAM_TRAVELTIME) String startTime) {
		List<Trip> findRoute = routeFinder.findRoute(stopLocationExtIdFrom, stopLocationExtIdTo, startDate, startTime);
		TripList tripList = new TripList();
		tripList.setTrip(findRoute);
		return tripList;
	}

	@RequestMapping(path = Routes.ANFRAGE_FIND_MATCHES, method = RequestMethod.GET)
	@ResponseStatus(OK)
	public StringListDto findMatches() {
		// FIXME hier kommt unsere geniale Matchinglogik zum Einsatz und liefert eine Menge von passenden Treffern
		StringListDto stringListDto = new StringListDto();
		//		stringListDto.setStrings(Collections.singletonList(from + "::" + to));
		return stringListDto;
	}

	@RequestMapping(path = Routes.ANFRAGE_FIND_DETAILS, method = RequestMethod.GET)
	@ResponseStatus(OK)
	public StringListDto getDetailsForPerson() {
		// FIXME zeige die Details zu einem gewählten Treffer an
		StringListDto stringListDto = new StringListDto();
		//		stringListDto.setStrings(Collections.singletonList(from + "::" + to));
		return stringListDto;
	}

}
