package de.tolina.sharevent.api.hacon.route;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.springframework.core.io.ClassPathResource;

import de.tolina.sharevent.api.common.XMLReaderWithoutNamespace;

public class RouteFinderEmu implements IRouteFinder {

	private static final Logger LOG = Logger.getLogger(RouteFinderEmu.class.getName());

	private Unmarshaller locationListUnmarshaller;

	@SuppressWarnings("javadoc")
	public RouteFinderEmu() {
		try {
			JAXBContext context = JAXBContext.newInstance(TripList.class);
			locationListUnmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			LOG.log(Level.SEVERE, "Failed to initialize JAXB.", e);
		}
	}

	@Override
	public List<Trip> findRoute(String stopLocationExtIdFrom, String stopLocationExtIdTo, Date start) {
		return findRoute(stopLocationExtIdFrom, stopLocationExtIdTo, new SimpleDateFormat("yyyy-MM-dd").format(start), new SimpleDateFormat("hh:mm").format(start));
	}

	@Override
	public List<Trip> findRoute(String stopLocationExtIdFrom, String stopLocationExtIdTo, String startDate, String startTime) {
		final String filename = "emu/api/hacon/" + "route_" + stopLocationExtIdFrom + "_" + stopLocationExtIdTo + ".xml";

		try {
			XMLStreamReader streamReader = XMLInputFactory.newFactory().createXMLStreamReader(new ClassPathResource(filename).getInputStream());
			XMLReaderWithoutNamespace readerWithoutNamespace = new XMLReaderWithoutNamespace(streamReader);

			TripList tripList = (TripList) locationListUnmarshaller.unmarshal(readerWithoutNamespace);
			return tripList.getTrip();
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Failed to find location list.", e);
			return null;
		}
	}
}
