package es.uniovi.asw.persistence;

import java.util.List;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.CitizenInfo;

public interface Insert {
	
	/**
	 * se le pasan todos los valores leidos de los ciudadanos y los sube a la base de datos.
	 * 
	 * @param citizenValues
	 * @param path
	 * @return la lista de ciudadanos
	 */
	public List<Citizen> insert(List<CitizenInfo> citizenValues);
	
}
