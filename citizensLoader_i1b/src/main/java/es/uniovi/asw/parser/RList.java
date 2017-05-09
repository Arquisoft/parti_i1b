package es.uniovi.asw.parser;

import java.util.List;

public abstract class RList implements ReadCitizens {

	// template method

	@Override
	public List<CitizenInfo> readCitizens(String path) {
		List<CitizenInfo> info = read(path);
		/// aqui se generan los reports
		return info;

	}

	protected abstract List<CitizenInfo> read(String path);
}
