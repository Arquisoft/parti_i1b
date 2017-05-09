package es.uniovi.asw.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uniovi.asw.persistence.model.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

	Administrator findByUsername(String username);
}
