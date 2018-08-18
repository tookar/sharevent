package de.tolina.sharevent.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.tolina.sharevent.api.AnfrageDto;
import de.tolina.sharevent.api.AusgabeDto;

@RestController
@RequestMapping(produces = APPLICATION_JSON_UTF8_VALUE)
public class AnfrageController {

	/**
	 * Erzeugt einen neuen Datensatz für einen Risikoträger.
	 */
	@RequestMapping(path = "anfrage", method = POST, consumes = APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(CREATED)
	public AusgabeDto addDatensatz(@RequestBody AnfrageDto anfrageDto) {
		return new AusgabeDto();
	}


}
