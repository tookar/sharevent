package de.tolina.sharevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.tolina.sharevent.api.hacon.location.LocationFinder;
import de.tolina.sharevent.api.hacon.route.RouteFinder;

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
}
