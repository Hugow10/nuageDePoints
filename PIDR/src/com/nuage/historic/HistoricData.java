/**
 * 
 */
package com.nuage.historic;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement
public class HistoricData implements Iterable<String> {
	@XmlElement
	LinkedList<String> paths = new LinkedList<>();

	/**
	 * @param string
	 */
	public void put(File file) {
		paths.add(file.getAbsolutePath());
	}

	/**
	 * 
	 */
	public File get(int i) {
		return new File(paths.get(i));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<String> iterator() {
		return paths.iterator();
	}
}
