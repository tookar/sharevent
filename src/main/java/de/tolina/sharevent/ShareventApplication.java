package de.tolina.sharevent;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import de.tolina.sharevent.api.hacon.location.LocationFinder;
import de.tolina.sharevent.api.hacon.route.RouteFinder;
import de.tolina.sharevent.api.here.IRouting;
import de.tolina.sharevent.api.here.RoutingEmu;
import de.tolina.sharevent.api.here.Waypoint;

@SpringBootApplication
public class ShareventApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareventApplication.class, args);
	}

	@Bean
	LocationFinder locationFinder() {
		return new LocationFinder();
	}

	@Bean
	RouteFinder routeFinder() {
		return new RouteFinder();
	}

	@Bean
	public CommandLineRunner test(RestTemplateBuilder builder) {
		return args -> {
			IRouting routing = new RoutingEmu();

			List<Waypoint> waypoints = new ArrayList<>();

			Waypoint waypoint = new Waypoint();
			waypoint.setLatitude(40.7499714);
			waypoint.setLongitude(-73.9979574);
			waypoints.add(waypoint);

			waypoint = new Waypoint();
			waypoint.setLatitude(40.7456827);
			waypoint.setLongitude(-73.9954344);
			waypoints.add(waypoint);

			byte[] routeMapWithIcons = routing.getRouteMapWithIcons(waypoints, Integer.valueOf(400), Integer.valueOf(600));
			Path path = Paths.get("image_emu.jpg");
			Files.write(path, routeMapWithIcons);
			System.out.println(path.toAbsolutePath().toString());
		};
	}
}
