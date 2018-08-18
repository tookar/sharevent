package de.tolina.sharevent.api.here;

import org.springframework.web.util.UriComponentsBuilder;

/**
 * HERE APIs use two random strings, app_id and app_code, to provide secure access to services.<br/>
 * Source: https://developer.here.com/faqs#access-control<br/>
 * Documentation: https://developer.here.com/develop/rest-apis
 */
class ApiAccess {

	/**
	 * Uniquely identifies your application.
	 */
	public static final String APP_ID = "adMxD537gzyISZEteKBd";

	/**
	 * Used in the authentication process to identify a session.
	 */
	public static final String APP_CODE = "LFkbh90UNOfoVzUpW84RYA";

	/**
	 * @return {@link UriComponentsBuilder} with the secure access parameters. This builder should be the base for all api calls.
	 */
	public static UriComponentsBuilder createUriComponentsBuilder(String uri) {
		UriComponentsBuilder result = UriComponentsBuilder.fromUriString(uri);
		result.queryParam("app_id", APP_ID);
		result.queryParam("app_code", APP_CODE);

		return result;
	}
}
