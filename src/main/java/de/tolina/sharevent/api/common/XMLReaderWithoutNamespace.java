package de.tolina.sharevent.api.common;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;

/**
 * XMLStreamReader to ignore namespaces.
 */
public class XMLReaderWithoutNamespace extends StreamReaderDelegate {

	@SuppressWarnings("javadoc")
	public XMLReaderWithoutNamespace(XMLStreamReader reader) {
		super(reader);
	}

	@Override
	public String getAttributeNamespace(int arg0) {
		return "";
	}

	@Override
	public String getNamespaceURI() {
		return "";
	}
}