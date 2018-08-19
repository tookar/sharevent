package de.tolina.sharevent.api.journey;

import de.tolina.sharevent.api.hacon.route.Trip;
import de.tolina.sharevent.api.user.User;
import lombok.Data;

@Data
public class Journey {

	private User initiator;
	private String label;
	private Trip trip;
}
