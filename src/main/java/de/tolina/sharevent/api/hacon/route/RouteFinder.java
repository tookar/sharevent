package de.tolina.sharevent.api.hacon.route;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.io.IOUtils;

import de.tolina.sharevent.api.common.XMLReaderWithoutNamespace;
import de.tolina.sharevent.api.hacon.ApiAccess;

import lombok.NonNull;

/**
 * Utility to find a route.
 */
public class RouteFinder {

	private static final Logger LOG = Logger.getLogger(RouteFinder.class.getName());

	private Client client;
	private WebTarget target;
	private Unmarshaller locationListUnmarshaller;

	@SuppressWarnings("javadoc")
	public RouteFinder() {
		client = ClientBuilder.newClient();
		target = client.target("https://demo.hafas.de/bvg-hackathon/restproxy/trip");

		try {
			JAXBContext context = JAXBContext.newInstance(TripList.class);
			locationListUnmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			LOG.log(Level.SEVERE, "Failed to initialize JAXB.", e);
		}
	}

	/**
	 * @return A route from <code>stopLocationIdFrom</code> to <code>stopLocationIdTo</code> for start time <code>start</code>.
	 */
	public List<Trip> findRoute(@NonNull String stopLocationExtIdFrom, @NonNull String stopLocationExtIdTo, @NonNull Date start) {
		return findRoute(stopLocationExtIdFrom, stopLocationExtIdTo, new SimpleDateFormat("yyyy-MM-dd").format(start), new SimpleDateFormat("hh:mm").format(start));
	}

	/**
	 * @return A route from <code>stopLocationIdFrom</code> to <code>stopLocationIdTo</code> for the start time.
	 */
	public List<Trip> findRoute(@NonNull String stopLocationExtIdFrom, @NonNull String stopLocationExtIdTo, @NonNull String startDate, @NonNull String startTime) {
		try {
			String response = target.queryParam("accessId", ApiAccess.ACCESS_ID)
					.queryParam("originExtId", stopLocationExtIdFrom)
					.queryParam("destExtId", stopLocationExtIdTo)
					.queryParam("date", startDate)
					.queryParam("time", startTime)
					.request(MediaType.TEXT_XML).get(String.class);

			InputStream inputStream = IOUtils.toInputStream(response, "UTF-8");
			XMLStreamReader streamReader = XMLInputFactory.newFactory().createXMLStreamReader(inputStream);
			XMLReaderWithoutNamespace readerWithoutNamespace = new XMLReaderWithoutNamespace(streamReader);

			TripList tripList = (TripList) locationListUnmarshaller.unmarshal(readerWithoutNamespace);
			return tripList.getTrip();
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Failed to find route.", e);
			return null;
		}
	}
}
