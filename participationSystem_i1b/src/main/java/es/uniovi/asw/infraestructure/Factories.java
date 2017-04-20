package es.uniovi.asw.infraestructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.asw.business.ServicesFactory;
import es.uniovi.asw.persistence.repositories.PersistenceFactory;


@Component
public class Factories {
	
	@Autowired
	private  ServicesFactory servicesFactory;
	@Autowired
	private  PersistenceFactory persistenceFactory;
	
	public ServicesFactory getServicesFactory() {
		return servicesFactory;
	}

	public PersistenceFactory getPersistenceFactory() {
		return persistenceFactory;
	}

}
