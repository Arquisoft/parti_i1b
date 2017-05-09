package es.uniovi.asw.business;

import es.uniovi.asw.persistence.model.Administrator;

public interface AdministratorService {

	Administrator checkLogin(String user, String pass);

	void save(Administrator admin);

	void delete(Administrator admin);
}
