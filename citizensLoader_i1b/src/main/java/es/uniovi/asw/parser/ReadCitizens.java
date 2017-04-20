package es.uniovi.asw.parser;

import java.util.List;

public interface ReadCitizens {

	/**
	 * Returns a list with the citizens information
	 * @param path of the file
	 * @return
	 */
	public List<CitizenInfo> readCitizens(String path);
}
