package es.uniovi.asw.parser;

import java.util.List;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.persistence.Insert;
import es.uniovi.asw.persistence.InsertP;

public class InsertR implements Insert{

	/**
	 * Es simplemente una clase de un nivel superior
	 * que invoca al InsertP que es el encargado de insertar los ciudadanos
	 */
	@Override
	public List<Citizen> insert(List<CitizenInfo> citizenValues) {
		return new InsertP().insert(citizenValues);
	}

}
