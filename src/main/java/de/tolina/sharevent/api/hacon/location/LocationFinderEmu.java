package de.tolina.sharevent.api.hacon.location;

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
import de.tolina.sharevent.api.hacon.location.name.LocationList;
import de.tolina.sharevent.api.hacon.location.name.StopLocation;
import lombok.NonNull;

public class LocationFinderEmu implements ILocationFinder {

	private static final Logger LOG = Logger.getLogger(LocationFinderEmu.class.getName());

	private Unmarshaller locationListUnmarshaller;

	@SuppressWarnings("javadoc")
	public LocationFinderEmu() {
		try {
			JAXBContext context = JAXBContext.newInstance(LocationList.class);
			locationListUnmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			LOG.log(Level.SEVERE, "Failed to initialize JAXB.", e);
		}
	}

	@Override
	public List<StopLocation> findLocationByName(@NonNull String name) {
		final String filename = "emu/api/hacon/" + "location_" + name + ".xml";

		try {
			XMLStreamReader streamReader = XMLInputFactory.newFactory().createXMLStreamReader(new ClassPathResource(filename).getInputStream());
			XMLReaderWithoutNamespace readerWithoutNamespace = new XMLReaderWithoutNamespace(streamReader);

			LocationList locationList = (LocationList) locationListUnmarshaller.unmarshal(readerWithoutNamespace);
			return locationList.getStopLocation();
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Failed to find location list.", e);
			return null;
		}
	}
}
