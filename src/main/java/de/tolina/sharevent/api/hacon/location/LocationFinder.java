package de.tolina.sharevent.api.hacon.location;

import java.io.InputStream;
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
import de.tolina.sharevent.api.hacon.location.name.LocationList;
import de.tolina.sharevent.api.hacon.location.name.StopLocation;
import lombok.NonNull;

/**
 * Utility to find locations.
 */
public class LocationFinder {

	private static final Logger LOG = Logger.getLogger(LocationFinder.class.getName());

	private Client client;
	private WebTarget target;
	private Unmarshaller locationListUnmarshaller;

	@SuppressWarnings("javadoc")
	public LocationFinder() {
		client = ClientBuilder.newClient();
		target = client.target("https://demo.hafas.de/bvg-hackathon/restproxy/location.name");

		try {
			JAXBContext context = JAXBContext.newInstance(LocationList.class);
			locationListUnmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			LOG.log(Level.SEVERE, "Failed to initialize JAXB.", e);
		}
	}

	/**
	 * @return {@link StopLocation} mathcing the given name.
	 */
	public List<StopLocation> findLocationByName(@NonNull String name) {
		try {
			String response = target.queryParam("accessId", ApiAccess.ACCESS_ID)
					.queryParam("input", name)
					.request(MediaType.TEXT_XML).get(String.class);

			InputStream inputStream = IOUtils.toInputStream(response, "UTF-8");
			XMLStreamReader streamReader = XMLInputFactory.newFactory().createXMLStreamReader(inputStream);
			XMLReaderWithoutNamespace readerWithoutNamespace = new XMLReaderWithoutNamespace(streamReader);

			LocationList locationList = (LocationList) locationListUnmarshaller.unmarshal(readerWithoutNamespace);
			return locationList.getStopLocation();
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Failed to find location list.", e);
			return null;
		}
	}
}
