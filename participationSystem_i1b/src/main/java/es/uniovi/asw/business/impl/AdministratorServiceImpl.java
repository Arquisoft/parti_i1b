package es.uniovi.asw.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.business.AdministratorService;
import es.uniovi.asw.infraestructure.Factories;
import es.uniovi.asw.persistence.model.Administrator;

@Service
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	private Factories factories;

	@Override
	public Administrator checkLogin(String user, String pass) {
		Administrator a = null;
		a = factories.getPersistenceFactory().getAdministratorRepository().findByUsername(user);
		if (a != null && a.getPassword().equals(pass)) {
			return a;
		}
		return null;
	}

	@Override
	public void save(Administrator admin) {
		factories.getPersistenceFactory().getAdministratorRepository().save(admin);

	}

	@Override
	public void delete(Administrator admin) {
		factories.getPersistenceFactory().getAdministratorRepository().delete(admin);

	}
}
