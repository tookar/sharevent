package de.tolina.sharevent.api.here;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

import lombok.NonNull;

public class RoutingEmu implements IRouting {

	private RestTemplate restTemplate;
	private String lineColorAsHex;
	private Integer lineWidth;
	private MapSchemeType mapSchemeType;
	private Resolution resolution;

	@SuppressWarnings("javadoc")
	public RoutingEmu() {
	}

	@Override
	public byte[] getRouteMapWithIcons(@NonNull List<Waypoint> waypoints, @NonNull Integer widthInPixel, @NonNull Integer heightInPixel) {
		try {
			return IOUtils.toByteArray(new ClassPathResource("emu/api/here/image_spandau_zoo.jpg").getInputStream());
		} catch (IOException e) {
			return null;
		}
	}
}
