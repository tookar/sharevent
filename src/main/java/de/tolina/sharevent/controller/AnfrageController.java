package de.tolina.sharevent.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Collections;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.tolina.sharevent.api.StringListDto;
import de.tolina.sharevent.route.Routes;

@RestController
@RequestMapping(produces = APPLICATION_JSON_UTF8_VALUE)
public class AnfrageController {

	/**
	* Gibt für eine Teilanfrage mögliche Haltestellen etc zurück
	*/
	@RequestMapping(path = Routes.ANFRAGE_SITE, method = RequestMethod.GET)
	@ResponseStatus(OK)
	public StringListDto lookupSites(@RequestParam(value = Routes.PARAM_ORT) String input) {
		// FIXME hier soll die HaCon-Api aufgerufen werden und zur Eingabe passende Orte ausgeben
		StringListDto stringListDto = new StringListDto();
		stringListDto.setStrings(Collections.singletonList(input + "::" + input));
		return stringListDto;
	}

	@RequestMapping(path = Routes.ANFRAGE_FIND_ROUTE, method = RequestMethod.GET)
	@ResponseStatus(OK)
	public StringListDto findRoute(@RequestParam(value = Routes.PARAM_FROM) String from, @RequestParam(value = Routes.PARAM_TO) String to) {
		// FIXME hier soll die HaCon-Api aufgerufen werden und zur Eingabe passende Routen ausgeben
		// FIXME braucht noch ein passendes Dto
		// FIXME ggf noch Vorlieben, Angebote wie habe Umweltticket, oder auch Dinge wie suche Mitfahrgelegenheit für das letzte Stück
		StringListDto stringListDto = new StringListDto();
		stringListDto.setStrings(Collections.singletonList(from + "::" + to));
		return stringListDto;
	}

	@RequestMapping(path = Routes.ANFRAGE_FIND_ROUTE, method = RequestMethod.GET)
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
