package de.tolina.sharevent.api.here;

import java.net.URI;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.NonNull;

/**
 * Routing related utility.
 * 
 * parameter documentaion: https://developer.here.com/documentation/map-image/topics/resource-routing.html
 */
public class Routing implements IRouting {

	private RestTemplate restTemplate;
	private String lineColorAsHex;
	private Integer lineWidth;
	private MapSchemeType mapSchemeType;
	private Resolution resolution;

	@SuppressWarnings("javadoc")
	public Routing(@NonNull RestTemplateBuilder restTemplateBuilder, @NonNull String lineColorAsHex, @NonNull Integer lineWidth, @NonNull MapSchemeType mapSchemeType,
			@NonNull Resolution resolution) {
		this(restTemplateBuilder.build(), lineColorAsHex, lineWidth, mapSchemeType, resolution);
	}

	public Routing(@NonNull RestTemplate restTemplate, @NonNull String lineColorAsHex, @NonNull Integer lineWidth, @NonNull MapSchemeType mapSchemeType,
			@NonNull Resolution resolution) {
		this.restTemplate = restTemplate;
		this.lineColorAsHex = lineColorAsHex;
		this.lineWidth = lineWidth;
		this.mapSchemeType = mapSchemeType;
		this.resolution = resolution;
	}

	@Override
	public byte[] getRouteMapWithIcons(@NonNull List<Waypoint> waypoints, @NonNull Integer widthInPixel, @NonNull Integer heightInPixel) {
		return restTemplate.getForObject(createRouteMapWithIconsUri(waypoints, widthInPixel, heightInPixel), byte[].class);
	}

	private URI createRouteMapWithIconsUri(@NonNull List<Waypoint> waypoints, @NonNull Integer widthInPixel, @NonNull Integer heightInPixel) {
		UriComponentsBuilder result = createRouteUriComponentsBuilder();

		for (int i = 0; i < waypoints.size(); i++) {
			Waypoint waypoint = waypoints.get(i);
			result.queryParam("waypoint" + i, waypoint.getLatitude() + "," + waypoint.getLongitude());
		}

		result.queryParam("w", widthInPixel);
		result.queryParam("h", heightInPixel);

		return result.build().encode().toUri();
	}

	private UriComponentsBuilder createRouteUriComponentsBuilder() {
		UriComponentsBuilder result = ApiAccess.createUriComponentsBuilder("https://image.maps.api.here.com/mia/1.6/routing");
		result.queryParam("lc", lineColorAsHex);
		result.queryParam("lw", lineWidth);
		result.queryParam("t", Integer.valueOf(mapSchemeType.getValue()));
		result.queryParam("ppi", Integer.valueOf(resolution.getPpi()));

		return result;
	}
}
