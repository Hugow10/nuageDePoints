/**
 * 
 */
package com.nuage.historic;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 */
public class HistoricHandler {
	public static HistoricData store(HistoricData data, File file) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(HistoricData.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(data, file);
			return data;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return
	 * @throws IOException
	 *             si fichier inaccessible
	 * 
	 */
	public static HistoricData load(File file) throws IOException {
		HistoricData output = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(HistoricData.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			output = (HistoricData) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return output;
	}
}
